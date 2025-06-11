package com.eagle.base.ai.service;

import com.eagle.base.ai.dao.PhotoGetItem;
import com.eagle.base.ai.dao.PhotoPostItem;
import com.eagle.base.ai.mapper.PhotoPostItemMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoPostItemService {

    private final PhotoPostItemMapper photoPostItemMapper;

    public PhotoPostItemService(PhotoPostItemMapper photoPostItemMapper) {
        this.photoPostItemMapper = photoPostItemMapper;
    }

    public void savePhotoPostItem(PhotoPostItem photoPostItem) {
        photoPostItemMapper.insert(photoPostItem);
    }

    public PhotoPostItem getByTaskId(String taskId) {
        return photoPostItemMapper.selectByTaskId(taskId);
    }

    public int updateItem(PhotoPostItem postItem) {
        return photoPostItemMapper.updateItem(postItem);
    }

    public List<PhotoPostItem> getPending(int num) {
        return photoPostItemMapper.getPending(num);
    }

    public List<PhotoPostItem> getList(int num) {
        return photoPostItemMapper.getAll(num);
    }
}
