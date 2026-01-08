# 📖 Library Management System (Java)

A console-based library system for managing books and members, with persistent storage across multiple program runs.

## Summary

This application was developed as a final assignment for CS 1061 (Computer Science II – Object-Oriented Programming in Java) at Front Range Community College (Colorado). It demonstrates object-oriented design, a menu-driven interface, and persistent data storage.

## Features

- Add books with support for multiple copies
- Register members with generated IDs
- Borrow and return books using checkout codes
- Track due dates and overdue items
- Save and restore application data between runs

## Usage

1. Compile with a Java Development Kit
2. Run `LibraryManagementSystem`
3. Follow the console menu
4. Library data saves automatically on exit

## Design

Data is persisted using Java serialization to preserve application state across multiple executions. Member IDs are generated using a name-based prefix and random suffix; while uniqueness is not strictly guaranteed, the available identifier space is sufficient for the intended scale of the project.

## License

Provided for educational and portfolio use.
