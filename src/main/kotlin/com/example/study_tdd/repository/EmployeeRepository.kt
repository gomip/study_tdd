package com.example.study_tdd.repository

import com.example.study_tdd.model.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee, Long>{

}