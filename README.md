# ATMAssignment

This is Spring Boot application using REST endpoints.

Notes :

I have configure the serve port 9999. One can change by visiting the application.properties file.


Steps to run the application

1. User can download the project named Assignment in the local respository.
2. User can import the import the maven project using any IDE of choice, I have used STS.
3. User can update the project using Maven.
4. User can now build the project using command mvn clean install
5. User can run the application using below url

http://localhost:9999/api/withdraw?accountNumber=123456789&pin=1234&amount=100

http://localhost:9999/api/checkbalance?accountNumber=123456789

