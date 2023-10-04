package az.spring.rest.springrestdemo.impleService;

import az.spring.rest.springrestdemo.enums.ErrorCodeEnum;
import az.spring.rest.springrestdemo.excpetion.CustomRestException;
import az.spring.rest.springrestdemo.model.Employee;
import az.spring.rest.springrestdemo.repository.EmployeeRepository;
import az.spring.rest.springrestdemo.rest.model.dto.EmployeeDto;
import az.spring.rest.springrestdemo.rest.model.response.EmployeeResponse;
import az.spring.rest.springrestdemo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Tag(name = "Employee services")
public class EmployeeServiceImple implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse getAllEmployees() {
        List<EmployeeDto> employeeDtoList = employeeRepository.findAll().
                stream().map(employee -> convertToDto(employee)).collect(Collectors.toList());

        return makeEmployeeResponse(employeeDtoList);
    }

    @Override
    @Operation(summary = "This gets employee by id")
    public EmployeeDto getEmployeeDto(long id) {

        return employeeRepository.findById(id).
                map(employee -> convertToDto(employee)).
                orElseThrow(()->new CustomRestException
                        (ErrorCodeEnum.EMPLOYEE_NOT_FOUND));
    }

    @Override
    public EmployeeResponse getEmployeeByNameAndSurname(String name, String surname) {
         List<EmployeeDto> getEmployeeDto = employeeRepository.findByNameAndSurname(name,surname).
                stream().map(employee -> convertToDto(employee)).
                collect(Collectors.toList());
         return makeEmployeeResponse(getEmployeeDto);
    }

    @Override
    public void insert(EmployeeDto employeeDto) {
        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeDto,employee);
        employeeRepository.save(employee);
    }

    @Override
    public void update(EmployeeDto employeeDto, long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->new CustomRestException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND));
        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setAge(employeeDto.getAge());
        employee.setSalary(employeeDto.getSalary());
        employeeRepository.save(employee);
    }

    @Override
    public void updateSome(EmployeeDto employeeDto, long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->new CustomRestException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND));
        if(employee.getName()!=null);
        if(employee.getSurname()!=null);
        if(employee.getAge()>0);
        if(employee.getSalary()>0);

        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setAge(employeeDto.getAge());
        employee.setSalary(employeeDto.getSalary());
        employeeRepository.save(employee);
    }

    @Override
    public void delete(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->new CustomRestException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND));
        employeeRepository.delete(employee);
    }


    private EmployeeDto convertToDto(Employee employee){
            return EmployeeDto.builder().
                    id(employee.getId()).
                    name(employee.getName()).
                    surname(employee.getSurname()).
                    age(employee.getAge()).
                    salary(employee.getSalary()).
                    build();
    }

    private EmployeeResponse makeEmployeeResponse(List<EmployeeDto> employeeDtoList){

        return EmployeeResponse.builder().
                employeeDto(employeeDtoList).build();
    }
}
