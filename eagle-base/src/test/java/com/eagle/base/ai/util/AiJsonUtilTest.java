package com.eagle.base.ai.util;

import com.eagle.base.ai.dao.PhotoGetItem;
import com.eagle.base.ai.dao.PhotoPostItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class AiJsonUtilTest {

    @Test
    public void fromJson() {
        String json = "{\"request_id\":\"15b860a7-09eb-97aa-82ad-db7a5d1b59a4\",\"output\":{\"task_id\":\"55f18d14-b45d-44ee-8013-17f17e164454\",\"task_status\":\"PENDING\"}}";
        PhotoPostItem item = AiJsonUtil.json2PhotoPostItem(json);
        if (item != null) {
            log.info("{}", item);
        }
    }

    @Test
    public void fromJson2() {
        String json = "{\"request_id\":\"3e64aa53-ddd4-9334-a33a-8cf0b021a5ca\",\"output\":{\"task_id\":\"d22fd98c-d536-47b1-8184-ea820c7e2516\",\"task_status\":\"SUCCEEDED\",\"submit_time\":\"2025-06-10 19:17:39.089\",\"scheduled_time\":\"2025-06-10 19:17:39.110\",\"end_time\":\"2025-06-10 19:17:48.859\",\"results\":[{\"orig_prompt\":\"沙滩，女大学生，比基尼，遮阳伞，拆线\",\"actual_prompt\":\"写实风格海滩人像摄影，一位身材匀称的女大学生站在沙滩上。她穿着蓝色比基尼，外搭白色薄纱披肩，手持一把粉色遮阳伞。海风轻拂她的长发，眼神望向远方。沙滩细腻洁白，可见一些贝壳和脚印。背景是碧蓝的大海与晴空，远处有几朵白云。High-definition realistic photography，自然光线下的人像全身照，三分法构图。\",\"url\":\"https://dashscope-result-wlcb-acdr-1.oss-cn-wulanchabu-acdr-1.aliyuncs.com/1d/97/20250610/8928fb36/d22fd98c-d536-47b1-8184-ea820c7e2516124742067.png?Expires=1749640668&OSSAccessKeyId=LTAI5tKPD3TMqf2Lna1fASuh&Signature=VRrzY%2FaNg1CwAVF%2Bp8suWfMYM8Y%3D\"}],\"task_metrics\":{\"TOTAL\":1,\"SUCCEEDED\":1,\"FAILED\":0},\"usage\":{\"image_count\":1}}}";
        PhotoGetItem photoGetItem = AiJsonUtil.json2PhotoGetItem(json);
        if (photoGetItem != null) {
            log.info("{}", photoGetItem);
            log.info("{}", photoGetItem.toString());
        }
    }
}