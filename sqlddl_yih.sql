-- phpMyAdmin SQL Dump
-- version 4.2.5
-- http://www.phpmyadmin.net
--
-- Host: localhost:8889
-- Generation Time: Apr 19, 2015 at 03:06 AM
-- Server version: 5.5.38
-- PHP Version: 5.5.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `flower_store`
--
create database if not exists `flower_store`;
use `flower_store`;
-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cname` varchar(20) NOT NULL,
  `pid` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`cname`, `pid`, `quantity`) VALUES
('spring', 4, 23),
('spring', 4, 23),
('Max', 1, 29),
('Max', 10, 15),
('Max', 10, 10),
('Max', 16, 24),
('Max', 14, 23),
('max', 0, 28),
('Kevin', 3, 13),
('max', 13, 10),
('Kevin', 10, 27),
('max', 21, 26),
('Kevin', 12, 14),
('max', 4, 24),
('Kevin', 10, 4),
('max', 26, 7),
('Kevin', 6, 28),
('Spring', 6, 24),
('Lucia', 14, 25),
('Kim', 16, 58),
('Spring', 0, 19),
('Lucia', 0, 0),
('Kim', 13, 17),
('Spring', 0, 6),
('Lucia', 15, 23),
('Kim', 17, 54),
('Kim', 3, 5),
('hhhhhhh', 888, 239),
('Max', 123, 123),
('Max', 123, 123),
('test', 10, 999);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `cname` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `Email` varchar(20) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `addr` varchar(64) NOT NULL,
  `phone` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`cname`, `password`, `Email`, `firstname`, `lastname`, `addr`, `phone`) VALUES
('liy46', '816816', 'liy46@pitt.edu', 'lijia', 'yang', '5260 centre ave', '4129328567'),
('hui37', '816816', 'hui37@pitt.edu', 'lijia', 'yang', '5260 centre ave', '4129328567'),
('liy37', '816816', 'liy46@pitt.edu', 'lijia', 'yang', '5260 centre ave', '4129328567'),
('gg37', '816816', 'gg7@pitt.edu', 'lijia', 'yang', '5260 centre ave', '4129328567'),
('ed37', '816816', 'ed@pitt.edu', 'lijia', 'yang', '5260 centre ave', '4129328567'),
('hu27', '816816', 'hu27@pitt.edu', 'lijia', 'yang', '5260 centre ave', '4129328567'),
('li37', '816816', 'li37@pitt.edu', 'lijia', 'yang', '5260 centre ave', '4129328567'),
('as37', '816816', 'as7@pitt.edu', 'lijia', 'yang', '5260 centre ave', '4129328567'),
('qq37', '816816', 'qq@pitt.edu', 'lijia', 'yang', '5260 centre ave', '4129328567'),
('max', '123', 'max@pitt.edu', 'Last', 'Max', '620 main st, pittsburgh', '4123901234');

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `pid` int(11) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`pid`, `stock`) VALUES
(0, 100),
(0, 120),
(1, 100),
(2, 120),
(3, 400),
(4, 300),
(5, 100),
(6, 201),
(7, 100),
(8, 110),
(9, 122),
(10, 120);

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
`oid` int(11) NOT NULL,
  `cname` varchar(20) NOT NULL,
  `pid` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `shippingaddr` varchar(64) NOT NULL,
  `card` varchar(128) NOT NULL,
  `status` varchar(20) NOT NULL,
  `datetime` datetime NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=95 ;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`oid`, `cname`, `pid`, `quantity`, `shippingaddr`, `card`, `status`, `datetime`) VALUES
(1, 'root', 1, 12, '238 main st', 'cash', 'pending', '2015-04-15 00:00:00'),
(2, 'root', 1, 12, '238 main st', 'cash', 'pending', '2015-04-15 00:00:00'),
(3, 'root', 1, 12, '238 main st', 'cash', 'pending', '2015-04-15 23:52:32'),
(4, 'spring', 3, 12, '135 forbes ave, pittsburgh', 'cash', 'completed', '2015-04-16 00:02:02'),
(5, 'spring', 3, 12, '135 forbes ave, pittsburgh', 'cash', 'completed', '2015-04-16 00:02:35'),
(6, 'spring', 15, 84, '233 forbes ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:21:12'),
(7, 'john', 8, 58, '872 forbes ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:34'),
(8, 'max', 8, 25, '94 fifth ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:34'),
(9, 'Nancy', 27, 15, '151 fifth ave, pittsburgh', 'cash', 'cancelled', '2015-04-17 20:23:34'),
(10, 'john', 16, 29, '927 forbes ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(11, 'max', 6, 84, '850 fifth ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(12, 'Nancy', 20, 24, '821 fifth ave, pittsburgh', 'cash', 'cancelled', '2015-04-17 20:23:35'),
(13, 'john', 18, 33, '554 forbes ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(14, 'max', 17, 88, '903 fifth ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(15, 'Nancy', 10, 9, '212 fifth ave, pittsburgh', 'cash', 'cancelled', '2015-04-17 20:23:35'),
(16, 'john', 8, 90, '763 forbes ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(17, 'max', 1, 48, '342 fifth ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(18, 'Nancy', 16, 76, '424 fifth ave, pittsburgh', 'cash', 'cancelled', '2015-04-17 20:23:35'),
(19, 'john', 4, 89, '872 forbes ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(20, 'max', 0, 86, '622 fifth ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(21, 'Nancy', 2, 97, '878 fifth ave, pittsburgh', 'cash', 'cancelled', '2015-04-17 20:23:35'),
(22, 'john', 24, 97, '81 forbes ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(23, 'max', 13, 46, '61 fifth ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(24, 'Nancy', 8, 15, '90 fifth ave, pittsburgh', 'cash', 'cancelled', '2015-04-17 20:23:35'),
(25, 'john', 4, 80, '383 forbes ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(26, 'max', 17, 18, '788 fifth ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(27, 'Nancy', 8, 97, '413 fifth ave, pittsburgh', 'cash', 'cancelled', '2015-04-17 20:23:35'),
(28, 'john', 19, 40, '564 forbes ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(29, 'max', 1, 79, '288 fifth ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(30, 'Nancy', 27, 0, '153 fifth ave, pittsburgh', 'cash', 'cancelled', '2015-04-17 20:23:35'),
(31, 'john', 22, 72, '828 forbes ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(32, 'max', 14, 10, '691 fifth ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(33, 'Nancy', 0, 79, '416 fifth ave, pittsburgh', 'cash', 'cancelled', '2015-04-17 20:23:35'),
(34, 'john', 10, 64, '273 forbes ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(35, 'max', 11, 2, '595 fifth ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:23:35'),
(36, 'Nancy', 23, 9, '419 fifth ave, pittsburgh', 'cash', 'cancelled', '2015-04-17 20:23:35'),
(37, 'Tim', 4, 70, '392 Main st, pittsburgh', 'cash', 'completed', '2015-04-17 20:33:48'),
(38, 'Lucia', 26, 77, '42 Negley ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:33:48'),
(39, 'Wang Xiao', 3, 85, '586 Aikin ave, pittsburgh', 'cash', 'cancelled', '2015-04-17 20:33:48'),
(40, 'Tim', 9, 36, '655 Main st, pittsburgh', 'cash', 'completed', '2015-04-17 20:33:48'),
(41, 'Lucia', 2, 25, '181 Negley ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:33:48'),
(42, 'Wang Xiao', 0, 97, '309 Aikin ave, pittsburgh', 'cash', 'cancelled', '2015-04-17 20:33:48'),
(43, 'Tim', 25, 5, '629 Main st, pittsburgh', 'cash', 'completed', '2015-04-17 20:33:48'),
(44, 'Lucia', 8, 49, '948 Negley ave, pittsburgh', 'cash', 'completed', '2015-04-17 20:33:48'),
(45, 'Wang Xiao', 24, 97, '644 Aikin ave, pittsburgh', 'cash', 'cancelled', '2015-04-17 20:33:48'),
(46, 'john', 2, 23, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:09'),
(47, 'john', 3, 9, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:09'),
(48, 'john', 5, 8, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:09'),
(49, 'john', 15, 23, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:09'),
(50, 'john', 8, 18, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:09'),
(51, 'john', 3, 26, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:09'),
(52, 'john', 15, 9, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:09'),
(53, 'john', 10, 15, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:09'),
(54, 'john', 13, 21, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:09'),
(55, 'john', 5, 18, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:09'),
(56, 'john', 2, 9, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:09'),
(57, 'john', 8, 18, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:09'),
(58, 'john', 17, 1, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:09'),
(59, 'john', 2, 23, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:52'),
(60, 'john', 3, 9, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:52'),
(61, 'john', 5, 8, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:52'),
(62, 'john', 15, 23, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:52'),
(63, 'john', 8, 18, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:52'),
(64, 'john', 3, 26, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:52'),
(65, 'john', 15, 9, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:52'),
(66, 'john', 10, 15, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:52'),
(67, 'john', 13, 21, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:52'),
(68, 'john', 5, 18, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:52'),
(69, 'john', 2, 9, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:52'),
(70, 'john', 8, 18, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:52'),
(71, 'john', 17, 1, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:21:52'),
(72, 'john', 2, 23, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:24:59'),
(73, 'john', 3, 9, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:24:59'),
(74, 'john', 5, 8, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:24:59'),
(75, 'john', 15, 23, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:24:59'),
(76, 'john', 8, 18, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:24:59'),
(77, 'john', 3, 26, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:24:59'),
(78, 'john', 15, 9, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:24:59'),
(79, 'john', 10, 15, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:24:59'),
(80, 'john', 13, 21, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:24:59'),
(81, 'john', 5, 18, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:24:59'),
(82, 'john', 2, 9, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:24:59'),
(83, 'john', 8, 18, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:24:59'),
(84, 'john', 17, 1, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 13:24:59'),
(85, 'Nancy', 2, 11, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 18:01:51'),
(86, 'Nancy', 10, 1, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 18:01:51'),
(87, 'Nancy', 8, 6, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 18:01:51'),
(88, 'Nancy', 5, 0, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 18:01:51'),
(89, 'Nancy', 6, 20, 'sam smith, 708 Mayer st', '90530', 'CA', '2015-04-18 18:01:51'),
(90, 'Spring', 4, 23, 'sam smith, 708 Mayer st, CA, 90530', '90530', 'CA', '2015-04-18 18:36:34'),
(91, 'Spring', 4, 23, 'sam smith, 708 Mayer st, CA, 90530', '90530', 'CA', '2015-04-18 18:36:34'),
(92, 'Spring', 6, 24, 'sam smith, 708 Mayer st, CA, 90530', '90530', 'CA', '2015-04-18 18:36:34'),
(93, 'Spring', 0, 19, 'sam smith, 708 Mayer st, CA, 90530', '90530', 'CA', '2015-04-18 18:36:34'),
(94, 'Spring', 0, 6, 'sam smith, 708 Mayer st, CA, 90530', '90530', 'CA', '2015-04-18 18:36:34');

-- --------------------------------------------------------

--
-- Table structure for table `ordernum`
--

CREATE TABLE `ordernum` (
  `ordernum` int(11) NOT NULL,
  `oid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ordernum`
--

INSERT INTO `ordernum` (`ordernum`, `oid`) VALUES
(1, 28),
(1, 19),
(1, 16),
(1, 22),
(1, 13),
(1, 34),
(2, 25),
(2, 10),
(2, 7),
(3, 31),
(4, 38),
(4, 44),
(4, 41),
(5, 20),
(5, 26),
(5, 32),
(5, 29),
(6, 11),
(6, 17),
(6, 23),
(7, 14),
(7, 8),
(7, 35),
(8, 30),
(8, 18),
(8, 27),
(9, 9),
(9, 24),
(9, 12),
(10, 46),
(10, 47),
(10, 48),
(10, 49),
(10, 50),
(10, 51),
(10, 52),
(10, 53),
(10, 54),
(10, 55),
(10, 56),
(10, 57),
(10, 58),
(10, 46),
(10, 47),
(10, 48),
(10, 49),
(10, 50),
(10, 51),
(10, 52),
(10, 53),
(10, 54),
(10, 55),
(10, 56),
(10, 57),
(10, 58),
(11, 71),
(11, 72),
(11, 73),
(11, 74),
(11, 75),
(11, 76),
(11, 77),
(11, 78),
(11, 79),
(11, 80),
(11, 81),
(11, 82),
(11, 83),
(11, 11),
(11, 12),
(11, 13),
(11, 14),
(11, 15),
(12, 90),
(12, 91),
(12, 92),
(12, 93),
(12, 94);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
`pid` int(11) NOT NULL,
  `pname` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `price` double NOT NULL,
  `des` varchar(64) NOT NULL,
  `img` varchar(20) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`pid`, `pname`, `type`, `price`, `des`, `img`) VALUES
(1, 'rose', 'Best Sellers!', 2, 'Roses are best known as ornamental plants grown for their flower', 'img/flower.jpg'),
(2, 'daisy', 'Best Sellers!', 2, 'daisy are best known as ornamental plants grown for their flower', 'img/flower.jpg'),
(3, 'carnation', 'Best Sellers!', 2, 'carnation are best known as ornamental plants grown for their fl', 'img/flower.jpg'),
(4, 'violet', 'Best Sellers!', 4, 'violet are best known as ornamental plants grown for their flowe', 'img/flower.jpg'),
(5, 'rose', 'Birthday', 3.2, 'Roses are best known as ornamental plants grown for their flower', 'img/flower.jpg'),
(6, 'rose', 'Birthday', 3.2, 'Roses are best known as ornamental plants grown for their flower', 'img/flower.jpg'),
(7, 'rose', 'Christmas', 7, 'Roses are best known as ornamental plants grown for their flower', 'img/flower.jpg'),
(8, 'rose', 'Christmas', 8, 'Roses are best known as ornamental plants grown for their flower', 'img/flower.jpg'),
(9, 'rose', 'Mother''s Day', 9, 'Roses are best known as ornamental plants grown for their flower', 'img/flower.jpg'),
(10, 'rose', 'Valentine''s Day', 10, 'Roses are best known as ornamental plants grown for their flower', 'img/flower.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `order`
--
ALTER TABLE `order`
 ADD PRIMARY KEY (`oid`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
 ADD PRIMARY KEY (`pid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
MODIFY `oid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=95;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;