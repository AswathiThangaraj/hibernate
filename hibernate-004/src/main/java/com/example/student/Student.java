package com.example.student;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    private int rollNo;  
    private String stuName;

    public Student() {} 

    public Student(int rollNo, String stuName) { 
        this.rollNo = rollNo;
        this.stuName = stuName;
    }

    public int getRollNo() { 
    	return rollNo; 
    }
    
    public void setRollNo(int rollNo) { 
    	this.rollNo = rollNo; 
    }

    public String getStuName() { 
    	return stuName; 
    }
    
    public void setStuName(String stuName) { 
    	this.stuName = stuName; 
    }
}