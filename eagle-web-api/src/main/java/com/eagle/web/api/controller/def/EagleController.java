package com.eagle.web.api.controller.def;

import com.eagle.base.constants.WebConstants;
import com.eagle.base.dao.EagleEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface EagleController {
    @GetMapping(WebConstants.WEB_API_PREFIX_V1 + "/list")
    List<EagleEntity> getEntityList();
}
