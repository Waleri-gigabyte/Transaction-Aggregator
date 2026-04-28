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
```

### Example:

```text
GET /aggregate?account=02248
```

### Response:

```text
[
  {
    "id": "31969aef-ffbe-413a-8a94-bc920556a0d4",
    "serverId": "server-04",
    "account": "02248",
    "amount": "5120",
    "timestamp": "2023-12-24T00:02:31.886783206"
  }
]
```
