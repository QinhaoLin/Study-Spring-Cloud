package service.impl;

import domain.ProductOrder;
import org.springframework.stereotype.Service;
import service.ProductOrderService;

import java.util.Date;
import java.util.UUID;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    @Override
    public ProductOrder save(int userId, int productId) {
        // 获取商品详情 TODO

        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());

        return productOrder;
    }
}
