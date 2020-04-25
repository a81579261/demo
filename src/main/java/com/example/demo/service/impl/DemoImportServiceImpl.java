package com.example.demo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.DemoImportDao;
import com.example.demo.dto.DemoImportDTO;
import com.example.demo.dto.DemoImportFailDTO;
import com.example.demo.entity.DemoImport;
import com.example.demo.service.DemoImportService;
import com.example.demo.utils.RegxUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
    @Transactional(rollbackFor = Exception.class)
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
            if (!RegxUtils.isMobile(e.getCellPhone())){
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
