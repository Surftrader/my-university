package com.poseal.university.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.poseal.university.model.ErrorMessage;

public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

    @Override
    public Response toResponse(DataNotFoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorMessage(ex.getMessage());
        errorMessage.setErrorCode("404");
        errorMessage.setDocumentation("http://github.com/Surftrader/my-university");

        return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
    }
}
