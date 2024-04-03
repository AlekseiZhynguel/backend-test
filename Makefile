.PHONY: init
init:
	@echo "Starting database..."
	@docker-compose up -d --wait
	@echo "Database initialized!!"
	@mvn generate-sources