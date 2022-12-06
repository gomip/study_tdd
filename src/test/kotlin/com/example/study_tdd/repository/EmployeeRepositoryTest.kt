package com.example.study_tdd.repository

import com.example.study_tdd.model.Employee
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.util.*

@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @Test
    @DisplayName("저장 테스트")
    fun givenEmployeeObject_whenSave_thenReturnSavedEmployee(){
        // given
        val employee = Employee(
            firstName = "junghoon",
            lastName = "kim",
            email = "devhoon15@gmail.com"
        )
        // when
        val savedEmployee : Employee = employeeRepository.save(employee)

        // then
        Assertions.assertThat(savedEmployee).isNotNull
        Assertions.assertThat(savedEmployee.id).isEqualTo(1)
    }

    @Test
    @DisplayName("전체 조회 테스트")
    fun givenTwoEmployees_whenSave_thenReturn2() {
        // given
        val employee1 = Employee(
            firstName = "junghoon",
            lastName = "kim",
            email = "devhoon15@gmail.com"
        )
        val employee2 = Employee(
            firstName = "gomip",
            lastName = "kim",
            email = "gomip@gmail.com"
        )
        employeeRepository.save(employee1)
        employeeRepository.save(employee2)

        // when
        val employeeList: List<Employee> = employeeRepository.findAll()

        // then
        Assertions.assertThat(employeeList).isNotNull
        Assertions.assertThat(employeeList.size).isEqualTo(2)
    }

    @Test
    @DisplayName("아이디로 조회 테스트")
    fun givenEmployObject_whenFindById_thenReturnSavedEmployeeId() {
        // given
        val employee = Employee(
            firstName = "junghoon",
            lastName = "kim",
            email = "devhoon15@test.com"
        )
        employeeRepository.save(employee)

        // when
        val newEmployee = employeeRepository.findById(employee.id).get()

        // then
        Assertions.assertThat(newEmployee).isNotNull
    }

    @Test
    @DisplayName("엔티티 속성값(이메일)로 조회 테스트")
    fun givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
        // given
        val employee = Employee(
            firstName = "junghoon",
            lastName = "kim",
            email = "devhoon15@test.com"
        )
        employeeRepository.save(employee)

        // when
        val newEmployee = employeeRepository.findByEmail("devhoon15@test.com").get()

        // then
        Assertions.assertThat(newEmployee).isNotNull
        println(newEmployee.firstName)
    }

    @Test
    @DisplayName("수정 테스트")
    fun givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        // given
        val employee = Employee(
            firstName = "junghoon",
            lastName = "kim",
            email = "devhoon15@test.com"
        )
        employeeRepository.save(employee)

        // when
        val savedEmployee = employeeRepository.findById(employee.id).get()
        savedEmployee.email = "gomip@test.com"
        savedEmployee.firstName = "gomip"
        val updatedEmployee = employeeRepository.save(savedEmployee)

        // then
        println("employee : ${employee.email}")
        Assertions.assertThat(updatedEmployee.email).isEqualTo("gomip@test.com")
        Assertions.assertThat(updatedEmployee.firstName).isEqualTo("gomip")
    }

    @Test
    @DisplayName("삭제 테스트")
    fun givenEmployeeObject_whenDelete_thenRemoveEmployee() {
        // given
        val employee = Employee(
            firstName = "junghoon",
            lastName = "kim",
            email = "devhoon15@test.com"
        )
        employeeRepository.save(employee)

        // when
        employeeRepository.deleteById(employee.id)
        val employeeOptional : Optional<Employee> = employeeRepository.findById(employee.id)

        // then
        Assertions.assertThat(employeeOptional).isEmpty
    }
}