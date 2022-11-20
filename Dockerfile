FROM ccr.ccs.tencentyun.com/moensun-dev/amazoncorretto:11.0.17-alpine3.16
MAINTAINER "bane" <fengxiaotx@163.com>
ENV container docker
ENV TZ=Asia/Shanghai
RUN apk add --update ttf-dejavu fontconfig
RUN mkdir /data
RUN chmod 777 -R /data
COPY ./serve/build/libs/app-serve.jar /workspace/app-serve.jar
RUN sh -c 'touch /workspace/app-serve.jar'

EXPOSE 80 8080
ENV JAVA_OPTS="-Xss512k -Xmx1g -XX:+HeapDumpOnOutOfMemoryError -server -XX:HeapDumpPath=./java_pid<pid>.hprof"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /workspace/app-serve.jar" ]