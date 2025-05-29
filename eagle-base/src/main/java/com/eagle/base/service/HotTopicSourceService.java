package com.eagle.base.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eagle.base.dao.HotTopicSource;
import com.eagle.base.mapper.HotTopicSourceMapper;
import org.springframework.stereotype.Service;

@Service
public class HotTopicSourceService extends ServiceImpl<HotTopicSourceMapper, HotTopicSource> {
    public void addSource(HotTopicSource source) {
        this.save(source);
    }
}
