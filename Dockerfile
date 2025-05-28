FROM java:8

MAINTAINER "ealge"

VOLUME /usr/platform/log

ADD eagle-all/target/eagle-all.jar /usr/platform/eagle-all.jar

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

EXPOSE 10080

WORKDIR /usr/platform
ENTRYPOINT ["java","-jar","eagle-all.jar"]