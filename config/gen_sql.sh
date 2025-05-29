#!/bin/bash

# 定义目标目录（如果未传参，则默认为当前目录）
TARGET_DIR="."

# 检查是否传递了目录参数
if [ -n "$1" ]; then
    TARGET_DIR="$1"
fi

# 检查目录是否存在
if [ ! -d "$TARGET_DIR" ]; then
    echo "错误：目录 $TARGET_DIR 不存在。"
    exit 1
fi

# 定义输出文件路径
OUTPUT_FILE="$TARGET_DIR/generated.sql"

# 清空或创建输出文件
> "$OUTPUT_FILE"

# 遍历目录下的所有 .sql 文件并合并到输出文件中
for sql_file in "$TARGET_DIR"/*.sql; do
    # 排除生成的文件本身（避免循环写入）
    if [ "$(basename "$sql_file")" = "generated.sql" ]; then
        continue
    fi

    cat "$sql_file" >> "$OUTPUT_FILE"
    echo "" >> "$OUTPUT_FILE"  # 可选：每个文件后添加空行
done

echo "SQL 文件已成功合并到 $OUTPUT_FILE"
