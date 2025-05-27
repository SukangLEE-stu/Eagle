CREATE DATABASE IF NOT EXISTS `eagle_db`;
USE `eagle_db`;
-- 创建表
DROP TABLE IF EXISTS users;
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100),
                       score INT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 插入1000条随机数据
INSERT INTO users (username, password, email, score)
SELECT
    CONCAT('user', FLOOR(RAND() * 10000)),
    SUBSTRING(MD5(RAND()), 1, 8),
    CONCAT(FLOOR(RAND() * 1000), '@example.com'),
    FLOOR(RAND() * 101)
FROM INFORMATION_SCHEMA.COLUMNS
         LIMIT 1000;
