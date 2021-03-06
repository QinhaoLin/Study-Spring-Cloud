package top.linqinhao.product_service.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.linqinhao.product_service.domain.ProductService;
import top.linqinhao.product_service.service.Product;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/product")
@RefreshScope
public class ProductController {

    @Value("${server.port}")
    private String port;

    @Value("${env}")
    private String env;

    @Autowired
    private ProductService productService;

    /**
     * 获取所有商品列表
     * @return
     */
    @RequestMapping("list")
    public Object listProduct(){
        return productService.listProduct();
    }

    /**
     * 根据id查找商品详情
     * @param id 商品id
     * @return
     */
    @RequestMapping("find")
    public Object findById(@RequestParam("id") int id){
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Product product = productService.findById(id);
        Product result = new Product();
        BeanUtils.copyProperties(product,result);
        result.setName(result.getName() + " data from port=" + port +"  env = "+ env);
        return result;
    }
}
