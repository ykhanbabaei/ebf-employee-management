package com.ebf.employeemanagement;

import com.ebf.employeemanagement.dto.CompanyDto;
import com.ebf.employeemanagement.dto.EmployeeDto;
import com.ebf.employeemanagement.model.Company;
import com.ebf.employeemanagement.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompaniesService {

    private final EmployeesRepository employeesRepository;
    private final CompaniesRepository companiesRepository;

    public List<EmployeeDto> findAllEmployeesByCompanyId(Long companyId){
        return employeesRepository.findAllByCompanyId(companyId).stream()
                .peek(e->e.setCompany(null))
                .map(EmployeeMapper.INSTANCE::employeeModelToDto)
                .collect(Collectors.toList());
    }

    public List<CompanyDto> findAllCompanies(){
        return companiesRepository.findAll().stream()
                .map(EmployeeMapper.INSTANCE::companyModelToDto)
                .collect(Collectors.toList());
    }

    public void deleteEmployee(Long employeeId) {
        employeesRepository.deleteById(employeeId);
    }

    public Long createEmployee(Long companyId, EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.INSTANCE.employeeDtoToModel(employeeDto);
        Optional<Company> companyOpt = companiesRepository.findById(companyId);
        Company company = companyOpt.orElseThrow(CompanyNotFoundException::new);
        employee.setCompany(company);
        Employee result = employeesRepository.save(employee);
        return result.getId();
    }

    public void update(Long employeeId, EmployeeDto employee) {
        Objects.requireNonNull(employeeId);
        Objects.requireNonNull(employee);
        employee.setId(employeeId);
        employeesRepository.save(EmployeeMapper.INSTANCE.employeeDtoToModel(employee));
    }

    public Optional<EmployeeDto> findEmployeeById(Long employeeId) {
        return employeesRepository.findById(employeeId)
                .map(this::loadDependency)
                .map(EmployeeMapper.INSTANCE::employeeModelToDto);
    }

    private Employee loadDependency(Employee employee) {
        Long companyId = employee.getCompany().getId();
        Company company = companiesRepository.findById(companyId).orElseThrow();
        employee.setCompany(company);
        return employee;
    }

    public Long createCompany(CompanyDto company) {
        return companiesRepository.save(EmployeeMapper.INSTANCE.companyDtoToModel(company)).getId();
    }

    public BigDecimal calculateAverageSalary(Long companyId) {
        return employeesRepository.calculateAverageSalary(companyId);
    }

    public Optional<CompanyDto> findCompanyById(Long companyId) {
        return companiesRepository.findById(companyId).map(EmployeeMapper.INSTANCE::companyModelToDto);
    }

    public static class CompanyNotFoundException extends RuntimeException {
    }


}
