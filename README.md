# Eagle

## bash介绍
1. activate.sh是docker compose配置，可以拉起一个mysql容器
2. exec.sh是打包docker镜像的，目前版本使用的是minikube本地构建
3. install.sh是helm安装包，需要先执行2.exec.sh
4. uninstall.sh是卸载helm安装包

## 项目介绍
config文件夹中有配置文件，eagleChart是helm chart配置


## 20260819
上云迁移问题：nginx没有上云，以前是共享本地磁盘实现网页刷新的，现在得想个办法输出了
Solution: 使用共享volume方式同步

## 20260821
初步上云成功，需要check数据库多写的问题，现在news和db的交互是多对一，导致数据库条目数过多