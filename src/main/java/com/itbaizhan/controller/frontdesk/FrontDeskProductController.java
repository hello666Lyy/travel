package com.itbaizhan.controller.frontdesk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.pojo.Member;
import com.itbaizhan.pojo.Product;
import com.itbaizhan.service.ProductService;
import jakarta.servlet.http.HttpSession;
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

    @RequestMapping("/routeDetail")
    public ModelAndView findOne(Integer pid, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Product product = productService.findOne(pid);

        Object member = session.getAttribute("member");
        if(member == null) {
            modelAndView.addObject("favorite", false);
        } else {
            Member member1 = (Member) member;
            boolean favorite = productService.findFavorite(pid, member1.getMid());
            modelAndView.addObject("favorite", favorite);
        }

        modelAndView.addObject("product", product);
        modelAndView.setViewName("frontdesk/route_detail");
        return modelAndView;
    }
}
