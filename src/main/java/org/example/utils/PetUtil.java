package org.example.utils;

import org.example.models.Category;
import org.example.models.Pet;
import org.example.models.Tag;

import java.util.List;
import java.util.Random;

public class PetUtil {

    public static Random random = new Random();

    public static Pet generateRandomPet() {
        return new Pet()
                .setId(random.nextLong(0, Long.MAX_VALUE))
                .setCategory(generateCategory())
                .setName("PetName" + random.nextInt())
                .setPhotoUrls(List.of("url"))
                .setTags(List.of(generateTag()))
                .setStatus("available");
    }

    public static Tag generateTag() {
        return new Tag()
                .setId(random.nextInt())
                .setName("Tag" + random.nextInt());
    }

    public static Category generateCategory() {
        return new Category()
                .setId(random.nextInt())
                .setName("Category" + random.nextInt());
    }
}
