package com.pcloud.tinyproductshop.product.domain;

import com.pcloud.tinyproductshop.product.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Getter @Setter
public class Product {
    @Id @GeneratedValue
    @Column(name="product_id")
    private Long Id;
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직 ==//
    /*도메인 주도 설계 방식 비즈니스 로직을 Entity에 삽입한다. 단 Entity가 해결할 수 있는 내용으로 제한된다.*/

    /**
     * Stock 증가
     * @param quantity
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     * @param quantity
     */
    public void removeStock(int quantity) {
        int resultStock = this.stockQuantity - quantity;
        if(resultStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = resultStock;
    }
}
