package com.example.study_tdd.repository

import com.example.study_tdd.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface EmployeeRepository : JpaRepository<Employee, Long>{
    fun findByEmail(email: String) : Employee?

    @Query("select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
    fun findByJPQL(firstName: String, lastName: String): Employee

    @Query("select e from Employee e where e.firstName = :firstName and e.lastName = :lastName")
    fun findByJPQLNamedParams(@Param("firstName") firstName: String, @Param("lastName")lastName: String): Employee

    @Query("select * from Employee e where e.first_name = ?1 and e.last_name = ?2", nativeQuery = true)
    fun findByNativeSql(firstName: String, lastName: String) : Employee

    @Query("select * from Employee e where e.first_name = :firstName and e.last_name = :lastName", nativeQuery = true)
    fun findByNativeSqlNamedParam(@Param("firstName") firstName: String,@Param("lastName") lastName: String) : Employee
}