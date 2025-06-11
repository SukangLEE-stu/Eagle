package com.eagle.base.ai.util;

import com.eagle.base.ai.dao.PhotoGetItem;
import com.eagle.base.ai.dao.PhotoGetResponse;
import com.eagle.base.ai.dao.PhotoPostItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AiJsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static PhotoPostItem json2PhotoPostItem(String json) {
        try {
            return objectMapper.readValue(json, PhotoPostItem.class);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException Error occurred: {}", e.getMessage());
            return null;
        }
    }

    public static PhotoGetItem json2PhotoGetItem(String json) {
        try {
            PhotoGetResponse response = objectMapper.readValue(json, PhotoGetResponse.class);
            return convert(response);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException Error occurred: {}", e.getMessage());
            return null;
        }
    }


    private static PhotoGetItem convert(PhotoGetResponse response) {
        if (response == null) return null;

        PhotoGetItem item = new PhotoGetItem();
        item.setRequestId(response.getRequestId());

        PhotoGetResponse.Output output = response.getOutput();
        if (output != null) {
            item.setTaskId(output.getTaskId());
            item.setTaskStatus(output.getTaskStatus());
            item.setSubmitTime(output.getSubmitTime());
            item.setScheduledTime(output.getScheduledTime());
            item.setEndTime(output.getEndTime());

            PhotoGetResponse.TaskMetrics taskMetrics = output.getTaskMetrics();
            if (taskMetrics != null) {
                item.setTaskMetricsTotal(taskMetrics.getTotal());
                item.setTaskMetricsSucceeded(taskMetrics.getSucceeded());
                item.setTaskMetricsFailed(taskMetrics.getFailed());
            }

            if (output.getResults() != null && !output.getResults().isEmpty()) {
                item.setResultUrl(output.getResults().get(0).getUrl());
            }
        }

        PhotoGetResponse.Usage usage = response.getUsage();
        if (usage != null) {
            item.setUsageImageCount(usage.getImageCount());
        }

        return item;
    }
}
