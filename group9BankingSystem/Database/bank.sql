-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 05, 2013 at 03:37 PM
-- Server version: 5.5.24-log
-- PHP Version: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `bank`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `ACCOUNT_ID` varchar(200) NOT NULL,
  `TYPE` varchar(45) NOT NULL,
  `BALANCE` varchar(30) NOT NULL,
  `USER_ID` varchar(200) NOT NULL,
  PRIMARY KEY (`ACCOUNT_ID`),
  KEY `USER_ID_idx` (`USER_ID`),
  KEY `TYPE` (`TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`ACCOUNT_ID`, `TYPE`, `BALANCE`, `USER_ID`) VALUES
('ACCOUNT_7763d6a4-9a29-4940-b606-3d5e5208243d', 'PERSONAL_ACCOUNT', '4504', 'USER_8ab3dd11-59e1-4d4f-bf4e-f7b9343fdf1e'),
('ACCOUNT_cf57bb19-9275-4bb4-b728-55429b1355d2', 'PERSONAL_ACCOUNT', '906', 'USER_8ab3dd11-59e1-4d4f-bf4e-f7b9343fdf1e');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE IF NOT EXISTS `department` (
  `DEPARTMENT_ID` varchar(200) NOT NULL,
  `DEPARTMENT_NAME` varchar(45) NOT NULL,
  `MANAGER_ID` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`DEPARTMENT_ID`),
  KEY `Department_idx` (`MANAGER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`DEPARTMENT_ID`, `DEPARTMENT_NAME`, `MANAGER_ID`) VALUES
