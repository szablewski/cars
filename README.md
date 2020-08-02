# Cars appi
REST API, which will accept a file of the given format - *.csv
and save to the database.
Only lines that have a specific color should be saved in the database.
The color is given as a parameter of the HTTP request used to upload the file.

## Technologies
* Java 11
* Spring boot 2.3.2.RELEASE
* Thymeleaf
* H2

## Installation

```
$ cd .../cars
$ mvn spring-boot:install
```
Go to http://localhost:8080/api/cars
Upload csv file.
You can also go to http://localhost:8080/console and verify that the data from the file has been saved

