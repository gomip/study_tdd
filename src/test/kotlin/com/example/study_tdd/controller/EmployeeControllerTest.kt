package com.example.study_tdd.controller

import com.example.study_tdd.model.Employee
import com.example.study_tdd.service.EmployeeService
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest
class EmployeeControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) {

    @MockBean
    lateinit var employeeService: EmployeeService

    @Test
    @DisplayName("post api 테스트")
    fun givenEmployeeObject_whenCreate_thenReturnSavedEmployee() {
        // given
        val employee = Employee(
            firstName = "junghoon",
            lastName = "kim",
            email = "devhoon15@test.com"
        )


        // when

        // then

    }

}