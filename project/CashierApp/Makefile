all: clean

clean:
	mvn clean

compile:
	mvn compile

build: compile
	mvn package

run: build
	echo Starting Spring at:  http://localhost:9090
	mvn spring-boot:run

docker-push:
	mvn package
	docker login
	docker build -t dattritat/spring-starbucks-cashier:v2.1 .
	docker push dattritat/spring-starbucks-cashier:v2.1
