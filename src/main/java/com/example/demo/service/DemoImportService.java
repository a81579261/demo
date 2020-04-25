package com.example.demo.service;

import com.example.demo.dto.DemoImportDTO;
import com.example.demo.entity.DemoImport;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Agus
 * @since 2020-04-25
 */
public interface DemoImportService extends IService<DemoImport> {

    void importDemo(List<DemoImportDTO> list, HttpServletResponse response);
}
