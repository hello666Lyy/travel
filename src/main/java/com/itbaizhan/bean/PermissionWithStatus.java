package com.itbaizhan.bean;

import lombok.Data;

@Data
public class PermissionWithStatus {
    private Integer pid;
    private String permissionName; // 权限名
    private String permissionDesc;//权限详情
    private Boolean roleHas;
}
