INSERT INTO degreedetails (Degree_Level, Degree_Year)
VALUES 
('Undergraduate','Freshmen'),
('Undergraduate','Sophomore'),
('Undergraduate','Junior'),
('Undergraduate','Senior'),
('Graduate','First Year'),
('Graduate','Second Year'),
('Graduate','Third Year'),
('Graduate','Fourth Year'),
('Graduate','Fifth Year');

INSERT INTO Category (CategoryType)
VALUES 
('Career Fair'),
('Research Symposium'),
('Pannel Discussion'),
('Tutoring Session'),
('Mock Interview'),
('Blood Drive'),
('Social'),
('Networking'),
('Sporting Event'),
('Performance');

INSERT INTO Department (Department_Name)
VALUES
('Computer Science'),
('Electrical Engineering'),
('Math'),
('Biology'),
('Chemistry'),
('English'),
('Education'),
('Art'),
('History'),
('Athletic');

INSERT INTO Programs (Program_Name, Program_Type, Department_ID)
VALUES
('Web Development', 'Major', 1),
('Data Science', 'Minor', 1),
('Electrical Engineering', 'Major', 2),
('Computer Science', 'Minor', 2),
('Math', 'Major', 3),
('Statistics', 'Minor', 3),
('Biology', 'Major', 4),
('Sciences', 'Minor', 4),
('Chemistry', 'Major', 5),
('English', 'Major', 6),
('Education', 'Minor', 7),
('Art', 'Major', 8),
('History', 'Major', 9),
('Athletics', 'Minor', 10);

INSERT INTO Persons (PersonName, Address)
VALUES
('Albus Dumbledore','001 Hogwarts'),
('Minerva McGonagall','002 Hogwarts'),
('Alastor Moody','003 Hogwarts'),
('Harry Potter','123 Hogwarts'),
('Hermione Granger','234 Hogwarts'),
('Ron Weasley','345 Hogwarts'),
('Dean Thomas','456 Hogwarts'),
('Ginny Weasley','567 Hogwarts'),
('George Weasley','678 Hogwarts'),
('Fred Weasley','789 Hogwarts'),
('Percy Weasley','890 Hogwarts'),
('Seamus Finnigan','901 Hogwarts'),
('Cedrick Diggory','111 HeDead'),
('Vernon Dursley','4 Privet'),
('Petuna Dursley','4 Privet'),
('Dudley Dursley','4 Privet'),
('Argus Fitch','543 Hogwarts'),
('Draco Malfoy','666 Hogwarts'),
('Pansy Parkinson','667 Hogwarts'),
('Rita Skeeter','005 Hogwarts');

INSERT INTO Faculty (Department_ID, Highest_Degree, Person_ID, Position_Title)
VALUES
(1,'PhD',1,'HeadMaster'),
(2,'PhD',2,'Professor'),
(3,'PhD',3,'Professor'),
(4,'PhD',4,'Associate Professor'),
(5,'PhD',5,'Associate Professor'),
(6,'PhD',6,'Associate Professor'),
(7,'PhD',7,'Associate Professor'),
(8,'PhD',8,'Associate Professor'),
(9,'PhD',9,'Associate Professor'),
(10,'PhD',10,'Associate Professor');



INSERT INTO Student (Person_ID, Degree_ID, Minor_code, Major_code, OnCampus, Faculty_ID)
VALUES
(11,1,2,1,'Y',1),
(12,1,4,3,'Y',2),
(13,2,6,5,'N',3),
(14,3,8,7,'N',4),
(15,2,11,9,'N',5),
(16,3,14,12,'Y',6),
(17,4,2,13,'Y',7),
(18,8,4,1,'N',8),
(19,9,6,3,'Y',9),
(20,6,8,5,'N',10);


INSERT INTO Campus_Events (Department_ID, Category_ID, Location, Description, StartDate, EndDate, StartTime, EndTime)
VALUES
(1, 3, 'HufflePuff Commons', 'Security Essentials', '2017-01-05', '2017-01-05', '8:00', '10:00'),
(1, 1, 'Great Hall', 'Computers & Engineering Jobs', '2017-03-05', '2017-03-05', '9:00', '13:00'),
(2, 1, 'Great Hall', 'Computers & Engineering Jobs', '2017-03-05', '2017-03-05', '9:00', '13:00'),
(3, 4, 'Muggle Studies', 'Derivatives', '2017-03-16', '2017-03-16', '11:00', '13:00'),
(5, 7, 'Chamber of Reception', 'Chemistry & English Gathering', '2017-03-18', '2017-03-18', '12:00', '13:00'),
(6, 7, 'Chamber of Reception', 'Chemistry & English Gathering', '2017-03-18', '2017-03-18', '12:00', '13:00'),
(4, 5, 'The Quad', 'Biologist Interview Prep', '2017-03-21', '2017-03-21', '8:00', '18:00'),
(7, 10, 'Music Classroom', 'The Language of Music', '2017-04-01', '2017-04-01', '8:00', '10:00'),
(9, 8, 'Room of Rewards', 'History Awards', '2017-04-02', '2017-04-02', '8:00', '10:00'),
(8, 2, 'The Lake', 'Discovering Nature', '2017-04-05', '2017-04-08', '14:00', '17:00'),
(10, 9, 'Quidditch Pitch', 'Gryffindor vs. Slytherin', '2017-04-12', '2017-04-12', '18:00', '20:00');

INSERT INTO Student_Events (Person_ID, Event_ID)
VALUES
(11,1),
(12,1),
(11,2),
(12,2),
(13,2),
(13,3),
(13,4),
(12,4),
(14,4),
(15,7),
(16,5),
(17,6),
(18,8),
(19,9),
(20,10),
(11,11);
