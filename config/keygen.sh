#!/bin/bash

generate_ak_sk() {
  echo "AHM_WEB_AK=$(LC_ALL=C tr -dc 'A-Za-z0-9' < /dev/urandom | head -c 16)"
  echo "AHM_WEB_SK=$(LC_ALL=C tr -dc 'A-Za-z0-9' < /dev/urandom | head -c 30)"
}

generate_ak_sk

# 生成 16字节二进制数据，转换为 Base64 字符串（长度 24字节，但可截断为 16字节）
AES_KEY=$(openssl rand -base64 16 | tr -d '\n' | head -c 16)
echo "AES_KEY=$AES_KEY"

