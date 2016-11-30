[![Build Status](https://travis-ci.org/daniel-barral/address-lookup-api.svg?branch=master)](https://travis-ci.org/daniel-barral/address-lookup-api)

# Address Lookup API

Docker API service to query for addresses based on their Eircode/Postcode or address fragment.

## Requirements

This project uses Java 8, Maven, Spring Boot, Redis and Docker.

## Installation

- Build Docker image:

mvn package docker:build

- Start Redis with persistent storage:

$ docker run --name some-redis -d redis redis-server --appendonly yes

- Start app:

$ docker run --name some-app --link some-redis:redis -d -p 8080:8080 -t danielbarral/addresslookup

## Testing client

You can access a testing client at:

http://localhost:8080/client.html

or

http://192.168.99.100:8080/client.html
(if running from virtual box - change jedisServer in application.properties)

