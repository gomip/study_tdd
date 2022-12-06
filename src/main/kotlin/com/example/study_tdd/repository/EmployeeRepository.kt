package com.example.study_tdd.repository

import com.example.study_tdd.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface EmployeeRepository : JpaRepository<Employee, Long>{
    fun findByEmail(email: String) : Optional<Employee>
}