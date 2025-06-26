package com.example.h2.exceptions;

import lombok.Builder;

@Builder
public record ExceptionDetails(String component, String message) {

}

