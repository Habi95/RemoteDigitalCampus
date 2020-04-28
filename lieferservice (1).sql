-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 28. Apr 2020 um 10:19
-- Server-Version: 10.4.11-MariaDB
-- PHP-Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `lieferservice`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `boolean`
--

CREATE TABLE `boolean` (
  `id` tinyint(1) NOT NULL,
  `boolean` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `boolean`
--

INSERT INTO `boolean` (`id`, `boolean`) VALUES
(0, 'false'),
(1, 'true');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `delivery`
--

CREATE TABLE `delivery` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `place` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `delivery`
--

INSERT INTO `delivery` (`id`, `name`, `place`) VALUES
(1, 'QuickFood', 'Nenzing');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `delivery_places`
--

CREATE TABLE `delivery_places` (
  `id` double NOT NULL,
  `place` varchar(100) NOT NULL,
  `delivery_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `delivery_places`
--

INSERT INTO `delivery_places` (`id`, `place`, `delivery_price`) VALUES
(1, 'Nenzing', 0),
(2, 'Bludesch', 3),
(3, 'Thüringen', 5),
(4, 'Frastanz', 5),
(5, 'Schlins', 2),
(6, 'Feldkirch', 10),
(7, 'Bludenz', 10),
(8, 'Nüziders', 8),
(9, 'Ludesch', 5);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `dishes`
--

CREATE TABLE `dishes` (
  `id` double NOT NULL,
  `dish_name` varchar(100) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `dishes`
--

INSERT INTO `dishes` (`id`, `dish_name`, `type`) VALUES
(1, 'Pizza Margherita', 2),
(2, 'Pizza Salami', 2),
(3, 'Pizza Schinken', 2),
(4, 'Pizza Calzone', 2),
(5, 'Pizza Speciale', 2),
(6, 'Gnocci Brokkoli', 2),
(7, 'Spaghetti al Nona', 2),
(8, 'Bruschetta', 1),
(9, 'Tomatensupppe mit Pizzabrot', 1),
(10, 'Pizza Fantasia', 2),
(11, 'Tomaten Mozzarella mit Pizzabrot', 1),
(12, 'Knoblauch Baguette', 1),
(13, 'Pizza Brot', 1),
(14, 'Knoblauch Rahmsuppe mit Pizzabrot', 1),
(15, 'Pizzabrot Salami', 1),
(16, 'Pizzabrot Schinken', 1),
(17, 'Spaghetti Cabonara', 2),
(18, 'Gnocci al Cabonara', 2),
(19, 'Tortellini alla Panna', 2),
(21, 'Spaghetti Brokkoli', 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `dish_ingridients`
--

CREATE TABLE `dish_ingridients` (
  `id` int(11) NOT NULL,
  `dish_id` double NOT NULL,
  `ing_id` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `dish_ingridients`
--

INSERT INTO `dish_ingridients` (`id`, `dish_id`, `ing_id`) VALUES
(1, 8, 2),
(2, 8, 4),
(3, 8, 10),
(4, 8, 13),
(5, 8, 14),
(6, 9, 1),
(7, 9, 9),
(8, 9, 13),
(9, 9, 14),
(10, 1, 1),
(11, 1, 2),
(12, 1, 3),
(13, 1, 4),
(14, 2, 1),
(15, 2, 2),
(16, 2, 3),
(17, 2, 5),
(18, 3, 1),
(19, 3, 2),
(20, 3, 3),
(21, 3, 6),
(22, 4, 1),
(23, 4, 2),
(24, 4, 3),
(25, 4, 5),
(26, 4, 6),
(27, 5, 1),
(28, 5, 2),
(29, 5, 3),
(30, 5, 6),
(31, 5, 7),
(32, 5, 8),
(33, 6, 8),
(34, 6, 9),
(35, 6, 10),
(36, 6, 11),
(37, 6, 13),
(38, 7, 1),
(39, 7, 4),
(40, 7, 7),
(41, 7, 9),
(42, 7, 10),
(43, 7, 12),
(44, 10, 1),
(45, 10, 2),
(46, 10, 3),
(47, 10, 13),
(48, 10, 10),
(49, 10, 7),
(50, 10, 4),
(51, 11, 15),
(52, 11, 4),
(53, 11, 13),
(54, 11, 14),
(55, 11, 2),
(56, 12, 14),
(57, 12, 13),
(58, 13, 14),
(59, 13, 1),
(60, 13, 15),
(61, 14, 9),
(62, 14, 13),
(63, 14, 14),
(64, 14, 15),
(65, 15, 1),
(66, 15, 5),
(67, 15, 14),
(68, 15, 15),
(69, 16, 1),
(70, 16, 2),
(71, 16, 6),
(72, 16, 15),
(73, 17, 12),
(74, 17, 13),
(75, 17, 6),
(76, 17, 10),
(77, 17, 9),
(78, 18, 11),
(79, 18, 9),
(80, 18, 6),
(81, 18, 10),
(82, 18, 13),
(83, 19, 16),
(84, 19, 17),
(85, 19, 6),
(86, 19, 9),
(87, 19, 13);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ingridients`
--

CREATE TABLE `ingridients` (
  `id` double NOT NULL,
  `ing_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `ingridients`
--

INSERT INTO `ingridients` (`id`, `ing_name`) VALUES
(1, 'Tomatensauce'),
(2, 'Käse'),
(3, 'Pizzateig 100g'),
(4, 'Tomaten'),
(5, 'Salami'),
(6, 'Schinken'),
(7, 'Speck'),
(8, 'Brokkoli'),
(9, 'Sahne'),
(10, 'Parmesan'),
(11, 'Gnocchi'),
(12, 'Spaghetti'),
(13, 'Knoblauch'),
(14, 'Pizzabrot'),
(15, 'Mozzarella'),
(16, 'Hackfleisch'),
(17, 'Tortellini');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `menu`
--

CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `wirt_id` int(11) NOT NULL,
  `dish_id` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `menu`
--

INSERT INTO `menu` (`id`, `wirt_id`, `dish_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 1, 6),
(7, 1, 7),
(8, 1, 8),
(9, 1, 9),
(10, 1, 10),
(11, 1, 11),
(12, 1, 12),
(13, 1, 13),
(14, 1, 14),
(15, 1, 15),
(16, 1, 16),
(17, 1, 17),
(18, 1, 18),
(19, 1, 19);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `order_detail`
--

CREATE TABLE `order_detail` (
  `id` double NOT NULL,
  `order_id` double NOT NULL,
  `dish_order_id` double NOT NULL,
  `price_order_dish_id` double NOT NULL,
  `is_free_delivery` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `order_detail`
--

INSERT INTO `order_detail` (`id`, `order_id`, `dish_order_id`, `price_order_dish_id`, `is_free_delivery`) VALUES
(3, 4, 7, 7, 0),
(4, 5, 18, 18, 0),
(5, 6, 7, 7, 0),
(6, 7, 4, 4, 1),
(7, 8, 19, 19, 1),
(8, 9, 19, 19, 1),
(9, 10, 19, 19, 1),
(10, 10, 4, 4, 1),
(11, 11, 7, 7, 0),
(12, 12, 8, 8, 1),
(13, 12, 17, 17, 1),
(14, 13, 5, 5, 0),
(15, 14, 19, 19, 0),
(16, 15, 6, 6, 0),
(17, 16, 7, 7, 1),
(18, 16, 7, 7, 1),
(19, 16, 7, 7, 1),
(20, 16, 7, 7, 1),
(21, 17, 8, 8, 1),
(22, 17, 19, 19, 1),
(23, 18, 5, 5, 1),
(24, 18, 4, 4, 1),
(25, 19, 8, 8, 1),
(26, 19, 18, 18, 1),
(27, 20, 14, 14, 1),
(28, 20, 1, 1, 1),
(29, 21, 12, 12, 0),
(30, 21, 18, 18, 0),
(31, 22, 15, 15, 1),
(32, 22, 17, 17, 1),
(33, 23, 8, 8, 1),
(34, 23, 19, 19, 1),
(35, 24, 14, 14, 1),
(36, 24, 7, 7, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `order_detail_ingriedients`
--

CREATE TABLE `order_detail_ingriedients` (
  `id` double NOT NULL,
  `order_detail_id` double NOT NULL,
  `ing_order_id` double NOT NULL,
  `removed_ing` tinyint(1) NOT NULL,
  `added_ing` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `order_detail_ingriedients`
--

INSERT INTO `order_detail_ingriedients` (`id`, `order_detail_id`, `ing_order_id`, `removed_ing`, `added_ing`) VALUES
(1, 6, 5, 1, 1),
(2, 7, 13, 0, 0),
(3, 8, 15, 0, 0),
(4, 8, 3, 0, 0),
(5, 15, 7, 0, 1),
(6, 21, 13, 0, 1),
(7, 22, 13, 0, 1),
(8, 23, 7, 0, 1),
(9, 24, 7, 0, 1),
(11, 26, 15, 0, 1),
(12, 27, 7, 0, 0),
(13, 27, 7, 0, 0),
(14, 27, 7, 0, 0),
(15, 28, 7, 0, 0),
(16, 29, 7, 0, 1),
(17, 30, 7, 0, 1),
(18, 31, 10, 0, 1),
(19, 32, 10, 0, 1),
(20, 33, 7, 0, 1),
(21, 35, 11, 0, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `price`
--

CREATE TABLE `price` (
  `id` double NOT NULL,
  `dish_id` double NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `price`
--

INSERT INTO `price` (`id`, `dish_id`, `price`) VALUES
(1, 1, 8.5),
(2, 2, 9),
(3, 3, 9),
(4, 4, 9),
(5, 5, 9),
(6, 6, 7),
(7, 7, 9),
(8, 8, 6.5),
(9, 9, 6.5),
(10, 3, 9),
(11, 11, 7),
(12, 12, 5),
(13, 13, 6.5),
(14, 14, 7),
(15, 15, 7),
(16, 16, 6.5),
(17, 17, 7.8),
(18, 18, 8),
(19, 19, 9.5);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `typ_of_dish`
--

CREATE TABLE `typ_of_dish` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `typ_of_dish`
--

INSERT INTO `typ_of_dish` (`id`, `name`) VALUES
(1, 'Vorspeise'),
(2, 'Hauptspeise');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `user_id` double NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `place` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`user_id`, `email`, `password`, `place`) VALUES
(1, 'luca.windisch@gmx.at', '1234', 'Nenzing'),
(2, 'hilde.schwald@gmx.at', '1234', 'Bludesch'),
(3, 'peter.müller@gmx.at', '1234', 'Schlins'),
(4, 'h.janine1@gmail.com', 'lala', 'nenzing'),
(5, 'franz.müller@hotmail.com', '1234', 'Bludesch'),
(6, 'hilde.oswald@gmx.at', '1234', 'Feldkirch'),
(7, 'franzi.alfi89@hotmail.com', 'hilde93', 'Feldkirch'),
(8, 'bruno.horst@gmx.at', '1234', 'Bludesch'),
(9, 'greta.heinrich@gmail.com', '1234', 'Schlins'),
(10, 'gerturde.gärtner@gmx.at', '1234', 'Nenzing');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user_order`
--

CREATE TABLE `user_order` (
  `id` double NOT NULL,
  `user_id` double NOT NULL,
  `total_price` double NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `user_order`
--

INSERT INTO `user_order` (`id`, `user_id`, `total_price`, `time`) VALUES
(2, 1, 9.75, '2020-04-22 11:47:39'),
(3, 1, 9.75, '2020-04-22 11:47:39'),
(4, 1, 9.75, '2020-04-22 11:47:39'),
(5, 1, 8, '2020-04-22 11:47:39'),
(6, 1, 9.75, '2020-04-22 11:47:39'),
(7, 1, 17.75, '2020-04-22 11:47:39'),
(8, 1, 19.25, '2020-04-22 11:47:39'),
(9, 1, 19.25, '2020-04-22 11:47:39'),
(10, 1, 19.25, '2020-04-22 11:47:39'),
(11, 1, 9.75, '2020-04-22 11:47:39'),
(12, 6, 15.05, '2020-04-22 11:47:39'),
(13, 5, 12, '2020-04-22 11:47:39'),
(14, 1, 10.25, '2020-04-22 11:47:39'),
(15, 2, 10, '2020-04-22 11:47:39'),
(16, 1, 36, '2020-04-22 11:47:39'),
(17, 6, 16.75, '2020-04-27 10:17:00'),
(18, 5, 18.75, '2020-04-27 10:27:33'),
(19, 1, 15.25, '2020-04-27 10:45:52'),
(20, 1, 16.25, '2020-04-27 10:53:48'),
(21, 2, 16.75, '2020-04-27 11:01:42'),
(22, 1, 15.55, '2020-04-27 11:23:34'),
(23, 1, 16.75, '2020-04-27 11:47:24'),
(24, 3, 16.75, '2020-04-27 12:03:22');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `wirt`
--

CREATE TABLE `wirt` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `place` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `wirt`
--

INSERT INTO `wirt` (`id`, `name`, `place`) VALUES
(1, 'Castello', 'Nenzing');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `boolean`
--
ALTER TABLE `boolean`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `delivery`
--
ALTER TABLE `delivery`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `delivery_places`
--
ALTER TABLE `delivery_places`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `dishes`
--
ALTER TABLE `dishes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `typ` (`type`);

--
-- Indizes für die Tabelle `dish_ingridients`
--
ALTER TABLE `dish_ingridients`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dishes_id` (`dish_id`),
  ADD KEY `ingr_id` (`ing_id`);

--
-- Indizes für die Tabelle `ingridients`
--
ALTER TABLE `ingridients`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `wirt_id` (`wirt_id`),
  ADD KEY `dish_id` (`dish_id`);

--
-- Indizes für die Tabelle `order_detail`
--
ALTER TABLE `order_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `dish_order_id` (`dish_order_id`),
  ADD KEY `price_dishID_ID` (`price_order_dish_id`),
  ADD KEY `free_delivery_boolean` (`is_free_delivery`);

--
-- Indizes für die Tabelle `order_detail_ingriedients`
--
ALTER TABLE `order_detail_ingriedients`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_detail_id` (`order_detail_id`),
  ADD KEY `order_ing_id` (`ing_order_id`),
  ADD KEY `remove_boolean_id` (`removed_ing`),
  ADD KEY `added_boolean_id` (`added_ing`);

--
-- Indizes für die Tabelle `price`
--
ALTER TABLE `price`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dish_mkprice_id` (`dish_id`);

--
-- Indizes für die Tabelle `typ_of_dish`
--
ALTER TABLE `typ_of_dish`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indizes für die Tabelle `user_order`
--
ALTER TABLE `user_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_order_id` (`user_id`);

--
-- Indizes für die Tabelle `wirt`
--
ALTER TABLE `wirt`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `boolean`
--
ALTER TABLE `boolean`
  MODIFY `id` tinyint(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `delivery`
--
ALTER TABLE `delivery`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT für Tabelle `delivery_places`
--
ALTER TABLE `delivery_places`
  MODIFY `id` double NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT für Tabelle `dishes`
--
ALTER TABLE `dishes`
  MODIFY `id` double NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT für Tabelle `dish_ingridients`
--
ALTER TABLE `dish_ingridients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=88;

--
-- AUTO_INCREMENT für Tabelle `ingridients`
--
ALTER TABLE `ingridients`
  MODIFY `id` double NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT für Tabelle `menu`
--
ALTER TABLE `menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT für Tabelle `order_detail`
--
ALTER TABLE `order_detail`
  MODIFY `id` double NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT für Tabelle `order_detail_ingriedients`
--
ALTER TABLE `order_detail_ingriedients`
  MODIFY `id` double NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT für Tabelle `price`
--
ALTER TABLE `price`
  MODIFY `id` double NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT für Tabelle `typ_of_dish`
--
ALTER TABLE `typ_of_dish`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `user_id` double NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT für Tabelle `user_order`
--
ALTER TABLE `user_order`
  MODIFY `id` double NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT für Tabelle `wirt`
--
ALTER TABLE `wirt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `dishes`
--
ALTER TABLE `dishes`
  ADD CONSTRAINT `typ` FOREIGN KEY (`type`) REFERENCES `typ_of_dish` (`id`);

--
-- Constraints der Tabelle `dish_ingridients`
--
ALTER TABLE `dish_ingridients`
  ADD CONSTRAINT `dishes_id` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
  ADD CONSTRAINT `ingr_id` FOREIGN KEY (`ing_id`) REFERENCES `ingridients` (`id`);

--
-- Constraints der Tabelle `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `dish_id` FOREIGN KEY (`dish_id`) REFERENCES `dish_ingridients` (`dish_id`),
  ADD CONSTRAINT `wirt_id` FOREIGN KEY (`wirt_id`) REFERENCES `wirt` (`id`);

--
-- Constraints der Tabelle `order_detail`
--
ALTER TABLE `order_detail`
  ADD CONSTRAINT `dish_order_id` FOREIGN KEY (`dish_order_id`) REFERENCES `dish_ingridients` (`dish_id`),
  ADD CONSTRAINT `free_delivery_boolean` FOREIGN KEY (`is_free_delivery`) REFERENCES `boolean` (`id`),
  ADD CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `user_order` (`id`),
  ADD CONSTRAINT `price_dishID_ID` FOREIGN KEY (`price_order_dish_id`) REFERENCES `price` (`id`);

--
-- Constraints der Tabelle `order_detail_ingriedients`
--
ALTER TABLE `order_detail_ingriedients`
  ADD CONSTRAINT `added_boolean_id` FOREIGN KEY (`added_ing`) REFERENCES `boolean` (`id`),
  ADD CONSTRAINT `order_detail_id` FOREIGN KEY (`order_detail_id`) REFERENCES `order_detail` (`id`),
  ADD CONSTRAINT `order_ing_id` FOREIGN KEY (`ing_order_id`) REFERENCES `ingridients` (`id`),
  ADD CONSTRAINT `remove_boolean_id` FOREIGN KEY (`removed_ing`) REFERENCES `boolean` (`id`);

--
-- Constraints der Tabelle `price`
--
ALTER TABLE `price`
  ADD CONSTRAINT `dish_mkprice_id` FOREIGN KEY (`dish_id`) REFERENCES `dish_ingridients` (`dish_id`);

--
-- Constraints der Tabelle `user_order`
--
ALTER TABLE `user_order`
  ADD CONSTRAINT `user_order_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
