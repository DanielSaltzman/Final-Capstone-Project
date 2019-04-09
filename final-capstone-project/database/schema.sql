-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
drop table if exists question_answer;
DROP TABLE IF EXISTS app_user; 
drop table if exists survey; 
drop table if exists answer;
drop table if exists question; 
drop table if exists student; 

CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  user_name varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  role varchar(32),
  salt varchar(255) NOT NULL
);

create table survey 
(survey_id serial PRIMARY KEY, 
survey_date text NOT NULL,
room text NOT NULL
); 

create table answer
(answer_id serial PRIMARY KEY, 
question_id int NOT NULL, 
answer_text text, 
student_id text NOT NULL, 
survey_id int NOT NULL
); 

create table question
(question_id serial PRIMARY KEY, 
question_text text NOT NULL 
); 

create table student
(student_id text PRIMARY KEY, 
student_first_name text NOT NULL, 
student_last_name text NOT NULL
); 

create table question_answer(
question_id int NOT NULL, 
answer_id int NOT NULL, 
constraint fk_survey_question_question_id foreign key (question_id) references question(question_id), 
constraint fk_survey_question_answer_id foreign key (answer_id) references answer(answer_id)
);


COMMIT;