('DEPT_1', 'DEPT_SYSADMIN', NULL),
('DEPT_2', 'DEPT_HR', NULL),
('DEPT_3', 'EXTERNAL_USER', NULL),
('DEPT_4', 'DEPT_SALES', NULL),
('DEPT_5', 'DEPT_UNALLOC', NULL),
('DEPT_6', 'DEPT_TRANS', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `internal_user`
--

CREATE TABLE IF NOT EXISTS `internal_user` (
  `USER_ID` varchar(200) CHARACTER SET utf8 NOT NULL,
  `DEPARTMENT_ID` varchar(200) CHARACTER SET utf8 NOT NULL,
  `SALARY` int(200) NOT NULL,
  UNIQUE KEY `USER_ID_3` (`USER_ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `USER_ID_2` (`USER_ID`),
  KEY `DEPARTMENT_ID` (`DEPARTMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `internal_user`
--

INSERT INTO `internal_user` (`USER_ID`, `DEPARTMENT_ID`, `SALARY`) VALUES
('USER_1defa649-a083-4313-9b4f-107cbbb00e08', 'DEPT_1', 20000),
('USER_36a928af-94eb-4165-929a-743b921fa2ff', 'DEPT_4', 12233),
('USER_3785733b-f076-449f-92a4-20acb1b2cf87', 'DEPT_6', 50000),
('USER_5c14e633-6012-46bb-ba54-ac4a780ab5cf', 'DEPT_2', 80000),
('USER_60173f6a-0671-4090-93e4-34e151c74a6c', 'DEPT_2', 23456),
('USER_80ff6722-01d8-4fe1-814d-37de8516fa39', 'DEPT_4', 30000),
('USER_8500f2b6-5722-480b-92d0-39cc5045fdf2', 'DEPT_6', 89000),
('USER_b08af8e8-9a98-456d-95ca-1a4d39ef4bab', 'DEPT_4', 12345),
('USER_bfe1d400-c0b3-451f-8e4d-4ee72a950c58', 'DEPT_1', 30000);

-- --------------------------------------------------------

--
-- Table structure for table `onetimelogin`
--

CREATE TABLE IF NOT EXISTS `onetimelogin` (
  `username` varchar(200) CHARACTER SET utf8 NOT NULL,
  `password` varchar(200) CHARACTER SET utf8 NOT NULL,
  `starttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `verified` int(1) NOT NULL DEFAULT '0',
  KEY `username` (`username`),
  KEY `password` (`password`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `onetimelogin`
--

INSERT INTO `onetimelogin` (`username`, `password`, `starttime`, `verified`) VALUES
('swaroopexter7', '$2a$10$QFpCAAtbxsB9QKulqb40qetdd4TBNF7UWZBPFziN1OtHquVLl4yO.', '2013-11-05 09:33:50', 1);

-- --------------------------------------------------------

--
-- Table structure for table `onetimepass`
--

CREATE TABLE IF NOT EXISTS `onetimepass` (
  `TransactionId` varchar(200) CHARACTER SET utf8 NOT NULL,
  `Password` varchar(200) CHARACTER SET utf8 NOT NULL,
  `StartTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `TransactionId` (`TransactionId`),
  KEY `TransactionId_2` (`TransactionId`),
  KEY `TransactionId_3` (`TransactionId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `onetimepass`
--

INSERT INTO `onetimepass` (`TransactionId`, `Password`, `StartTime`) VALUES
('TRANS_dd3f09c1-eecb-4f51-889f-00d17b07ff43', '3e;Gg)nAMC', '2013-10-29 04:01:43'),
('TRANS_942742af-d4f5-44a1-ab5a-f5b07d2a90e9', '@vDj@,eyg(', '2013-10-29 04:08:29'),
('TRANS_17d08b1c-25df-44b0-986c-1c2022a01056', 'Bi~2+U;wYF', '2013-10-29 17:44:19'),
('TRANS_d1794eb5-43f4-486d-b3b9-0da3e851ea5f', '6]2;Ah)msR', '2013-11-03 02:21:59'),
('TRANS_e7049ca3-bc21-461d-b944-fed5b1dc3909', 'VNx)rY0EtH', '2013-11-03 02:30:32'),
('TRANS_aa586f88-4667-4b40-a497-c7ea7d5202ef', 'ae;,"fq-M$', '2013-11-03 02:40:04'),
('TRANS_85f04e96-b087-43d2-8b20-683bee6aa8f5', '''!9l2jFO\\-', '2013-11-03 05:22:18'),
('TRANS_b88f9975-9076-4ab4-ab38-237658f4aa11', '$2a$10$Xm8zMOUwCeIWvwfUwRzseeenoCySSBVtaJwMosmvbhKO5wW5RSc8S', '2013-11-05 10:09:01');

-- --------------------------------------------------------

--
-- Table structure for table `requests`
--

CREATE TABLE IF NOT EXISTS `requests` (
  `USER_ID` varchar(200) CHARACTER SET utf8 NOT NULL,
  `ACCOUNT_ID` varchar(200) CHARACTER SET utf8 NOT NULL,
  `STATUS` int(11) NOT NULL DEFAULT '0',
  KEY `USER_ID` (`USER_ID`),
  KEY `ACCOUNT_ID` (`ACCOUNT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `requests`
--

INSERT INTO `requests` (`USER_ID`, `ACCOUNT_ID`, `STATUS`) VALUES
('USER_3785733b-f076-449f-92a4-20acb1b2cf87', 'ACCOUNT_7763d6a4-9a29-4940-b606-3d5e5208243d', 1);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `ROLE_ID` varchar(30) NOT NULL,
  `AUTHORITY` varchar(45) NOT NULL,
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `AUTHORITY` (`AUTHORITY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`ROLE_ID`, `AUTHORITY`) VALUES
('role_3', 'EXTERNAL_USER'),
('role_1', 'ROLE_ADMIN'),
('role_4', 'ROLE_MANAGER'),
('ROLE_5', 'ROLE_OFFICIAL'),
('role_2', 'ROLE_REGULAREMPLOYEE');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `TRANS_ID` varchar(45) CHARACTER SET utf8 NOT NULL,
  `AMOUNT` varchar(45) CHARACTER SET utf8 NOT NULL,
  `TYPE` varchar(200) CHARACTER SET utf8 NOT NULL,
  `DESTACCOUNT` varchar(45) CHARACTER SET utf8 NOT NULL,
  `ORIGINACCOUNT` varchar(45) CHARACTER SET utf8 NOT NULL,
  `STATUS` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`TRANS_ID`),
  KEY `DESTACCOUNT` (`DESTACCOUNT`),
  KEY `ORIGINACCOUNT` (`ORIGINACCOUNT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`TRANS_ID`, `AMOUNT`, `TYPE`, `DESTACCOUNT`, `ORIGINACCOUNT`, `STATUS`) VALUES
('TRANS_17d08b1c-25df-44b0-986c-1c2022a01056', '500', 'TRANSFER', 'ACCOUNT_7763d6a4-9a29-4940-b606-3d5e5208243d', 'ACCOUNT_cf57bb19-9275-4bb4-b728-55429b1355d2', 2),
('TRANS_2bc33ba0-7a96-4597-b920-38cfb1f3a54b', '5200', 'TRANSFER', 'ACCOUNT_7763d6a4-9a29-4940-b606-3d5e5208243d', 'ACCOUNT_cf57bb19-9275-4bb4-b728-55429b1355d2', 1),
('TRANS_85f04e96-b087-43d2-8b20-683bee6aa8f5', '400', 'TRANSFER', 'ACCOUNT_cf57bb19-9275-4bb4-b728-55429b1355d2', 'ACCOUNT_7763d6a4-9a29-4940-b606-3d5e5208243d', 1),
('TRANS_942742af-d4f5-44a1-ab5a-f5b07d2a90e9', '500', 'TRANSFER', 'ACCOUNT_7763d6a4-9a29-4940-b606-3d5e5208243d', 'ACCOUNT_cf57bb19-9275-4bb4-b728-55429b1355d2', 1),
('TRANS_aa586f88-4667-4b40-a497-c7ea7d5202ef', '300', 'TRANSFER', 'ACCOUNT_cf57bb19-9275-4bb4-b728-55429b1355d2', 'ACCOUNT_7763d6a4-9a29-4940-b606-3d5e5208243d', 1),
('TRANS_b88f9975-9076-4ab4-ab38-237658f4aa11', '500', 'TRANSFER', 'ACCOUNT_cf57bb19-9275-4bb4-b728-55429b1355d2', 'ACCOUNT_7763d6a4-9a29-4940-b606-3d5e5208243d', 1),
('TRANS_d1794eb5-43f4-486d-b3b9-0da3e851ea5f', '1000', 'TRANSFER', 'ACCOUNT_cf57bb19-9275-4bb4-b728-55429b1355d2', 'ACCOUNT_7763d6a4-9a29-4940-b606-3d5e5208243d', 1),
('TRANS_dd3f09c1-eecb-4f51-889f-00d17b07ff43', '500', 'TRANSFER', 'ACCOUNT_7763d6a4-9a29-4940-b606-3d5e5208243d', 'ACCOUNT_cf57bb19-9275-4bb4-b728-55429b1355d2', 1),
('TRANS_e7049ca3-bc21-461d-b944-fed5b1dc3909', '500', 'TRANSFER', 'ACCOUNT_7763d6a4-9a29-4940-b606-3d5e5208243d', 'ACCOUNT_cf57bb19-9275-4bb4-b728-55429b1355d2', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `USER_ID` varchar(200) NOT NULL,
  `LASTNAME` varchar(50) NOT NULL,
  `FIRSTNAME` varchar(50) NOT NULL,
  `SSN` varchar(50) NOT NULL,
  `EMAILID` varchar(50) NOT NULL,
  `ADDRESS` text NOT NULL,
  `ZIPCODE` varchar(10) NOT NULL,
  `GENDER` varchar(10) NOT NULL,
  `CONTACT_NUMBER` varchar(15) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(200) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  `ROLE_ID` varchar(45) NOT NULL,
  `changedPassword` int(1) NOT NULL DEFAULT '0',
  `ChangeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`),
  UNIQUE KEY `SSN` (`SSN`),
  UNIQUE KEY `CONTACT_NUMBER` (`CONTACT_NUMBER`),
  KEY `ROLE_ID` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`USER_ID`, `LASTNAME`, `FIRSTNAME`, `SSN`, `EMAILID`, `ADDRESS`, `ZIPCODE`, `GENDER`, `CONTACT_NUMBER`, `USERNAME`, `PASSWORD`, `ENABLED`, `ROLE_ID`, `changedPassword`, `ChangeDate`) VALUES
('USER_1defa649-a083-4313-9b4f-107cbbb00e08', 'Testuser', 'internaluser', '123456765', 'satya.n931@gmail.com', '123fdsfdafadas', '123456', 'male', '123456783', 'test', '$2a$10$Ekt6MFS6o/DFVub1db6HA.wzWGv8zNFdNEOiJw6uEGSo4zRwGj.cy', 1, 'role_1', 0, '0000-00-00 00:00:00'),
('USER_36a928af-94eb-4165-929a-743b921fa2ff', 'swaroop', 'satya', '123567342', 'satyaswaroop.boddu@asu.edu', 'sasasa', '85281', 'male', '4804326499', 'swaroopboddu2', '$2a$10$FNgnajugDs20svvKACK5Hu.Lgx.P95j7UjRCxpnmF0rf0sgqrXIyC', 0, 'role_1', 0, '2013-11-05 04:54:10'),
('USER_3785733b-f076-449f-92a4-20acb1b2cf87', 'swarooptrans', 'satya', '123456780', 'satya.nx93@gmail.com', '1255 E University Dr, Tempe', '123456', 'male', '4804996499', 'swarooptrans', '$2a$10$QK.Dt/LlpmiLmHeX66NVMuFevydBLrki2P4jmcxRTCSC54QfiAh7i', 1, 'role_2', 0, '0000-00-00 00:00:00'),
('USER_5c14e633-6012-46bb-ba54-ac4a780ab5cf', 'swaroopoff', 'swaroopoff', '123456789', 'satya2.n93@gmail.com', '1255 E University Dr, Tempe', '123456', 'male', '4804126499', 'swaroopoff', '$2a$10$qOXsCF7/OffjXiGil4w3VeF7JdVUwobpxSkMqGEFTan1Z7OM34u6K', 1, 'ROLE_5', 0, '0000-00-00 00:00:00'),
('USER_60173f6a-0671-4090-93e4-34e151c74a6c', 'swaroop', 'boddu', '123456781', 'satya3.n93@gmail.com', 'hello', '123456', 'male', '4804226499', 'swaroopboddu', '$2a$10$cB/WoMyL4K9Wzpab25CSHekcgWn10TYABq4xmsE6.hKGYAWeSkXBu', 1, 'role_1', 0, '0000-00-00 00:00:00'),
('USER_802940c3-bd2b-4cae-9d92-21a8dff4aa84', 'sasasaas', 'ddsadsa', '123456783', 'mbalumur1@asu.edu', 'asddfsd', '85281', 'male', '4805326499', 'swaroopexters', '$2a$10$YXQ/wtS.3fCCk4.1kB52/eRKznHiRCAdy.lrmQRTc38wsKEzaVAtG', 0, 'role_3', 0, '0000-00-00 00:00:00'),
('USER_80ff6722-01d8-4fe1-814d-37de8516fa39', 'swaroopnew', 'boddu', '999999999', 'satyaswaroopb@gmail.com', '1255 E University Dr\r\nTempe', '85281', 'male', '4804326239', 'swaroopnew', '$2a$10$Dw9SpUdb/d2GDtqNAW0TZ.Eb2FzLP8vZ8MgR51sH9/B3LEtKtt8vm', 1, 'role_2', 0, '2013-11-02 22:33:31'),
('USER_8500f2b6-5722-480b-92d0-39cc5045fdf2', 'swaroopmanager', 'boddu', '123456784', 'sboddu2@asu.edu', '1255 E University Dr, Tempe', '85281', 'male', '4904326499', 'swaroopmanager', '$2a$10$iqTdwu0H16299Sb0hey.zOyDSkxRAS3qG63FEOaP7BxMkFf/cqhfG', 1, 'role_4', 0, '0000-00-00 00:00:00'),
('USER_8ab3dd11-59e1-4d4f-bf4e-f7b9343fdf1e', 'firstexternal', 'ddsadsa', '123456786', 'sboddu1@asu.edu', 'asddfsd', '85281', 'male', '2804326499', 'swaroopexter7', '$2a$10$6dR0L8EJraOfqw8W.DkG1OTx.9UprMHGbxN21dngMNgCevqaE25HO', 1, 'role_3', 0, '0000-00-00 00:00:00'),
('USER_b08af8e8-9a98-456d-95ca-1a4d39ef4bab', 'testsales', 'sales', '123456790', 'satya.n93@gmail.com', 'abscdg', '123456', 'male', '4804026499', 'swaroopsales', '$2a$10$3/oJRwYwQtK3922vQxLd5O9Ssx/tn5P2eFyAtUHCkFJG1vTfLETcO', 1, 'role_1', 0, '0000-00-00 00:00:00'),
('USER_b54726e0-12b2-494a-9c8b-c93e95fd51b5', 'firstexternal', 'user', '1234567891', 'mbalumur@asu.edu', 'asddfsd', '85281', 'male', '4804329999', 'swaroopexter', '$2a$10$/LxQr14ty/B9VjQfBVWdZOzQEJTCUBEU1bMWSFPXzzfOJ0w/8r2YW', 0, 'role_3', 0, '0000-00-00 00:00:00'),
('USER_bfe1d400-c0b3-451f-8e4d-4ee72a950c58', 'Testuser1', 'internaluser', '12345672', 'satya@xxx.com', '123fdsfdafadas', '123456', 'male', '1234567897', 'test1', '$2a$10$J/0fEU8Xdnhz/DCq60K2zulXg0K9P/Y2jrkeMpDMxgZ8.VTzeeP6e', 1, 'role_1', 0, '0000-00-00 00:00:00'),
('USER_c77ee334-b984-4315-b3ef-fa4400fb7945', 'firstexternal', 'ddsadsa', '1234566789', 'mbalumur22@asu.edu', 'asddfsd', '85281', 'male', '4804326399', 'swaroopexter5', '$2a$10$ZTOREmrdDDpzmG17EAIAD.jvf42BI3bObEwgVrDmrEs6E.Tjlwneu', 0, 'role_3', 0, '0000-00-00 00:00:00');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `department`
--
ALTER TABLE `department`
  ADD CONSTRAINT `department_ibfk_2` FOREIGN KEY (`MANAGER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `internal_user`
--
ALTER TABLE `internal_user`
  ADD CONSTRAINT `internal_user_ibfk_3` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `internal_user_ibfk_4` FOREIGN KEY (`DEPARTMENT_ID`) REFERENCES `department` (`DEPARTMENT_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `onetimelogin`
--
ALTER TABLE `onetimelogin`
  ADD CONSTRAINT `onetimelogin_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`USERNAME`);

--
-- Constraints for table `onetimepass`
--
ALTER TABLE `onetimepass`
  ADD CONSTRAINT `onetimepass_ibfk_1` FOREIGN KEY (`TransactionId`) REFERENCES `transaction` (`TRANS_ID`);

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`DESTACCOUNT`) REFERENCES `account` (`ACCOUNT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`ORIGINACCOUNT`) REFERENCES `account` (`ACCOUNT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_2` FOREIGN KEY (`ROLE_ID`) REFERENCES `roles` (`ROLE_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
