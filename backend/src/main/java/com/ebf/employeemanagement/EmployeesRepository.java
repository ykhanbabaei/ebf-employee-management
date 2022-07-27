package com.ebf.employeemanagement;


import com.ebf.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByCompanyId(Long companyId);

    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.company.id = ?1")
    BigDecimal calculateAverageSalary(Long companyId);

}
