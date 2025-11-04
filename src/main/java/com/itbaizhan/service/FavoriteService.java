package com.itbaizhan.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.mapper.ProductMapper;
import com.itbaizhan.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {
    @Autowired
    private ProductMapper productMapper;

    // 收藏线路
    public void addFavorite(Integer pid, Integer mid) {
        productMapper.addFavorite(pid, mid);
    }

    // 取消收藏
    public void delFavorite(Integer pid, Integer mid) {
        productMapper.delFavorite(pid, mid);
    }

    // 我的收藏
    public Page<Product> findMemberFavorite(int page, int size, Integer mid) {
        return productMapper.findMemberFavorite(new Page(page,size), mid);
    }
}
