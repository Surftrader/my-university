package com.poseal.university.service.group;

import java.util.List;

import com.poseal.university.dao.GroupDao;
import com.poseal.university.dao.impl.GroupDaoHibernate;
import com.poseal.university.exception.DataNotFoundException;
import com.poseal.university.exception.ServiceException;
import com.poseal.university.model.Group;
import com.poseal.university.service.dto.GroupDto;
import com.poseal.university.service.mappers.GroupMapper;
import com.poseal.university.service.mappers.Mapper;

public class GroupService {

    private GroupDao groupDao;
    private Mapper<GroupDto, Group> groupMapper;

    public GroupService() {
        groupDao = new GroupDaoHibernate();
        groupMapper = new GroupMapper();
    }

    public GroupDto findOne(Integer groupId) {
        GroupDto groupDto = null;

        if (groupId == null || String.valueOf(groupId).isEmpty()) {
            throw new IllegalArgumentException("Group ID mustn't be NULL or EMPTY!");
        }
        Group group = groupDao.findOne(groupId);
        groupDto = groupMapper.transformToDto(group);

        if (groupDto == null) {
            throw new DataNotFoundException("Group with id = " + groupId + " not found!");
        }

        return groupDto;
    }

    public List<GroupDto> findAll() {
        List<Group> groups = groupDao.findAll();
        return groupMapper.transformToListDto(groups);
    }

    public GroupDto saveGroup(GroupDto groupDto) {
        Group group = groupMapper.transformToModel(groupDto);
        group.setName(group.getName().trim());
        group.setFaculty(group.getFaculty());

        if (group.getName().isEmpty()) {

            throw new ServiceException("Invalid name of group!");
        }

        if (group.getId() == null) {
            group = groupDao.create(group);
        } else {
            groupDao.update(group);
        }
        return groupMapper.transformToDto(group);
    }

    public void remove(Integer groupId) {
        groupDao.remove(groupId);
    }
}
