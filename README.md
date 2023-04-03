## Advertising service
REST service for managing listings for online advertising service to solve the SM360 Backend Tech Assignment from : https://github.com/sm360/backend-tech-assignment
### Get started

Docker version 23.0.2 or later.
SpringBoot version 2.7.7
Postgresql version 12.2

### Java application with Spring framework and a Postgres database

Project structure:
```
.
├── advertising_app
│   ├── Dockerfile
│   └── ...
├── advertising_db
│   └── schema-advertising.sql
├── advertising_doc
│   └── ...
├── docker-compose.yaml
└── README.md
```
[_docker-compose.yaml_](docker-compose.yaml)
```
services:
  advertising_app:
    build: advertising_app
    ports:
    - 8080:8080
  advertising_db:
    image: postgres:12.2
    ports:
      - 5432:5432
    ...
```
The compose file defines an application with two services `advertising_app` and `advertising_db`.
When deploying the application, docker compose maps port 8080 of the backend service container to port 8080 of the host as specified in the file and the port 5432 to the database service.
Make sure port 8080 and 5432 on the host is not already being in use.

## Deploy with docker compose

```
$ docker compose up -d
```

## Swagger 
To get documentation in OpenApi format call the endpoint. Also, you can find a postman collection inside of advertising-doc folder
```
localhost:8080/agency/api/v1/api-docs
```
### /agency/api/v1/create-dealer

#### POST
##### Summary:

Create a new Dealer

##### Description:

Create a new Dealer

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | Operation successfully executed |
| 400 | Wrong parameters or bad request |

### /agency/api/v1/get-all-dealer

#### GET
##### Summary:

Get all Dealer

##### Description:

Get all Dealer

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | Operation successfully executed |
| 400 | Wrong parameters or bad request |

### /agency/api/v1/set-tier-limit

#### POST
##### Summary:

Set the tier limit to publish listing

##### Description:

Set the tier limit to publish listing

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | Operation successfully executed |
| 400 | Wrong parameters or bad request |

### /agency/api/v1/create-listing/{dealerId}

#### POST
##### Summary:

Create a new listing with status draft default

##### Description:

Create a new listing with status draft default

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| dealerId | path | Information related to dealer | Yes | string (uuid) |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | Operation successfully executed |
| 400 | Wrong parameters or bad request |
### /agency/api/v1/update-listing/{listingId}

#### PUT
##### Summary:

Update the information of a listing to draft

##### Description:

Update the information of a listing to draft

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| listingId | path | Information related to the id listing | Yes | string (uuid) |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | Operation successfully executed |
| 400 | Wrong parameters or bad request |

### /agency/api/v1/un-publish-listing/{listingId}

#### PUT
##### Summary:

Change the status of a listing to draft

##### Description:

Change the status of a listing to draft

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| listingId | path | Information related to listing | Yes | string (uuid) |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | Operation successfully executed |
| 400 | Wrong parameters or bad request |


### /agency/api/v1/publish-listing/{listingId}

#### PUT
##### Summary:

Change the status of a listing to publish having the tier limit condition

##### Description:

Change the status of a listing to publish having the tier limit condition

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| listingId | path | Information related to listing | Yes | string (uuid) |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | Operation successfully executed |
| 400 | Wrong parameters or bad request |


### /agency/api/v1/get-all-listing-by-dealer-and-state/{dealerId}/{state}

#### GET
##### Summary:

To get the listings from a dealer and state

##### Description:

To get the listings from a dealer and state

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| dealerId | path | Information related to dealer | Yes | string (uuid) |
| state | quepathry | Information related to listing | Yes | string |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | Operation successfully executed |
| 400 | Wrong parameters or bad request |

