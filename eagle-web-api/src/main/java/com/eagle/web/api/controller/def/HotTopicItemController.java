package com.eagle.web.api.controller.def;

import com.eagle.base.constants.WebConstants;
import com.eagle.base.dao.HotTopicItem;
import com.eagle.base.dao.HttpResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(WebConstants.WEB_API_PREFIX_V1 + "/hot/item")
public interface HotTopicItemController {
    @GetMapping("/all")
    ResponseEntity<List<HotTopicItem>> getAllItems();

    @GetMapping("/{id}")
    ResponseEntity<HotTopicItem> getItemById(@PathVariable Long id);

    @GetMapping("/by/time/{hours}")
    HttpResult getItemsByTime(@PathVariable Integer hours);

    @GetMapping("/by/source/{num}")
    HttpResult getItemsBySource(@PathVariable Long num);
}
