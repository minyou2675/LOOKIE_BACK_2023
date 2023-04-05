package jpabook.jpashop.respository;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //컴포넌트 스캔의 대상
public class MemberRepository {

    @PersistenceContext //스프링에 의해 jpa의 엔터티매니저를 주입받을 수 있다.
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }
    public Member findOne(Long id){
        return em.find(Member.class, id); //Member member = em.find(member.class,id);
    } //아이디로 멤버 찾기

    public List<Member> findAll() {
       return em.createQuery("select m from Member m", Member.class) //Entity 객체를 대상으로 query 문 수행
                .getResultList();
    } // 전부 찾기
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name) //파라미터 바인딩
                .getResultList();
    } // 이름으로 찾기
}