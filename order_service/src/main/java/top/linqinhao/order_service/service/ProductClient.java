package top.linqinhao.order_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.linqinhao.order_service.fallback.ProductClientFallback;

/**
 * 标识为Feign客户端
 * 商品服务客户端
 */
@FeignClient(name = "product-service", fallback = ProductClientFallback.class)
//@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/api/v1/product/find")
    String findById(@RequestParam(value = "id") int id);

}
