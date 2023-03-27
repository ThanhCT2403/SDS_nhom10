CREATE DATABASE workplace

CREATE TABLE Department(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100),
	location VARCHAR(100)
);

CREATE TABLE Employee(
	id INT PRIMARY KEY auto_increment,
   full_name VARCHAR(255),
   email VARCHAR(200),
   gender ENUM('Male','Female'),
   phone CHAR(15),
   cmnd VARCHAR(255),
   address VARCHAR(200),
   dateofbirth DATE,
   position ENUM('Manager','Staff','Intern'),
   salary double,
   tax INT,
   datejoin DATE,
   department_id INT,
   FOREIGN KEY(department_id) REFERENCES department(id)
)


CREATE TABLE ACCOUNT(
	username VARCHAR(50) unique,
	PASSWORD VARCHAR(50)
)
INSERT INTO account VALUES ('t1','123'),
                         ('t2', '123')account

SELECT COUNT(b.id) FROM department a JOIN employee b ON a.id = b.department_id WHERE a.id = 3      

SELECT * FROM employee WHERE department_id IS NULL

ALTER TABLE employee MODIFY COLUMN salary float