package com.eagle.web.api.controller.impl;


import com.eagle.base.dao.EagleEntity;
import com.eagle.web.api.controller.def.EagleController;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class EagleControllerImpl implements EagleController {

    @Override
    public List<EagleEntity> getEntityList() {
        List<EagleEntity> eagleEntityList = Arrays.asList(EagleEntity.builder()
                .id("1").level("DEBUG").time(String.valueOf(new Date().getTime())).description("debug info").build());
        return eagleEntityList;
    }
}
