package top.linqinhao.order_service.fallback;

import org.springframework.stereotype.Component;
import top.linqinhao.order_service.service.ProductClient;

/**
 * 针对商品服务做降级处理
 */
@Component
public class ProductClientFallback implements ProductClient {

    @Override
    public String findById(int id) {
        System.out.println("feign 调用 product-service findbyid 异常");
        return null;
    }

}
