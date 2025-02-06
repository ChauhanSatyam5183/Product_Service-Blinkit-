package com.example.ProductTeam.Repository;

import com.example.ProductTeam.Entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<Images,Long> {
    List<Images> findByProduct_ProductId(Long productId);
}
