package com.ebf.employeemanagement;

import com.ebf.employeemanagement.dto.CompanyDto;
import com.ebf.employeemanagement.dto.EmployeeDto;
import com.ebf.employeemanagement.model.Company;
import com.ebf.employeemanagement.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDto employeeModelToDto(Employee employee);

    Employee employeeDtoToModel(EmployeeDto employee);

    CompanyDto companyModelToDto(Company company);

    Company companyDtoToModel(CompanyDto company);

}
