package com.itbaizhan.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.mapper.ProductMapper;
import com.itbaizhan.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public Page<Product> findPage(int page, int size) {
        return productMapper.findProductPage(new Page(page, size));
    }
    // 新增旅游产品
    public void add(Product product) {
        productMapper.insert(product);
    }
    // 查询产品
    public Product findOne(Integer pid) {
        return  productMapper.findOne(pid);
    }
    // 修改旅游产品
    public void update(Product product) {
        productMapper.updateById(product);
    }

}
