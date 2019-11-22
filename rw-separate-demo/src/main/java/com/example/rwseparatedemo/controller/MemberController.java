package com.example.rwseparatedemo.controller;

import com.example.rwseparatedemo.entity.Member;
import com.example.rwseparatedemo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 14:33 2019/11/22
 */
@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/save")
    public int saveMember(String name) {
        return memberService.save(Member.builder().name(name).build());
    }

    @PostMapping("/list")
    public List<Member> listMembers() {
        return memberService.selectAll();
    }
}
