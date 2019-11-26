package com.example.mbgdemo.mapper.simple;

import com.example.mbgdemo.model.simple.TJobs;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface TJobsMapper {
    @Delete({
        "delete from t_jobs",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_jobs (jobName, updateTime, ",
        "description)",
        "values (#{jobname,jdbcType=VARCHAR}, #{updatetime,jdbcType=TIMESTAMP}, ",
        "#{description,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TJobs record);

    @Select({
        "select",
        "id, jobName, updateTime, description",
        "from t_jobs",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="jobName", property="jobname", jdbcType=JdbcType.VARCHAR),
        @Result(column="updateTime", property="updatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)
    })
    TJobs selectByPrimaryKey(Long id);

    @Select({
        "select",
        "id, jobName, updateTime, description",
        "from t_jobs"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="jobName", property="jobname", jdbcType=JdbcType.VARCHAR),
        @Result(column="updateTime", property="updatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)
    })
    List<TJobs> selectAll();

    @Update({
        "update t_jobs",
        "set jobName = #{jobname,jdbcType=VARCHAR},",
          "updateTime = #{updatetime,jdbcType=TIMESTAMP},",
          "description = #{description,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TJobs record);
}