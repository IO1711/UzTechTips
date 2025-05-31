package com.techtips.uzbekTechTips.services;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techtips.uzbekTechTips.model.ImageWassabi;
import com.techtips.uzbekTechTips.repositories.ImageWassabiRepository;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;


@Service
public class ImageUploadService {

    private final S3Client s3Client;

    @Autowired
    private ImageWassabiRepository imageWassabiRepository;

    @Value("${wasabi.bucketName}")
    private String bucketName;

    public ImageUploadService(S3Client s3Client){
        this.s3Client = s3Client;
    }

    public String uploadImage(MultipartFile file) throws IOException{
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(fileName)
            .acl("public-read")
            .build();

        try(InputStream inputStream = file.getInputStream()){
            s3Client.putObject(putObjectRequest, software.amazon.awssdk.core.sync.RequestBody.fromInputStream(inputStream, file.getSize()));
        }

        
        //"https://s3.ap-southeast-1.wasabisys.com/" + bucketName + "/" + 
        return fileName;
    }

    public void saveImageUrl(String imageName) {
        // Use a repository to save the image URL in your database
        ImageWassabi image = new ImageWassabi();
        image.setContent(imageName);
        imageWassabiRepository.save(image);
    }

    public void deleteImage(String imageName){
        try{
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(imageName)
                .build();

                s3Client.deleteObject(deleteObjectRequest);
                System.out.println("Deleted: " + imageName);
        }
        catch(S3Exception e){
            System.err.println("Failed to delete file: " + imageName);
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
    
}
