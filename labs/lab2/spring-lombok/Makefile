all: clean


clean:
	mvn clean

compile:
	mvn compile

run: compile
	mvn spring-boot:run

build:
	mvn package

run-jar:
	java -jar target/demo-docker-1.0.jar

# Docker

docker-build: build
	docker build -t demo-docker .
	docker images

docker-run: docker-build
	docker run --name demo-docker1 -td -p 81:8080 demo-docker
	docker ps

docker-clean:
	docker stop spring-hello
	docker rm spring-hello
	docker rmi spring-hello

docker-shell:
	docker exec -it demo-docker1 bash

