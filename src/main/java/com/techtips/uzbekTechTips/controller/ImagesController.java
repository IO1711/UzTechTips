package com.techtips.uzbekTechTips.controller;

import java.io.InputStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techtips.uzbekTechTips.services.ImageUploadService;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@CrossOrigin(origins = {
    "https://techtipsuzbcreator.netlify.app",
    "https://techtipsuzb.netlify.app",
    "http://localhost:3000"
})
@RestController
@RequestMapping("/api/images")
public class ImagesController {

    @Value("${wasabi.bucketName}")
    private String bucketName;

    private final S3Client s3Client;
    

    private final ImageUploadService imageUploadService;

    @Autowired
    public ImagesController(ImageUploadService imageUploadService, S3Client s3Client){
        this.imageUploadService = imageUploadService;
        this.s3Client = s3Client;
    }


    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(fileName)
            .build();

        ResponseInputStream<GetObjectResponse> getObjectResponse = s3Client.getObject(getObjectRequest);

        InputStream inputStream = getObjectResponse;

        InputStreamResource  resource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName)
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }



    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file){
        try{
            String savedFileName = imageUploadService.uploadImage(file);
            return savedFileName;
        }
        catch(Exception e){
            e.printStackTrace();
            return "Error in uploading image";
        }
    }


    @PostMapping("/delete/{imageName}")
    public String deleteImage(@PathVariable String imageName){
        imageUploadService.deleteImage(imageName);
        return "Deleted: " + imageName;
    }


    
}
