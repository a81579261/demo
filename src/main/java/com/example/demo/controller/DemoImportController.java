package com.example.demo.controller;


import com.alibaba.excel.EasyExcel;
import com.example.demo.dto.DemoImportDTO;
import com.example.demo.handler.MySheetWriteHandler;
import com.example.demo.service.DemoImportService;
import com.example.demo.utils.ExcelUtil;
import com.example.demo.utils.Response.GlobalResponseCode;
import com.example.demo.utils.Response.MyException;
import com.example.demo.utils.Response.ResBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Agus
 * @since 2020-04-25
 */
@RestController
@Slf4j
@RequestMapping("/demo-import")
@Api(value = "导入Demo")
public class DemoImportController {

    @Autowired
    private DemoImportService demoImportService;

    @GetMapping(value = "/getDemo")
    @ApiOperation(value = "下载模板", notes = "下载模板")
    public void getDemo(HttpServletResponse response) {
        try {
            String fileName = "导入模板";
            String sheetName = "模板";
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
            EasyExcel.write(response.getOutputStream(), DemoImportDTO.class).registerWriteHandler(new MySheetWriteHandler()).sheet(sheetName).doWrite(new ArrayList());
        } catch (IOException e) {
            log.error("导出异常，e: {}", e);
        }
    }

    @ApiOperation(value = "导入", notes = "导入")
    @RequestMapping(value = "import", method = RequestMethod.POST)
    @ApiParam(name = "导入文件", value = "导入文件", type = "Object", required = true)
    public ResBody importDemo(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
        if (file.isEmpty()) {
            throw new MyException(GlobalResponseCode.SYS_PARAM_ERROR, "上传文件不能为空");
        }
        List<DemoImportDTO> list = ExcelUtil.read(file.getInputStream(), DemoImportDTO.class);
        if (CollectionUtils.isEmpty(list)){
            throw new MyException(GlobalResponseCode.SYS_PARAM_ERROR, "上传文件数据为空");
        }
        log.info("进入导入importDemo，list:[{}]", JSONArray.fromObject(list));
        demoImportService.importDemo(list,response);
        return ResBody.buildSuccessResBody();
    }
}

