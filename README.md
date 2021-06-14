# employee-management
Hi

i have attached [employee-service-contract.json] --> the file generated from OPENAPI by following the URL 
 http://localhost:8090/v3/api-docs 
 and I formatted it in JSON file after success build to the project

please follow this instruction for success build to the project:

1-inside the root of the project issue command ---> [mvn clean install] or you can use [./mvnw] wrapper of spring boot 
by doing this you will generate the jar file and run unit test (for unit test i just put a smaple for integration test function and the rest of function will be easy so put just sample)

2-insdei the root of the project issue command ---> [docker-compoe up] and it will build the image using the existing Dockerfile then start the DB then our serivce 

you can test this project by :

1) curl -X POST -H "Content-Type: application/json" \
    -d '{"name":"testName","age":20,"contactList":[{"address":"42 london st","mobile":"01123456789"}]}' \
    http://localhost:8090/employee


2) PUT http://localhost:8090/employee/{employeeName}/{your-desired-state}
curl -X PUT -H "Content-Type: application/json" \
    http://localhost:8090/employee/testName/IN_CHECK

Or, you can use Postman
thanks
