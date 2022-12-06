package com.example.study_tdd.service.imple

import com.example.study_tdd.exception.ResourceNotFoundException
import com.example.study_tdd.model.Employee
import com.example.study_tdd.repository.EmployeeRepository
import com.example.study_tdd.service.EmployeeService
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmployeeServiceImpl (
    private val employeeRepository: EmployeeRepository
        ): EmployeeService {

    override fun saveEmployee(employee: Employee): Employee {

        val savedEmployee = employeeRepository.findByEmail(employee.email)
            ?: throw ResourceNotFoundException("Employee already Exists with given email : ${employee.email}")

        return employeeRepository.save(savedEmployee)
    }

    override fun getAllEmployee(): List<Employee> {
        return employeeRepository.findAll()
    }

    override fun getEmployeeById(employeeId: Long): Optional<Employee> {
        return employeeRepository.findById(employeeId)
    }

    override fun updateEmployee(employee: Employee) : Employee{
        return employeeRepository.save(employee)
    }

    override fun deleteEmployee(employeeId: Long) {
        employeeRepository.deleteById(employeeId)
    }
}