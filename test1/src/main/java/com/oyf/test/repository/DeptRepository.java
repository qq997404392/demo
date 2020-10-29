package com.oyf.test.repository;

import com.oyf.test.entity.DeptEntity;
import com.oyf.test.entity.EmpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeptRepository extends JpaRepository<DeptEntity, Integer> {

    // 根据deptNo查询
    DeptEntity findByDeptNo(int deptNo);

    // 根据deptNo和dName查询，注意属性名大小写
    DeptEntity findByDeptNoAndDName(int deptNo, String dName);

    @Query(value = "SELECT e FROM DeptEntity e WHERE e.dName = :dName1 OR e.dName = :dName2")
    List<DeptEntity> findByDName1(String dName1, String dName2);

    @Query(value = "FROM DeptEntity e WHERE e.dName = ?1 OR e.dName = ?2")
    List<DeptEntity> findByDName2(String dName1, String dName2);

    @Query(value = "SELECT * FROM dept e WHERE e.dName = :dName1 OR e.dName = :dName2", nativeQuery = true)
    List<DeptEntity> findByDName3(String dName1, String dName2);

    @Query(value = "FROM DeptEntity e WHERE e.deptNo = ?1")
    List<DeptEntity> findByDeptNo1(int deptNo);

}
