-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 06. Apr 2020 um 11:46
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
-- Datenbank: `digital_notice`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `notice_book`
--

CREATE TABLE `notice_book` (
  `number_of_pages` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `notice_head` varchar(100) NOT NULL,
  `notice` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `notice_book`
--

INSERT INTO `notice_book` (`number_of_pages`, `date`, `notice_head`, `notice`) VALUES
(1, '2020-04-02 10:01:05', 'test', 'dies ist ein test'),
(2, '2020-04-02 10:01:43', '1. abfrage', 'hier sollte:\r\n(\"HelloWorld\")\r\nstehen'),
(3, '2020-04-02 10:36:53', 'hi', 'Yeahhh'),
(4, '2020-04-02 10:39:38', 'Yeahh', ' hi'),
(5, '2020-04-02 10:39:51', 'Yeahh', ' hi'),
(6, '2020-04-02 10:40:16', 'Yeahh', ' hi'),
(7, '2020-04-02 10:41:22', ' es funkt', 'Das geht ja wunderrrbar'),
(8, '2020-04-02 10:42:57', ' es funkt', 'Das geht ja wunderrrbar');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `notice_book`
--
ALTER TABLE `notice_book`
  ADD PRIMARY KEY (`number_of_pages`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `notice_book`
--
ALTER TABLE `notice_book`
  MODIFY `number_of_pages` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
