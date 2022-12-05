package com.atguigu.system.test;

import com.atguigu.model.system.SysRole;
import com.atguigu.system.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SysRoleServiceTest {

    @Autowired
    private SysRoleService sysRoleService;

    /*
        测试查询所有
    */
    @Test
    public void testList(){
        List<SysRole> list = sysRoleService.list();
        for (SysRole sysRole : list) {
            System.out.println("sysRole = " + sysRole);
        }
    }

    /*
        测试添加
    */
    @Test
    public void testSave(){
        //创建SysRole对象
        SysRole sysRole = new SysRole("运维人员", "pro", "负责运维工作");
        SysRole sysRole2 = new SysRole("运维人员2", "pro2", "负责运维工作");
//        sysRoleService.save(sysRole);
        List<SysRole> sysRoleList = new ArrayList<>();
        sysRoleList.add(sysRole);
        sysRoleList.add(sysRole2);
        sysRoleService.saveBatch(sysRoleList);
    }

    /*
        测试更新
    */
    @Test
    public void testUpdate(){
        SysRole byId = sysRoleService.getById(12);
        byId.setRoleName("测试人员");
        sysRoleService.updateById(byId);
    }

    /*
        测试删除
    */
    @Test
    public void testDelete(){
        sysRoleService.removeById(13L);
    }
}
