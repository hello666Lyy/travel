package com.itbaizhan.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.mapper.PermissionMapper;
import com.itbaizhan.mapper.RoleMapper;
import com.itbaizhan.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    // 分页查询角色
    public Page<Role> findPage(int page, int size) {
        return roleMapper.selectPage(new Page<Role>(page, size), null);
    }

    // 查询角色
    public Role findById(Integer rid) {
        return roleMapper.selectById(rid);
    }

    // 修改角色
    public void update(Role role) {
        roleMapper.updateById(role);
    }
    // 新增角色
    public void add(Role role) {
        roleMapper.insert(role);
    }
    // 修改角色权限
    public void updatePermission(Integer rid, Integer[] ids) {
        roleMapper.deleteRoleAllPermission(rid);
        for(Integer pid : ids){
            roleMapper.addRolePermission(rid,pid);
        }
    }
    //查询角色详情
    public Role findDesc(Integer rid) {
        Role role = roleMapper.findDesc(rid);
        return role;
    }

    // 删除角色
    public void delete(Integer rid) {
        roleMapper.deleteById(rid);
    }
}
