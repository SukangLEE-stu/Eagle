package com.eagle.base.mapper;

import com.eagle.base.dao.HotTopicItem;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface HotTopicItemMapper {
    List<HotTopicItem> selectAll();
    HotTopicItem selectById(Long id);
    void insert(HotTopicItem hotTopicItem);
    void update(HotTopicItem hotTopicItem);
    void deleteById(Long id);
    List<HotTopicItem> selectBySourceId(Long sourceId);

    List<HotTopicItem> getItemsByTime(Integer hours);

    HotTopicItem getItem(HotTopicItem item);
}
