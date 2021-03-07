package ru.geekbrains;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbrains.base.enums.CategoryType;
import ru.geekbrains.dto.Category;
import ru.geekbrains.dto.CategoryErr;
import ru.geekbrains.service.CategoryErrService;
import ru.geekbrains.service.CategoryService;
import ru.geekbrains.util.RetrofitUtils;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.assertj.core.api.Assertions.assertThat;

public class getEmptyCategoryPositiveTest {
   static CategoryService categoryService;

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
    }

    @Test
    void getCategoryNegativeTest() throws IOException {
        Response<Category> response = categoryService
                .getCategory(CategoryType.ELECTRONICS.getId())
                .execute();
        assertThat(response.code()).isEqualTo(200);
        assertThat(response.body().getId()).isEqualTo(CategoryType.ELECTRONICS.getId());
    }

}
