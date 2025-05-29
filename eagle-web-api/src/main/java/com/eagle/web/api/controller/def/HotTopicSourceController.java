package com.eagle.web.api.controller.def;

import com.eagle.base.constants.WebConstants;
import com.eagle.base.dao.HotTopicSource;
import com.eagle.base.dao.HttpResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping(WebConstants.WEB_API_PREFIX_V1 + "/hot/source")
public interface HotTopicSourceController {
    @GetMapping("/{id}")
    HotTopicSource getById(@PathVariable Long id);
    @GetMapping("/all")
    HttpResult getAll();
}
