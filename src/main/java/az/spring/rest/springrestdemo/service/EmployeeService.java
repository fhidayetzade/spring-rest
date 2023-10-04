package az.spring.rest.springrestdemo.service;

import az.spring.rest.springrestdemo.rest.model.dto.EmployeeDto;
import az.spring.rest.springrestdemo.rest.model.response.EmployeeResponse;

public interface EmployeeService {

    EmployeeResponse getAllEmployees();
    EmployeeDto getEmployeeDto(long id);
    EmployeeResponse getEmployeeByNameAndSurname(String name,String surname);

    public void insert(EmployeeDto employeeDto);

    void update(EmployeeDto employeeDto, long id);

    void updateSome(EmployeeDto employeeDto, long id);

    void delete(long id);
}
