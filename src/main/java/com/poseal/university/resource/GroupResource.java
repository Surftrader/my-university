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

import com.poseal.university.service.dto.GroupDto;
import com.poseal.university.service.group.GroupService;

@Path("/groups")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public class GroupResource {

    private GroupService groupService;

    public GroupResource() {
        groupService = new GroupService();
    }

    @GET
    public List<GroupDto> getGroups(@Context UriInfo uriInfo) {
        List<GroupDto> groupsDto = groupService.findAll();
        for (GroupDto groupDto : groupsDto) {
            setLinkToDto(groupDto, uriInfo);
        }

        return groupsDto;
    }

    @GET
    @Path("/{groupId}")
    public GroupDto getGroup(@PathParam("groupId") Integer id, @Context UriInfo uriInfo) {
        GroupDto groupDto = groupService.findOne(id);
        setLinkToDto(groupDto, uriInfo);

        return groupDto;
    }

    @POST
    public Response addGroup(GroupDto groupDto, @Context UriInfo uriInfo) {
        groupDto = groupService.saveGroup(groupDto);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(groupDto.getId())).build();
        return Response.created(uri).entity(groupDto).build();
    }

    @PUT
    @Path("/{groupId}")
    public GroupDto updateGroup(@PathParam("groupId") Integer id, GroupDto groupDto) {
        groupDto.setId(id);
        groupService.saveGroup(groupDto);
        return groupDto;
    }

    @DELETE
    @Path("/{groupId}")
    public void deleteGroup(@PathParam("groupId") Integer id) {
        groupService.remove(id);
    }

    private String getUriForSelf(UriInfo uriInfo, GroupDto groupDto) {
        String uri = uriInfo.getBaseUriBuilder().path(GroupResource.class).path(Integer.toString(groupDto.getId()))
                .build().toString();
        return uri;
    }

    private GroupDto setLinkToDto(GroupDto groupDto, UriInfo uriInfo) {
        String uri = getUriForSelf(uriInfo, groupDto);
        groupDto.addLink(uri, "self");
        return groupDto;
    }
}
