package com.scm.SCM20.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.SCM20.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private Cloudinary cloudinary;
    @Override
    public String uploadImage(MultipartFile contactImage) {

        String fileName = UUID.randomUUID().toString();

        try {
            byte[] data = new byte[contactImage.getInputStream().available()];
            contactImage.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                    "public_id", fileName));
            return this.getUrlFromPublicId(fileName);

        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getUrlFromPublicId(String publicId) {

        return cloudinary.url()
                .transformation(
                        new Transformation<>()
                                .width(500)
                                .height(500)
                                .crop("fill")
                )
                .generate(publicId);

    }

}
