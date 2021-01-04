package com.oyf.mysql_aes.controller;

import com.oyf.mysql_aes.entity.TbUser;
import com.oyf.mysql_aes.repository.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/10/29
 * @description：
 */
@RestController
@RequestMapping("/tbUser")
public class TbUserController {

    @Autowired
    private TbUserMapper tbUserMapper;

    @PostMapping("/insert")
    public void insert(@RequestBody TbUser tbUser) {
        System.out.println(tbUser.toString());
        tbUserMapper.insert(tbUser);
    }

    @GetMapping("/find")
    public void find(String id) {
        System.out.println(id);
        TbUser tbUser = tbUserMapper.find(id);
        System.out.println(tbUser.toString());
    }

}
