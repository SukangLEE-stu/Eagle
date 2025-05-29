# Eagle

## bash介绍
1. activate.sh是docker compose配置，可以拉起一个mysql容器
2. exec.sh是打包docker镜像的，目前版本使用的是minikube本地构建
3. install.sh是helm安装包，需要先执行2.exec.sh
4. uninstall.sh是卸载helm安装包

## 项目介绍
config文件夹中有配置文件，eagleChart是helm chart配置
