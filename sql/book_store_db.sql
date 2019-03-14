-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 14, 2019 at 06:49 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `book_store_db`
--
CREATE DATABASE IF NOT EXISTS `book_store_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `book_store_db`;

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE `author` (
  `id_author` int(11) NOT NULL,
  `full_name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`id_author`, `full_name`) VALUES
(1, 'Robin Parrish'),
(2, 'Bowen Greenwood'),
(3, 'Stephen King'),
(4, 'Kim Roundtree');

-- --------------------------------------------------------

--
-- Table structure for table `billing_info`
--

CREATE TABLE `billing_info` (
  `id_billing_info` int(11) NOT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `company_name` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `postcode` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `billing_info`
--

INSERT INTO `billing_info` (`id_billing_info`, `firstname`, `lastname`, `company_name`, `country`, `address`, `postcode`, `phone`, `email`, `id_user`) VALUES
(1, 'Senan', 'Kazimov', 'Company LTD', 'Belgium', 'Ozbekistan str 1', 'AZ1020', '055-555-55-55', 'senan0144@gmail.com', 1),
(2, 'Aslan', 'Ahmedov', 'Company LTD', 'Estonia', 'unknown', 'ES1010', '055-323-23-23', 'aslan@gmail.com', 5);

-- --------------------------------------------------------

--
-- Table structure for table `blog`
--

CREATE TABLE `blog` (
  `id` int(11) NOT NULL,
  `title` text NOT NULL,
  `share_date` date NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `id_book` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `desc` text NOT NULL,
  `image_path` varchar(45) DEFAULT NULL,
  `language` varchar(45) DEFAULT NULL,
  `write_date` date DEFAULT NULL,
  `id_author` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`id_book`, `title`, `desc`, `image_path`, `language`, `write_date`, `id_author`) VALUES
(1, 'Black House', 'In this sequel to The Talisman, Jack Sawyer is now in his late thirties and has taken early retirement from the LAPD, retreating to a small town in Wisconsin. He has no memory of his adventures as a twelve-year-old boy, when he traveled into a parallel universe in search of the talisman that would save his mother\'s life. A series of murders involving young children force him out of retirement. There is more to these cases than murder, though, and Jack must retrieve his childhood memories to rescue the latest victim, who is coveted by the killer\'s evil overlord, a powerful force from End-World, in Roland the gunslinger\'s universe', 'default.jpg', 'eng', '2001-09-09', 3),
(2, 'Christine', 'A love triangle involving 17-year-old misfit Arnie Cunningham, his new girlfriend and a haunted 1958 Plymouth Fury. Dubbed Christine by her previous owner, Arnie\'s first car is jealous, possessive and deadly', 'default.jpg', 'eng', '1983-09-04', 3),
(3, 'Death of Secrets', 'Kathy Kelver nearly trips over a murder victim on her way back to her dorm room late one night. In his last words, the dying man gives her stolen data about a secret project that could blow the lid off a shocking conspiracy. From the halls of Congress to the National Security Agency and beyond, Kathy must run for her life from shadowy forces who want her dead, while trying to build a relationship and hang on to her faith. The secret she\'s carrying could end the right to privacy forever, if she doesn\'t survive to warn the world', 'default.jpg', 'eng', '1995-04-20', 2),
(4, 'Nightmare', 'When Maia Peters visits during her senior year of college, she\'s not expecting to be impressed. Maia grew up as the only child of a pair of world-renowned \"ghost hunters,\" so the paranormal is nothing new. In fact, the ride feels pretty boring until the very end. There, a face appears from the mist. The face of Jordin Cole, a girl who disappeared from campus a year ago', 'default.jpg', 'eng', '2004-11-30', 1),
(5, 'Fear to Freedom', 'Fear  to  Freedom  is  a  collaboration  of  10 authors  who  share  their  fears  and  triumphs.  It  is  your  guide  to  a  life  of  faith,  favor  and  fulfillment', 'default.jpg', 'eng', '2016-03-02', 4);

-- --------------------------------------------------------

--
-- Table structure for table `book_category`
--

CREATE TABLE `book_category` (
  `id_book_category` int(11) NOT NULL,
  `id_book` int(11) DEFAULT NULL,
  `id_category` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `book_category`
--

INSERT INTO `book_category` (`id_book_category`, `id_book`, `id_category`) VALUES
(1, 1, 6),
(2, 2, 5),
(3, 3, 7),
(4, 4, 8),
(5, 4, 9),
(6, 2, 9),
(7, 5, 11),
(8, 5, 13);

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id_cart` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_book` int(11) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id_cart`, `id_user`, `id_book`, `quantity`) VALUES
(72, 1, 5, 6);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id_category` int(11) NOT NULL,
  `type` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id_category`, `type`) VALUES
(1, 'Biography'),
(2, 'Business'),
(3, 'Cookbooks'),
(4, 'Health & Fitness'),
(5, 'History'),
(6, 'Mystery'),
(7, 'Religion & Inspiration'),
(8, 'Romance'),
(9, 'Fantasy'),
(10, 'Sleeveless'),
(11, 'Science'),
(12, 'Harry Potter'),
(13, 'Self-Improvemen'),
(14, 'Home & Garden'),
(15, 'Humor books');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `description` text NOT NULL,
  `share_date` datetime NOT NULL,
  `title` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `id_review` int(11) NOT NULL,
  `desc` varchar(45) DEFAULT NULL,
  `write_date` datetime DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_book` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id_role` int(11) NOT NULL,
  `role_type` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id_role`, `role_type`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(3, 'ROLE_UNAUTHENTICATED');

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `id_stock` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `age_range` int(11) DEFAULT NULL,
  `last_added_date` datetime DEFAULT NULL,
  `id_book` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`id_stock`, `quantity`, `price`, `age_range`, `last_added_date`, `id_book`) VALUES
(1, 17, 25.95, 12, '2019-02-03 00:00:00', 1),
(2, 0, 18.95, 18, '2019-01-03 00:00:00', 2),
(3, 12, 12.95, 3, '2019-01-18 00:00:00', 3),
(4, 35, 10.95, 6, '2018-11-11 00:00:00', 4),
(5, 22, 12.95, 12, '2019-02-20 00:00:00', 5);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(45) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `token` text,
  `id_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `name`, `surname`, `username`, `password`, `email`, `status`, `token`, `id_role`) VALUES
(1, 'Senan', 'Kazimov', 'senan0144', '$2a$10$v.xHljR4LsBOEnd6DGvOHeVNByfNuiyTjG9cYapCM8BeWgBSxa8v6', 'senan0144@gmail.com', 1, '7a855c0f-3471-4ffb-9c02-5c995ebac6c3', 2),
(5, 'Aslan', 'Ahmedov', 'akmedof', '$2a$10$5A1IJyXwyKyOSz/oAz3JB.bXK3ZyYJdKfKaiOYh5g29J7rSqshcSK', 'akmedofaslan@gmail.com', 1, 'bb45bbd9-282c-449d-a219-f94ab5a82b7c', 2);

-- --------------------------------------------------------

--
-- Table structure for table `wishlist`
--

CREATE TABLE `wishlist` (
  `id_wishlist` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_book` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `wishlist`
--

INSERT INTO `wishlist` (`id_wishlist`, `id_user`, `id_book`) VALUES
(23, 5, 3),
(24, 5, 4),
(95, 1, 3),
(100, 1, 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`id_author`);

--
-- Indexes for table `billing_info`
--
ALTER TABLE `billing_info`
  ADD PRIMARY KEY (`id_billing_info`),
  ADD UNIQUE KEY `id_user_UNIQUE` (`id_user`);

--
-- Indexes for table `blog`
--
ALTER TABLE `blog`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id_book`),
  ADD KEY `fk_book_author_idx` (`id_author`);

--
-- Indexes for table `book_category`
--
ALTER TABLE `book_category`
  ADD PRIMARY KEY (`id_book_category`),
  ADD KEY `fk_book_category_book_idx` (`id_book`),
  ADD KEY `fk_book_category_category_idx` (`id_category`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id_cart`),
  ADD UNIQUE KEY `unique_index` (`id_user`,`id_book`),
  ADD KEY `fk_basket_user_idx` (`id_user`),
  ADD KEY `fk_basket_book_idx` (`id_book`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id_category`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id_review`),
  ADD KEY `fk_review_user_idx` (`id_user`),
  ADD KEY `fk_review_book_idx` (`id_book`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id_stock`),
  ADD KEY `fk_stock_book_idx` (`id_book`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `fk_user_role_idx` (`id_role`);

--
-- Indexes for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD PRIMARY KEY (`id_wishlist`),
  ADD KEY `fk_bookshelf_user_idx` (`id_user`),
  ADD KEY `fk_bookshelf_book_idx` (`id_book`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `author`
--
ALTER TABLE `author`
  MODIFY `id_author` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `billing_info`
--
ALTER TABLE `billing_info`
  MODIFY `id_billing_info` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `blog`
--
ALTER TABLE `blog`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `id_book` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `book_category`
--
ALTER TABLE `book_category`
  MODIFY `id_book_category` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id_cart` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id_category` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `id_review` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id_role` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `stock`
--
ALTER TABLE `stock`
  MODIFY `id_stock` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `wishlist`
--
ALTER TABLE `wishlist`
  MODIFY `id_wishlist` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `billing_info`
--
ALTER TABLE `billing_info`
  ADD CONSTRAINT `fk_billing_info_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `fk_book_author` FOREIGN KEY (`id_author`) REFERENCES `author` (`id_author`);

--
-- Constraints for table `book_category`
--
ALTER TABLE `book_category`
  ADD CONSTRAINT `fk_book_category_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`),
  ADD CONSTRAINT `fk_book_category_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`);

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `fk_cart_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`),
  ADD CONSTRAINT `fk_cart_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `fk_review_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_review_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE;

--
-- Constraints for table `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `fk_stock_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`);

--
-- Constraints for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD CONSTRAINT `fk_wishlist_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`),
  ADD CONSTRAINT `fk_wishlist_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
