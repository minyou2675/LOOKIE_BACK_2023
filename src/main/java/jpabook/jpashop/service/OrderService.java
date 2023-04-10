package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.respository.ItemRepository;
import jpabook.jpashop.respository.MemberRepository;
import jpabook.jpashop.respository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long Order(Long memberId, Long itemId, int count){

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
         OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(),count);
        /**
         * 통일되지 않은 생성자 사용 지양 OrderItem orderItem = new OrderItem();
         * 막는 법은 constructor을 만들때 protected OrderItem(){} -> JPA에선 사용하지 말라는 의미
         * 또는 NoArgsConstructor(acess=AcessLevel.PROTECTED)
         *
         */
        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);


        return order.getId();
    }

    //주문 취소
    @Transactional
    public void cancelOrder(Long orderId){
        //주문 조회
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
    //검색
//    public List<Order> findOrders(OrderSearch orderSearch){
//        return OrderRepository.findAll(orderSearch);
//    }
}
