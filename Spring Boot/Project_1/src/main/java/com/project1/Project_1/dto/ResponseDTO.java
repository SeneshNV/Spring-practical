package com.project1.Project_1.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class ResponseDTO {
    public Object data;
    public String message;
    public HttpStatus responseCode;
}
