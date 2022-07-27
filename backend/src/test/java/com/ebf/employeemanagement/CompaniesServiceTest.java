package com.ebf.employeemanagement;

import com.ebf.employeemanagement.dto.CompanyDto;
import com.ebf.employeemanagement.dto.EmployeeDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class CompaniesServiceTest {

    @Autowired
    private CompaniesService companiesService;

    @Test
    public void testAverageSalary(){
        CompanyDto company = new CompanyDto(1L, "comp1");
        final EmployeeDto employee1 = mockEmployeeWithSalary(BigDecimal.valueOf(45000L), company);
        final EmployeeDto employee2 = mockEmployeeWithSalary(BigDecimal.valueOf(65000L), company);
        companiesService.createCompany(company);
        companiesService.createEmployee(company.getId(), employee1);
        companiesService.createEmployee(company.getId(), employee2);

        Assertions.assertEquals(companiesService.calculateAverageSalary(company.getId()).longValue(), 55000L);
    }

    private EmployeeDto mockEmployeeWithSalary(BigDecimal salary, CompanyDto company) {
        EmployeeDto employee = new EmployeeDto();
        employee.setName("name1");
        employee.setSurname("surname1");
        employee.setAddress("Addr1");
        employee.setEmail("fname.lname@mycompany.com");
        employee.setSalary(salary);
        employee.setCompany(company);
        return employee;
    }

}
