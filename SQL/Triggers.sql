Delimiter //

CREATE TRIGGER delete_student_events
BEFORE DELETE
   ON campus_events FOR EACH ROW

BEGIN

   delete from student_events
   where student_events.Event_ID=campus_events.Event_ID;

END; //

DELIMITER ;