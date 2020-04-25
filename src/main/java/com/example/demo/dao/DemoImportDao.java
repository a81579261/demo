package com.example.demo.dao;

import com.example.demo.entity.DemoImport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Agus
 * @since 2020-04-25
 */
@Mapper
@Repository
public interface DemoImportDao extends BaseMapper<DemoImport> {

}
