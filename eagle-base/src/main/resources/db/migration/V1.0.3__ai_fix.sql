CREATE DATABASE IF NOT EXISTS `eagle_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `eagle_db`;

ALTER TABLE photo_post_items
ADD COLUMN url VARCHAR(100) COMMENT '图片访问地址';