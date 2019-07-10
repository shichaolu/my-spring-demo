package com.example.myspringdemo;

import com.example.myspringdemo.entity.Member;
import com.example.myspringdemo.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyAppTests {

    @Autowired
    private MemberService memberService;

    @Test
    public void testInsert() {
        Member member = new Member();
        member.setName("张三");
        memberService.insert(member);
    }

    @Test
    public void testSave() {
        Member member = new Member();
        member.setName("王二");
        memberService.save(member);
    }

    @Test
    public void testRead() {
        for (int i = 0; i < 4; i++) {
            memberService.selectAll();
        }
    }

}
