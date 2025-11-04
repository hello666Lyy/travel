package com.itbaizhan.controller.frontdesk;

import com.itbaizhan.pojo.Category;
import com.itbaizhan.service.CategoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/frontdesk/category")
public class FrontdeskCategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/all")
    @ResponseBody
    public List<Category> all() {
        return categoryService.findAll();
    }

}
