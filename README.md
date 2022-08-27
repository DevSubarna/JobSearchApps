### Entities

##### 
    1) Job (Salary, title, app_id(Application id), company_id(Client id))
    2) Application(Date, Resume version, job_id)
    3) Skill(description, experience, language, name, job_id)
    4) Company(name, addr_id)
    5) Client(mission, reason, website, recruiter_id)
    6) Recruiter(id)
    7) Address(street, city, state, zipcode)
    8) ScreeningInterview(date, email, phonenumber, name, result)
    9) TechnicalInterview(date, email, phonenumber, questions, duration, location)
    10) HiringManagerInterview(date, email, phonenumer, startDate, teamsize)

### Profiles

#####
    1) Development (H2 in memory Database) -> application-development.properties 
    2) Production (MySql) -> application-production.properties 

##### Can be change in application.properties file by setting the value 
    spring.profiles.active=production

### JMS

##### Use JMS for send message for any CRUD operation occurs for Entity. This applied on Job Entity. Topic name is cs544Queue and uses Pub/Sub model.
    springJms.cs544Queue=cs544Queue

### AOP

##### Use AOP for logging if any delete operation happened in any controller. Added Before and After log for each deletion operations.

### Error Handling

##### Customized error handling added. Created NotFoundException to handle if any record not found in database.

### Jackson 

##### Use for conversion of XML and Json. 
    Add in Request Header "Accept" -> "application/xml" to get xml response.
    For Example: The output is like 
        <Address>
            <id>2</id>
            <street>500 South</street>
            <city>Captown</city>
            <state>California</state>
            <zipcode>45789</zipcode>
        </Address>

### Unit Testing

##### created unit test cases for service layers for each entity.

### API Documentation

##### Address: 
    1) Create: 
        url: localhost:8080/portal/address/create 
        body: {
            "street":"10 South",
            "city": "Captown",
            "state": "California",
            "zipcode": "15896"
            }
        Http method: POST
    2) Find: 
        url: localhost:8080/portal/address/2
        Http method: GET
    3) Edit: 
        url: localhost:8080/portal/address/edit/2
        Http method: PUT
        Body: {
            "street":"500 South",
            "city": "Captown",
            "state": "California",
            "zipcode": "15896"
        }
    4) Delete:
        url: localhost:8080/portal/address/delete/29

##### Job
    urls: 
        /portal/job/create
        /portal/job/edit/2
        /portal/job/
        /portal/job/2
        /portal/job/edit/2/skill
        /portal/job/edit/2/company
        /portal/job/edit/2/application
        /portal/job/delete/2
        /portal/job/delete

##### Skill
    urls:
        /portal/skill/new
        /portal/skill/2
        /portal/skill/edit/2
        /portal/skill/delete/2

##### Application
    urls: 
        /portal/application/new
        /portal/application/edit/2
        /portal/application/delete/2
        /portal/application/2

##### Client

##### Recruiter

##### Concurrency

    Use Optimistic locking in Job Enitiy as type version

##### Migration from Hibernate to EclipseLink

Add EclipseLink dependencies in pom.xml and add exclude hibernate from spring-boot-jpa dependency. 
Then added configuration file for eclipselink to get the properties as like persistence.xml from Java code. 