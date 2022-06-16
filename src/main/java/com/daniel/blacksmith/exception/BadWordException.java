package com.daniel.blacksmith.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadWordException extends RuntimeException{
    private String targetWord;
    private String message;

    public BadWordException(String targetWord, String message){
        // super(message); 이렇게 하면 Throwable의 detailMessage에 세팅하는 것이 된다. 하지만 오버라이딩을 했기 때문에 getMessage는
        // 필드에 있는 message를 가져오므로 null이 나온다.
        this.message = message;
        this.targetWord = targetWord;
    }

    public String getTargetWord() {
        return targetWord;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
