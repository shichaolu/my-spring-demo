package com.example.rwseparatedemo.service;

import com.example.rwseparatedemo.entity.Member;

import java.util.List;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 11:21 2019/7/10
 */
public interface MemberService {

    int save(Member member);

    List<Member> selectAll();

}
