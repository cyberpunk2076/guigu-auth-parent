package com.atguigu.system.service;

import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

public interface SysRoleService extends IService<SysRole> {
    //分页及带条件查询的方法
    IPage<SysRole> findPage(Page<SysRole> page, SysRoleQueryVo roleQueryVo);
}
