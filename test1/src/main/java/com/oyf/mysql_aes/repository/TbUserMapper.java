package com.oyf.mysql_aes.repository;

import com.oyf.mysql_aes.entity.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/10/29
 * @description：
 */
@Mapper
@Repository
public interface TbUserMapper {

    void insert(TbUser tbUser);

    TbUser find(String id);

}
