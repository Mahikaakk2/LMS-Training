CREATE TABLE IF NOT EXISTS `courses`(
    `course_id` int AUTO_INCREMENT PRIMARY KEY,
    `course_title` varchar(200) NOT NULL,
    `course_description` varchar(200) NOT NULL,
   `course_duration` TIME NOT NULL,
    `course_resource` varchar(1000) NOT NULL,
    `course_category` varchar(200) NOT NULL,
    `created_at` date NOT NULL,
    `created_by` int NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` int DEFAULT NULL,
    `isApproved` BIT DEFAULT 0
);
