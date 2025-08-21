package com.yourcompany.resourceservice.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStatusDTO {

    private String responseStatus;

    private String message;
 
    private String errorMessage;
}
