package com.eagle.web.api.controller.impl;

import com.eagle.base.dao.HotTopicItem;
import com.eagle.base.dao.HttpResult;
import com.eagle.base.service.HotTopicItemService;
import com.eagle.web.api.controller.def.HotTopicItemController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class HotTopicItemControllerImpl implements HotTopicItemController {

    @Autowired
    private HotTopicItemService hotTopicItemService;

    // 获取所有条目
    @Override
    public ResponseEntity<List<HotTopicItem>> getAllItems() {
        return ResponseEntity.ok(hotTopicItemService.getAllItems());
    }

    // 根据 ID 获取单个条目
    @Override
    public ResponseEntity<HotTopicItem> getItemById(@PathVariable Long id) {
        HotTopicItem item = hotTopicItemService.getItemById(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

    @Override
    public HttpResult getItemsByTime(Integer hours) {
        try {
            List<HotTopicItem> items;
            if (hours != null) {
                items = hotTopicItemService.getItemsByTime(hours);
            } else {
                items = hotTopicItemService.getItemsByTime(1);
            }
            if (items != null && !items.isEmpty()) {
                Map<Long, List<HotTopicItem>> map = items.stream().collect(
                    Collectors.groupingBy(
                        HotTopicItem::getSourceId,
                        LinkedHashMap::new, // 保持插入顺序
                        Collectors.toList()
                    )
                );

                return HttpResult.builder().code(200).message("success").data(map).build();
            }
            throw new RuntimeException("Error sql result");
        } catch (Exception e) {
            return HttpResult.builder().code(400).message("failed").build();
        }
    }

    @Override
    public HttpResult getItemsBySource(Long num) {
        List<HotTopicItem> items = hotTopicItemService.getItemsBySource(num);
        if (items != null && !items.isEmpty()) {
            return HttpResult.builder().code(200).message("success").data(items).build();
        }
        return HttpResult.builder().code(400).message("failed").build();
    }
}
