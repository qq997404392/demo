package com.oyf.test.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "emp")
public class EmpEntity {
    @Id
    @Column(name = "empno", length = 10)
    private int empNo;

    @Column(name = "ename", length = 20)
    private String eName;

    @Column(name = "job", length = 20)
    private String job;

    @Column(name = "mgr", length = 10)
    private String mgr;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "hiredate")
    private Date hireDate;

    @Column(name = "sal", length = 20)
    private String sal;

    @Column(name = "comm", length = 20)
    private String comm;

    @Column(name = "deptno", length = 20)
    private String deptNo;

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMgr() {
        return mgr;
    }

    public void setMgr(String mgr) {
        this.mgr = mgr;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getSal() {
        return sal;
    }

    public void setSal(String sal) {
        this.sal = sal;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }
}
