package com.oyf.test;

import com.oyf.test.entity.DeptEmpEntity;
import com.oyf.test.entity.DeptEntity;
import com.oyf.test.repository.DeptEmpRepository;
import com.oyf.test.repository.DeptRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1ApplicationTests {
    @Autowired
    DeptRepository deptRepository;

    @Autowired
    DeptEmpRepository deptEmpRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void deptTest() {
        /*DeptEntity entity = new DeptEntity();
        entity.setDeptNo(02);
        entity.setdName("test2");
        entity.setLoc("test2");

        deptRepository.save(entity);*/

        /*List<DeptEntity> list = deptRepository.findAll(new Sort(Sort.Direction.DESC,"deptNo"));
        for (DeptEntity entity : list) {
            System.out.println(entity);
        }*/

       /* List<DeptEntity> list1 = deptRepository.findByDeptNo1(10);
        for (DeptEntity entity : list1) {
            System.out.println(entity);
        }*/


        List<DeptEmpEntity> list4 = deptEmpRepository.findEmpByDeptNo(10);
        System.out.println("111" + list4);
        for (DeptEmpEntity deptEmpEntity : list4) {
            System.out.println(deptEmpEntity);
        }
    }

}
