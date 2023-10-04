package az.spring.rest.springrestdemo.rest.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {


    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    private int age;
    private double salary;
}
