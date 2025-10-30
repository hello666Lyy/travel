package com.itbaizhan.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.bean.PermissionWithStatus;
import com.itbaizhan.mapper.PermissionMapper;
import com.itbaizhan.pojo.Permission;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    // 查询权限
    public List<PermissionWithStatus> findById(Integer rid) {
        List<Permission> permissions = permissionMapper.selectList(null);

        List<PermissionWithStatus> permissionWithStatusList = new ArrayList<>();
        List<Integer> pids = permissionMapper.findPermissionIdByRole(rid);
        for (Permission permission : permissions) {
            PermissionWithStatus permissionWithStatus = new PermissionWithStatus();
            BeanUtils.copyProperties(permission, permissionWithStatus);
            permissionWithStatus.setRoleHas(pids.contains(permission.getPid()));
            permissionWithStatusList.add(permissionWithStatus);
        }
        return permissionWithStatusList;
    }

    // 分页查询权限
    public Page<Permission> findPage(int page, int size) {
        return permissionMapper.selectPage(new Page<>(page,size),null);
    }

    //新增权限
    public void add(Permission permission) {
        permissionMapper.insert(permission);
    }

    public void delete(Integer pid) {
        permissionMapper.deleteById(pid);
    }

    // 查询权限
    public Permission selectById(Integer pid) {
        return permissionMapper.selectById(pid);
    }

    // 修改权限
    public void update(Permission permission) {
        permissionMapper.updateById(permission);
    }
}
