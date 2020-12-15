# qaa-assignment
Assignment for Software Engineer in Test Position

This is a simple Spring MVC Java Application for persisting new books and authors to a MySQL DB

Steps to set it up locally:

* Install docker-compose and navigate to docker folder executing
docker-compose up to bring up the database for the application
* Locate **application.properties** and change **spring.jpa.hibernate.ddl-auto = create** the first time to persist some initial test data.<br>
Change back the property to **update**
* Locate and run SpringBootDemoApplication to bring up the web application in your local environment
* Access the Web App from http://localhost:8080
