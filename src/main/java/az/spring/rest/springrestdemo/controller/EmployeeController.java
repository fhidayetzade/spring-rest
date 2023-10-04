package az.spring.rest.springrestdemo.controller;

import az.spring.rest.springrestdemo.rest.model.dto.EmployeeDto;
import az.spring.rest.springrestdemo.rest.model.response.EmployeeResponse;
import az.spring.rest.springrestdemo.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {


    private final EmployeeService employeeService;
    public String index(){
        return "hello";
    }

    @GetMapping
    public EmployeeResponse employeeResponse(){

        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeDto(@PathVariable long id){
        return employeeService.getEmployeeDto(id);
    }

    @GetMapping("/search")
    public EmployeeResponse getEmployeeNameAndSurname(
            @RequestParam String name,
            @RequestParam String surname){
        return  employeeService.getEmployeeByNameAndSurname(name,surname);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insertEmployee(@RequestBody @Valid EmployeeDto employeeDto){
            employeeService.insert(employeeDto);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody EmployeeDto employeeDto, @PathVariable("id") long id){
        employeeService.update(employeeDto,id);
    }

    @PatchMapping("/{id}")
    public void updateSome(@RequestBody EmployeeDto employeeDto, @PathVariable("id") long id){
        employeeService.updateSome(employeeDto,id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        employeeService.delete(id);
    }
}
