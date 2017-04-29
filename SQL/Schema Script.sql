drop database ExperientialLearningDB;

create database ExperientialLearningDB;

use ExperientialLearningDB;

create table DegreeDetails(Degree_ID int auto_increment, Degree_Level varchar(100), Degree_Year varchar(100),
primary key (Degree_ID));

Create table Category( Category_ID int auto_increment , CategoryType varchar(100), 
Primary Key (Category_ID) );

Create table Department(Department_ID int auto_increment, Department_Name varchar(100), 
primary key (Department_ID));

Create table Programs( Program_ID int auto_increment, Program_Name varchar(100), Program_Type varchar(100), Department_ID int, 
primary key (Program_ID), 
foreign key (Department_ID) references Department(Department_ID) on update restrict on delete restrict);

Create table Persons (PersonName varchar(100), Address varchar(100), Person_ID int auto_increment, 
primary key (Person_ID));

Create table Faculty(Department_ID int , Highest_Degree varchar(100),  Person_ID int , Position_Title varchar(100), 
primary key(Person_ID),
foreign key (Department_ID) references Department(Department_ID) on update restrict on delete restrict,
foreign key (Person_ID) references Persons(Person_ID) on update restrict on delete restrict);


create table Student(Person_ID int, Degree_ID int, Minor_code int, 
Major_code int, OnCampus char, Faculty_ID int, 
primary key(Person_ID),
foreign key (Degree_ID) references DegreeDetails(Degree_ID) on update restrict on delete restrict,
foreign key (Person_ID) references Persons(Person_ID) on update restrict on delete restrict,
foreign key (Faculty_ID) references Faculty(Person_ID) on update restrict on delete restrict,
foreign key (Minor_code) references Programs(Program_ID) on update restrict on delete restrict,
foreign key (Major_code) references Programs(Program_ID) on update restrict on delete restrict);

Create table Campus_Events( Event_ID int auto_increment, Department_ID int, Category_ID int, 
Location varchar(100), Description varchar(100), StartDate date, EndDate date, 
StartTime time, EndTime time, 
Primary Key (Event_ID),
foreign key (Department_ID) references Department(Department_ID) on update restrict on delete restrict,
foreign key (Category_ID) references Category(Category_ID) on update restrict on delete restrict);

Create table Student_Events(Person_ID int , Event_ID int, 
primary key(Person_ID, Event_ID),
foreign key (Person_ID) references Student(Person_ID) on update restrict on delete restrict,
foreign key (Event_ID) references Campus_Events(Event_ID) on update restrict on delete restrict);


