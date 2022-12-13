package com.example.study_tdd.controller

import com.example.study_tdd.model.Employee
import com.example.study_tdd.service.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/employees")
class EmployeeController (
    private val employeeService: EmployeeService
        ){
    @PostMapping
    fun createEmployee(@RequestBody employee: Employee) : Employee{
        return employeeService.saveEmployee(employee)
    }

    @GetMapping
    fun getAllEmployees() : List<Employee>{
        return employeeService.getAllEmployee()
    }

    @GetMapping("/{employeeId}")
    fun getEmployeeById(@PathVariable("employeeId") employeeId: Long) : ResponseEntity<Employee> {
//        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId))
        return employeeService.getEmployeeById(employeeId)
            .map {it : Employee? -> ResponseEntity.ok(it)}
            .orElseGet { ResponseEntity.notFound().build() }
    }

    @PutMapping("/{employeeId}")
    fun updateEmployee(@PathVariable("employeeId") employeeId: Long, @RequestBody employee: Employee) : ResponseEntity<Employee> {
        return employeeService.getEmployeeById(employeeId)
            .map { savedEmployee: Employee ->
                savedEmployee.firstName = employee.firstName
                savedEmployee.lastName = employee.lastName
                savedEmployee.email = employee.email
                val updatedEmployee = employeeService.updateEmployee(savedEmployee)
                ResponseEntity(updatedEmployee, HttpStatus.OK)
            }
            .orElseGet { ResponseEntity.notFound().build() }
    }

    @DeleteMapping("/{employeeId}")
    fun deleteEmployee(@PathVariable("employeeId")employeeId: Long) : ResponseEntity<String>{
        employeeService.deleteEmployee(employeeId)
        return ResponseEntity("Employee Deleted successfully!", HttpStatus.OK)
    }
}