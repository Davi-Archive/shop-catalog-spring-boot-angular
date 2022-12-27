package io.davi.catalog;

import io.davi.catalog.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogApplication  {

    public static void main(String[] args) {
        SpringApplication.run(CatalogApplication.class, args);
    }


    @Autowired
    private S3Service s3Service;


}
