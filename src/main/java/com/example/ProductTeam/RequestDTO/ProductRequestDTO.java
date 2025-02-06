package com.example.ProductTeam.RequestDTO;

import com.example.ProductTeam.Enum.ProductCategory;

import java.time.ZonedDateTime;
import java.util.List;

public class ProductRequestDTO {

    private String productName;
    private String description;
    private ProductCategory productCategory;
    private String brandName;
    private List<String> imageUrls;

    // Default constructor
    public ProductRequestDTO() {}


    // Getter and Setter methods

    public ProductCategory getProductCategory(){
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory){
        this.productCategory=productCategory;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }


}
