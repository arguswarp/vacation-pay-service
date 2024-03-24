# Vacation Pay Service App

## Description
This app provides simple api to get the amount of vacation pay.
It is calculated by period of days or by period between two dates in format dd.MM.yyyy

## Installation
First clone this repository to your local machine:
```
git clone
```
Next add **.env** file to the root folder of the project. It must contain environment variables listed below:
```
DB_URL=jdbc:postgresql://vps-db/vacation_pay_service_db
DB_USERNAME=<database username>
DB_PASSWORD=<password for database user>
```
You must have docker and docker compose installed.

Finally, run:
```bash
docker compose up -d
```
This command will build new image from the [Dockerfile](/Dockerfile) for the app and starts containers with app and database.

## Usage
If run on default port 8080 go [here](http://localhost:8080/swagger-ui/index.html#/) for swagger docs
