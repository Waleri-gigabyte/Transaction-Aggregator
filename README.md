# Transaction Aggregator

Spring Boot service that aggregates transaction data from two remote services.

## Features

- REST endpoint for transaction aggregation
- Fetches data from two remote transaction services
- Retry logic for temporary remote server failures
- In-memory caching by account number
- Asynchronous remote requests
- Sorting transactions by timestamp in descending order

## Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Cache
- Spring Async
- Gradle

## API

### GET `/aggregate`

Query parameter:

```text
account=<account_number>
