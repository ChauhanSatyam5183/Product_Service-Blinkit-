package com.example.ProductTeam.Controller;

import com.example.ProductTeam.RequestDTO.ImagesDTO;
import com.example.ProductTeam.Service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    @Autowired
    private ImagesService imagesService;


    @PutMapping("/addimages/{productid}")
    public ResponseEntity<Long> addimagesofproduct(Long productid,@RequestBody List<ImagesDTO> imagesDTOlist){

        Long result = imagesService.add(productid, imagesDTOlist);

        System.out.println(productid);

        if ("Images updated successfully".equals(result)) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);  // For "No images found" or other errors
        }
    }
}
