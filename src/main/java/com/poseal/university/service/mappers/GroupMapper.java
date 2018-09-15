package com.poseal.university.service.mappers;

import java.util.ArrayList;
import java.util.List;

import com.poseal.university.model.Faculty;
import com.poseal.university.model.Group;
import com.poseal.university.service.dto.GroupDto;
import com.poseal.university.service.faculty.FacultyService;

public class GroupMapper implements Mapper<GroupDto, Group> {

    @Override
    public Group transformToModel(GroupDto groupDto) {
        Group group = new Group();
        group.setId(groupDto.getId());
        group.setName(groupDto.getName());

        FacultyService facultyService = new FacultyService();
        Faculty faculty = facultyService.findOne(groupDto.getFacultyId());
        group.setFaculty(faculty);

        return group;
    }

    @Override
    public GroupDto transformToDto(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setName(group.getName());
        groupDto.setFacultyId(group.getFaculty().getId());

        return groupDto;
    }

    @Override
    public List<GroupDto> transformToListDto(List<Group> groups) {
        List<GroupDto> groupsDto = new ArrayList<>();
        for (Group group : groups) {
            groupsDto.add(transformToDto(group));
        }
        return groupsDto;
    }

    @Override
    public List<Group> transformToListModel(List<GroupDto> groupsDto) {
        List<Group> groups = new ArrayList<>();
        for (GroupDto groupDto : groupsDto) {
            groups.add(transformToModel(groupDto));
        }
        return groups;
    }
}
