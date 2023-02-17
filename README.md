# Find Best Matched Restaurants
This project is a simple application that searches for restaurants based on some filters.
The application is divided in a React front-end and a back-end Java API.

![Screen Shot 2023-02-17 at 12 19 51](https://user-images.githubusercontent.com/511398/219694052-72ab28e7-9853-44dc-ac01-2953d3dff44a.png)

## Running with Docker
The application can be run with Docker. To do that you just need to run the provided Makefile on the project root.
To run the back-end and front-end you can just run:

```
make up
```

This will run the back-end on port 8080 and the front-end on port 3000.

To shutdown the application you can just run:

```
make down
```

## Back-end

The following was used to build the back-end:
* OpenJDK 17
* Spring boot 3.0.2

### Building and running manually

To build and run the back-end you need [maven](https://maven.apache.org/) installed.

You can run the application locally on `http://localhost:8080/` by running:
```
mvn spring-boot:run
```

To build the project just run:
```
mvn pacakge
```
The application jar will be stored in the `target/` directory.

You can run the application jar with the following command:
```
java -jar restaurantsapi-0.0.1-SNAPSHOT.jar
```
This will stat the server on `http://localhost:8080/`.

### Running tests

The project has some simple JUnit tests, which can be run with the following command:
```
mvn test
```

### Application endpoints

The application has just one GET endpoint that searches for restaurants.
The restaurants endpoint is as follows:

```
GET /api/restaurants

Query parameters:
name       : string
rating     : integer
distance   : integer
price      : integer
cuisine    : string

```

For example, to get all the french cuisine restaurants with rate 3 or more, you could make the
following request:

```
GET /api/restaurants?cuisine=french&rating=3
```

## Front-end

The following was used to build the front-end:
* Node 16
* React 18.2
* Tailwind 3.2.6

### Building and running

To build and run the application you need `npm` installed. Before running or building the project you need to install all its dependencies, which can be done by running the following command:
```
npm ci
```

With the dependencies installed, you can start a dev server on `http://localhost:3000/` by running:
```
npm start
```
When running the dev server, the application will try to look for a back-end on `http://localhost:8080`.

To build the project just run:
```
npm run build
```
The build artifacts will be stored in the `build/` directory.
