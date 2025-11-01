package com.itbaizhan.security;

import com.itbaizhan.mapper.AdminMapper;
import com.itbaizhan.pojo.Admin;
import com.itbaizhan.pojo.Permission;
import com.itbaizhan.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private AdminService adminService;

    // 自定义认证逻辑
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 认证
        Admin admin = adminService.findByAdminName(username);
        if(admin == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if(!admin.isStatus()) {
            throw new UsernameNotFoundException("用户不可用");
        }

        //权限
        List<Permission> permissions = adminService.findAllPermission(admin.getUsername());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(Permission permission:permissions) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermissionDesc()));
        }

        return User.withUsername(admin.getUsername())
                .password(admin.getPassword())
                .authorities(grantedAuthorities)
                .build();
    }
}
