package com.itbaizhan.controller.frontdesk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.pojo.Product;
import com.itbaizhan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/frontdesk/product")
public class FrontDeskProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/routeList")
    public ModelAndView findProduct(Integer cid, String productName,
                                    @RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Page<Product> productPage = productService.findProduct(cid, productName, page, size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productPage", productPage);
        modelAndView.addObject("cid",cid);
        modelAndView.addObject("productName",productName);
        modelAndView.setViewName("frontdesk/route_list");
        return modelAndView;
    }
}
