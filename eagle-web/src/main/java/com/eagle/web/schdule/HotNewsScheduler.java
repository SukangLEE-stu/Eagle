package com.eagle.web.schdule;

import com.eagle.base.dao.HotTopicItem;
import com.eagle.base.dao.HotTopicSource;
import com.eagle.base.service.HotTopicItemService;
import com.eagle.base.service.HotTopicSourceService;
import com.eagle.eye.util.HotNewsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Slf4j
public class HotNewsScheduler {
    @Autowired
    private HotTopicItemService hotTopicItemService;
    @Autowired
    private HotTopicSourceService hotTopicSourceService;

    @Scheduled(fixedRate = 1000 * 60 * 10)
    public void queryHotNews() {
        HotNewsUtil.HotNewsResult result = HotNewsUtil.getInstance().getHotNews();
        log.info("查询到{}条热搜", result.getItems().size());
        int countSource = 0;
        int countItem = 0;
        for (HotTopicSource source : result.getSourceMap().values()) {
            HotTopicSource obj = hotTopicSourceService.getById(source.getId());
            if (obj == null) {
                hotTopicSourceService.addSource(source);
                countSource++;
            }
        }
        for (HotTopicItem item : result.getItems()) {
            HotTopicItem obj = hotTopicItemService.getItem(item);
            if (obj == null) {
                hotTopicItemService.addItem(item);
                countItem++;
            }
        }
        log.info("添加的数据条目: 源: {}条, 新闻: {}条", countSource, countItem);
    }

    @Value("${eagle.cache.file-path}")
    private String cacheFilePath;
    private static final String CACHE_FILE_PATH = "history/";

    @Scheduled(cron = "55 0,10,20,30,40,50 * * * ?")
    public void saveHotNews() {
        List<HotTopicSource> topicSources = hotTopicSourceService.list();
        if (topicSources == null || topicSources.isEmpty()) {
            return;
        }
        for (HotTopicSource source : topicSources) {
            List<HotTopicItem> items = hotTopicItemService.getItemsBySource(source.getId());
            if(items == null || items.isEmpty()){
                return;
            }
            // 构建HTML内容
            String htmlContent = buildHtmlContent(source, items);
            // 创建保存路径
            String fileName = source.getName() + "-" + LocalDateTime.now(ZoneId.of("Asia/Shanghai")).format(DateTimeFormatter.ofPattern("yyyyMMdd_HH")) + ".html";

            try {
                File dir = new File(cacheFilePath + CACHE_FILE_PATH);
                if (!dir.exists()) {
                    dir.mkdirs(); // 自动创建目录
                }

                File file = new File(cacheFilePath + CACHE_FILE_PATH + fileName);
                FileUtils.writeStringToFile(file, htmlContent, "UTF-8");
                log.info("已生成{}热搜HTML文件：{}", source.getName(), file.getAbsolutePath());

                String rootFileName = source.getName() + ".html";
                File rootFile = new File(cacheFilePath + rootFileName);
                FileUtils.writeStringToFile(rootFile, htmlContent, "UTF-8");


            } catch (IOException e) {
                log.error("生成热搜HTML文件失败", e);
            }
        }

    }


    /**
     * 构建HTML内容
     * 该方法用于生成一个包含热点话题榜单的HTML字符串
     * 它会根据提供的热点话题来源信息和热点话题项列表来构建HTML内容
     *
     * @param source 热点话题的来源，用于获取来源名称
     * @param items 热点话题项的列表，用于生成HTML列表项
     * @return 返回构建好的HTML内容字符串
     */
    /**
     * 构建HTML内容
     * 该方法用于生成一个包含热点话题榜单的HTML字符串
     * 它会根据提供的热点话题来源信息和热点话题项列表来构建HTML内容
     *
     * @param source 热点话题的来源，用于获取来源名称
     * @param items 热点话题项的列表，用于生成HTML列表项
     * @return 返回构建好的HTML内容字符串
     */
    private String buildHtmlContent(HotTopicSource source, List<HotTopicItem> items) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n")
                .append("<html lang=\"zh\">\n")
                .append("<head>\n")
                .append("    <meta charset=\"UTF-8\">\n")
                .append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n")
                .append("    <title>").append(source.getName()).append(" - 热搜榜单</title>\n")
                .append("    <style>\n")
                .append("        body {\n")
                .append("            font-family: 'Segoe UI', sans-serif;\n")
                .append("            background: #f7f9fc;\n")
                .append("            color: #333;\n")
                .append("            margin: 0;\n")
                .append("            padding: 2em;\n")
                .append("        }\n")
                .append("        h1 {\n")
                .append("            text-align: center;\n")
                .append("            color: #2c3e50;\n")
                .append("        }\n")
                .append("        table {\n")
                .append("            width: 90%;\n")
                .append("            max-width: 900px;\n")
                .append("            margin: 2em auto;\n")
                .append("            border-collapse: collapse;\n")
                .append("            box-shadow: 0 4px 12px rgba(0,0,0,0.1);\n")
                .append("            background-color: #fff;\n")
                .append("            border-radius: 8px;\n")
                .append("            overflow: hidden;\n")
                .append("        }\n")
                .append("        th, td {\n")
                .append("            padding: 12px 16px;\n")
                .append("            text-align: left;\n")
                .append("            border-bottom: 1px solid #ddd;\n")
                .append("        }\n")
                .append("        tr:hover {\n")
                .append("            background-color: #f1f1f1;\n")
                .append("        }\n")
                .append("        th {\n")
                .append("            background-color: #3498db;\n")
                .append("            color: white;\n")
                .append("        }\n")
                .append("        a {\n")
                .append("            color: #3498db;\n")
                .append("            text-decoration: none;\n")
                .append("        }\n")
                .append("        a:hover {\n")
                .append("            text-decoration: underline;\n")
                .append("        }\n")
                .append("        .footer {\n")
                .append("            text-align: center;\n")
                .append("            margin-top: 4em;\n")
                .append("            color: #aaa;\n")
                .append("        }\n")
                .append("    </style>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("    <h1>🔍 ").append(source.getName()).append(" 热搜榜单</h1>\n")
                .append("    <table>\n")
                .append("        <thead>\n")
                .append("            <tr>\n")
                .append("                <th>标题</th>\n")
                .append("                <th>热度</th>\n")
                .append("                <th>发布时间</th>\n")
                .append("            </tr>\n")
                .append("        </thead>\n")
                .append("        <tbody>\n");

        for (HotTopicItem item : items) {
            sb.append("            <tr>\n")
                    .append("                <td><a href=\"").append(item.getLink())
                    .append("\" target=\"_blank\">").append(item.getTitle()).append("</a></td>\n")
                    .append("                <td>").append(item.getExtra() != null ? item.getExtra() : "未知").append("</td>\n")
                    .append("                <td>")
                    .append(item.getCreateTime()
                            .atOffset(ZoneOffset.UTC)
                            .toZonedDateTime()
                            .withZoneSameInstant(ZoneId.of("Asia/Shanghai"))
                            .format(DateTimeFormatter.ofPattern("HH:mm:ss")))
                    .append("</td>\n")
                    .append("            </tr>\n");
        }

        sb.append("        </tbody>\n")
                .append("    </table>\n")
                .append("    <div class=\"footer\">\n")
                .append("        &copy; 2025 Eagle 热搜系统 | <a href=\"/").append(source.getName()).append(".html\">返回首页</a>\n")
                .append("    </div>\n")
                .append("</body>\n")
                .append("</html>");

        return sb.toString();
    }
}
