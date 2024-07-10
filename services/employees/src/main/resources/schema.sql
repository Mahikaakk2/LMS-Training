CREATE TABLE IF NOT EXISTS `user` (
                                          `user_id` int AUTO_INCREMENT PRIMARY KEY,
                                          `firstname` varchar(200) NOT NULL,
    `lastname` varchar(200) NOT NULL,
    `email` varchar(100) NOT NULL,
    `mobileNumber` varchar(20) NOT NULL,
    `password` varchar(50)  NOT NULL,
    `role`  varchar(80)   NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(30) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(30) DEFAULT NULL
    );