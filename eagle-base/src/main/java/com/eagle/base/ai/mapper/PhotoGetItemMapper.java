package com.eagle.base.ai.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eagle.base.ai.dao.PhotoGetItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhotoGetItemMapper {
    PhotoGetItem selectByRequestId(String requestId);
    PhotoGetItem selectByTaskId(String taskId);
    int addPhotoGetItem(PhotoGetItem photoGetItem);

    int updatePhotoItem(PhotoGetItem photoGetItem);
}
