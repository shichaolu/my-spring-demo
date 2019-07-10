package com.example.myspringdemo.service.impl;

import com.example.myspringdemo.annotation.Master;
import com.example.myspringdemo.entity.Member;
import com.example.myspringdemo.mapper.MemberMapper;
import com.example.myspringdemo.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 11:21 2019/7/10
 */
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insert(Member member) {
        return memberMapper.insert(member);
    }

    @Master
    @Override
    public int save(Member member) {
        return memberMapper.insert(member);
    }

    @Override
    public List<Member> selectAll() {
        return memberMapper.selectAll();
    }

}
