bash ./uninstall.sh
bash ./config/docker_build.sh
echo '================================'
docker images | grep 'eagle'
echo '================================'
docker save -o config/eagle.docker.tar eagle-app:1.0.1
echo '=========save==finished========='
eval $(minikube docker-env); docker load -i config/eagle.docker.tar
echo '=========load==finished========='