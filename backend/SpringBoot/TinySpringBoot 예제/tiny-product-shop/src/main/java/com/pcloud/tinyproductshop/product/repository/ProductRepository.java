package com.pcloud.tinyproductshop.product.repository;

import com.pcloud.tinyproductshop.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepository implements IProductRepository {

    private final EntityManager em;

    @Override
    public void save(Product product) {
        //처음 생성되는 객체는 id가 없으므로 영속성 객체로 등록
        if(product.getId() == null) {
            em.persist(product);
        } else {
            //Id가 있다면 영속성 객체를 merge 시킨다.
            em.merge(product);
        }
    }

    @Override
    public Optional<Product> findOne(Long id) {
        return Optional.ofNullable(em.find(Product.class, id));
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("select p from Product p").getResultList();
    }
}
