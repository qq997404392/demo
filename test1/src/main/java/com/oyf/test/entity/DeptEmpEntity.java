package com.oyf.test.entity;

import javax.persistence.*;

@Entity
@Table(name = "dept_emp")
@IdClass(DeptEmpId.class)
public class DeptEmpEntity {
    @Id
    @Column(name = "deptno", length = 10)
    private int deptNo;

    @Id
    @Column(name = "empno", length = 10)
    private int empNo;

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    @Override
    public String toString() {
        return "DeptEmpEntity{" +
                "deptNo=" + deptNo +
                ", empNo=" + empNo +
                '}';
    }
}
