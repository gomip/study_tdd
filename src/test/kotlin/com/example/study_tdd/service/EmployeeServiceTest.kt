package com.example.study_tdd.service

import com.example.study_tdd.exception.ResourceNotFoundException
import com.example.study_tdd.model.Employee
import com.example.study_tdd.repository.EmployeeRepository
import com.example.study_tdd.service.imple.EmployeeServiceImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class EmployeeServiceTest {

    private val employeeRepository: EmployeeRepository = mockk()

    private val employeeService: EmployeeServiceImpl = EmployeeServiceImpl(employeeRepository)

    lateinit var employee : Employee

    @BeforeEach
    fun setup() {
        employee = Employee(
            firstName = "junghoon",
            lastName = "kim",
            email = "devhoon15@test.com"
        )
    }

    @Test
    @DisplayName("저장 테스트")
    fun givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObjectTh() {
//         given
        every {employeeRepository.findByEmail(employee.email)} returns mockk()
        every {employeeRepository.save(employee)} returns mockk()

        // when
        val savedEmployee = employeeService.saveEmployee(employee)

        // then
        Assertions.assertThat(savedEmployee).isNotNull
    }

    @Test
    @DisplayName("저장 익셉션 발생 테스트")
    fun givenEmployeeObject_whenSaveEmployee_thenThrowException() {
        // given
        every {employeeRepository.findByEmail(employee.email)} throws ResourceNotFoundException("Employee already Exists with given email : ${employee.email}")

        // when
        val ex = assertThrows<ResourceNotFoundException>{
            employeeService.saveEmployee(employee)
        }

        // then
        Assertions.assertThat(ex.message).isEqualTo("Employee already Exists with given email : devhoon15@test.com")
    }

    @Test
    @DisplayName("전체 조회 테스트")
    fun givenEmployeeList_whenFindAll_thenEmployeeList() {
        // given
        val employee2 = Employee(
            firstName = "junghoon",
            lastName = "test",
            email = "test@test.com"
        )
        every {employeeRepository.findAll()} returns listOf(employee,employee2)

        // when
        val listEmployee = employeeService.getAllEmployee()
        println(listEmployee[0].email)
        println(listEmployee[1].email)

        // then
        Assertions.assertThat(listEmployee).isNotNull
        Assertions.assertThat(listEmployee.size).isEqualTo(2)
    }

    @Test
    @DisplayName("전체 조회 테스트 - 빈 리스트")
    fun givenEmptyEmployeeList_whenFindAllEmpty_thenReturnEmptyEmployeeList() {
        // given
        every {employeeRepository.findAll()} returns emptyList()

        // when
        val listEmployee = employeeService.getAllEmployee()

        // then
        Assertions.assertThat(listEmployee).isEmpty()
        Assertions.assertThat(listEmployee.size).isEqualTo(0)
    }

    @Test
    @DisplayName("아이디로 조회 테스트")
    fun givenEmployeeId_whenFindByEmployeeId_thenReturnEmployeeObject() {
        // given
        every {employeeRepository.findById(employee.id)} returns mockk()

        // when
        val savedEmployee = employeeService.getEmployeeById(employee.id)

        // then
        Assertions.assertThat(savedEmployee).isNotNull
    }

    @Test
    @DisplayName("수정 서비스 테스트")
    fun givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        // given
        every {employeeRepository.save(employee)} returns mockk()
        employee.firstName = "new"
        employee.lastName = "employee"

        // when
        val updatedEmployee = employeeService.updateEmployee(employee)

        // then
        Assertions.assertThat(updatedEmployee).isNotNull
        Assertions.assertThat(employee.firstName).isEqualTo("new")

    }

    @Test
    @DisplayName("삭제테스트")
    fun givenEmployeeId_whenDeleteById_thenDeleteEmployeeObject() {
        // given
        every {employeeRepository.deleteById(1L)} returns mockk()

        // when
        employeeService.deleteEmployee(1L)

        // then
        verify(exactly = 1) { employeeService.deleteEmployee(1L) }
    }
}