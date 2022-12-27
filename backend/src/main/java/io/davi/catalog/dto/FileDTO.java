package io.davi.catalog.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class FileDTO implements Serializable {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
