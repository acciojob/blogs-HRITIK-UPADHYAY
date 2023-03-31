package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        Blog blog = blogRepository2.findById(blogId).get();
        blog.getImageList().add(image);

        image.setBlog(blog);

        blogRepository2.save(blog);

        return image;
    }

    public void deleteImage(Integer id){
        Image image = imageRepository2.findById(id).get();
        Blog blog = image.getBlog();
        blog.getImageList().remove(image);

        blogRepository2.save(blog);

        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        int count = 0;
        Image image = imageRepository2.findById(id).get();

        int imageDimension = (image.getDimensions().charAt(0) - '0') * (image.getDimensions().charAt(2) - '0');
        int totalDimension = (screenDimensions.charAt(0) - '0') * (screenDimensions.charAt(2) - '0');

        count = totalDimension/imageDimension;

        return count;
    }
}
