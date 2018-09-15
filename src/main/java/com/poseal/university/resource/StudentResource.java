package com.poseal.university.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.poseal.university.service.dto.StudentDto;
import com.poseal.university.service.student.StudentService;

@Path("/students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public class StudentResource {

    private StudentService studentService;

    public StudentResource() {
        studentService = new StudentService();
    }

    @GET
    public List<StudentDto> getStudents(@Context UriInfo uriInfo) {
        List<StudentDto> studentsDto = studentService.findAll();
        for (StudentDto studentDto : studentsDto) {
            setLinkToDto(studentDto, uriInfo);
        }
        return studentsDto;
    }

    @GET
    @Path("/{studentId}")
    public StudentDto getStudent(@PathParam("studentId") Integer id, @Context UriInfo uriInfo) {
        StudentDto studentDto = studentService.findOne(id);
        setLinkToDto(studentDto, uriInfo);

        return studentDto;
    }

    @POST
    public Response addStudent(StudentDto studentDto, @Context UriInfo uriInfo) {
        studentDto = studentService.saveStudent(studentDto);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(studentDto.getId()))
                .build();
        return Response.created(uri).entity(studentDto).build();
    }

    @PUT
    @Path("/{studentId}")
    public StudentDto updateStudent(@PathParam("studentId") Integer id, StudentDto studentDto) {
        studentDto.setId(id);
        studentService.saveStudent(studentDto);
        return studentDto;
    }

    @DELETE
    @Path("/{studentId}")
    public void deleteStudent(@PathParam("studentId") Integer id) {
        studentService.remove(id);
    }

    private String getUriForSelf(UriInfo uriInfo, StudentDto studentDto) {
        String uri = uriInfo.getBaseUriBuilder()
                .path(StudentResource.class)
                .path(Integer.toString(studentDto.getId()))
                .build().toString();
        return uri;
    }

    private StudentDto setLinkToDto(StudentDto studentDto, UriInfo uriInfo) {
        String uri = getUriForSelf(uriInfo, studentDto);
        studentDto.addLink(uri, "self");
        return studentDto;
    }
}
