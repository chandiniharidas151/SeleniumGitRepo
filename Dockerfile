# Use an official OpenJDK runtime as a parent image
FROM openjdk:11

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the local code to the container image
COPY . .

# Download and install Chrome
RUN apt-get update && apt-get install -y wget && \
    wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb && \
    dpkg -i google-chrome-stable_current_amd64.deb && \
    apt-get install -f -y

# Download and install ChromeDriver
RUN wget https://chromedriver.storage.googleapis.com/94.0.4606.61/chromedriver_linux64.zip && \
    unzip chromedriver_linux64.zip && \
    mv chromedriver /usr/local/bin && \
    rm chromedriver_linux64.zip

# Install Maven
RUN apt-get install -y maven

# Build the project
RUN mvn clean install

# Run the Cucumber tests
CMD ["mvn", "test"]