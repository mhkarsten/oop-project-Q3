## REST: Representational State Transfer
- Basic idea of REST: there is a message format that allows client and server implementation to change without affecting the operation of the other REST system.

- REST systems interact through standard operations on resources, not commands (so not like the WDT MOVE_PIECE JSON commands for a game of chess).
## Hibernate Object Relational Mapping
Used to handle the object-relational impedance mismatch problems inherent to incorperating a relational database into a OOP project.
## Spring Data Java Persistence API
-  Simplifies and generalizes your data access without cutting down on functionality.
-  Provides repositories for high-level data access.
-  Also allows you to just write your own SQL queries or data access code.
## Inversion of Control (IoC)
The idea of having an object define its dependencies but having a container or framework create/manage these.
## The real question: what are beans?
In Spring, these dependencies of components managed by the framework are called beans. We @annotate these beans and components containing beans, and set up a configuration file/class with metadata that defines the IoC container.
 