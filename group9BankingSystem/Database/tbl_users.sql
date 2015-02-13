CREATE TABLE IF NOT EXISTS `users` (
  `USER_ID` varchar(200) NOT NULL,
  `LASTNAME` varchar(50) NOT NULL,
  `FIRSTNAME` varchar(50) NOT NULL,
  `SSN` varchar(50) NOT NULL,
  `EMAILID` varchar(50) NOT NULL,
  `ADDRESS` text NOT NULL,
  `ZIPCODE` varchar(10) NOT NULL,
  `GENDER` varchar(5) NOT NULL,
  `CONTACT_NUMBER` varchar(15) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  `ROLE_ID` varchar(45) NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`),
  KEY `fk_user_roles` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
