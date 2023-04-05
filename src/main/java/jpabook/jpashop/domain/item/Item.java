package jpabook.jpashop.domain.item;

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
        }
