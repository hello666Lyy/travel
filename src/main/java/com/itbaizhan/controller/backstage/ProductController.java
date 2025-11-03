package com.itbaizhan.controller.backstage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.bean.WangEditorResult;
import com.itbaizhan.mapper.ProductMapper;
import com.itbaizhan.pojo.Category;
import com.itbaizhan.pojo.Product;
import com.itbaizhan.service.CategoryService;
import com.itbaizhan.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/backstage/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    
    @RequestMapping("/all")
    public ModelAndView all(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size) {
        Page<Product> productPage = productService.findPage(page, size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/product_all");
        modelAndView.addObject("productPage", productPage);
        return modelAndView;
    }

    @RequestMapping("/addPage")
    public ModelAndView addPage() {
        List<Category> categoryList = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/product_add");
        modelAndView.addObject("categoryList", categoryList);
        return modelAndView;
    }

    @RequestMapping("/add")
    public String add(Product product) {
        productService.add(product);
        return "redirect:/backstage/product/all";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public WangEditorResult upload(HttpServletRequest request, MultipartFile file) throws Exception {
//        String realPath = ResourceUtils.getURL("classpath:").getPath()+"/static/upload";

//        String realPath = System.getProperty("user.dir") + "/upload";

        String realPath = System.getProperty("user.dir") + "/src/main/resources/static/upload/";
        File dir = new File(realPath);
        if (!dir.exists()) dir.mkdirs();

        String filename = file.getOriginalFilename();
        filename = UUID.randomUUID() + filename;

        File newFile = new File(dir, filename);

        file.transferTo(newFile);

        WangEditorResult wangEditorResult = new WangEditorResult();
        String[] data = {"/upload/" + filename};
        wangEditorResult.setErrno(0);
        wangEditorResult.setData(data);

        return wangEditorResult;
    }

    @RequestMapping("edit")
    public ModelAndView edit(Integer pid) {
        Product product = productService.findOne(pid);
        List<Category> categoryList = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/product_edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("categoryList", categoryList);
        return modelAndView;
    }

    @RequestMapping("/update")
    public String update(Product product) {
        productService.update(product);
        return "redirect:/backstage/product/all";
    }
}
