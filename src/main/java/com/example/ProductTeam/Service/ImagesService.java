package com.example.ProductTeam.Service;

import com.example.ProductTeam.Entity.Images;
import com.example.ProductTeam.Entity.Product;
import com.example.ProductTeam.Repository.ImagesRepository;
import com.example.ProductTeam.RequestDTO.ImagesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ImagesService {

    @Autowired
    private ImagesRepository imagesRepository;

    @Autowired
    private ProductService productService;



    public Long add(Long productid,List<ImagesDTO> imagesDTOlist) {
        for(ImagesDTO imagesDTO:imagesDTOlist){
            Images image = new Images();
            image.setImageUrl(imagesDTO.getImageUrl());

            image.setCreatedAt(ZonedDateTime.now());
            image.setCreatedBy("satyam chauhan");
           image.setProduct(productService.getpbyid(productid));

            imagesRepository.save(image);
        }

       return 1090L;
    }
}
