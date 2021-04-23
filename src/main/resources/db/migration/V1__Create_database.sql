create table people(
    id bigint primary key,
    first_name varchar(40) not null,
    second_name varchar(40),
    last_name varchar(40) not null,
    birthday date
);

create table helpers(
    people_id bigint primary key,
    work text not null,
    foreign key (people_id) references people(id)
);

create table teachers(
    people_id bigint primary key,
    experience_years int not null,
    education_degree text,
    max_hours_a_week int not null,
    about_me text,
    foreign key (people_id) references people(id)
);

create table students(
    people_id bigint primary key,
    education_start_date date not null,
    foreign key (people_id) references people(id)
);

create table disciplines(
    id bigint primary key,
    name text not null,
    description text,
    hours_a_week int not null
);

create table teachers_disciplines(
    teacher_id bigint,
    discipline_id bigint,
    teachers_comment text,
    primary key (teacher_id, discipline_id),
    foreign key (teacher_id) references teachers(people_id),
    foreign key (discipline_id) references disciplines(id)
);

create table programs(
    id bigint primary key,
    name text not null,
    description text
);

create table students_programs(
    student_id bigint,
    program_id bigint,
    primary key (student_id, program_id),
    foreign key (student_id) references students(people_id),
    foreign key (program_id) references programs(id)
);

create table programs_disciplines(
    program_id bigint,
    teacher_id bigint,
    discipline_id bigint,
    primary key (program_id, teacher_id, discipline_id),
    foreign key (program_id) references programs(id),
    foreign key (teacher_id, discipline_id) references teachers_disciplines(teacher_id, discipline_id)
);

create table timetable(
    id bigint primary key,
    program_id bigint,
    teacher_id bigint,
    discipline_id bigint,
    day int not null,
    time time not null,
    room int not null,
    check (day >= 1 AND day <= 7),
    foreign key (program_id) references programs(id),
    foreign key (teacher_id, discipline_id) references teachers_disciplines(teacher_id, discipline_id)
);

create table lessons(
    id bigint primary key,
    theme text,
    description text,
    timetableCell_id bigint,
    date date,
    foreign key (timetableCell_id) references timetable(id)
);

create table hometasks(
    lesson_id bigint primary key,
    text text,
    deadline date,
    foreign key (lesson_id) references lessons(id)
);

create table homeworks(
    id bigint primary key,
    student_id bigint,
    hometask_id bigint,
    homework text,
    students_comment text,
    mark int,
    teachers_comment text,
    foreign key (student_id) references students(people_id),
    foreign key (hometask_id) references hometasks(lesson_id)
);

create table lesson_marks(
    id bigint primary key,
    student_id bigint,
    lesson_id bigint,
    value int not null,
    comment text,
    foreign key (student_id) references students(people_id),
    foreign key (lesson_id) references lessons(id)
);

create table final_marks(
    student_id bigint,
    discipline_id bigint,
    value int not null,
    comment text,
    primary key (student_id, discipline_id),
    foreign key (student_id) references students(people_id),
    foreign key (discipline_id) references disciplines(id)
);
