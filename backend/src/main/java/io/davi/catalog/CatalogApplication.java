package io.davi.catalog;

import com.amazonaws.services.s3.AmazonS3;
import io.davi.catalog.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CatalogApplication.class, args);
    }


    @Autowired
    private S3Service s3Service;

    @Override
    public void run(String... args) throws Exception {
        s3Service.uploadFile("c:\\img.jpg");
    }
}
