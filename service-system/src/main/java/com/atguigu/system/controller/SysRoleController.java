package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation("查询所有")
    @GetMapping("/findAll")
    public Result findAll(){
        //调用SysRoleService中查询所有的方法
        List<SysRole> sysRoleList = sysRoleService.list();
        return Result.ok(sysRoleList);
    }

    @ApiOperation("分页及带条件查询的方法")
    @GetMapping("/{current}/{size}")
    public Result findPage(
            @ApiParam(name = "current",value = "当前页",required = true)
            @PathVariable Long current ,
            @ApiParam(name = "size",value = "每页显示的条数",required = true)
            @PathVariable Long size ,
            @ApiParam(name = "roleQueryVo",value = "查询条件",required = false)
            SysRoleQueryVo roleQueryVo){
        //创建Page对象
        Page<SysRole> page = new Page<>(current, size);
        //调用SysRoleService中分页及带条件查询的方法
        IPage<SysRole> iPage = sysRoleService.findPage(page, roleQueryVo);
        return Result.ok(iPage);
    }

    @ApiOperation("添加角色")
    @PostMapping("/save")
    public Result save(@RequestBody SysRole sysRole){
        //调用SysRoleService中保存的方法
        sysRoleService.save(sysRole);
        return Result.ok();
    }

    @ApiOperation("根据Id查询角色信息")
    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable Long id){
        //调用SysRoleService中根据id查询的方法
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    @ApiOperation("更新角色")
    @PutMapping("/update")
    public Result update(@RequestBody SysRole sysRole){
        //调用SysRoleService中根据id更新的方法
        sysRoleService.updateById(sysRole);
        return Result.ok();
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/deleteById/{id}")
    public Result delete(@PathVariable Long id){
        //调用SysRoleService中根据id删除的方法
        sysRoleService.removeById(id);
        return Result.ok();
    }

    @ApiOperation("批量删除角色")
    @DeleteMapping("/batchDelete")
    public Result batchDelete(@RequestBody List<Long> ids){
        //调用SysRoleService中批量删除的方法
        sysRoleService.removeByIds(ids);
        return Result.ok();
    }
}
