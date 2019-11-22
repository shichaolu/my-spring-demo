package com.example.rwseparatedemo.service.impl;

import com.example.rwseparatedemo.annotation.Master;
import com.example.rwseparatedemo.entity.Member;
import com.example.rwseparatedemo.mapper.MemberMapper;
import com.example.rwseparatedemo.service.MemberService;
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

    @Master
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int save(Member member) {
        return memberMapper.insert(member);
    }

    @Override
    public List<Member> selectAll() {
        return memberMapper.selectAll();
    }

}
