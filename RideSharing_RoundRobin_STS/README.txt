RIDE SHARING PLATFORM - ROUND ROBIN LOAD BALANCING
===================================================

This is a fresh multi-project Spring Boot application intended for import into
Spring Tool Suite (STS). It demonstrates Eureka service discovery, API Gateway
routing, and client-side round-robin load balancing across three ride-service
instances.

TECHNOLOGY
----------
Java 21
Spring Boot 3.5.5
Spring Cloud 2025.0.0
Spring Cloud Netflix Eureka
Spring Cloud Gateway
Spring Cloud LoadBalancer
Maven

PROJECTS AND PORTS
------------------
01-eureka-server  : Eureka registry       : 8761
02-ride-service   : Ride service instances: 8081, 8082, 8083
03-rider-service  : Rider service         : 8084
04-ride-gateway   : API Gateway           : 8000

IMPORT INTO STS
---------------
1. Extract the ZIP file.
2. Open STS.
3. Select File > Import > Maven > Existing Maven Projects.
4. Browse to the extracted RideSharing_RoundRobin_STS folder.
5. Select all four project folders (or import each project folder separately).
6. Click Finish and wait for Maven dependencies to download.
7. If required: right-click each project > Maven > Update Project.

START THE APPLICATIONS IN THIS ORDER
------------------------------------
1. Start 01-eureka-server:
   Right-click EurekaServerApplication.java > Run As > Spring Boot App.
   Open http://localhost:8761 to view the Eureka dashboard.

2. Start the first 02-ride-service instance:
   Run RideServiceApplication.java > Run As > Spring Boot App.
   It uses port 8081 by default.

3. Start two additional ride-service instances:
   In STS, open Run > Run Configurations.
   Select the Spring Boot App launch configuration for RideServiceApplication.
   Duplicate the launch configuration twice.
   For the second instance, select Arguments and enter in Program arguments:
       --server.port=8082
   Click Apply and Run.
   For the third instance, enter:
       --server.port=8083
   Click Apply and Run.
   Confirm Eureka shows three RIDE-SERVICE instances with ports 8081, 8082, 8083.
   Do not start the second/third instances with the default 8081 port.

4. Start 03-rider-service:
   Run RiderServiceApplication.java > Run As > Spring Boot App.
   It starts on port 8084.

5. Start 04-ride-gateway:
   Run RideGatewayApplication.java > Run As > Spring Boot App.
   It starts on port 8000.

POSTMAN TESTING
---------------
Import Postman_Ride_Sharing_Collection.json into Postman.

Or create these requests manually:

A. GET http://localhost:8000/rides
   Click Send several times. Responses should show the ride service instance
   port, for example:
       Ride Service Response from Port: 8081
       Ride Service Response from Port: 8082
       Ride Service Response from Port: 8083
   The first port may vary. Repeated requests should be distributed across
   the healthy registered instances.

B. GET http://localhost:8000/riders
   Expected response:
       Rider Service Running on Port: 8084

DIRECT SERVICE CHECKS
---------------------
GET http://localhost:8081/rides
GET http://localhost:8082/rides
GET http://localhost:8083/rides
GET http://localhost:8084/riders

EXPECTED RESULT
---------------
Requests sent to /rides through the API Gateway are load-balanced among the
three registered RIDE-SERVICE instances. Requests sent to /riders are routed
to the RIDER-SERVICE instance. Eureka provides service discovery.

TROUBLESHOOTING
---------------
- Ensure the Eureka server is running before starting clients.
- Ensure all four project dependencies finish resolving in Maven.
- Verify STS uses JDK 21: Window > Preferences > Java > Installed JREs.
- If a port is occupied, stop the other process using that port.
- If the Gateway returns 503, check Eureka and verify all service instances
  are registered and healthy.
- If Maven dependencies do not refresh, right-click project > Maven > Update
  Project, then Project > Clean.
- Keep all services running while testing through port 8000.

NOTE
----
Round-robin selection is provided by Spring Cloud LoadBalancer's default
service-instance supplier when no custom strategy is configured. This is a
local demonstration; it does not include a database, authentication, or
persistent ride booking because those are outside this load-balancing task.
