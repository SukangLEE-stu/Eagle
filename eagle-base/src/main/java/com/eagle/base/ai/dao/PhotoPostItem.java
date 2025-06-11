package com.eagle.base.ai.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@TableName("photo_post_items")
@Data
public class PhotoPostItem {
    @TableId(type = IdType.AUTO)
    private String id;
    @TableField("request_id")
    private String requestId;
    private Output output;
    @TableField("task_id")
    private String taskId;
    @TableField("task_status")
    private String taskStatus;
    @TableField("url")
    private String url;
    @TableField("created_at")
    private Date createdAt;

    @JsonProperty("request_id")
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    // Output 内部类
    public static class Output {
        private String taskId;
        private String taskStatus;

        @JsonProperty("task_id")
        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        @JsonProperty("task_status")
        public String getTaskStatus() {
            return taskStatus;
        }

        public void setTaskStatus(String taskStatus) {
            this.taskStatus = taskStatus;
        }
    }
}
