package com.eagle.base.ai.mapper;

import com.eagle.base.ai.dao.PhotoPostItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotoPostItemMapper {
    int insert(PhotoPostItem photoPostItem);
    // 根据 request_id 查询单条记录
    PhotoPostItem selectByRequestId(String requestId);

    // 查询所有记录
    List<PhotoPostItem> selectAll(int size);

    PhotoPostItem selectByTaskId(String taskId);

    int updateItem(PhotoPostItem postItem);

    List<PhotoPostItem> getPending(int num);

    List<PhotoPostItem> getAll(int num);
}
