package com.atguigu.system.test;

import com.atguigu.model.system.SysRole;
import com.atguigu.system.mapper.SysRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /*
        测试查询所有
    */
    @Test
    public void testSelectList(){
        //调用SysRoleMapper中selectList方法
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        for (SysRole sysRole : sysRoles) {
            System.out.println("sysRole = " + sysRole);
        }
    }

    /*
        测试添加
    */
    @Test
    public void testInsert(){
        //创建SysRole对象
        SysRole sysRole = new SysRole("开发人员3", "dev", "负责开发工作");
//        sysRole.setId(10L);
        //调用SysRoleMapper中的insert方法
        sysRoleMapper.insert(sysRole);
        //获取数据库自增的id值
        System.out.println("sysRole.getId() = " + sysRole.getId());
    }

    /*
        测试更新
    */
    @Test
    public void testUpdate(){
        //调用SysRoleMapper中的selectById方法查询一个对象
        SysRole sysRole = sysRoleMapper.selectById(9);
        //更新角色名字
        sysRole.setRoleName("测试管理员");
        //设置更新时间为null
        sysRole.setUpdateTime(new Date());
        //调用SysRoleMapper中的updateById方法
        sysRoleMapper.updateById(sysRole);
    }

    /*
        测试删除
    */
    @Test
    public void testDelete(){
        //调用SysRoleMapper中deleteById的方法进行逻辑删除
        int i = sysRoleMapper.deleteById(9);
        System.out.println(i>0?"逻辑删除成功！":"逻辑删除失败！");
    }

    /*
        测试查询总记录数
    */
    @Test
    public void testSelectCount(){
        //通过调用SysRoleMapper中的selectCount查询总记录数
        Integer integer = sysRoleMapper.selectCount(null);
        System.out.println("integer = " + integer);
    }

    /*
        测试批量操作
    */
    @Test
    public void testBatch(){
        //创建一个List存放要查询的记录的id值
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(8L);
        //根据id批量查询
        List<SysRole> sysRoles = sysRoleMapper.selectBatchIds(ids);
        for (SysRole sysRole : sysRoles) {
            System.out.println("sysRole = " + sysRole);
        }
        //批量删除
        sysRoleMapper.deleteBatchIds(ids);
    }
    /*
        测试带条件的查询
    */
    @Test
    public void testQueryWrapper(){
        //创建QueryWrapper对象
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        //设置查询条件
        // 查询role_code的值为dev的所有记录
//        queryWrapper.eq("role_code","dev");
        //查询role_name自动值中包含“管理”的记录
//        queryWrapper.like("role_name","管理");
        //查询role_name的值以“员”结尾的记录，并以id降序排序
//        queryWrapper.likeLeft("role_name","员").orderByDesc("id");
        //查询id值大于等于8的记录，并指定查询的字段
        queryWrapper.ge("id",8L).select("id","role_name");
        List<SysRole> sysRoles = sysRoleMapper.selectList(queryWrapper);
        for (SysRole sysRole : sysRoles) {
            System.out.println("sysRole = " + sysRole);
        }
    }

    /*
       测试局部更新（了解）
    */
    @Test
    public void testUpdateWrapper(){
        //创建UpdateWrapper对象
        UpdateWrapper<SysRole> updateWrapper = new UpdateWrapper<>();
        //设置要更新的字段
        updateWrapper.set("role_name","开发管理员").eq("id",10L);
        //调用SysRoleMapper中根据条件更新的方法
        sysRoleMapper.update(null,updateWrapper);
    }

    /*
        测试分页
    */
    @Test
    public void testPage(){
        //创建Page对象
        Page<SysRole> page = new Page<>(4, 3);
        //调用SySRoleMapper中分页的方法
        Page<SysRole> sysRolePage = sysRoleMapper.selectPage(page, null);
        //获取当前页
        System.out.println("当前页是：" + sysRolePage.getCurrent());
        //获取每页显示的条数
        System.out.println("每页显示的条数是： " + sysRolePage.getSize());
        //获取总记录数
        System.out.println("总记录数是： " + sysRolePage.getTotal());
        //获取总页数：
        System.out.println("总页数是： " + sysRolePage.getPages());
        //判断是否有上一页
        System.out.println("是否有上一页： " + sysRolePage.hasPrevious());
        //判断是否有下一页
        System.out.println("是否有下一页： " + sysRolePage.hasNext());
        System.out.println("当前页的记录有：");
        List<SysRole> records = sysRolePage.getRecords();
        for (SysRole record : records) {
            System.out.println("record = " + record);
        }
    }
}
