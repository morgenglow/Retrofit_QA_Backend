package ru.geekbrains.commonRequests;
import com.github.javafaker.Faker;
import ru.geekbrains.base.enums.CategoryType;
import ru.geekbrains.dto.Product;
import ru.geekbrains.service.ProductService;
import ru.geekbrains.util.RetrofitUtils;
import java.io.IOException;


public class CreateProduct {
    static ProductService productService;
    static Integer productId;
    public static Product createProduct() throws IOException {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
        Faker faker = new Faker();
        Product product = new Product()
                .withCategoryTitle(CategoryType.FOOD.getTitle())
                .withPrice((int) (Math.random() * 1000 + 1))
                .withTitle(faker.food().ingredient());

        retrofit2.Response<Product> response =
                productService.createProduct(product)
                        .execute();
        return response.body();
    }
}
