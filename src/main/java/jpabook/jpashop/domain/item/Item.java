package jpabook.jpashop.domain.item;

import jpabook.jpashop.exception.item.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

        @Id
        @GeneratedValue
        @Column(name="item_id")
        private Long id;

        private String name;
        private int price;
        private int stockQuantity;

        @ManyToMany
        @JoinTable(name="category_id",
                joinColumns = @JoinColumn(name="category_id"),
                        inverseJoinColumns = @JoinColumn(name="item_id"))
        private List<Item> categories = new ArrayList<>();

        //==비즈니스 로직==//

        /**
         *
         * stock 증가
         */
        public void addStock(int quantity){
                this.stockQuantity += quantity;
        }
        public void removeStock(int quantity){
                int restStock = this.stockQuantity - quantity;
                if(restStock < 0 ){
                        throw new NotEnoughStockException("need more stock");
                }
                this.stockQuantity = restStock;
        }
        }
