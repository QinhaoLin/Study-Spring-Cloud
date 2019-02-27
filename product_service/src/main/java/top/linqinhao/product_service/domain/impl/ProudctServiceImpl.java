package top.linqinhao.product_service.domain.impl;

import org.springframework.stereotype.Service;
import top.linqinhao.product_service.domain.ProductService;
import top.linqinhao.product_service.service.Product;

import java.util.*;

@Service
public class ProudctServiceImpl implements ProductService {

    private static final Map<Integer, Product> daoMap = new HashMap<>();

    static {
        Product p1 = new Product(1,"iphonex",9999,110);
        Product p2 = new Product(2,"冰箱",2850,100);
        Product p3 = new Product(3,"洗衣机",2999,123);
        Product p4 = new Product(4,"电视",3999,32);
        Product p5 = new Product(5,"电话",89,34);
        Product p6 = new Product(6,"汽车",199999,66);
        Product p7 = new Product(7,"椅子",199,62);
        Product p8 = new Product(8,"耳机",99,88);
        Product p9 = new Product(9,"电脑",4999,97);
        Product p10 = new Product(10,"自行车",9999,47);
        daoMap.put(p1.getId(), p1);
        daoMap.put(p2.getId(), p2);
        daoMap.put(p3.getId(), p3);
        daoMap.put(p4.getId(), p4);
        daoMap.put(p5.getId(), p5);
        daoMap.put(p6.getId(), p6);
        daoMap.put(p7.getId(), p7);
        daoMap.put(p8.getId(), p8);
        daoMap.put(p9.getId(), p9);
        daoMap.put(p10.getId(), p10);
    }

    @Override
    public List<Product> listProduct() {
        Collection<Product> collection = daoMap.values();
        List<Product> list = new ArrayList<>(collection);
        return list;
    }

    @Override
    public Product findById(int id) {
        return daoMap.get(id);
    }

}
