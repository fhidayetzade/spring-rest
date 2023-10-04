package az.spring.rest.springrestdemo.enums;

import lombok.Getter;

public enum ErrorCodeEnum {


    EMPLOYEE_NOT_FOUND(1001," Can not find employee with given id"),
    VALIDATION_ERROR(1002," Validation errors"),
    UNKNOWN_VALIDIATION_ERROR(1003, " Unknown validation errors");
    private final int code;
    @Getter
    private  final String message;

    ErrorCodeEnum(int code,String message) {
        this.code=code;
        this.message = message;
    }

    public int getCode(){
        return code;
    }
}
