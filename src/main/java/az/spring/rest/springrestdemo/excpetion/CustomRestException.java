package az.spring.rest.springrestdemo.excpetion;

import az.spring.rest.springrestdemo.enums.ErrorCodeEnum;
import lombok.Getter;

public class CustomRestException extends RuntimeException{

    @Getter
    private final int code;
    private final String message;


    public CustomRestException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getMessage());
        this.code=errorCodeEnum.getCode();
        this.message=errorCodeEnum.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
