CREATE TABLE IF NOT EXISTS `queries` (
    `query_id` int AUTO_INCREMENT PRIMARY KEY,
    `query_text` varchar(2000) NOT NULL,
    `response_text` varchar(2000) DEFAULT NULL,
    `newJoiner_id` int NOT NULL,
    `mentor_id` int NOT NULL,
    `course_id` int NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(30) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(30) DEFAULT NULL,
    `status` BOOLEAN DEFAULT FALSE
);