# DOCTOR ANYWHERE FRESHER ASSIGNMENT - LÊ NHẬT MINH

## **I. Tools preparation**

- Java JDK 11
- Docker Desktop
- IntelliJ IDEA ( or simliar suitable IDEA)
- DBeaver ( for importing sql script)
- Postman ( for testing API)

## **II. Tasks completion**

Based on the requirements in the file, I have completed all the requirements and also bonus points:

1. Create a new Spring Boot project using your favourite IDE. Use Maven or Gradle to manage dependencies - 100% Done.
2. Define a simple data model for a "Task" object that includes the following properties: id, title, description, completed - 100% Done.
3. Implement the following RESTful endpoints:

- GET /tasks: Get a list of all tasks - 100% Done.
- POST /tasks : Create a new task - 100% Done
- GET /tasks/{id} : Get a single task by ID - 100% Done.
- PUT /tasks/{id} : Update a task by ID - 100% Done.
- DELETE /tasks/{id} : Delete a task by ID - 100% Done.

4. Use MySQL for persistence - 100% Done.
5. Test your API using Postman or any other REST client to ensure that it works as expected - 100% Done.
6. Use git as the version control for your project - 100% Done

### Bonus points

- Implement error handling for each endpoint - 100% Done.
- Use Spring Data JPA to store the task data in a MySQL database - 100% Done.
- Use Spring Security to add authentication and authorization to the API - 100% Done.
- Can run the application in a container - 100% Done.

## **III. How to run the project**

### Note: make sure that port 9090 and port 3308 is free to use

### Link Git project: https://github.com/minhnhatle104/DA_Assignment

**1. Run without container**

- Open Docker Desktop, using cmd or any similar terminal, run those script:

```
docker pull mysql
docker run --name mysqldb -e MYSQL_ROOT_PASSWORD=admin123 -d -p 3308:3306 mysql:latest

```

- Extract zip file, inside zip file there are folder **lnminh_daassignment** and files: **da_assignment.sql** and **README.md**.
- Open DBeaver, choose MYSQL Driver, connect to MySQL docker:

```
port=3308
username=root
password=admin123
```

- Import **da_assignment.sql** and run sql script inside DBeaver.
- Open IntelliJ IDEA, import project **lnminh_daassignment** into IntelliJ IDEA.
- Run project by press arrow button on the top or press Shift+F10.
- API of task will be accessed by base url: **http://localhost:9090/api**

**2. Run with container**

- Start Docker Desktop.
- Extract zip file, inside zip file there are folder **lnminh_daassignment** and files: **da_assignment.sql** and **README.md**.
- Open IntelliJ IDEA, import project **lnminh_daassignment** into IntelliJ IDEA.
- On the left side of IntelliJ IDEA, choose Maven -> Click Toogle Skip Tests mode ( icon lightning) -> Choose **clean** and **package** inside **lifecycle** -> Choose Run Maven Build ( icon arrow)
- Using cmd or similar terminal, cd to project **lnminh_daassignment**.
- At the root of project, running this script:

```
docker-compose up -d --build
```

- API of task will be accessed by base url: **http://localhost:9090/api**

## **IV. API Specification**

All of below url endpoints starting with prefix: **http://localhost:9090/api**

**1. POST /users/signup** : Sign up an account for user

- Data request:

```
{
    "username": string, ( maximum 255 characters)
    "password": string, ( maximum 255 characters)
    "role": "ROLE_USER" or "ROLE_ADMIN" ( must have prefix ROLE_ before USER and ADMIN)
}
```

**2. POST /users/signin** : Sign in to receive jwt token for user

- Data request:

```
{
    "username": string, ( maximum 255 characters)
    "password": string ( maximum 255 characters)
}
```

**3. GET /tasks** : Get a list of all tasks

- Data request:

```
Headers: contains field Authorization with an account role ROLE_USER or ROLE_ADMIN
Authorization: Bearer token
```

**4. POST /tasks** : Create a new task

- Data request:

```
Headers: contains field Authorization with an account role ROLE_ADMIN
Authorization: Bearer token
```

```
{
    "title": string, ( maximum 255 characters)
    "description": string, ( maximum 255 characters)
    "completed": true or false
}
```

**5. GET /tasks/{id}** : Get a single task by ID

- Data request:

```
Headers: contains field Authorization with an account role ROLE_USER or ROLE_ADMIN
Authorization: Bearer token
```

**6. PUT /tasks/{id}** : Update a task by ID

- Data request:

```
Headers: contains field Authorization with an account role ROLE_ADMIN
Authorization: Bearer token
```

```
{
    "title": string, ( maximum 255 characters)
    "description": string, ( maximum 255 characters)
    "completed": true or false
}
All of these fields can send or can send each of them. It depends on the needs of user
```

**7. DELETE /tasks/{id}** : Delete a task by ID

- Data request:

```
Headers: contains field Authorization with an account role ROLE_ADMIN
Authorization: Bearer token
```

### **Base Response for API endpoints**

```
{
    "status": int ( status code)
    "message": string ( information about action of an api endpoint)
    "data": object ( data send back to client)
}
```
