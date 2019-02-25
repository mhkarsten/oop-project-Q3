## REST: Representational State Transfer
- Basic idea of REST: there is a message format that allows client and server implementation to change without affecting the operation of the other REST system.

- REST systems interact through standard operations on resources, not commands (so not like the WDT MOVE_PIECE JSON commands for a game of chess).
### Hibernate Object-Relational Mapping
Used to handle the object-relational impedance mismatch problems inherent to incorperating a relational database into a OOP project.
### Spring Data Java Persistence API
-  Simplifies and generalizes your data access without cutting down on functionality.
-  Provides repositories for high-level data access.
-  Also allows you to just write your own SQL queries or data access code.
### Inversion of Control (IoC), or what does @Autowired mean?
The idea of having an object define its dependencies but having a container or framework create/manage these.

[More on inversion of control in Spring](https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring)
### Database auditing/entity versioning
When using an ORM, we still want to have something functionally equivalent to a database log which records all CRUD operations and allows for version control.
This is were it gets confusing: there are *three* ways of implementing auditing in a typical RESTful java application. You can use any one of the following:
- JPA
- Hibernate Envers
- Spring Data JPA
  
[Look here for more on auditing](https://www.baeldung.com/database-auditing-jpa)
### The real question: what are beans?
In Spring, these dependencies of components managed by the framework (see IoC) are called beans. We @annotate these beans and components containing beans, and set up a configuration file/class with metadata that defines the IoC container. 

[More on beans](https://www.baeldung.com/spring-bean)

Note that many of these things can be defined in both the beans.xml file and the java classes themselves, so pick and stick to either I'd suggest.

---------------------------------------

All the REST lingo in [this](https://www.callicoder.com/spring-boot-jpa-hibernate-postgresql-restful-crud-api-example/) article is why I felt like studying some relevant stuff.