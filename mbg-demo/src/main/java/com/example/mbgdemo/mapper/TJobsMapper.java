package com.example.mbgdemo.mapper;

import com.example.mbgdemo.model.TJobs;
import com.example.mbgdemo.model.TJobsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TJobsMapper {
    long countByExample(TJobsExample example);

    int deleteByExample(TJobsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TJobs record);

    int insertSelective(TJobs record);

    List<TJobs> selectByExample(TJobsExample example);

    TJobs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TJobs record, @Param("example") TJobsExample example);

    int updateByExample(@Param("record") TJobs record, @Param("example") TJobsExample example);

    int updateByPrimaryKeySelective(TJobs record);

    int updateByPrimaryKey(TJobs record);
}