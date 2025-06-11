package com.eagle.web.ai;
// 该代码 OpenAI SDK 版本为 2.6.0
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eagle.base.ai.dao.PhotoGetItem;
import com.eagle.base.ai.dao.PhotoPostItem;
import com.eagle.base.ai.service.PhotoGetItemService;
import com.eagle.base.ai.service.PhotoPostItemService;
import com.eagle.base.ai.util.AiDownloadUtil;
import com.eagle.base.ai.util.AiJsonUtil;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;

@Slf4j
@Service
public class AiService {
    @Resource
    private AiConstants aiConstants;

    @Resource
    private PhotoPostItemService photoPostItemService;

    @Resource
    private PhotoGetItemService photoGetItemService;

    /**
     * 与OpenAI的ChatCompletion接口进行交互，生成基于给定问题的对话响应
     * 此方法使用指定的文本模型类型来处理用户的问题，并返回模型生成的响应
     *
     * @param question 用户输入的问题，用于生成对话
     * @param modelType 文本模型类型，决定了使用哪个模型来生成回答
     * @return 模型生成的对话响应如果发生错误，返回null
     */
    public String chat(String question, TextModelType modelType) {
        // 创建OpenAIClient客户端，用于发送请求到OpenAI的API
        OpenAIClient client = OpenAIOkHttpClient.builder()
                .apiKey(aiConstants.getApiKey())
                .baseUrl(aiConstants.getTextServicePostUrl())
                .build();

        // 构建聊天完成的参数，包括用户的问题和使用的模型类型
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .addUserMessage(question)
                .model(modelType.getModelName())
                .build();

        // 日志输出，标志着聊天开始
        log.debug("开始chat!");

        try {
            // 创建并发送聊天完成的请求
            ChatCompletion chatCompletion = client.chat().completions().create(params);
            // 日志输出，记录聊天完成的对象信息
            log.info("chatCompletion: {}", chatCompletion);
            // 此处应有返回聊天完成的响应逻辑，但示例代码中未提供具体实现
            return "";
        } catch (Exception e) {
            // 日志输出，记录错误信息
            log.error("Error occurred: {}", e.getMessage());
            // 遍历异常堆栈，详细记录错误信息
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                log.error("{}", stackTraceElement);
            }
            // 发生异常时，返回null
            return null;
        }
    }

    /**
     * 根据给定的提示和尺寸通过HTTP请求生成照片帖子项
     * 该方法使用HTTP POST请求调用AI服务，以给定的提示、宽度和高度生成照片
     * 然后将响应保存为PhotoPostItem对象并返回
     *
     * @param prompt 用户输入的提示，用于生成照片
     * @param width  用户输入的期望照片宽度
     * @param height 用户输入的期望照片高度
     * @return 生成的PhotoPostItem对象，如果发生错误则返回null
     */
    public PhotoPostItem photoRequest(String prompt, int width, int height) {
        try (HttpResponse response = HttpUtil.createPost(aiConstants.getPhotoServicePostUrl())
                .header("X-DashScope-Async", "enable")
                .header("Authorization", "Bearer " + aiConstants.getApiKey())
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"model\": \"wanx2.1-t2i-turbo\",\n" +
                        "    \"input\": {\n" +
                        "        \"prompt\": \"" + prompt + "\"\n" +
                        "    },\n" +
                        "    \"parameters\": {\n" +
                        "        \"size\": \"" + width + "*" + height + "\",\n" +
                        "        \"n\": 1\n" +
                        "    }\n" +
                        "}")
                .execute()) {
            // 将HTTP响应体转换为PhotoPostItem对象
            PhotoPostItem photoPostItem = AiJsonUtil.json2PhotoPostItem(response.body());
            // 保存转换后的PhotoPostItem对象
            photoPostItemService.savePhotoPostItem(photoPostItem);
            // 返回生成的PhotoPostItem对象
            return photoPostItem;
        } catch (Exception e) {
            // 记录错误信息
            log.error("Error occurred: {}", e.getMessage());
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                log.error("{}", stackTraceElement);
            }
            // 如果发生异常，返回null
            return null;
        }
    }

    @Value("${eagle.cache.ai-photos-path}")
    private String AiFilePath;

    /**
     * 根据任务ID获取照片项
     * 本方法通过HTTP GET请求调用照片服务接口，获取与任务ID关联的照片信息，
     * 并将其保存或更新在数据库中
     *
     * @param taskId 任务ID，用于标识特定的照片获取任务
     * @return 返回一个PhotoGetItem对象，包含照片信息；如果获取失败或解析错误，则返回null
     */
    public PhotoGetItem photoFetch(String taskId) {
        final String REP = "{TASK_ID}";

        try (HttpResponse response = HttpUtil.createGet(aiConstants.getPhotoServiceGetUrl().replace(REP, taskId))
                .header("Authorization", "Bearer " + aiConstants.getApiKey())
                .execute()) {

            PhotoGetItem item = AiJsonUtil.json2PhotoGetItem(response.body());
            // 构建查询条件：根据 request_id 查询是否存在记录
            QueryWrapper<PhotoGetItem> wrapper = new QueryWrapper<>();
            wrapper.eq("request_id", item.getRequestId());

            // 使用 update(entity, wrapper) 实现按 request_id 更新
            log.info("{}", item.toString());
            photoGetItemService.addOrUpdate(item);

            item = photoGetItemService.getByTaskId(item.getTaskId());
            if ("SUCCEEDED".equals(item.getTaskStatus())) {
                PhotoGetItem finalItem = item;
                AiDownloadUtil.run(() -> {
                    String imageUrl = finalItem.getResultUrl(); // 获取图片URL
                    if (imageUrl == null || imageUrl.isEmpty()) {
                        log.warn("Result URL is empty for task ID: {}", finalItem.getTaskId());
                        return;
                    }

                    String fileName = extractFileNameFromUrl(finalItem.getResultUrl()); // 使用 request_id 作为文件名
                    int count = 1;
                    while (count <= 10) {
                        try {
                            // 创建目录（如果不存在）
                            java.nio.file.Path path = java.nio.file.Paths.get(AiFilePath);
                            if (!java.nio.file.Files.exists(path)) {
                                java.nio.file.Files.createDirectories(path);
                            }

                            // 下载图片并保存
                            java.net.URL url = new java.net.URL(imageUrl);
                            try (InputStream in = url.openStream()) {
                                java.nio.file.Files.copy(in, java.nio.file.Paths.get(AiFilePath, fileName),
                                        java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                            }

                            log.info("Image saved to: {}/{}", AiFilePath, fileName);

                            PhotoPostItem photoPostItem = photoPostItemService.getByTaskId(finalItem.getTaskId());
                            if (photoPostItem != null) {
                                photoPostItem.setTaskStatus(finalItem.getTaskStatus());
                                photoPostItem.setUrl(fileName);
                                photoPostItemService.updateItem(photoPostItem);
                            }

                            break;
                        } catch (Exception e) {
                            log.error("Failed to download and save image from URL:{}. {}", imageUrl, count, e);
                            count += 1;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                log.error("InterruptedException occurred: {}", ex.getMessage());
                                return;
                            }
                        }
                    }
                });
            }
            return item;
        } catch (Exception e) {
            log.error("Error occurred: {}", e.getMessage());
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                log.error("{}", stackTraceElement);
            }
            return null;
        }
    }


    private static String extractFileNameFromUrl(String url) {
        if (url == null || url.isEmpty()) return null;

        // 去除协议部分（如 http:// 或 https://）
        String path = url;
        if (path.contains("://")) {
            try {
                java.net.URL netUrl = new java.net.URL(url);
                path = netUrl.getPath(); // 获取路径部分，忽略参数
            } catch (Exception e) {
                // 如果解析失败，尝试手动截取
                int queryIndex = url.indexOf('?');
                if (queryIndex > 0) {
                    path = url.substring(0, queryIndex);
                }
            }
        }

        // 取最后 / 的位置，获取文件名
        int lastSlashIndex = path.lastIndexOf('/');
        log.info("path is {}, {}", path, lastSlashIndex);
        if (lastSlashIndex >= 0 && lastSlashIndex < path.length() - 1) {
            return path.substring(lastSlashIndex + 1);
        }

        return null;
    }

}
