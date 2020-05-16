FROM openjdk:8-jre-alpine
RUN mkdir -p /opt/visme/test-app
ADD ./* /opt/visme/test-app
WORKDIR /opt/visme/test-app
CMD java $JAR_OPTS -jar /opt/visme/test-app/*.jar
