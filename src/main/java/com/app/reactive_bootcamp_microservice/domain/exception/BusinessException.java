package com.app.reactive_bootcamp_microservice.domain.exception;

import com.app.reactive_bootcamp_microservice.domain.enums.TechnicalMessage;

public class BusinessException extends CustomException {

    public BusinessException(TechnicalMessage technicalMessage) {

        super(technicalMessage.getMessage(), technicalMessage);
    }

}
