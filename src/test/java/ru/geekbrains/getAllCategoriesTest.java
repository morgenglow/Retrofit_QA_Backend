package ru.geekbrains;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbrains.dto.Category;
import ru.geekbrains.java4.lesson6.db.dao.CategoriesMapper;
import ru.geekbrains.java4.lesson6.db.dao.ProductsMapper;
import ru.geekbrains.java4.lesson6.db.model.CategoriesExample;
import ru.geekbrains.service.CategoryService;
import ru.geekbrains.util.DbUtils;
import ru.geekbrains.util.RetrofitUtils;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.assertj.core.api.Assertions.assertThat;

public class getAllCategoriesTest {
   static CategoryService categoryService;
    static CategoriesMapper categoriesMapper;

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        categoriesMapper = DbUtils.getCategoriesMapper();
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
    }

    @Test
    void getFoodCategoryPositiveTest() throws IOException {
        Response<Category> response = categoryService
                .getALLCategory()
                .execute();
        assertThat(response.code()).isEqualTo(404);
        //вторая проверка не падает, потому что запрос к БД отрабатывает корректно. А, вообще, адрес для запроса всех категорий не актуален
        assertThat(DbUtils.getCategoriesMapper().countByExample(new CategoriesExample())).isGreaterThan(2L);
    }

}
