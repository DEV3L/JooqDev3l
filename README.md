# JooqDev3l
jOOQ Example

##  What is jOOQ?
"jOOQ generates Java code from your database and lets you build type safe SQL queries through its fluent API."

http://www.jooq.org/

jOOQ, like all ORMs, is a database abstraction layer for your code. One thing I have liked so far about jOOQ is that it let's you control the underlying queries. I have used frameworks like Hibernate and JPA before, and they are nice in that it lets you treat manipulation of database tables like objects, but the statements generated are often ugly and inefficient.

## Dependancies
* git
* maven
 * comments in pom.xml explain various steps
* mySQL installed and running locally
 * authentication
  * user: root
  * password:
  * values can be changed to appropriate local configuration in pom.xml

## Project Highlights
* jOOQ 3.5
* Maven plugin utilization for project setup
 * Executes sql scripts to create schema and table if not exists
 * Generates jOOQ source files
 * Compiles after these two steps
* JUnit Test Coverage (Example Usage)
 * AuthorManagerIT
* Maven Dependencies Used
 * junit
 * log4j
 * jooq
 * javax validation
 * mysql driver

## Eclipse Project setup
* Use git clone to pull project into workspace directory
 * git clone https://github.com/DEV3L/JooqDev3l.git
* Use maven to resolve Eclipse dependencies
 * mvn eclipse:eclipse
  * generates db and jooq code
* Import 'JooqDev3l' as existing project into Eclipse 
* Use maven to run integration tests and install
 * mvn clean install
