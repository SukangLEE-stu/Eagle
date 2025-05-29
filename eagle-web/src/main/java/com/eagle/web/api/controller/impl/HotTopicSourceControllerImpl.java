package com.eagle.web.api.controller.impl;

import com.eagle.base.dao.HotTopicSource;
import com.eagle.base.dao.HttpResult;
import com.eagle.base.service.HotTopicSourceService;
import com.eagle.web.api.controller.def.HotTopicSourceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HotTopicSourceControllerImpl implements HotTopicSourceController {
    @Autowired
    private HotTopicSourceService hotTopicSourceService;

    @Override
    public HttpResult getAll() {
        List<HotTopicSource> list = hotTopicSourceService.list();
        if (list != null && !list.isEmpty()){
            return HttpResult.builder().code(200).message("success").data(list).build();
        }
        return HttpResult.builder().code(400).message("failed").build();
    }

    @Override
    public HotTopicSource getById(@PathVariable Long id) {
        return hotTopicSourceService.getById(id);
    }
}
