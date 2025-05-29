package com.eagle.base.dao;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("hot_topic_source") // 表名
@Builder
public class HotTopicSource {

    @TableId(value = "id", type = IdType.AUTO) // 主键
    private Long id;

    private Integer sort;

    private String name;

    @TableField("source_key")
    private String sourceKey;

    @TableField("icon_color")
    private String iconColor;

    @TableField("create_time")
    private LocalDateTime createTime;
}
