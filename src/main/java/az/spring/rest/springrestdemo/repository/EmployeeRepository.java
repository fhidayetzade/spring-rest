package az.spring.rest.springrestdemo.repository;

import az.spring.rest.springrestdemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    public List<Employee> findByNameAndSurname(String name, String surname);


}
