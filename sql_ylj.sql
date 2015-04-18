-- phpMyAdmin SQL Dump
-- version 4.2.5
-- http://www.phpmyadmin.net
--
-- Host: localhost:8889
-- Generation Time: Apr 18, 2015 at 02:58 AM
-- Server version: 5.5.38
-- PHP Version: 5.5.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `flower_store`
--
create database if not exists flower_store;
use flower_store;
-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cname` varchar(20) NOT NULL,
  `pid` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `sname` varchar(20) NOT NULL,
  `addr` varchar(64) NOT NULL,
  `phone` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`cname`, `password`, `Email`, `firstname`, `lastname`, `sname`, `addr`, `phone`) VALUES
('liy46', '816816', 'liy46@pitt.edu', 'lijia', 'yang', 's2', '5260 centre ave', '4129328567'),
('hui37', '816816', 'hui37@pitt.edu', 'lijia', 'yang', 's2', '5260 centre ave', '4129328567'),
('liy37', '816816', 'liy46@pitt.edu', 'lijia', 'yang', 's2', '5260 centre ave', '4129328567'),
('gg37', '816816', 'gg7@pitt.edu', 'lijia', 'yang', 's2', '5260 centre ave', '4129328567'),
('ed37', '816816', 'ed@pitt.edu', 'lijia', 'yang', 's2', '5260 centre ave', '4129328567'),
('hu27', '816816', 'hu27@pitt.edu', 'lijia', 'yang', 's2', '5260 centre ave', '4129328567'),
('li37', '816816', 'li37@pitt.edu', 'lijia', 'yang', 's2', '5260 centre ave', '4129328567'),
('as37', '816816', 'as7@pitt.edu', 'lijia', 'yang', 's2', '5260 centre ave', '4129328567'),
('qq37', '816816', 'qq@pitt.edu', 'lijia', 'yang', 's2', '5260 centre ave', '4129328567');

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
  `cname` varchar(20) NOT NULL,
  `pid` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `shippingaddr` varchar(64) NOT NULL,
  `card` varchar(128) NOT NULL,
  `status` varchar(20) NOT NULL,
  `datetime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(1, 'rose', 'birthday', 2, 'Roses are best known as ornamental plants grown for their flower', '/web/img/flower.jpg'),
(2, 'daisy', 'valentine', 2, 'daisy are best known as ornamental plants grown for their flower', '/web/img/flower.jpg'),
(3, 'carnation', 'mothers day', 2, 'carnation are best known as ornamental plants grown for their fl', '/web/img/flower.jpg'),
(4, 'violet', 'birthday', 4, 'violet are best known as ornamental plants grown for their flowe', '/web/img/flower.jpg'),
(5, 'rose', 'birthday', 3.2, 'Roses are best known as ornamental plants grown for their flower', '/web/img/flower.jpg'),
(6, 'rose', 'birthday', 3.2, 'Roses are best known as ornamental plants grown for their flower', '/web/img/flower.jpg'),
(7, 'rose', 'birthday', 7, 'Roses are best known as ornamental plants grown for their flower', '/web/img/flower.jpg'),
(8, 'rose', 'birthday', 8, 'Roses are best known as ornamental plants grown for their flower', '/web/img/flower.jpg'),
(9, 'rose', 'birthday', 9, 'Roses are best known as ornamental plants grown for their flower', '/web/img/flower.jpg'),
(10, 'rose', 'birthday', 10, 'Roses are best known as ornamental plants grown for their flower', '/web/img/flower.jpg');

-- --------------------------------------------------------


--
-- Indexes for table `product`
--
ALTER TABLE `product`
 ADD PRIMARY KEY (`pid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;