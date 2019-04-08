-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS survey;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS survey_question;

CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  user_name varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  role varchar(32),
  salt varchar(255) NOT NULL
);

create table survey 
(survey_id serial PRIMARY KEY, 
survey_date TIMESTAMP NOT NULL
); 

create table question
(question_id serial PRIMARY KEY, 
survey_id int NOT NULL, 
question_number int NOT NULL, 
question_text text NOT NULL, 
question_answer text NOT NULL
); 


create table survey_question(
question_id int NOT NULL, 
survey_id int NOT NULL, 
constraint fk_survey_question_question_id foreign key (question_id) references question(question_id), 
constraint fk_survey_question_survey_id foreign key (survey_id) references survey(survey_id)
);

COMMIT;