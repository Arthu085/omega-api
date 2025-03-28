FROM eclipse-temurin:17-jdk AS builder

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para dentro do container
COPY . .

# Compila o projeto
RUN ./mvnw clean package -DskipTests

# Etapa Final - Cria uma imagem otimizada para rodar o JAR
FROM eclipse-temurin:17-jre AS runner

WORKDIR /app

# Copia apenas o JAR gerado para a imagem final
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta da API
EXPOSE 8080

# Define a variável de ambiente da porta
ENV PORT=8080

# Comando para rodar spring
ENTRYPOINT ["java", "-jar", "app.jar"]
