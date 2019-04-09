-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

-- Insert statements go here

INSERT INTO survey(survey_id, survey_date, room, survey_name)
VALUES
('Wednesday, May 23 2018 09:02 AM', 'tecbusjavab', 'survey one'), 
('Thursday, May 24 2018 09:02 AM', 'tecbusjavab', 'survey two'), 
('Wednesday, May 23 2018 09:02 AM', 'tecbusjavag', 'survey three'), 
('Thursday, May 24 2018 09:02 AM', 'tecbusjavag', 'survey four');


INSERT INTO answer(question_id, answer_text, student_id, survey_id)
VALUES
('1', 'correct answer', 'STUDENT1', '1'), 
('1', 'incorrect answer', 'STUDENT2', '1'), 
('2', 'correct answer', 'STUDENT1', '2'), 
('2', 'incorrect answer', 'STUDENT2', '2'), 
('1', 'correct answer', 'STUDENT1', '3'), 
('1', 'incorrect answer', 'STUDENT2', '3'), 
('2', 'correct answer', 'STUDENT1', '4'), 
('2', 'incorrect answer', 'STUDENT2', '4');

INSERT INTO question(question_text)
VALUES
('Presence'), 
('The pace of yesterday''s class was:'), 
('The content of the previous class was:'),
('I feel my level of understanding of the previous day''s material is:'),
('On a scale of 1-10, my energy level today is:');

INSERT INTO survey_question(question_id, survey_id) 
VALUES
(1,2), 
(1,3),
(1,4),
(1,5),
(2,2),
(2,3),
(2,4),
(2,5),
(3,2),
(3,3),
(3,4),
(3,5),
(4,2),
(4,3),
(4,4),
(4,5),
(5,2),
(5,3),
(5,4),
(5,5);

INSERT INTO student(student_id, student_first_name, student_last_name)
VALUES
('STUDENT1', 'Bobby', 'Keys'), 
('STUDENT2', 'Nicky', 'Hopkins') ;

COMMIT;