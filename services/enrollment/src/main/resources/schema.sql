CREATE TABLE IF NOT EXISTS `enrollments` (
    `enrollment_id` int AUTO_INCREMENT PRIMARY KEY,
     `mentor_id` int NOT NULL,
     `course_id` int NOT NULL,
     `new_join_id` int NOT NULL,
     `created_at` date NOT NULL,
     `created_by` varchar(30) NOT NULL
);