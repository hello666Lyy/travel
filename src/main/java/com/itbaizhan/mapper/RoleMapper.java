package com.itbaizhan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbaizhan.pojo.Admin;
import com.itbaizhan.pojo.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    // 查询用户拥有的所有角色的id
    List<Integer> findRoleldByAdmin(Integer aid);
}
