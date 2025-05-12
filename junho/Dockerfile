# 베이스 이미지 설정
FROM openjdk:21-jdk-slim

# 작업 디렉토리를 설정
WORKDIR /app

# 파일 복사
COPY build/libs/book_spring_dev-1.0-SNAPSHOT.jar app.jar

# 커맨드 실행
CMD ["java", "-jar", "app.jar"]
