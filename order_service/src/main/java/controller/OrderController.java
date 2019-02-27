package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ProductOrderService;

@RestController
@RequestMapping("api/v1/order/save")
public class OrderController {

    @Autowired
    private ProductOrderService productOrderService;

    public  Object save(@RequestParam("user_id") int userid, @RequestParam("product_id") int productId){

        return null;
    }
}

