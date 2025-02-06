package com.example.ProductTeam.Service;

import com.example.ProductTeam.Entity.Images;
import com.example.ProductTeam.Entity.Product;
import com.example.ProductTeam.Enum.ProductCategory;
import com.example.ProductTeam.Repository.ImagesRepository;
import com.example.ProductTeam.Repository.ProductRepository;
import com.example.ProductTeam.RequestDTO.ProductRequestDTO;
import com.example.ProductTeam.ResponceDTO.ProductResponceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ImagesRepository imagesRepository;

    @Autowired
    private ProductRepository productRepository;

    // Create Product
    public Long create(ProductRequestDTO productRequestDTO) {
        Product savedProduct=toProduct(productRequestDTO);

        return savedProduct.getProductId();
    }


    // Get Product by ID
    public ProductResponceDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return null;


        return toProductResponce(product);
    }


    public String updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return "Product not found";  // Product with given ID doesn't exist
        }

        product.setProductName(productRequestDTO.getProductName());
        product.setDescription(productRequestDTO.getDescription());
        product.setBrandName(productRequestDTO.getBrandName());
        product.setProductCategory(productRequestDTO.getProductCategory());
        product.setIsActive(true);
        product.setCreatedAt(ZonedDateTime.now());

        //updateing product images
        // Delete existing  images first
        imagesRepository.deleteAll(product.getImages());

        saveimages(productRequestDTO,product);



        productRepository.save(product);  // Save updated product
        return "Success";
    }


    // Delete Product
    public String deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return "Product not found";

        // Delete associated images first
        imagesRepository.deleteAll(product.getImages());
        productRepository.delete(product);
        return "Product deleted successfully!";
    }

    // Get All Products
    public ResponseEntity<List<ProductResponceDTO>> getAll() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if no products found
        }

        List<ProductResponceDTO> responseDTOList = new ArrayList<>();
        for (Product product : products) {

            responseDTOList.add(toProductResponce(product));
        }

        return ResponseEntity.ok(responseDTOList);
    }

    // Get Products by Category
    public ResponseEntity<List<ProductResponceDTO>> getByCategory(ProductCategory category) {
        List<Product> products = productRepository.findByProductCategory(category);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if no products found
        }

        List<ProductResponceDTO> responseDTOList = new ArrayList<>();
        for (Product product : products) {

            responseDTOList.add(toProductResponce(product));
        }

        return ResponseEntity.ok(responseDTOList);
    }

    // Get Products by Name
    public ResponseEntity<List<ProductResponceDTO>> getAllByName(String name) {
        List<Product> products = productRepository.findByProductNameContainingIgnoreCase(name);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ProductResponceDTO> responseDTOList = new ArrayList<>();
        for (Product product : products) {
            responseDTOList.add(toProductResponce(product));
        }

        return ResponseEntity.ok(responseDTOList);
    }


    //getallproduct by brand name
    public List<ProductResponceDTO> getbybrandname(String brand) {
        // Get products by brand name
        List<Product> products = productRepository.findByBrandNameIgnoreCase(brand);

        List<ProductResponceDTO> productResponceDTOList = new ArrayList<>();

        // Convert Product entities to ProductResponceDTO
        for (Product product : products) {
            productResponceDTOList.add(toProductResponce(product));
        }

        return productResponceDTOList;
    }




    public ProductResponceDTO toProductResponce(Product product){
        ProductResponceDTO responseDTO = new ProductResponceDTO();
        responseDTO.setProductName(product.getProductName());
        responseDTO.setDescription(product.getDescription());
        responseDTO.setBrandName(product.getBrandName());
        responseDTO.setPrice(10000L);  // Set default price, update if necessary

        List<String> imageUrls = new ArrayList<>();
        for (Images image : product.getImages()) {
            imageUrls.add(image.getImageUrl());
        }

       responseDTO.setImageUrls(imageUrls);

        return responseDTO;
    }

    public Product toProduct(ProductRequestDTO productRequestDTO){
        Product product = new Product();
        product.setProductName(productRequestDTO.getProductName());
        product.setDescription(productRequestDTO.getDescription());
        product.setBrandName(productRequestDTO.getBrandName());
        product.setProductCategory(productRequestDTO.getProductCategory());  // Using ProductCategory Enum
        product.setIsActive(true);
        product.setCreatedAt(ZonedDateTime.now());

        product.setCreatedBy("satyam chauhan");

        Product savedProduct = productRepository.save(product);

        // Saving images
        saveimages(productRequestDTO,savedProduct);

        return savedProduct;
    }

    public void saveimages(ProductRequestDTO productRequestDTO,Product savedProduct){
        List<Images> imagesList = new ArrayList<>();
        for (String imageUrl : productRequestDTO.getImageUrls()) {
            Images image = new Images();
            image.setImageUrl(imageUrl);
            image.setCreatedAt(ZonedDateTime.now());
            image.setCreatedBy("satyam chauhan");
            image.setProduct(savedProduct);
            imagesList.add(imagesRepository.save(image));
        }
    }


    public Product getpbyid(Long id){
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return null;


        return product;
    }
}
