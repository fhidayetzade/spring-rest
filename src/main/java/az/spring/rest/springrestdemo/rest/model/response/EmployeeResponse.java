package az.spring.rest.springrestdemo.rest.model.response;

import az.spring.rest.springrestdemo.rest.model.dto.EmployeeDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    boolean status;
    private List<EmployeeDto> employeeDto;
}
