package com.example.ProductTeam.Repository;

import com.example.ProductTeam.Entity.Product;
import com.example.ProductTeam.Enum.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    // Change method to use correct field name 'productName'
    List<Product> findByProductNameContainingIgnoreCase(String productName);

    List<Product> findByProductCategory(ProductCategory category);

    List<Product> findByBrandNameIgnoreCase(String brand);
}
