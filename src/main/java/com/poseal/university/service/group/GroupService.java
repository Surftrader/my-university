package com.poseal.university.service.group;

import java.util.List;

import com.poseal.university.dao.GroupDao;
import com.poseal.university.dao.impl.GroupDaoHibernate;
import com.poseal.university.model.Group;

public class GroupService {
    private GroupDao groupDao;

    public GroupService() {
        groupDao = new GroupDaoHibernate();
    }

    public Group findOne(Integer groupId) {
        Group group = null;

        if (groupId == null || String.valueOf(groupId).isEmpty()) {
            throw new IllegalArgumentException("Group ID mustn't be NULL or EMPTY!");
        }
        group = groupDao.findOne(groupId);

        return group;
    }

    public List<Group> findAll() {
        return groupDao.findAll();
    }
}
