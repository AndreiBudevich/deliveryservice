package by.deliveryservice.util;

import by.deliveryservice.model.Category;
import by.deliveryservice.repository.BaseRepository;
import by.deliveryservice.repository.infile.InFileCategoryRepository;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Array;
import java.util.Arrays;

@UtilityClass
public class CategoryUtil {

    private final BaseRepository<Category> categoryRepository = new InFileCategoryRepository();

    public static Category[] getCategories(String[] ids) {
        return Arrays.stream(ids)
                .map(id -> categoryRepository.get(Integer.parseInt(id)).orElse(null))
                .toArray(size -> (Category[]) Array.newInstance(Category.class, size));
    }
}
