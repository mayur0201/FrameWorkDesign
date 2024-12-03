FROM openjdk:11-jre-slim

# Install Docker CLI
RUN apt-get update && apt-get install -y docker.io && apt-get clean

# Set the working directory
WORKDIR /app

# Copy your project files to the container
COPY . .

# Install Maven (if not already included in your project)
RUN apt-get update && apt-get install -y maven

# Ensure scripts are executable
RUN chmod +x start_dockerGrid.sh stop_dockerGrid.sh

# Run the tests
CMD ["sh", "-c", "mvn test"]
