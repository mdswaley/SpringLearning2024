package com.example.spring_web.Repository;

import com.example.spring_web.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
//    using JpaRepository we don't have to write any single piece of code
//    bcz all the crud operation is already define inside JpaRepository interface
}
