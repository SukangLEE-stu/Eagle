// 文件路径：src/main/java/com/eagle/base/ai/dao/PhotoGetResponse.java
package com.eagle.base.ai.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class PhotoGetResponse {

    @JsonProperty("request_id")
    private String requestId;

    private Output output;

    private Usage usage;

    @Data
    public static class Output {

        @JsonProperty("task_id")
        private String taskId;

        @JsonProperty("task_status")
        private String taskStatus;

        @JsonProperty("submit_time")
        private String submitTime;

        @JsonProperty("scheduled_time")
        private String scheduledTime;

        @JsonProperty("end_time")
        private String endTime;

        private List<Result> results;

        @JsonProperty("task_metrics")
        private TaskMetrics taskMetrics;
    }

    @Data
    public static class Result {

        @JsonProperty("orig_prompt")
        private String origPrompt;

        @JsonProperty("actual_prompt")
        private String actualPrompt;

        @JsonProperty("url")
        private String url;
    }

    @Data
    public static class TaskMetrics {

        @JsonProperty("TOTAL")
        private int total;

        @JsonProperty("SUCCEEDED")
        private int succeeded;

        @JsonProperty("FAILED")
        private int failed;
    }

    @Data
    public static class Usage {

        @JsonProperty("image_count")
        private int imageCount;
    }
}
