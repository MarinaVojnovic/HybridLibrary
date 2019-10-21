# Hybrid Library REST API web application

Implement Hybrid Library sample application with following features:

* Application users should be populated automatically on application startup
* Application users can have different role
* User password should be hashed
* Admins can fetch registered user(s) (password should not be exposed)
* Admins can manage books (create, update, delete), whereas both admins and regular users can fetch books
* Admins can upload book cover image
* Book cannot be deleted if there are rented copies
* Admins and regular users can rent/return books if there are available copies
* Book rent period should be configurable
* Admins can fetch most rented book
* Admins can fetch overdue book returns
* All implemented endpoints should be secured
* Use Swagger to document endpoints
* Use Logback to define configurable log files location and time & size based rolling policy

### Acceptance criteria

I want to have an application that will help me manage books in my library. The library needs to be managed by multiple users with different roles. There needs to be an administrator role/user who manages other users but also manage books. There needs to be a librarian role/user who manages the book rental proces. The library contains books with description and cover images, but books can have multiple pysical copies (the copies are identified either by isbn, or some marking on the book copy itself). If the book has a copy that is still rented, it can't be deleted. The rent period is same for all books, and it can be configured as a global setting for the entire library. There needs to be an endpoint that helps librarians to see which books are rented and overdue with return. 

### Technical details

* Use database (in memory database like H2 is fine)
* The users should be automatically populated (admin and librarian)
* Credentials (passwords) of users need to be hashed and not in plain text
* Code should be covered with unit tests
* Endpoints need to be documented (Swagger)
* Logging needs to be set up (Logback)

### Way of working

* Work must be reflected with issue in github (create ticket for every task)
* Work is organized on Kanban board, all tickets start in "to do" and are progressed to "in progress" and finally into "review"
* Each ticket must be on a separate branch, after work is done, ticket is moved into "review", pull request is created and is accepted by product owners (mentors)
* Ticket is moved to "done" by mentors after review is finished, and the code is approved. When ticket is moved to "done" the pull request is merged.

### Resources

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Security](https://spring.io/projects/spring-security)
* [Maven](https://maven.apache.org/)
* [H2 database](https://www.h2database.com/html/main.html)
* [Lombok](https://projectlombok.org/)
* [Logback](https://logback.qos.ch/)
* [Swagger](https://swagger.io/)
