FROM openjdk:11-jre-slim

WORKDIR /app

# 호스트 머신의 JAR 파일을 컨테이너의 /app 디렉토리로 복사
COPY build/libs/Factory_Emission-0.0.1-SNAPSHOT.jar /app/
COPY src/main/resources/application.properties /app/

# JAR 파일 실행 명령어
CMD ["java", "-jar", "Factory_Emission-0.0.1-SNAPSHOT.jar"]