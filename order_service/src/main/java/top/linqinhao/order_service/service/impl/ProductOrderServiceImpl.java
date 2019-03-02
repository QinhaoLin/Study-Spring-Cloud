package top.linqinhao.order_service.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.client.RestTemplate;
import top.linqinhao.order_service.domain.ProductOrder;
import org.springframework.stereotype.Service;
import top.linqinhao.order_service.service.ProductClient;
import top.linqinhao.order_service.service.ProductOrderService;
import top.linqinhao.order_service.utils.JsonUtils;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private ProductClient productClient;

    @Override
    public ProductOrder save(int userId, int productId) {
        if (userId == 1){
            return null;
        }
        // Ribbon 调用方式一
        // 获取商品详情
//        Map<String, Object> productMap = restTemplate.getForObject("http://product-service/api/v1/product/find?id=" + productId, Map.class);
//        System.out.println(productMap);

        // Ribbon 调用方式二
//        ServiceInstance instance = loadBalancerClient.choose("product-service");
//        String url = String.format("http://%s:%s/api/v1/product/find?id="+productId,instance.getHost(),instance.getPort());
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, Object> productMap = restTemplate.getForObject(url + productId, Map.class);

        // Feign调用方式
        String response = productClient.findById(productId);
        JsonNode jsonNode = JsonUtils.str2JsonNode(response);
        System.out.println(jsonNode);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(jsonNode.get("name").toString());
        productOrder.setPrice(Integer.parseInt(jsonNode.get("price").toString()));
        return productOrder;
    }
}
