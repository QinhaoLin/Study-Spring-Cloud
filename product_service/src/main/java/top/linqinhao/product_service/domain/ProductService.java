package top.linqinhao.product_service.domain;

import top.linqinhao.product_service.service.Product;

import java.util.List;

public interface ProductService {
    List<Product> listProduct();

    Product findById(int id);
}
