FROM maven:3.9.2 AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /app/src/
RUN mvn package -DskipTests

FROM amazoncorretto:17

WORKDIR /app
COPY --from=builder /app/target/wishlist-0.0.1-SNAPSHOT.jar .

# Define as variáveis de ambiente
ENV SPRING_DATA_MONGODB_URI=mongodb://mongodb:27017/luizalabs
ENV SPRING_DATA_MONGODB_DATABASE=luizalabs
ENV MONGODB_USERNAME=admin
ENV MONGODB_PASSWORD=mm236533

EXPOSE 8080

# Inicia a aplicação Java com as variáveis de ambiente
CMD ["sh", "-c", "java -jar -Dspring.data.mongodb.uri=$SPRING_DATA_MONGODB_URI -Dspring.data.mongodb.database=$SPRING_DATA_MONGODB_DATABASE -Dmongodb.username=$MONGODB_USERNAME -Dmongodb.password=$MONGODB_PASSWORD wishlist-0.0.1-SNAPSHOT.jar"]