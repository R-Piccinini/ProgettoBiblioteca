-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Giu 09, 2024 alle 04:04
-- Versione del server: 10.4.32-MariaDB
-- Versione PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `biblioteca`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `autori`
--

CREATE TABLE `autori` (
  `id` int(11) NOT NULL,
  `autore` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `autori`
--

INSERT INTO `autori` (`id`, `autore`) VALUES
(1, 'Umberto Eco'),
(2, 'Sun Tzu'),
(3, 'Zerocalcare'),
(5, 'George Orwell'),
(6, 'Omero'),
(7, 'Oscar Wilde'),
(8, 'Carlo Collodi');

-- --------------------------------------------------------

--
-- Struttura della tabella `case_editrici`
--

CREATE TABLE `case_editrici` (
  `id` int(11) NOT NULL,
  `casa_editrice` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `case_editrici`
--

INSERT INTO `case_editrici` (`id`, `casa_editrice`) VALUES
(1, 'Mondadori'),
(2, 'Feltrinelli'),
(3, 'Bao Publishing'),
(5, 'intra'),
(6, 'Rizzoli'),
(7, 'Marsilio'),
(8, 'La nave di Teseo'),
(9, 'ET Classici');

-- --------------------------------------------------------

--
-- Struttura della tabella `generi`
--

CREATE TABLE `generi` (
  `id` int(11) NOT NULL,
  `genere` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `generi`
--

INSERT INTO `generi` (`id`, `genere`) VALUES
(1, 'Romanzo storico'),
(2, 'Giallo'),
(3, 'Biografia'),
(4, 'Fumetto'),
(6, 'Romanzo Distopico'),
(7, 'Epica'),
(8, 'Romanzo Storico'),
(9, 'Saggio'),
(10, 'Mondadori'),
(11, 'Fantasy'),
(12, 'Fiaba');

-- --------------------------------------------------------

--
-- Struttura della tabella `libri`
--

CREATE TABLE `libri` (
  `id` int(11) NOT NULL,
  `titolo` varchar(100) NOT NULL,
  `id_autore` int(11) NOT NULL,
  `anno` int(11) NOT NULL,
  `id_genere` int(11) NOT NULL,
  `id_casaed` int(11) NOT NULL,
  `ISBN` varchar(14) NOT NULL,
  `qnt` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `libri`
--

INSERT INTO `libri` (`id`, `titolo`, `id_autore`, `anno`, `id_genere`, `id_casaed`, `ISBN`, `qnt`) VALUES
(1, 'Quando muori resta a me', 3, 2024, 3, 3, 'ASJFOE12', 6),
(2, 'Dimentica il mio nome', 3, 2014, 4, 2, 'SHDFIWN123', 6),
(7, '1984', 5, 2021, 6, 5, '979-1280035158', 6),
(8, 'Odissea', 6, 2010, 7, 6, '978-8817020718', 2),
(9, 'Illiade', 6, 2018, 7, 7, '978-8831743020', 5),
(10, 'Il nome della Rosa', 1, 2020, 8, 8, '978-8834603000', 1),
(11, 'L\'arte della guerra', 2, 2013, 9, 2, '978-8807900525', 4),
(12, 'Costruire il nemico', 1, 2020, 9, 8, '978-8834603321', 1),
(13, 'Il ritratto di Dorian Grey', 7, 2021, 11, 1, '978-8804737506', 3),
(14, 'Pinocchio', 8, 2014, 12, 9, '978-8806223410', 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `prestiti`
--

CREATE TABLE `prestiti` (
  `id` int(11) NOT NULL,
  `inizio_prestito` date NOT NULL,
  `fine_prestito` date DEFAULT NULL,
  `id_utente` int(11) NOT NULL,
  `id_libro` int(11) NOT NULL,
  `storico` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `prestiti`
--

INSERT INTO `prestiti` (`id`, `inizio_prestito`, `fine_prestito`, `id_utente`, `id_libro`, `storico`) VALUES
(7, '2024-06-08', NULL, 3, 1, 'aperto'),
(8, '2024-06-08', '2024-06-08', 4, 10, 'chiuso'),
(9, '2024-06-08', NULL, 5, 13, 'aperto'),
(10, '2024-06-08', NULL, 1, 11, 'aperto'),
(11, '2024-06-08', NULL, 1, 12, 'aperto'),
(12, '2024-06-08', '2024-06-08', 3, 7, 'chiuso'),
(13, '2024-06-08', NULL, 5, 2, 'aperto');

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `cognome` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `CF` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`id`, `nome`, `cognome`, `email`, `telefono`, `CF`) VALUES
(1, 'Mario', 'Rossi', '123@gmail.com', '22050400', 'DFGHGHDK1234'),
(3, 'Carlo', 'Verdi', 'carlo.verdi@gmail.com', '232334455', 'PRC23405EOE'),
(4, 'Paolo', 'Gialli', 'paolo.gialli@hotmail.it', '5005339', 'FFQ2295556'),
(5, 'Rosa', 'Santi', 'rosa123@libero.it', '66664332', 'RCS456PQ12');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `autori`
--
ALTER TABLE `autori`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `case_editrici`
--
ALTER TABLE `case_editrici`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `generi`
--
ALTER TABLE `generi`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `libri`
--
ALTER TABLE `libri`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ISBN` (`ISBN`),
  ADD KEY `id_autore` (`id_autore`,`id_genere`,`id_casaed`),
  ADD KEY `id_genere` (`id_genere`),
  ADD KEY `id_casaed` (`id_casaed`);

--
-- Indici per le tabelle `prestiti`
--
ALTER TABLE `prestiti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_utente` (`id_utente`,`id_libro`),
  ADD KEY `id_libro` (`id_libro`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `CF` (`CF`) USING BTREE,
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `autori`
--
ALTER TABLE `autori`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella `case_editrici`
--
ALTER TABLE `case_editrici`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT per la tabella `generi`
--
ALTER TABLE `generi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT per la tabella `libri`
--
ALTER TABLE `libri`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT per la tabella `prestiti`
--
ALTER TABLE `prestiti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `libri`
--
ALTER TABLE `libri`
  ADD CONSTRAINT `libri_ibfk_1` FOREIGN KEY (`id_genere`) REFERENCES `generi` (`id`),
  ADD CONSTRAINT `libri_ibfk_2` FOREIGN KEY (`id_casaed`) REFERENCES `case_editrici` (`id`),
  ADD CONSTRAINT `libri_ibfk_3` FOREIGN KEY (`id_autore`) REFERENCES `autori` (`id`);

--
-- Limiti per la tabella `prestiti`
--
ALTER TABLE `prestiti`
  ADD CONSTRAINT `prestiti_ibfk_1` FOREIGN KEY (`id_utente`) REFERENCES `utenti` (`id`),
  ADD CONSTRAINT `prestiti_ibfk_2` FOREIGN KEY (`id_libro`) REFERENCES `libri` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
