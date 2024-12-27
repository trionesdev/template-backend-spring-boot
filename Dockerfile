FROM ccr.ccs.tencentyun.com/moensun-dev/amazoncorretto:21.0.3-alpine3.19
MAINTAINER "bane" <fengxiaotx@163.com>
ENV container docker
ENV TZ=Asia/Shanghai
RUN mkdir /data
RUN chmod 777 -R /data
COPY ./serve/target/backend-serve.jar /workspace/backend-serve.jar
RUN sh -c 'touch /workspace/backend-serve.jar'

EXPOSE 80 8200
ENV JAVA_OPTS="-Xss512k -Xmx1g -XX:+HeapDumpOnOutOfMemoryError -server -XX:HeapDumpPath=./java_pid<pid>.hprof"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /workspace/backend-serve.jar" ]