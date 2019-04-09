### Project Go Green 2019 @TUDelft
# Greendr

## The Greendr API
To support the logging of environment-saving actions, we built a Postgres-backed RESTful API that carbon-tracking apps can interface with for user data CRUD operations. Below are a few basic examples in Java using Spring. More information about the used paths and functions can be found in the [documentation of Greendr](https://thomw2o0o.github.io/)


## Starting the server
Before running the demo or the example code, the server must be started. As we use [Maven](https://maven.apache.org/download.cgi) for our project management, it is assumed that mvn is on your path. (if this command is not recognized, see [here](https://www.mkyong.com/maven/how-to-install-maven-in-windows/)). It is also assumed that you have [PostgreSQL](https://www.postgresql.org/download/) installed configured in accordance with the [application.properties](src/main/resources/application.properties) file. The default configuration of this application is as follows:
~~~
spring.datasource.url = jdbc:postgresql://localhost:5432/gogreen
spring.datasource.username = postgres
spring.datasource.password = 24601
spring.jpa.hibernate.ddl-auto = update
~~~
This can of course be changed to access a remote database or a different type of database. To start the server, simply run the following command in the root of the repository:
`mvn exec:java@server`

## Demo application
The code below comes from the demo application, which can be run by executing `mvn exec:java@demo`. The full code for this demo can be found [here](src/main/java/demo/DemoApplication.java)
### [Registration and Authentication](https://thomw2o0o.github.io/server/controller/AuthController.html)
As only the root and register paths can be accessed without proper authentication, any interaction with the API must be properly authenticated by either logging in using existing credentials or registring a new user.
~~~ java
RestTemplate template = new RestTemplate();
User user = new User("Mark","s3cretp4ssword");
try {
    user = template.postForObject( "http://localhost:8080/auth/register", new HttpEntity<>(user),User.class);
} catch (HttpClientErrorException exception) {
    System.out.println("The user already exists!");
}
//Set the proper basic authentication header for every request
template.getInterceptors().add(new BasicAuthenticationInterceptor(user.getName(), user.getPassword()));
template.postForEntity("http://localhost:8080/auth/login/" + user.getName(),new HttpEntity<>(user),User.class);
System.out.println(user.toString());
~~~
### [Getting a user](https://thomw2o0o.github.io/server/controller/UserController.html)
~~~ java
//... insert authentication ...
//Two ways to get a user
user = template.postForObject( "http://localhost:8080/users/byName/" + user.getName(), new HttpEntity<>(user),User.class);
User userById = template.postForObject( "http://localhost:8080/users/" + user.getID(), new HttpEntity<>(user),User.class);
~~~
### [Adding a generic feat](https://thomw2o0o.github.io/server/controller/FeatController.html)
In our API, feat objects represent the environment-saving actions of our users.
~~~ java
//... insert authentication ...
user = template.postForObject( "http://localhost:8080/users/byName/" + user.getName(), new HttpEntity<>(user),User.class);
Feat feat = new Feat(1,150,4, new Date(),user);
template.postForLocation( "http://localhost:8080/users/" + user.getID() + "/feats/new", new HttpEntity<>(feat));
User betterUser = template.getForObject( "http://localhost:8080/users/" + user.getID(),  User.class);
System.out.println("Points of Mark before feat:" + user.getPoints() + "\nPoints of Mark after feat:" + betterUser.getPoints());
~~~

## The Greendr app
To have a direct environmental impact, we also made an application that relies on the Greendr API for the persistence of the user data. It can be started using `mvn exec:java@client`

## Running the tests
Running the tests for Greendr using Maven is also very straightforward. Simply open a terminal at the repository's root and run `mvn test`
## Our goal
We set out to develop an app that inspires people to reduce their carbon footprint in an engaging, competitive way by applying tried and true techniques. We believe we succeeded in this aim.

## The team
### Dan Dan Berendsen (@dberendsen) StudentID: 4904982

<img src = "photos/IMG_6796.JPG" width = "150" height = "250">

### Pandey, Harshitaa (@hpandey) StudentID: 4780205

<img src = "photos/photo.jpg" width = "150" height = "200">

### Max Karsten (@mhkarsten) StudentID: 4943090

<img src = "photos/PasPhoto_Max_Karsten.jpg" width = "150" height = "200">

### Jason Bloom (@jsjgbloom) StudentID: 4719794

<img src = "photos/photo_4719791_Jason_Bloom.jpg" width = "150" height = "200">

### Thom van der Woude (@tbvanderwoude) StudentID: 4945727

<img src = "photos/IMG_20190215_183148.jpg" width = "150" height = "200">

## TL;DR
We made a client and server to make the world a greener place.