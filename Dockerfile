FROM openjdk:11-jdk-slim

# Install required dependencies
RUN apt-get update && apt-get install -y \
    docker.io \
    maven \
    curl \
    bash && \
    apt-get clean

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Ensure the start and stop scripts are executable
RUN chmod +x /app/start_docker.sh /app/stop_docker.sh

# Set the Maven dependency cache to speed up builds
RUN mvn dependency:go-offline -B

# Run the tests
CMD ["sh", "-c", "mvn test"]
