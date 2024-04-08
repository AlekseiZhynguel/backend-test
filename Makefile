.PHONY: init
init:
	@echo "Starting database..."
	@docker-compose up -d --wait
	@echo "Database initialized!!"
	@mvn spring-boot:run

.PHONY: run
run:
	mvn spring-boot:run

.PHONY: install
install:
	@mvn clean install
