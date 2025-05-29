package com.eagle.base.service;

import com.eagle.base.dao.HotTopicItem;
import com.eagle.base.mapper.HotTopicItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotTopicItemService {

    @Autowired
    private HotTopicItemMapper hotTopicItemMapper;

    public List<HotTopicItem> getAllItems() {
        return hotTopicItemMapper.selectAll();
    }

    public HotTopicItem getItemById(Long id) {
        return hotTopicItemMapper.selectById(id);
    }

    public List<HotTopicItem> getItemsBySource(Long num) {
        return hotTopicItemMapper.selectBySourceId(num);
    }

    public void addItem(HotTopicItem item) {
        hotTopicItemMapper.insert(item);
    }

    public void updateItem(HotTopicItem item) {
        hotTopicItemMapper.update(item);
    }

    public void deleteItem(Long id) {
        hotTopicItemMapper.deleteById(id);
    }

    public List<HotTopicItem> getItemsByTime(int i) {
        return hotTopicItemMapper.getItemsByTime(i);
    }

    public HotTopicItem getItem(HotTopicItem item) {
        return hotTopicItemMapper.getItem(item);
    }
}
