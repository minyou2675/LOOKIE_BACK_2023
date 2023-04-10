package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //데이터 변경은 트랜잭션 안에서 기본적으로 readOnly=false
//@RequiredArgsConstructor // final이 있는 인자들에 생성자 생성
public class MemberService {

    @Autowired //인젝션
    MemberRepository memberRepository;



    //회원가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member); // !! 동시에 가입 시도하면 validation 통과할 수도 있다 !!
        memberRepository.save(member); //회원 세이브
        return member.getId(); //ID 반환
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원전체 조회

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
