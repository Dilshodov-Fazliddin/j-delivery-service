# j-delivery-service

Backend service for a parcel delivery system. It manages consumers, their addresses,
merchants, parcels and tariff prices, calculates delivery cost based on parcel
dimensions, weight and the distance between two geographic points, and notifies
consumers by email about parcel events.

## Tech stack

- **Java 17**
- **Spring Boot 4.0.1** — Web MVC, Data JPA, Security, Validation
- **PostgreSQL** as the database
- **Flyway** for database migrations
- **MapStruct** for DTO ↔ entity mapping
- **Lombok** to reduce boilerplate
- **Spring `RestClient`** for outbound calls to the notification service
- **springdoc-openapi** for Swagger UI / OpenAPI documentation
- **Gradle** as the build tool

## Project structure

```
src/main/java/uzumtech/jdeliveryservice
├── component        # NotificationAdapter — outbound email notifications
├── config           # RestClient, Security and Jackson configuration
├── constant         # Constants and enums (TariffType, ParcelStatus, Error, ...)
├── controller       # REST controllers
├── dto              # request / response / error DTOs (Java records)
├── entity           # JPA entities (+ BaseEntity with auditing fields)
├── exception        # custom exceptions
├── handler          # global REST exception handler + RestClient error handler
├── mapper           # MapStruct mappers
├── repository       # Spring Data JPA repositories
├── service          # service interfaces + impl
└── utils            # GeoUtils (Haversine distance), MessageBuilder
```

## Domain model

- **Consumer** — a customer; has a unique email and phone number, and one address.
- **Address** — belongs to a consumer.
- **Merchant** — the sender/origin of a parcel.
- **Parcel** — created for a consumer; references a merchant and an address. Has a
  `ParcelStatus` lifecycle (`CREATED → SEARCHING_COURIER → PICKED_UP → IN_TRANSIT →
  DELIVERED`, plus `CANCELLED` / `FAILED`).
- **Price** — the base price for a `TariffType`.

### Tariff rules

Cost is derived from the tariff (`START`, `ECONOMY`, `COMFORT`, `PRIORITY`). Each
tariff has a maximum size (length + width + height), maximum weight, and a price per
kilometer (see `TariffTypeRule`):

| Tariff   | Max size | Max weight | Price / km |
|----------|---------:|-----------:|-----------:|
| START    |      150 |         20 |       1000 |
| ECONOMY  |      450 |         40 |       1200 |
| COMFORT  |      750 |         60 |       1500 |
| PRIORITY |     1050 |         70 |       2000 |

The final price is `basePrice(tariff) + distanceKm * pricePerKm`, where the distance
is the Haversine ("as the crow flies") distance multiplied by a road coefficient of
`1.2`.

## Configuration

The application is configured entirely through environment variables (see
`src/main/resources/application.yml`):

| Variable                        | Description                              | Default |
|---------------------------------|------------------------------------------|---------|
| `DB_URL`                        | JDBC URL of the PostgreSQL database      | —       |
| `DB_USERNAME`                   | Database username                        | —       |
| `DB_PASSWORD`                   | Database password                        | —       |
| `DB_SCHEMA`                     | Schema used by Flyway                    | —       |
| `SERVER_PORT`                   | HTTP port                                | `8086`  |
| `NOTIFICATION_MAIL_URL`         | URL of the notification service          | —       |
| `NOTIFICATION_MERCHANT_LOGIN`   | Basic-auth login for the notifier        | —       |
| `NOTIFICATION_MERCHANT_PASSWORD`| Basic-auth password for the notifier     | —       |

## Running locally

### Prerequisites

- JDK 17
- A running PostgreSQL instance

### Steps

1. Set the environment variables listed above. For example (PowerShell):

   ```powershell
   $env:DB_URL = "jdbc:postgresql://localhost:5432/delivery"
   $env:DB_USERNAME = "postgres"
   $env:DB_PASSWORD = "postgres"
   $env:DB_SCHEMA = "public"
   $env:NOTIFICATION_MAIL_URL = "http://localhost:9000/notifications"
   $env:NOTIFICATION_MERCHANT_LOGIN = "login"
   $env:NOTIFICATION_MERCHANT_PASSWORD = "password"
   ```

2. Run the application:

   ```bash
   ./gradlew bootRun
   ```

   On Windows:

   ```powershell
   .\gradlew.bat bootRun
   ```

Flyway runs the migrations from `src/main/resources/db/migration` on startup.

### Build & test

```bash
./gradlew build      # compile, run tests and assemble the jar
./gradlew test       # run tests only
```

## API documentation

Once the application is running, the interactive OpenAPI documentation is available at:

- Swagger UI: `http://localhost:8086/swagger-ui.html`
- OpenAPI spec: `http://localhost:8086/v3/api-docs`

### Endpoints overview

Base path: `/api/delivery`

| Method | Path                          | Description                                   |
|--------|-------------------------------|-----------------------------------------------|
| POST   | `/consumers`                  | Create a consumer                             |
| PATCH  | `/consumers/{id}`             | Update a consumer (partial)                   |
| DELETE | `/consumers/{id}`             | Soft-delete a consumer (sets `active=false`)  |
| POST   | `/addresses/{consumerId}`     | Create an address for a consumer              |
| PATCH  | `/addresses/{id}`             | Update an address (partial)                   |
| DELETE | `/addresses/{id}`             | Delete an address                             |
| GET    | `/addresses/consumer/{id}`    | Get the address of a consumer                 |
| POST   | `/merchant`                   | Create a merchant                             |
| GET    | `/merchant/info/{name}`       | Get a merchant by name                        |
| POST   | `/parcels/{consumerId}`       | Create a parcel (calculates price, notifies)  |
| PATCH  | `/parcels/{parcelId}`         | Update a parcel (partial)                     |
| PUT    | `/parcels/{parcelId}`         | Update parcel status (`?parcelStatus=`)       |
| DELETE | `/parcels/{parcelId}`         | Soft-delete a parcel (sets `active=false`)    |
| GET    | `/parcels/consumer/{id}`      | List active parcels of a consumer (paged)     |
| POST   | `/prices`                     | Create a price for a tariff                   |
| PUT    | `/prices/{id}`                | Update a price                                |
| DELETE | `/prices/{id}`                | Soft-delete a price (sets `active=false`)     |
| GET    | `/prices`                     | List active prices (paged)                    |
| GET    | `/prices/deleted`             | List soft-deleted prices (paged)              |
| GET    | `/bill/calculate`            | Calculate delivery cost for a parcel          |

## Error handling

All errors are translated into a consistent JSON shape by `GlobalExceptionHandler`.
Validation failures return a `400` with the list of field errors; business and
external errors are mapped to their respective HTTP statuses. Error codes are defined
in the `Error` enum.

## Notifications

When a parcel is created or its status changes, `NotificationAdapter` sends an email
through the external notification service using HTTP Basic authentication. Outbound
HTTP errors are surfaced via `RestClientExceptionHandler` as `HttpClientException` /
`HttpServerException`.
