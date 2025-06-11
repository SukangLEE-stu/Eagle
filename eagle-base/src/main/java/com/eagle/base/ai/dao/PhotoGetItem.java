// 文件路径：src/main/java/com/eagle/base/ai/dao/PhotoGetItem.java
package com.eagle.base.ai.dao;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("photo_get_items")
public class PhotoGetItem {
    @TableId(type = IdType.AUTO)
    private Long id;

    @JsonProperty("request_id")
    @TableField("request_id")
    private String requestId;

    // 平铺字段用于数据库映射
    @TableField("task_id")
    private String taskId;

    @TableField("task_status")
    private String taskStatus;

    @TableField("submit_time")
    private String submitTime;

    @TableField("scheduled_time")
    private String scheduledTime;

    @TableField("end_time")
    private String endTime;

    @TableField("task_metrics_total")
    private int taskMetricsTotal;

    @TableField("task_metrics_succeeded")
    private int taskMetricsSucceeded;

    @TableField("task_metrics_failed")
    private int taskMetricsFailed;

    @TableField("result_url")
    private String resultUrl;

    @TableField("usage_image_count")
    private int usageImageCount;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;
}
