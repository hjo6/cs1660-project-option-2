FROM openjdk:8
WORKDIR /app
COPY . /app
RUN apt-get update && apt-get -y install libxext6 && apt-get -y install libxrender1 && apt-get -y install libxtst6
CMD ["java","-jar","projectGUI.jar"]
