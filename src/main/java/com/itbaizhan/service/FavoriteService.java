package com.itbaizhan.service;

import com.itbaizhan.mapper.ProductMapper;
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

}
