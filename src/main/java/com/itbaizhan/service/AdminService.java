package com.itbaizhan.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.bean.RoleWithStatus;
import com.itbaizhan.mapper.AdminMapper;
import com.itbaizhan.mapper.RoleMapper;
import com.itbaizhan.pojo.Admin;
import com.itbaizhan.pojo.Permission;
import com.itbaizhan.pojo.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private BCryptPasswordEncoder encoder;

    // 分页查询管理员
    public Page<Admin> findPage(int page, int size) {
        Page selectPage = adminMapper.selectPage(new Page<Admin>(page, size), null);
        return selectPage;
    }

    //新增管理员
    public void add(Admin admin) {
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminMapper.insert(admin);
    }

    //根据id查询管理员
    public Admin findById(Integer aid) {
        return adminMapper.selectById(aid);
    }

    //修改管理员
    public void update(Admin admin) {
        String oldPassword = adminMapper.selectById(admin.getAid()).getPassword();
        String newPassword = admin.getPassword();

        if(!oldPassword.equals(newPassword)){
            admin.setPassword(encoder.encode(newPassword));
        }

        adminMapper.updateById(admin);
    }

    // 查询管理员详情
    public Admin findDesc(Integer aid) {
        return adminMapper.findDesc(aid);
    }

    //查询用户角色情况
    public List<RoleWithStatus> findRole(Integer aid) {
        List<Role> roles = roleMapper.selectList(null);
        List<Integer> rids = roleMapper.findRoleldByAdmin(aid);

        List<RoleWithStatus> roleWithStatusList = new ArrayList<>();
        for (Role role : roles) {
            RoleWithStatus roleWithStatus = new RoleWithStatus();
            BeanUtils.copyProperties(role, roleWithStatus);
            roleWithStatus.setAdminHas(rids.contains(role.getRid()));
            roleWithStatusList.add(roleWithStatus);
        }
        return roleWithStatusList;
    }

    // 修改用户角色
    public void updateRole(Integer aid, Integer[] ids) {
        adminMapper.deleteAdminAllRoles(aid);
        for (Integer id : ids) {
            adminMapper.addAdminRole(aid,id);
        }
    }

    // 修改用户状态
    public void updateStatus(Integer aid) {
        Admin admin = adminMapper.selectById(aid);
        admin.setStatus(!admin.isStatus());
        adminMapper.updateById(admin);
    }

    // 根据名字查询管理员
    public Admin findByAdminName(String username) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return adminMapper.selectOne(queryWrapper);
    }

    // 根据管理员名字查询权限
    public List<Permission> findAllPermission(String username) {
        return adminMapper.findAllPermission(username);
    }
}
