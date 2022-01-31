-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 31, 2022 at 01:51 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `railway`
--

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `id` int(11) NOT NULL,
  `ticketNumber` int(30) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `phoneNumber` varchar(15) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `title` varchar(10) NOT NULL,
  `class` varchar(20) NOT NULL,
  `seat` varchar(5) NOT NULL,
  `from_station` varchar(50) NOT NULL,
  `to_station` varchar(50) NOT NULL,
  `departure_time` varchar(20) NOT NULL,
  `arrival_time` varchar(20) NOT NULL,
  `train_name` varchar(50) NOT NULL,
  `train_number` varchar(20) NOT NULL,
  `added_date` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`id`, `ticketNumber`, `firstName`, `lastName`, `phoneNumber`, `gender`, `title`, `class`, `seat`, `from_station`, `to_station`, `departure_time`, `arrival_time`, `train_name`, `train_number`, `added_date`) VALUES
(1, 1000, 'Richard', 'Pryor', '2347098337748', 'Male', 'DMTT', 'Standard Class', '2', 'Kom-Kom', 'Trans Amadi', '11:00 AM', '12:30 AM', 'Mass Transit (MTT)', '7368', '2019/12/17 19:38:03'),
(2, 1003, 'Jennifer', 'Santos', '07092737284', 'Female', 'DMTT', 'First Class', '2', 'Kom-Kom', 'Diobu Market', '9:00 PM', '10:00 PM', 'Mass Transit (MTT)', '7368', '2019/12/17 19:44:34'),
(3, 1006, 'Daisy', 'Johnson', '09092838282', 'Female', 'UMTT', 'Standard Class', '2', 'Port Harcourt Stations', 'Aba', '8:00 AM', '10:00 AM', 'Mass Transit (MTT)', '3874', '2019/12/17 19:49:14'),
(4, 1009, 'Hakeem', 'Lyon', '09082732832', 'Male', 'UMTT', 'First Class', '2', 'Diobu', 'Imo River', '7:00 PM', '8:30 PM', 'Mass Transit (MTT)', '3874', '2019/12/17 19:57:48'),
(5, 1012, 'Steve', 'Aoki', '2347098495868', 'Male', 'UMTT', 'First Class', '1', 'Diobu Market', 'Trans Amadi', '4:00 PM', '5:30 PM', 'Express Train (INTERCITY)', '7368', '2019/12/18 01:10:05'),
(6, 1015, 'William', 'Queen', '08092838283', 'Male', 'UMTT', 'Standard Class', '3', 'Diobu', 'Trans Amadi', '3:00 PM', '3:30 PM', 'Express Train (INTERCITY)', '7368', '2019/12/18 01:15:59'),
(7, 1018, 'Julia', 'Morgan', '2348092893844', 'Female', 'DMTT', 'First Class', '2', 'Aba', 'Elelenwo', '5:00 AM', '8:00 AM', 'Mass Transit (MTT)', '7368', '2019/12/18 01:15:59'),
(8, 1021, 'Kendrick', 'Lamar', '08093832838', 'Male', 'UMTT', 'First Class', '2', 'Imo River', 'Elelenwo', '4:00 AM', '6:00 AM', 'Mass Transit (MTT)', '7368', '2019/12/18 02:21:08'),
(10, 1027, 'Ed', 'Sheeran', '08093942838', 'Male', 'UMTT', 'First Class', '2', 'Aba', 'Port Harcourt Stations', '8:00 AM', '12:00 AM', 'Mass Transit (MTT)', '7368', '2019/12/18 02:21:08'),
(11, 1030, 'Justin', 'Timberlake', '08098294829', 'Male', 'UMTT', 'First Class', '7', 'Kom-Kom', 'Diobu', '8:00 PM', '11:00 PM', 'Mass Transit (MTT)', '7368', '2019/12/18 02:21:08'),
(12, 1033, 'Linda', 'Gerrad', '08078932838', 'Female', 'DMTT', 'Standard Class', '2', 'D-line', 'Kom-Kom', '3:00 AM', '5:00 AM', 'Mass Transit (MTT)', '3874', '2019/12/18 02:21:08'),
(13, 1036, 'Hullen', 'Collins', '08193837838', 'Male', 'DMTT', 'First Class', '1', 'Diobu (HALT)', 'Trans Amadi (TERMINAL)', '5:00 PM', '5:30 PM', 'Express Train (INTERCITY)', '8932', '2019/12/28 00:56'),
(14, 1039, 'Prince', 'Royce', '08193388838', 'Male', 'UMTT', 'First Class', '1', 'Aba', 'Diobu', '8:00 AM', '10:00 AM', 'Mass Transit (MTT)', '7368', '2019/12/18 02:21:08'),
(15, 1042, 'Jon', 'Bellion', '08094932838', 'Male', 'UMTT', 'Standard Class', '1', 'Aba (TERMINAL)', 'Port-Harcourt Stations (TERMINAL)', '4:00 AM', '8:00 AM', 'Mass Transit (MTT)', '8392', '2020/01/18 18:27'),
(16, 1045, 'Jack', 'Kane', '08093832838', 'Male', 'UMTT', 'First Class', '10', 'Aba', 'Trans Amadi', '6:00 AM', '8:30 AM', 'Mass Transit (MTT)', '3874', '2019/12/18 02:21:09'),
(17, 1048, 'Susan', 'Mack', '08193392838', 'Female', 'DMTT', 'First Class', '4', 'Diobu Market', 'Aba', '9:00 AM', '10:00 AM', 'Mass Transit (MTT)', '3874', '2019/12/18 02:21:09'),
(18, 1051, 'Bebe', 'Regina', '08093339838', 'Female', 'DMTT', 'First Class', '2', 'Trans Amadi', 'Aba', '8:00 PM', '10:00 PM', 'Mass Transit (MTT)', '3874', '2019/12/18 02:21:17'),
(19, 1054, 'Maroon', 'Five', '08093837473', 'Male', 'DMTT', 'Standard Class', '2', 'Aba (TERMINAL)', 'Trans Amadi (TERMINAL)', '7:00 AM', '9:00 AM', 'Mass Transit (MTT)', '8392', '2019/12/20 11:10:39'),
(20, 1057, 'West', 'Life', '07093893828', 'Male', 'UMTT', 'First Class', '7', 'Aba (TERMINAL)', 'Diobu Market (HALT)', '6:00 AM', '9:00 AM', 'Mass Transit (MTT)', '6783', '2019/12/21 10:41:19'),
(21, 1060, 'Genoa', 'Dan', '08198384748', 'Female', 'DMTT', 'First Class', '4', 'Port-Harcourt Stations (TERMINAL)', 'Aba (TERMINAL)', '8:00 AM', '1:00 PM', 'Mass Transit (MTT)', '8392', '2019/12/21 10:45:36'),
(22, 1063, 'Fernado', 'Gazia', '08193834748', 'Male', 'UMTT', 'First Class', '4', 'Aba (TERMINAL)', 'D-Line (TERMINAL)', '5:00 AM', '7:00 AM', 'Mass Transit (MTT)', '8392', '2019/12/21 23:11:36'),
(23, 1066, 'Sydney', 'White', '07089384738', 'Female', 'DMTT', 'First Class', '2', 'Port-Harcourt Stations (TERMINAL)', 'Aba Town (HALT)', '3:00 AM', '8:00 AM', 'Mass Transit (MTT)', '8392', '2019/12/22 04:28'),
(24, 1069, 'Kylie', 'Jemma', '09083828382', 'Female', 'DMTT', 'First Class', '1', 'Port-Harcourt Stations (TERMINAL)', 'Trans Amadi (TERMINAL)', '4:00 PM', '6:30 PM', 'Express Train (INTERCITY)', '8932', '2019/12/22 11:53'),
(25, 1072, 'Jason', 'Moama', '09093834378', 'Male', 'DMTT', 'First Class', '3', 'Aba (TERMINAL)', 'Diobu Market (HALT)', '2:30 PM', '5:00 PM', 'Mass Transit (MTT)', '8392', '2019/12/27 12:19'),
(26, 1075, 'Johnnie', 'Walker', '07083847389', 'Male', 'DMTT', 'First Class', '1', 'Port-Harcourt Stations (TERMINAL)', 'Trans Amadi (TERMINAL)', '2:00 PM', '4:00 PM', 'Express Train (INTERCITY)', '8932', '2019/12/26 01:19'),
(27, 1078, 'Chip', 'Johnson', '09083743722', 'Male', 'UMTT', 'Standard Class', '2', 'Trans Amadi (TERMINAL)', 'Diobu Market (HALT)', '8:30 AM', '10:00 AM', 'Express Train (INTERCITY)', '8932', '2019/12/27 12:53'),
(28, 1081, 'Taylor', 'Mason', '09093847584', 'Female', 'UMTT', 'First Class', '2', 'Aba (TERMINAL)', 'Port-Harcourt Stations (TERMINAL)', '7:00 AM', '10:00 AM', 'Mass Transit (MTT)', '6783', '2019/12/26 01:19'),
(29, 1084, 'Julianne', 'Jackson', '09089337899', 'Female', 'DMTT', 'First Class', '1', 'Diobu Market (HALT)', 'Trans Amadi (TERMINAL)', '5:15 PM', '5:50 PM', 'Express Train (INTERCITY)', '8932', '2019/12/26 01:19'),
(30, 1087, 'Miley', 'Cyrus', '07087684556', 'Female', 'UMTT', 'Standard Class', '5', 'Aba Town (HALT)', 'Diobu (HALT)', '4:00 PM', '9:00 PM', 'Mass Transit (MTT)', '6783', '2020/01/13 18:45'),
(31, 1090, 'West', 'Life', '08083933939', 'Male', 'UMTT', 'Standard Class', '2', 'Aba (TERMINAL)', 'Diobu Market (HALT)', '6:00 AM', '8:00 AM', 'Mass Transit (MTT)', '8392', '2019/12/27 13:11'),
(32, 1093, 'Jaden', 'Smith', '08192937489', 'Male', 'DMTT', 'First Class', '1', 'Diobu Market (HALT)', 'D-Line (TERMINAL)', '10:00 AM', '10:30 AM', 'Express Train (INTERCITY)', '8932', '2019/12/27 13:11'),
(33, 1096, 'Sandra', 'Johnson', '09087864567', 'Female', 'UMTT', 'Standard Class', '2', 'Aba (TERMINAL)', 'Trans Amadi (TERMINAL)', '7:00 AM', '9:00 AM', 'Mass Transit (MTT)', '6783', '2020/01/14 09:37'),
(34, 1099, 'Kendrick', 'Jamal', '07089283789', 'Male', 'DMTT', 'First Class', '3', 'Diobu Market (HALT)', 'Elelenwo (TERMINAL)', '4:15 PM', '5:50 PM', 'Express Train (INTERCITY)', '8932', '2019/12/28 01:05'),
(35, 1102, 'Idris', 'Elba', '08117378936', 'Male', 'UMTT', 'Standard Class', '1', 'Imo River (TERMINAL)', 'Diobu (HALT)', '7:00 PM', '8:00 PM', 'Mass Transit (MTT)', '6783', '2019/12/28 01:05'),
(36, 1105, 'Lois', 'Lane', '08067575775', 'Female', 'DMTT', 'First Class', '3', 'Elelenwo (TERMINAL)', 'Trans Amadi (TERMINAL)', '4:20 PM', '5:15 PM', 'Express Train (INTERCITY)', '8932', '2019/12/28 01:08'),
(37, 1108, 'Aurora', 'Kindle', '07064732984', 'Female', 'DMTT', 'Standard Class', '2', 'Trans Amadi (TERMINAL)', 'D-Line (TERMINAL)', '2:00 PM', '2:30 PM', 'Express Train (INTERCITY)', '9689', '2020/01/19 16:59'),
(38, 1111, 'Samuel', 'Jackson', '08094837473', 'Male', 'DMTT', 'First Class', '2', 'Imo River (TERMINAL)', 'Diobu Market (HALT)', '7:00 PM', '8:30 PM', 'Mass Transit (MTT)', '8392', '2020/01/24 17:43'),
(39, 1114, 'Richard', 'Gigi', '08091051463', 'Male', 'DMTT', 'Standard Class', '7', 'Trans Amadi (TERMINAL)', 'D-Line (TERMINAL)', '8:30 AM', '12:30 PM', 'Mass Transit (MTT)', '8392', '2020/01/20 18:08'),
(40, 1117, 'Wilberforce', 'Joseph', '08091051463', 'Male', 'UMTT', 'First Class', '3', 'Aba (TERMINAL)', 'Aba (TERMINAL)', '5:00 AM', '9:00 AM', 'Mass Transit (MTT)', '6783', '2020/01/20 18:08');

