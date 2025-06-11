package com.eagle.web.ai;

import com.eagle.base.ai.dao.PhotoGetItem;
import com.eagle.base.ai.dao.PhotoPostItem;
import com.eagle.base.ai.service.PhotoPostItemService;
import com.eagle.base.dao.HotTopicItem;
import com.eagle.base.dao.HotTopicSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class AiPhotoScheduler {
    @Autowired
    private AiService aiService;
    @Autowired
    private PhotoPostItemService photoPostItemService;

    @Scheduled(fixedRate = 3000)
    public void downloadPhotos() {
        List<PhotoPostItem> photoPostItems = photoPostItemService.getPending(100);
        if (!photoPostItems.isEmpty()) {
            log.info("获取AI生成图像状态任务开始，需要执行数量: {}", photoPostItems.size());
        }
        for (PhotoPostItem photoPostItem : photoPostItems) {
            PhotoGetItem photoGetItem = aiService.photoFetch(photoPostItem.getTaskId());
            log.info("获取AI生成图像状态任务执行，当前进度: {}", photoGetItem);
        }
    }
}
