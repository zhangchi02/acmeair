FROM swr.cn-north-1.myhuaweicloud.com/op_svc_cse/openjdk:8-jre-alpine

RUN mkdir -p /maven/acmeair/

COPY ./*-exec.jar  /maven/acmeair/

ENTRYPOINT java $JAVA_OPTS -jar /maven/acmeair/$JAR_NAME-exec.jar
