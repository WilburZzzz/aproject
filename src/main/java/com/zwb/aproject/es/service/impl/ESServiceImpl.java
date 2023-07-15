package com.zwb.aproject.es.service.impl;

import com.zwb.aproject.es.dao.ESDao;
import com.zwb.aproject.es.service.IESService;
import com.zwb.aproject.es.vo.ESUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ESServiceImpl implements IESService {

    @Autowired
    private ESDao userDao;

    @Override
    public void save(ESUser esTestUser) {
        userDao.save(esTestUser);
    }

}
