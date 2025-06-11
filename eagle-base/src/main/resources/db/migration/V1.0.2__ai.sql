CREATE DATABASE IF NOT EXISTS `eagle_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `eagle_db`;

CREATE TABLE IF NOT EXISTS photo_post_items (
                                 id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
                                 request_id VARCHAR(255) NOT NULL COMMENT '请求唯一标识',
                                 task_id VARCHAR(255) NOT NULL COMMENT '任务唯一标识',
                                 task_status VARCHAR(100) NOT NULL COMMENT '任务状态（例如：PENDING, SUCCEEDED）',
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS photo_get_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    request_id VARCHAR(255) NOT NULL COMMENT '请求ID',

    -- Output 字段
    task_id VARCHAR(255) COMMENT '任务ID',
    task_status VARCHAR(100) COMMENT '任务状态',
    submit_time DATETIME COMMENT '提交时间',
    scheduled_time DATETIME COMMENT '调度时间',
    end_time DATETIME COMMENT '结束时间',

    -- TaskMetrics 子字段
    task_metrics_total INT DEFAULT 0 COMMENT '总任务数',
    task_metrics_succeeded INT DEFAULT 0 COMMENT '成功数',
    task_metrics_failed INT DEFAULT 0 COMMENT '失败数',

    -- Usage 子字段
    usage_image_count INT DEFAULT 0 COMMENT '图片数量',

    -- Result 列表（这里我们只保留一个 url 示例，如需完整列表可扩展）
    result_url TEXT COMMENT '结果URL',

    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    UNIQUE (request_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图片生成请求结果';
