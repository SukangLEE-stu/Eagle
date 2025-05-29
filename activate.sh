docker stop eagle_mysql
docker stop eagle_app
docker stop eagle_nginx
# docker rm eagle_app
docker rm eagle_nginx
# docker rm eagle-mysql
bash ./config/gen_sql.sh ./config
# mkdir -p ./config/mysql/data
# mkdir -p ./config/mysql/conf
mkdir -p ./config/hot-news/history
mvn clean package -Dspring.profiles.active=prod
cp eagle-all/target/eagle-all.jar config/eagle-all.jar
docker-compose -f ./config/compose.yaml up