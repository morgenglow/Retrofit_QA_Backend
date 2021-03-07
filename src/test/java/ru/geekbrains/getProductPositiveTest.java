package ru.geekbrains;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbrains.commonRequests.CreateProduct;
import ru.geekbrains.dto.Product;
import ru.geekbrains.java4.lesson6.db.dao.ProductsMapper;
import ru.geekbrains.service.ProductService;
import ru.geekbrains.util.DbUtils;
import ru.geekbrains.util.RetrofitUtils;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.assertj.core.api.Assertions.assertThat;

public class getProductPositiveTest {
    static ProductService productService;
    static Integer productId;
    static ProductsMapper productsMapper;
    Product product;

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        productsMapper = DbUtils.getProductsMapper();
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @BeforeEach
    void beforeEach() throws IOException {
        product = CreateProduct.createProduct();
        productId = product.getId();
    }

    @Test
    void getProductPositiveTest() throws IOException {
        Response<Product> response = productService
                .getProduct(product.getId())
                .execute();
        assertThat(response.code()).isEqualTo(200);
        assertThat(productsMapper.selectByPrimaryKey(Long.valueOf(productId)).getTitle()).isEqualTo(product.getTitle());
    }

    @AfterEach
    void delete(){
        DbUtils.getProductsMapper().deleteByPrimaryKey(Long.valueOf(productId));
    }

}
