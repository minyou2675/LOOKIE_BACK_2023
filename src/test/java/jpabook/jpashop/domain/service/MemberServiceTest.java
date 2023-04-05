package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.respository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    @MockBean
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Test
    @Rollback(value = false)
    public void signup() throws  Exception{
        Member member = new Member();
        member.setName("kim");
        System.out.println(member.getName());
        Long savedId = memberService.join(member);

//        assertEquals(member, memberRepository.findOne(savedId)); //둘이 같느냐

        }
    @Test
    public void duplicate() throws Exception{
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);

        fail("예외가 발생해야 한다.");


    }

}