package com.example.study_tdd.service

import com.example.study_tdd.model.Employee
import java.util.*

interface EmployeeService {
    fun saveEmployee(employee: Employee): Employee
    fun getAllEmployee(): List<Employee>
    fun getEmployeeById(employeeId: Long): Optional<Employee>
    fun updateEmployee(employee: Employee) : Employee
    fun deleteEmployee(employeeId: Long)
}