-- --------------------------------------------------------

--
-- Table structure for table `stations`
--

CREATE TABLE `stations` (
  `id` int(11) NOT NULL,
  `station_name` varchar(50) NOT NULL,
  `station_type` varchar(50) NOT NULL,
  `added_date` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stations`
--

INSERT INTO `stations` (`id`, `station_name`, `station_type`, `added_date`) VALUES
(1, 'Aba', 'TERMINAL', '2019/12/20 00:00:00'),
(2, 'Aba Town', 'HALT', '2019/12/20 00:00:00'),
(3, 'Imo River', 'TERMINAL', '2019/12/20 00:00:00'),
(4, 'Kom - Kom', 'TERMINAL', '2019/12/20 00:00:00'),
(5, 'Elelenwo', 'TERMINAL', '2019/12/20 00:00:00'),
(6, 'Trans Amadi', 'TERMINAL', '2019/12/20 00:00:00'),
(7, 'Diobu', 'HALT', '2019/12/20 00:00:00'),
(8, 'D-Line', 'TERMINAL', '2019/12/20 00:00:00'),
(9, 'Diobu Market', 'HALT', '2019/12/20 00:00:00'),
(10, 'Port-Harcourt Stations', 'TERMINAL', '2019/12/20 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `trains`
--

CREATE TABLE `trains` (
  `id` int(11) NOT NULL,
  `train_name` varchar(50) NOT NULL,
  `train_number` varchar(20) NOT NULL,
  `added_date` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trains`
--

INSERT INTO `trains` (`id`, `train_name`, `train_number`, `added_date`) VALUES
(1, 'Mass Transit (MTT)', '6783', '2019/12/20 07:31:50'),
(2, 'Express Train (INTERCITY)', '8932', '2019/12/20 07:32:42'),
(3, 'Mass Transit (MTT)', '8392', '2019/12/20 08:44:43'),
(4, 'Express Train (INTERCITY)', '9689', '2019/12/28 01:06');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ticketNumber` (`ticketNumber`);

--
-- Indexes for table `stations`
--
ALTER TABLE `stations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `trains`
--
ALTER TABLE `trains`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bookings`
--
ALTER TABLE `bookings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `stations`
--
ALTER TABLE `stations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `trains`
--
ALTER TABLE `trains`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
