package com.example.study_tdd.integration

import com.example.study_tdd.repository.EmployeeRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class EmployeeControllerITest {
    @Autowired lateinit var mockMvc: MockMvc
    @Autowired lateinit var employeeRepository: EmployeeRepository
    @Autowired lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setup() {
        employeeRepository.deleteAll()
    }

    @Test
    @DisplayName("생성 테스트")
    fun given_when_then() {
        // given

        // when

        // then
    }
}