# Library Management System (Java)

A console-based library system that manages books and members,
with persistent storage across multiple program runs.

## Summary

This application was developed as a final assignment for CS 1061
(Computer Science II – Object-Oriented Programming in Java) at
Front Range Community College (Colorado). It demonstrates object-
oriented design, a menu-driven interface, and persistent data storage.

## Features

- Add books and multiple copies
- Register members with generated IDs
- Borrow / return books with checkout codes
- Track due dates and overdue items
- Save and restore data between runs

## Usage

1. Compile with a Java Development Kit
2. Run `LibraryManagementSystem`
3. Follow the console menu
4. Library data saves automatically on exit

## Design

Data is persisted using Java serialization to preserve object
state across multiple executions. Member IDs use a generated
suffix; collisions are unlikely for typical use.

## License

Provided for educational and portfolio use.
