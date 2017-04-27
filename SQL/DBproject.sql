create database ExperientialLearningDB;
use ExperientialLearningDB;


Create table Category( Category_ID int auto_increment , CategoryType varchar(20), Primary Key (Category_ID) );
Create table Department(Department_ID int auto_increment, Department_Name varchar(20), primary key (Department_ID));
Create table Persons (PersonName varchar(20), Address varchar(30), Person_ID int auto_increment, primary key (Person_ID));

Create table Faculty(Faculty_ID int auto_increment, Department_ID int , Highest_Degree varchar(20),  Person_ID int , 
Position_Title varchar(20), primary key(Faculty_ID),
foreign key (Department_ID) references Department(Department_ID) on update restrict on delete restrict,
foreign key (Person_ID) references Persons(Person_ID) on update restrict on delete restrict);

Create table Programs( Program_ID int auto_increment, Program_Name varchar(50), Program_Type varchar(50), Department_ID int, primary key (Program_ID), 
foreign key (Department_ID) references Department(Department_ID) on update restrict on delete restrict);

create table Student(Student_ID int auto_increment, Person_ID int, StudyingYear int, Minor_code int, 
Major_code int, OnCampus char, Faculty_ID int, primary key(Student_ID),
foreign key (Person_ID) references Persons(Person_ID) on update restrict on delete restrict,
foreign key (Faculty_ID) references Faculty(Faculty_ID) on update restrict on delete restrict,
foreign key (Minor_code) references Programs(Program_ID) on update restrict on delete restrict,
foreign key (Major_code) references Programs(Program_ID) on update restrict on delete restrict);

Create table Campus_Events( Event_ID int auto_increment, Department_ID int, Category_ID int, Location varchar(20), Description varchar(50), StartDate date, EndDate date, 
StartTime time, EndTime time, Primary Key (Event_ID),
foreign key (Department_ID) references Department(Department_ID) on update restrict on delete restrict,
foreign key (Category_ID) references Category(Category_ID) on update restrict on delete restrict);

Create table Student_Events(Student_ID int , Event_ID int, primary key(Student_ID, Event_ID),
foreign key (Student_ID) references Student(Student_ID) on update restrict on delete restrict,
foreign key (Event_ID) references Campus_Events(Event_ID) on update restrict on delete restrict);
