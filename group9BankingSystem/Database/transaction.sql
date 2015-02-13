-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 02, 2013 at 12:18 AM
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

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`DESTACCOUNT`) REFERENCES `account` (`ACCOUNT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`ORIGINACCOUNT`) REFERENCES `account` (`ACCOUNT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
