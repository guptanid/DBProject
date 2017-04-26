use experientiallearningdb;
delimiter //
create procedure GetAllCategories()
begin
select * from category;
end  //
delimiter ;

delimiter //
create procedure GetAllDepartments()
begin
select * from department;
end  //
delimiter ;

delimiter //
create procedure GetAllEvents()
begin
select Event_ID, Department_Name, CategoryType, Location, Description, StartDate, EndDate, StartTime, EndTime 
from campus_events 
INNER JOIN department ON department.Department_ID= campus_events.Department_ID
INNER JOIN categories ON campus_events.Category_ID=categories.Category_ID;
end  //
delimiter ;
delimiter //
create procedure GetEventDetails(IN p_event_id int)
begin
select Event_ID, Department_Name, CategoryType, Location, Description, StartDate, EndDate, StartTime, EndTime 
from campus_events 
INNER JOIN department ON department.Department_ID= campus_events.Department_ID
INNER JOIN categories ON campus_events.Category_ID=categories.Category_ID
WHERE Event_ID=p_event_id;
end  //
delimiter ;
delimiter //
create procedure AddNewEvent( 
IN p_department_id int, In p_category_id int,in p_location varchar(100),in p_description varchar(100), 
in p_startdate date, in  p_enddate date,in p_starttime time, in p_endtime time)
begin
insert into campus_events(
Department_ID, Category_ID, Location, Description, StartDate, EndDate, StartTime, EndTime)
values(
p_department_id,p_category_id,p_location,p_description,p_startdate,p_enddate,p_starttime,p_endtime
);

end  //
delimiter ;
delimiter //
create procedure DeleteEventDetails(IN p_event_id int)
begin
delete from campus_events 
WHERE Event_ID=p_event_id;
end  //
delimiter ;
delimiter //
create procedure UpdateEventDetails( IN p_event_id int,
IN p_department_id int, In p_category_id int,in p_location varchar(100),in p_description varchar(100), 
in p_startdate date, in  p_enddate date,in p_starttime time, in p_endtime time)
begin
update campus_events
set 
Department_ID=p_department_id,
Category_ID=p_category_id,
Location=p_location,
Description=p_description,
StartDate=p_startdate,
 EndDate= p_enddate,
StartTime= p_starttime,
EndTime= p_endtime
where Event_ID= p_event_id;
end  //
delimiter ;