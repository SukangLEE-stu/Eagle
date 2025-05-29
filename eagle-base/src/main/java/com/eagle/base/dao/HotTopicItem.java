package com.eagle.base.dao;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("hot_topic_item") // 表名
@Builder
public class HotTopicItem {

    @TableId(value = "id", type = IdType.AUTO) // 主键
    private Long id;
    private Long sourceId;
    private Integer priority;
    private String title;
    private String extra;
    private String link;
    @TableField("create_time")
    private LocalDateTime createTime; // 如果表中也有 create_time 字段的话
}
