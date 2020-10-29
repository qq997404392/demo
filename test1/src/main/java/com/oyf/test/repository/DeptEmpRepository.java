package com.oyf.test.repository;

import com.oyf.test.entity.DeptEmpEntity;
import com.oyf.test.entity.EmpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeptEmpRepository extends JpaRepository<DeptEmpEntity, Integer> {

    // 查询某部门下所有员工
    @Query(value = "FROM DeptEmpEntity e WHERE e.deptNo = ?1")
    List<DeptEmpEntity> findEmpByDeptNo(int deptNo);
}
