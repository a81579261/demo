package com.example.demo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.dto.DemoImportDTO;
import com.example.demo.dto.DemoImportFailDTO;
import com.example.demo.entity.DemoImport;
import com.example.demo.dao.DemoImportDao;
import com.example.demo.service.DemoImportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Agus
 * @since 2020-04-25
 */
@Service
@Slf4j
public class DemoImportServiceImpl extends ServiceImpl<DemoImportDao, DemoImport> implements DemoImportService {

    @Autowired
    private DemoImportDao demoImportDao;

    @Override
    public void importDemo(List<DemoImportDTO> list, HttpServletResponse response) {
        log.info("开始导入............");
        Long beginTime = System.currentTimeMillis();
        //用于记录失败列表
        List<DemoImportFailDTO> failList = new ArrayList<>();
        for (DemoImportDTO e : list) {
            if (StringUtils.isBlank(e.getName()) || StringUtils.isBlank(e.getCellPhone())) {
                addToFailList(e, failList, "名字和手机号码不能为空");
                continue;
            }
            //手机号码校验
            String regex1="^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$";
            if (Pattern.compile(regex1).matcher(e.getCellPhone()).matches()){
                addToFailList(e, failList, "手机号码不符合");
                continue;
            }
            QueryWrapper<DemoImport> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("cell_phone",e.getCellPhone());
            Integer count = demoImportDao.selectCount(queryWrapper);
            if (count>0){
                addToFailList(e, failList, "手机号码已存在");
                continue;
            }
            DemoImport demoImport = new DemoImport();
            BeanUtils.copyProperties(e,demoImport);
            demoImportDao.insert(demoImport);
        }
        if (CollectionUtils.isNotEmpty(failList)){
            log.info("导入存在错误列表,failList:[{}]", JSONArray.fromObject(failList));
            try {
                String fileName = "导入失败数据";
                response.setContentType("application/vnd.ms-excel");
                response.setCharacterEncoding("utf-8");
                fileName = URLEncoder.encode(fileName, "UTF-8");
                response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
                EasyExcel.write(response.getOutputStream(), DemoImportFailDTO.class).sheet().doWrite(failList);
            } catch (IOException e) {
                log.error("导出异常，e: {}", e);
            }
        }
        log.info("结束导入，总耗时：{}", System.currentTimeMillis() - beginTime);
    }

    private void addToFailList(DemoImportDTO e,List<DemoImportFailDTO> failList,String message){
        DemoImportFailDTO dto = new DemoImportFailDTO();
        BeanUtils.copyProperties(e,dto);
        dto.setFailReason(message);
        failList.add(dto);
    }
}
