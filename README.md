# White Water Airline

An application that simulates the work of a real airline. The final project of EPAM Java Web Development training.

## Table of contents

- [Introduction](#introduction)
- [Technologies](#technologies)
- [Roles](#roles)
- [Database schema](#database-schema)

## Introduction

The application has several roles: Administrator, Manager and User.  
The administrator can create and cancel flights, add new and edit existing aircraft and routes. Also edit user information (name and surname).  
The dispatcher can assign a crew to a flight, change it and remove it if necessary.  
The user can register, log in, track the list of flights assigned to him and confirm his participation in them.  
When a new flight is created, canceled or crews are created, the application sends email notifications to dispatchers and users.

## Technologies

- Java 9
- Jakarta EE 9
- Bootstrap 5.0.2
- JQuery 3.6.0
- Tomcat 10.0.6

## Roles

The application has next roles:

- Administrator
- Dispatcher
- User
- Guest

### Administrator

- Creation of a new aircraft, route and flight.
- Editing information about an aircraft, route or flight.
- Flight cancellation.
- View lists of aircraft, routes, crews, users and flights.
- Viewing flight information
- Viewing the list of crews to which the employee is assigned.
- Search for flights

### Dispatcher

- Creation of a new crew.
- Editing an existing crew.
- Crew removal.
- Viewing flight information
- Viewing the list of crews to which the employee is assigned.
- Search for flights

### User

- Registration in the application
- Viewing the list of crews to which the employee is assigned
- Confirmation of participation in the crew
- View flight and crew information
- Search for flights

### Guest

- View information about the company and its contacts
- Search for available company flights

## Database schema

![](https://imgur.com/a/0E63L7w "DB Schema")