package com.poseal.university.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.poseal.university.model.ErrorMessage;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorMessage(ex.getMessage());
        errorMessage.setErrorCode("500");
        errorMessage.setDocumentation("http://git.foxminded.com.ua/Surftrader/university");

        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
    }
}
