package com.example.ProductTeam.Controller;

import com.example.ProductTeam.Enum.ProductCategory;
import com.example.ProductTeam.RequestDTO.ProductRequestDTO;
import com.example.ProductTeam.ResponceDTO.ProductResponceDTO;
import com.example.ProductTeam.Service.ImagesService;
import com.example.ProductTeam.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImagesService imagesService;

    // Create Product
    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody ProductRequestDTO productRequestDTO) {
        Long ID = productService.create(productRequestDTO);
        if (ID != null) {
            return new ResponseEntity<>(ID, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get Product by ID
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<ProductResponceDTO> getById(@PathVariable Long id) {
        ProductResponceDTO responseDTO = productService.getProductById(id);
        if (responseDTO != null) {
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Product
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        String result = productService.updateProduct(id, productRequestDTO);
        System.out.println(id);
        if (result.equals("Success")) {
            return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update product", HttpStatus.BAD_REQUEST);
        }
    }



    // Delete Product
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String result = productService.deleteProduct(id);
        if (result.equals("Success")) {
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete product", HttpStatus.BAD_REQUEST);
        }
    }

    // Get All Products
    @GetMapping("/all")
    public ResponseEntity<List<ProductResponceDTO>> getAll() {
        return productService.getAll();
    }

    // Get Products by Category
    @GetMapping("/bycategory/{category}")
    public ResponseEntity<List<ProductResponceDTO>> getByCategory(@PathVariable ProductCategory category) {
        return productService.getByCategory(category);
    }

    // Get Products by Name
    @GetMapping("/byname/{name}")
    public ResponseEntity<List<ProductResponceDTO>> getByName(@PathVariable String name) {
        return productService.getAllByName(name);
    }



    //get by brand name
    @GetMapping("/bybrandname/{brand}")
    public ResponseEntity<List<ProductResponceDTO>> getbybrand(@PathVariable String brand){
        List<ProductResponceDTO> Productlist=productService.getbybrandname(brand);

        if(Productlist.size()==0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(Productlist, HttpStatus.OK);
    }



}
