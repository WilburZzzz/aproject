package com.zwb.aproject.es.controller;

import com.zwb.aproject.es.service.IESService;
import com.zwb.aproject.es.vo.ESUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 */
@RestController
@RequestMapping("/es")
public class ESController {

    @Autowired
    private IESService testService;

    @PostMapping("/save")
    public String save(@RequestBody ESUser esTestUser) {
        long id = System.currentTimeMillis();
        esTestUser.setId(id);
        testService.save(esTestUser);
        return "新增成功,id为：" + id;
    }

}
