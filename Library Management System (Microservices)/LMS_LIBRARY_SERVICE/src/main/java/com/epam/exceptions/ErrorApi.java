package com.epam.exceptions;

import lombok.Data;

public @Data class ErrorApi
{
    private String service;
    private String timestamp;
    private String error;
    private String status;
}
