package com.eagle.eye.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eagle.base.dao.HotTopicItem;
import com.eagle.base.dao.HotTopicSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class HotNewsUtil {
    private static volatile HotNewsUtil instance;
    private final static String URL = "https://momoyu.cc/api/hot/list?type=0";
    public static HotNewsUtil getInstance() {
        if (instance == null) {
            synchronized (HotNewsUtil.class) {
                if (instance == null) {
                    instance = new HotNewsUtil();
                }
            }
        }
        return instance;
    }
    private HotNewsUtil(){

    }

    public HotNewsResult getHotNews() {
        try {
            JSONObject jsonObject;
            try (HttpResponse response = HttpRequest.get(URL)
                    .timeout(5000) // 设置超时时间
                    .execute()) {
                log.info("HotNews Get response body length: {}", response.body().length());
//            System.out.println(response.body());
                jsonObject = JSON.parseObject(response.body());
            }
            return getJavaResult(jsonObject);

        } catch (Exception e) {
            e.printStackTrace();
            return new HotNewsResult();
        }
    }

    private HotNewsResult getJavaResult(JSONObject jsonObject) {
        if (jsonObject.getInteger("status") == 100000 || jsonObject.getString("message").contains("成功")) {
            HotNewsResult result = new HotNewsResult();
            JSONArray array = jsonObject.getJSONArray("data");
            for (int i = 0; i < array.size(); i++) {
                JSONObject item = array.getJSONObject(i);
                HotTopicSource source = HotTopicSource.builder()
                        .id(item.getLong("id"))
                        .sort(item.getInteger("sort"))
                        .name(item.getString("name"))
                        .sourceKey(item.getString("source_key"))
                        .iconColor(item.getString("icon_color"))
                        .createTime(LocalDateTime.parse(item.getString("create_time"), DateTimeFormatter.ISO_DATE_TIME))
                        .build();
                result.sourceMap.putIfAbsent(source.getId(), source);

                JSONArray items = item.getJSONArray("data");
                for (int j = 1; j <= items.size(); j++) {
                    JSONObject pItem = items.getJSONObject(j - 1);
                    HotTopicItem topicItem = HotTopicItem.builder()
                            .sourceId(source.getId())
                            .priority(j)
                            .title(pItem.getString("title"))
                            .extra(pItem.getString("extra"))
                            .link(pItem.getString("link"))
                            .createTime(source.getCreateTime())
                            .build();
                    result.items.add(topicItem);
                }
            }
            return result;
        } else {
            return new HotNewsResult();
        }
    }

//    public static void main(String[] args) {
//        HotNewsUtil util = HotNewsUtil.getInstance();
//        util.getHotNews();
//    }

    @Data
    public static class HotNewsResult {
        private Map<Long, HotTopicSource> sourceMap;
        private List<HotTopicItem> items;
        public HotNewsResult() {
            sourceMap = new HashMap<>();
            items = new ArrayList<>();
        }
    }
}
