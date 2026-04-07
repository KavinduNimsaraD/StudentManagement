# Student Management System

A simple and efficient Desktop Application developed using Java (Swing) and MySQL to manage student and course details. 

## 🛠️ Prerequisites
Before running this application, ensure you have the following installed on your PC:
* **Java Runtime Environment (JRE)** (Java 8 or newer)
* **XAMPP / WAMP Server** (For MySQL Database)

## 🗄️ Database Setup Instructions

To successfully run this application, you must set up the local MySQL database first. Please follow these steps:

1. Open **XAMPP Control Panel** and start the **MySQL** module.
2. Open your web browser and go to `http://localhost/phpmyadmin`.
3. Go to the **"SQL"** tab at the top.
4. Copy and paste the following SQL script into the query box and click **"Go"**:

```sql
-- Create the Database
CREATE DATABASE IF NOT EXISTS school_db;
USE school_db;

-- Create Student Table
CREATE TABLE Student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT,
    contact VARCHAR(15)
);

-- Create Course Table
CREATE TABLE Course (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100) NOT NULL,
    duration VARCHAR(50)
);

```
Developed by: Kavindu Nimsara
