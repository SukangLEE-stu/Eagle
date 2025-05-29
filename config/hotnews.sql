CREATE DATABASE IF NOT EXISTS `eagle_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `eagle_db`;

CREATE TABLE hot_topic_source (
                                  id BIGINT PRIMARY KEY,
                                  sort INT,
                                  name VARCHAR(255),
                                  source_key VARCHAR(255),
                                  icon_color VARCHAR(50),
                                  create_time DATETIME
);


CREATE TABLE hot_topic_item (
                                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                source_id BIGINT,
                                priority INT,
                                title TEXT,
                                extra VARCHAR(255),
                                link VARCHAR(1024),
                                create_time DATETIME,
                                FOREIGN KEY (source_id) REFERENCES hot_topic_source(id)
);

ALTER TABLE hot_topic_source
    CONVERT TO CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

ALTER TABLE hot_topic_item
  CONVERT TO CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

SET NAMES 'utf8mb4';

# INSERT INTO hot_topic_source (id, sort, name, source_key, icon_color, create_time) VALUES
# (1, 1, '知乎热榜', 'zhihu', '#0177D7', NOW()),
# (2, 2, '豆瓣热话', 'douban', '#2e963d', NOW()),
# (3, 3, '微博热搜', 'weibo', '#e6242a', NOW()),
# (4, 4, 'B站热门', 'bilibili', '#00A1D6', NOW()),
# (5, 5, '虎扑热帖', 'hupu', '#FF6F61', NOW()),
# (6, 6, 'V2EX 热门', 'v2ex', '#3E8ACC', NOW()),
# (7, 7, '掘金热榜', 'juejin', '#FE8C6A', NOW()),
# (8, 8, 'SegmentFault 热点', 'segmentfault', '#2D8AC8', NOW()),
# (9, 9, '开源中国热门', 'oschina', '#00BFB3', NOW()),
# (10, 10, '知乎专栏', 'zhihu-column', '#0078D7', NOW());
#
# INSERT INTO hot_topic_item (id, source_id, title, extra, link, create_time, priority) VALUES
# (1001, 1, '为什么大家觉得 AI 编程很可怕？', '1476万浏览', 'https://www.zhihu.com/question/1', NOW(), 1),
# (1002, 1, '分享你最喜欢的书单', '230人参与', 'https://www.douban.com/gallery/topic/1', NOW(), 3),
# (1003, 1, '马斯克宣布将推出新型电动车', '热度：爆', 'https://weibo.com/hot-topic/tesla', NOW(), 6),
# (1004, 4, '《原神》新角色技能展示', '2.3万弹幕', 'https://www.bilibili.com/video/123456', NOW(), 1),
# (1005, 5, 'NBA 最新转会消息汇总', '1.2万讨论', 'https://bbs.hupu.com/nba', NOW(), 1),
# (1006, 6, 'Go 语言并发编程最佳实践', '1.5k阅读', 'https://www.v2ex.com/t/golang-concurrency', NOW(), 1),
# (1007, 7, 'Spring Boot 教程合集', '3.2k收藏', 'https://juejin.cn/post/springboot-tutorial', NOW(), 1),
# (1008, 8, 'JavaScript 性能优化技巧', '2.1k点赞', 'https://segmentfault.com/a/js-performance', NOW(), 1),
# (1009, 9, '国产数据库 StarRocks 开源进展', '1.8k星标', 'https://gitee.com/starrocks', NOW(), 1),
# (1010, 10, '知乎AI写作体验报告', '1.2万关注', 'https://zhuanlan.zhihu.com/p/ai-writing', NOW(), 1);
#
