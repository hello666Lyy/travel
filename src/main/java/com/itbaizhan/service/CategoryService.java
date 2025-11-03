package com.itbaizhan.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.mapper.CategoryMapper;
import com.itbaizhan.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    // 分页查询
    public Page<Category> findPage(int page, int size) {
        return categoryMapper.selectPage(new Page<>(page, size), null);
    }

    // 新增分类
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    // 查询分类
    public Category findById(Integer cid) {
        return categoryMapper.selectById(cid);
    }
    // 修改分类
    public void update(Category category) {
        categoryMapper.updateById(category);
    }

    // 删除分类
    public void delete(Integer cid) {
        categoryMapper.deleteById(cid);
    }

    //查询所有类别
    public List<Category> findAll() {
        return categoryMapper.selectList(null);
    }
}
