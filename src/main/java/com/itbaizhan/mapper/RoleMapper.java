package com.itbaizhan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbaizhan.pojo.Admin;
import com.itbaizhan.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    // 查询用户拥有的所有角色的id
    List<Integer> findRoleldByAdmin(Integer aid);

    void deleteRoleAllPermission(Integer rid);

    void addRolePermission(@Param("rid")Integer rid, @Param("pid")Integer pid);

    Role findDesc(Integer rid);
}
