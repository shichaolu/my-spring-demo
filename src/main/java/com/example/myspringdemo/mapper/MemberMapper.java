package com.example.myspringdemo.mapper;


import com.example.myspringdemo.entity.Member;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 11:28 2019/7/10
 */
@Repository
public interface MemberMapper extends Mapper<Member> {

}
