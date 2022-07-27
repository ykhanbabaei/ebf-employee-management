package com.ebf.employeemanagement;

import com.ebf.employeemanagement.dto.CompanyDto;
import com.ebf.employeemanagement.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CompanyController {

    private final CompaniesService companiesService;

    @GetMapping("/companies/{companyId}/employees")
    public List<EmployeeDto> getEmployees(@PathVariable("companyId") Long companyId){
        return companiesService.findAllEmployeesByCompanyId(companyId);
    }

    @GetMapping("/companies")
    public List<CompanyDto> getCompanies(){
        return companiesService.findAllCompanies();
    }

    @GetMapping("/companies/{companyId}")
    public CompanyDto getCompany(@PathVariable("companyId") Long companyId){
        return companiesService.findCompanyById(companyId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/employees/{employeeId}")
    public EmployeeDto getEmployee(@PathVariable("employeeId") Long employeeId){
        return companiesService.findEmployeeById(employeeId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/companies/{companyId}/employees")
    public Long createEmployee(@PathVariable("companyId") Long companyId, @RequestBody EmployeeDto employee){
        return companiesService.createEmployee(companyId, employee);
    }

    @PostMapping("/companies")
    public Long createCompany(@RequestBody CompanyDto company){
        return companiesService.createCompany(company);
    }

    @PutMapping("/employees/{employeeId}")
    public void updateEmployee(@PathVariable("employeeId") Long employeeId, @RequestBody EmployeeDto employee){
        companiesService.update(employeeId, employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") Long employeeId){
        companiesService.deleteEmployee(employeeId);
    }

    @GetMapping("/companies/{companyId}/average-salary")
    public BigDecimal calculateAverageSalary(@PathVariable("companyId") Long companyId){
        return companiesService.calculateAverageSalary(companyId);
    }

}
