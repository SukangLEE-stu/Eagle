package com.eagle.base.ai.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eagle.base.ai.dao.PhotoGetItem;
import com.eagle.base.ai.mapper.PhotoGetItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoGetItemService {

    @Autowired
    private PhotoGetItemMapper photoGetItemMapper;

    public PhotoGetItem getByRequestId(String requestId) {
        return photoGetItemMapper.selectByRequestId(requestId);
    }

    public PhotoGetItem getByTaskId(String taskId) {
        return photoGetItemMapper.selectByTaskId(taskId);
    }

    public int addOrUpdate(PhotoGetItem photoGetItem) {
        PhotoGetItem item = photoGetItemMapper.selectByTaskId(photoGetItem.getTaskId());
        if (item != null) {
            return photoGetItemMapper.updatePhotoItem(photoGetItem);
        } else {
            return photoGetItemMapper.addPhotoGetItem(photoGetItem);
        }
    }


}
