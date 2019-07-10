package com.example.myspringdemo.service;

import com.example.myspringdemo.entity.Member;

import java.util.List;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 11:21 2019/7/10
 */
public interface MemberService {

    int insert(Member member);

    int save(Member member);

    List<Member> selectAll();

}
