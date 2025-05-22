# fms-jdbc-demo
Demonstrate access MySQL database using Java and JDBC. Demo for ISP course 2023.

## Run MySQL as Docker 

Below are example scripts to set up MySQL using Docker. 

**Pull the MySQL 8.0 Docker image:**
```sh
docker pull mysql:8.0
```

**Run a new MySQL container:**
```sh
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:8.0
```

## Create Empty Database

Connect to MySQL server using MySQL Workbench or similar tool and create an empty database named `test01`.

## Database Tables

The following SQL script creates the necessary tables in the MySQL database:

```sql
CREATE TABLE FLIGHTS (
    FLIGHTNUMBER VARCHAR(10) PRIMARY KEY,
    NOOFSEATS INT NOT NULL,
    DEPARTUREDATE DATE NOT NULL
);

CREATE TABLE RESERVATIONS (
    IDRESERVATION INT AUTO_INCREMENT PRIMARY KEY,
    FLIGHTNUMBER VARCHAR(10),
    NOOFTICKETS INT NOT NULL,
    FOREIGN KEY (FLIGHTNUMBER) REFERENCES FLIGHTS(FLIGHTNUMBER)
);
```

## Run Application

Run application main class `MainApp`. 


