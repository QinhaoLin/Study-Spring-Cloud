package top.linqinhao.order_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.client.RestTemplate;
import top.linqinhao.order_service.domain.ProductOrder;
import org.springframework.stereotype.Service;
import top.linqinhao.order_service.service.ProductOrderService;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Override
    public ProductOrder save(int userId, int productId) {
        // 获取商品详情
//        Map<String, Object> productMap = restTemplate.getForObject("http://product-service/api/v1/product/find?id=" + productId, Map.class);
//        System.out.println(productMap);

        ServiceInstance instance = loadBalancerClient.choose("product-service");


        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName((String) productMap.get("name"));
        productOrder.setPrice((int) productMap.get("price"));
        return productOrder;
    }
}
