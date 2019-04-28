# Library-Management-System
Spring MVC and Hibernate application to manage functionalities in a Library
Summary:
The project implements functionalities of Library involving admin and the user role. It is a Spring MVC application that uses hibernate to persist the POJOs to the MySQL database. The application allows communication between the library user and the library user account.
The application is completely menu driven and user friendly. There are various functionalities that are implemented that shows the flow of the data from admin to the user and back to the admin. All the data changes are stored in the tables with each and every step.
Functionalities:
Admin Role:
1.	Add new books to the library
2.	View the request of issued books
3.	Approve or Reject the issue request
4.	Return the issued book
User Role:
1.	Create an account in the library as a library user.
2.	Sign in only after account creation
3.	View all the available books in the library
4.	Raise a book issue request
5.	Check the status of the issued books with their current status
6.	View all the books issued by a particular user
Technologies used:
Frontend:
1.	HTML 5
2.	CSS
3.	JavaScript
4.	Bootstrap
5.	AngularJS
Backend:
1.	Spring MVC
2.	Hibernate
Database:
1.	MySQL Database
Description of Roles:
Admin Role: The admin will own the functionalities of adding the books to the library (with validations), taking action on the book issue request from the library user, a mail sent to the user on whether the book was issued to them or not, generation of pdf report on the books under circulation (available to the admin only), return the book when a user approaches to return them. 
Declarative security included in the admin section.
User Role: A signup page to create an account in the library. The user will be able to see all the books available to them. They can raise a request to issue any book, with a feature to view the status of the request. They will have a record of all the books issued by them.
