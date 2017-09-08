-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 09, 2017 at 11:33 AM
-- Server version: 5.7.14
-- PHP Version: 7.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `konferencije`
--

-- --------------------------------------------------------

--
-- Table structure for table `galerija`
--

CREATE TABLE `galerija` (
  `idslika` int(32) NOT NULL,
  `sesija` int(32) NOT NULL,
  `slika` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `galerija`
--

INSERT INTO `galerija` (`idslika`, `sesija`, `slika`) VALUES
(18, 30, '265249.80848419986k22.jpg'),
(19, 30, '314600.95599961194k11.jpg'),
(20, 30, '955405.6079019752k33.jpg'),
(21, 35, '411637.5022793155k22.jpg'),
(22, 35, '194513.46350086128k33.jpg'),
(23, 21, '736440.1902526111k22.jpg'),
(24, 28, '490793.56408208306k33.jpg'),
(25, 24, '496839.0278217926k11.jpg'),
(26, 25, '324314.44715129375k22.jpg'),
(27, 29, '561454.785731453download.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE `komentar` (
  `idkomentar` int(32) NOT NULL,
  `predavanje` int(32) NOT NULL,
  `korisnik` varchar(32) NOT NULL,
  `komentar` varchar(150) NOT NULL,
  `ocena` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `konferencija`
--

CREATE TABLE `konferencija` (
  `idkonf` int(32) NOT NULL,
  `naziv` varchar(32) DEFAULT NULL,
  `moderator` varchar(32) NOT NULL,
  `datumOd` date DEFAULT NULL,
  `datumDo` date DEFAULT NULL,
  `oblast` int(5) NOT NULL,
  `mesto` int(5) NOT NULL,
  `sifra` varchar(61) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `konferencija`
--

INSERT INTO `konferencija` (`idkonf`, `naziv`, `moderator`, `datumOd`, `datumDo`, `oblast`, `mesto`, `sifra`) VALUES
(12, 'Web programiranje', 'misa', '2017-07-12', '2017-07-16', 2, 2, 'jsf'),
(13, 'Lepota baste', 'raca', '2017-07-21', '2017-07-23', 9, 1, 'cvet'),
(14, 'Gradnja zamka u warcraftu', 'duki', '2017-07-30', '2017-07-31', 3, 6, 'wow'),
(15, 'Razvoj celije', 'micamicasrbin', '2017-08-12', '2017-08-16', 8, 6, '123'),
(16, 'Marihuana za lecenje diskusija', 'micamicasrbin', '2017-08-18', '2017-08-19', 6, 2, '123');

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `username` varchar(32) NOT NULL,
  `lozinka` varchar(32) NOT NULL,
  `ime` varchar(32) DEFAULT NULL,
  `prezime` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `institucija` varchar(32) DEFAULT NULL,
  `pol` varchar(32) DEFAULT NULL COMMENT 'muski/zenski',
  `slika` varchar(64) DEFAULT 'muski.png',
  `majca` varchar(10) DEFAULT NULL,
  `linkedin` varchar(64) DEFAULT NULL,
  `tip` varchar(32) NOT NULL COMMENT 'korisnik/moderator/admin'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`username`, `lozinka`, `ime`, `prezime`, `email`, `institucija`, `pol`, `slika`, `majca`, `linkedin`, `tip`) VALUES
('admin', '123', 'Admin', 'Adminski', 'admin@gmail.com', 'Konferencij.org', 'zenski', 'muski.png', 'l', 'dawd', 'admin'),
('Doktor Bozidar', '1', NULL, NULL, NULL, NULL, 'muski', 'muski.png', NULL, NULL, 'korisnik'),
('Doktor Joco', '1', NULL, NULL, NULL, NULL, 'muski', 'muski.png', NULL, NULL, 'korisnik'),
('Doktor Mengele', '1', NULL, NULL, NULL, NULL, 'muski', 'muski.png', NULL, NULL, 'korisnik'),
('Doktor Milorad', '1', NULL, NULL, NULL, NULL, 'muski', 'muski.png', NULL, NULL, 'korisnik'),
('Doktor Miso', '1', NULL, NULL, NULL, NULL, 'muski', 'muski.png', NULL, NULL, 'korisnik'),
('duki', '321', 'Dusan', 'Drakulic', 'WoWash@gmail', 'WoW', 'muski', '608868.1120452964IMG_0715.JPG', 'X', 'nema', 'moderator'),
('joca', '123', 'Joca', 'Perisic', 'nema', 'Sam svoj gazda', 'muski', '864295.1678930173IMG_0721.JPG', 'XXL', 'nema', 'korisnik'),
('micamicasrbin', '123', 'Uros', 'Stojiljkovic', '123@gmai.com', 'Info sistemi BG', 'muski', 'muski.png', NULL, NULL, 'moderator'),
('mika', '123', 'Mihajlo', 'Drakulic', 'nema', 'nema', 'muski', '871720.9601435476IMG_0507.JPG', 'M', 'nema', 'korisnik'),
('Milorad Petrovic', '1', NULL, NULL, NULL, NULL, 'muski', 'muski.png', NULL, NULL, 'korisnik'),
('misa', '123', 'Milan', 'Drakulic', 'misa@gmail.com', 'itnesto', 'muski', 'muski.png', 'L', '2e124312', 'moderator'),
('raca', '123', 'Radmila', 'Drakulic', 'raca@gmal.com', 'Tehnicka skola ', 'zenski', '496294.8901524932IMG_0688.JPG', 'XL', 'nema', 'moderator'),
('Strucnjak Cile', '1', NULL, NULL, NULL, NULL, 'muski', 'muski.png', NULL, NULL, 'korisnik'),
('tanjica', '123', 'Tanja ', 'Drakulic', 'tanja@gmail.com', 'Vrtic Pcelica', 'zenski', '797730.7545677318IMG_0545.JPG', 'M', 'nema', 'korisnik'),
('zika', '123', 'Zika', 'Zikic', 'nema', 'nema', 'muski', '89913.54604504631wood-texture_13.jpg', 'L', 'nema', 'korisnik'),
('Zivan', '1', NULL, NULL, NULL, NULL, 'muski', 'muski.png', NULL, NULL, 'korisnik');

-- --------------------------------------------------------

--
-- Table structure for table `mesto`
--

CREATE TABLE `mesto` (
  `idmesto` int(32) NOT NULL,
  `grad` varchar(32) NOT NULL,
  `drzava` varchar(32) NOT NULL,
  `lokacija` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mesto`
--

INSERT INTO `mesto` (`idmesto`, `grad`, `drzava`, `lokacija`) VALUES
(1, 'Beograd', 'Srbija', 'Dom Kulture Smederevo'),
(2, 'Smederevo', 'Srbija', 'Sumice Beograd'),
(3, 'Novi Sad', 'Srbija', 'Spens Novi Sad'),
(4, 'Beograd', 'Srbija', 'Opstina Vozdovac Beograd'),
(5, 'Beograd', 'Srbija', 'Dom Sindikata Beograd'),
(6, 'Smederevo', 'Srbija', 'Bioskop Svetlosti Smederevo'),
(7, 'Beograd', 'Srbija', 'Startit centar Beograd'),
(8, 'Nis', 'Srbija', 'Startit centar Nis');

-- --------------------------------------------------------

--
-- Table structure for table `oblast`
--

CREATE TABLE `oblast` (
  `idoblast` int(32) NOT NULL,
  `oblast` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `oblast`
--

INSERT INTO `oblast` (`idoblast`, `oblast`) VALUES
(2, 'elektrotehnika i racunarstvo'),
(3, 'arhitektura'),
(4, 'gradjevina i geodezija'),
(5, 'masinstvo i industrisko inzinjerstvo'),
(6, 'medicina'),
(7, 'fizicke i hemijske nauke'),
(8, 'bioloske nauke'),
(9, 'zastita zivotne sredine');

-- --------------------------------------------------------

--
-- Table structure for table `poruka`
--

CREATE TABLE `poruka` (
  `idporuka` int(32) NOT NULL,
  `posiljalac` varchar(32) NOT NULL,
  `primalac` varchar(32) NOT NULL,
  `datum` datetime NOT NULL,
  `text` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `poruka`
--

INSERT INTO `poruka` (`idporuka`, `posiljalac`, `primalac`, `datum`, `text`) VALUES
(5, 'micamicasrbin', 'micamicasrbin', '2017-06-22 02:45:29', '421412451'),
(7, 'micamicasrbin', 'admin', '2017-07-06 22:04:58', 'cao druze kako si'),
(9, 'admin', 'misa', '2017-07-07 18:57:42', 'konferencija1 se otkazuje'),
(10, 'admin', 'micamicasrbin', '2017-07-07 18:57:42', 'konferencija1 se otkazuje'),
(11, 'admin', 'micamicasrbin', '2017-07-07 18:57:42', 'konferencija1 se otkazuje'),
(12, 'admin', 'micamicasrbin', '2017-07-07 18:57:42', 'konferencija1 se otkazuje'),
(14, 'micamicasrbin', 'admin', '2017-07-08 00:14:27', 'To kralju ovo radi'),
(15, 'micamicasrbin', 'admin', '2017-07-08 00:19:55', 'cao druze'),
(16, 'micamicasrbin', 'admin', '2017-07-08 00:20:20', 'e sta ima novo'),
(17, 'micamicasrbin', 'Doktor Joco', '2017-07-08 00:21:08', 'DJE SI JOCO LEGENDO'),
(18, 'micamicasrbin', 'joca', '2017-07-08 00:21:28', 'cao smeksi ;)\r\n'),
(19, 'joca', 'micamicasrbin', '2017-07-08 00:22:03', 'de si lepi'),
(20, 'duki', 'micamicasrbin', '2017-07-08 00:26:37', 'cao duco sta ima'),
(21, 'duki', 'micamicasrbin', '2017-07-08 00:27:10', 'evo blejim'),
(22, 'duki', 'micamicasrbin', '2017-07-08 00:27:15', 'a ti?'),
(23, 'duki', 'mika', '2017-07-08 00:34:50', 'di si lepi'),
(24, 'duki', 'zika', '2017-07-08 00:35:03', 'di si ziko\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `predavanje`
--

CREATE TABLE `predavanje` (
  `idpred` int(32) NOT NULL,
  `sesija` int(32) NOT NULL,
  `naslov` varchar(64) NOT NULL,
  `autor1` varchar(32) NOT NULL,
  `autor2` varchar(32) DEFAULT NULL,
  `autor3` varchar(32) DEFAULT NULL,
  `autor4` varchar(32) DEFAULT NULL,
  `trajanje` int(32) DEFAULT NULL,
  `tip` varchar(32) NOT NULL COMMENT 'uvodno/predavanje/pauza/zavrsno',
  `pocetak` time(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `predavanje`
--

INSERT INTO `predavanje` (`idpred`, `sesija`, `naslov`, `autor1`, `autor2`, `autor3`, `autor4`, `trajanje`, `tip`, `pocetak`) VALUES
(13, 30, 'uvodno predavanje', 'Doktor Miso', NULL, NULL, NULL, 20, 'uvodno', '12:00:00.000000'),
(15, 30, 'o temi', 'Doktor Miso', NULL, NULL, NULL, 20, 'predavanje', '14:00:00.000000'),
(16, 30, 'zatvaranje', 'Doktor Miso', NULL, NULL, NULL, 20, 'zavrsno', '15:00:00.000000'),
(17, 29, 'otvaranje', 'Doktor Joco', NULL, NULL, NULL, 20, 'uvodno', '15:00:00.000000'),
(18, 29, 'zatvaranje', 'Doktor Joco', NULL, NULL, NULL, 20, 'zavrsno', '16:00:00.000000'),
(19, 34, 'dobro dosli', 'Zivan', NULL, NULL, NULL, 60, 'uvodno', '12:00:00.000000'),
(20, 34, 'predavanje1', 'Zivan', NULL, NULL, NULL, 20, 'predavanje', '13:00:00.000000'),
(23, 35, 'uvod', 'Doktor Milorad', NULL, NULL, NULL, 20, 'uvodno', '16:00:00.000000'),
(24, 35, 'predavanje1', 'Doktor Milorad', NULL, NULL, NULL, 20, 'predavanje', '16:20:00.000000'),
(25, 35, 'predavanje2', 'Doktor Milorad', NULL, NULL, NULL, 20, 'predavanje', '16:40:00.000000'),
(26, 35, 'predavanje3', 'Doktor Milorad', NULL, NULL, NULL, 20, 'predavanje', '17:00:00.000000'),
(27, 31, 'uvod', 'Doktor Mengele', NULL, NULL, NULL, 20, 'uvodno', '17:00:00.000000'),
(28, 31, 'cas 1', 'Doktor Mengele', NULL, NULL, NULL, 20, 'predavanje', '17:30:00.000000'),
(29, 31, 'cas 2', 'Doktor Mengele', NULL, NULL, NULL, 20, 'predavanje', '19:00:00.000000'),
(30, 32, 'cas 1', 'Doktor Bozidar', NULL, NULL, NULL, 20, 'predavanje', '19:00:00.000000'),
(31, 32, 'cas 2', 'Doktor Bozidar', NULL, NULL, NULL, 20, 'predavanje', '20:00:00.000000'),
(32, 33, 'Obnova Dusanovog Casrtstva', 'Milorad Petrovic', NULL, NULL, NULL, 60, 'predavanje', '20:00:00.000000'),
(33, 19, 'uvod', 'Strucnjak Cile', NULL, NULL, NULL, 0, 'uvodno', '12:00:00.000000'),
(34, 19, 'html', 'Strucnjak Cile', NULL, NULL, NULL, 60, 'predavanje', '13:00:00.000000'),
(35, 19, 'css', 'Strucnjak Cile', NULL, NULL, NULL, 60, 'predavanje', '14:00:00.000000'),
(37, 19, 'servlet', 'misa', NULL, NULL, NULL, 60, 'predavanje', '16:00:00.000000'),
(38, 20, 'servlet', 'misa', NULL, NULL, NULL, 60, 'predavanje', '16:00:00.000000'),
(39, 21, 'servlet', 'misa', NULL, NULL, NULL, 60, 'predavanje', '16:00:00.000000'),
(40, 21, 'servlet', 'misa', NULL, NULL, NULL, 60, 'predavanje', '16:00:00.000000'),
(41, 22, 'servlet', 'misa', NULL, NULL, NULL, 60, 'predavanje', '16:00:00.000000'),
(42, 23, 'servlet', 'misa', NULL, NULL, NULL, 60, 'predavanje', '16:00:00.000000'),
(43, 27, 'Svet WoWa', 'duki', NULL, NULL, NULL, 60, 'predavanje', '11:00:00.000000'),
(44, 28, 'Alijansa i Horda', 'duki', NULL, NULL, NULL, 60, 'predavanje', '11:00:00.000000'),
(45, 24, 'Basta', 'raca', NULL, NULL, NULL, 60, 'predavanje', '12:00:00.000000'),
(46, 25, 'Basta2', 'raca', NULL, NULL, NULL, 60, 'predavanje', '12:00:00.000000'),
(47, 26, 'Basta3', 'raca', NULL, NULL, NULL, 60, 'predavanje', '12:00:00.000000');

-- --------------------------------------------------------

--
-- Table structure for table `prijava`
--

CREATE TABLE `prijava` (
  `korisnik` varchar(32) NOT NULL,
  `idPrijava` int(11) NOT NULL,
  `konferencija` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prijava`
--

INSERT INTO `prijava` (`korisnik`, `idPrijava`, `konferencija`) VALUES
('micamicasrbin', 17, 12),
('micamicasrbin', 18, 13),
('micamicasrbin', 19, 14),
('duki', 20, 12),
('duki', 21, 13),
('duki', 22, 16),
('duki', 23, 14),
('misa', 24, 13),
('misa', 25, 14),
('raca', 26, 12);

-- --------------------------------------------------------

--
-- Table structure for table `prijava_predavanje`
--

CREATE TABLE `prijava_predavanje` (
  `username` varchar(32) NOT NULL,
  `sesija` int(32) NOT NULL,
  `idPrijava` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prijava_predavanje`
--

INSERT INTO `prijava_predavanje` (`username`, `sesija`, `idPrijava`) VALUES
('micamicasrbin', 27, 6),
('micamicasrbin', 28, 7),
('raca', 19, 8),
('raca', 19, 9),
('raca', 19, 10);

-- --------------------------------------------------------

--
-- Table structure for table `sala`
--

CREATE TABLE `sala` (
  `idsala` int(32) NOT NULL,
  `mesto` int(32) NOT NULL,
  `naziv` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sala`
--

INSERT INTO `sala` (`idsala`, `mesto`, `naziv`) VALUES
(5, 1, 'Velika sala'),
(6, 1, 'Mala sala'),
(7, 2, 'Soba 1'),
(8, 2, 'Soba 2'),
(9, 2, 'Soba 3'),
(10, 3, 'Velika sala'),
(11, 3, 'Mala sala'),
(12, 4, 'sala 1'),
(13, 4, 'sala 2'),
(14, 5, 'Scena Velika'),
(15, 5, 'Scena mala'),
(16, 5, 'Scena srednja'),
(17, 6, 'Jedina prostorija'),
(18, 7, 'Sala 1'),
(19, 7, 'Sala 2'),
(20, 7, 'Sala 3'),
(21, 8, 'Velika'),
(22, 8, 'Mala');

-- --------------------------------------------------------

--
-- Table structure for table `sesija`
--

CREATE TABLE `sesija` (
  `idsesija` int(32) NOT NULL,
  `konferencija` int(32) NOT NULL,
  `sala` int(32) DEFAULT NULL,
  `datum` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sesija`
--

INSERT INTO `sesija` (`idsesija`, `konferencija`, `sala`, `datum`) VALUES
(19, 12, 7, '2017-07-12'),
(20, 12, NULL, '2017-07-13'),
(21, 12, 9, '2017-07-14'),
(22, 12, 7, '2017-07-15'),
(23, 12, 9, '2017-07-16'),
(24, 13, NULL, '2017-07-21'),
(25, 13, NULL, '2017-07-22'),
(26, 13, NULL, '2017-07-23'),
(27, 14, 17, '2017-07-30'),
(28, 14, 17, '2017-07-31'),
(29, 15, NULL, '2017-08-12'),
(30, 15, 17, '2017-08-13'),
(31, 15, NULL, '2017-08-14'),
(32, 15, NULL, '2017-08-15'),
(33, 15, NULL, '2017-08-16'),
(34, 16, 7, '2017-08-18'),
(35, 16, 8, '2017-08-19');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `galerija`
--
ALTER TABLE `galerija`
  ADD PRIMARY KEY (`idslika`),
  ADD KEY `sesija` (`sesija`);

--
-- Indexes for table `komentar`
--
ALTER TABLE `komentar`
  ADD PRIMARY KEY (`idkomentar`),
  ADD KEY `predavanje` (`predavanje`),
  ADD KEY `korisnik` (`korisnik`);

--
-- Indexes for table `konferencija`
--
ALTER TABLE `konferencija`
  ADD PRIMARY KEY (`idkonf`),
  ADD KEY `moderator` (`moderator`),
  ADD KEY `oblast` (`oblast`),
  ADD KEY `mesto` (`mesto`);

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `mesto`
--
ALTER TABLE `mesto`
  ADD PRIMARY KEY (`idmesto`);

--
-- Indexes for table `oblast`
--
ALTER TABLE `oblast`
  ADD PRIMARY KEY (`idoblast`);

--
-- Indexes for table `poruka`
--
ALTER TABLE `poruka`
  ADD PRIMARY KEY (`idporuka`),
  ADD KEY `posiljalac` (`posiljalac`),
  ADD KEY `primalac` (`primalac`);

--
-- Indexes for table `predavanje`
--
ALTER TABLE `predavanje`
  ADD PRIMARY KEY (`idpred`),
  ADD KEY `sesija` (`sesija`),
  ADD KEY `autor1` (`autor1`),
  ADD KEY `autor2` (`autor2`),
  ADD KEY `autor3` (`autor3`),
  ADD KEY `autor4` (`autor4`);

--
-- Indexes for table `prijava`
--
ALTER TABLE `prijava`
  ADD PRIMARY KEY (`idPrijava`),
  ADD KEY `korisnik` (`korisnik`),
  ADD KEY `korisnik_2` (`korisnik`),
  ADD KEY `konferencija` (`konferencija`);

--
-- Indexes for table `prijava_predavanje`
--
ALTER TABLE `prijava_predavanje`
  ADD PRIMARY KEY (`idPrijava`),
  ADD KEY `sesija` (`sesija`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`idsala`),
  ADD KEY `mesto` (`mesto`);

--
-- Indexes for table `sesija`
--
ALTER TABLE `sesija`
  ADD PRIMARY KEY (`idsesija`),
  ADD KEY `konferencija` (`konferencija`),
  ADD KEY `sala` (`sala`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `galerija`
--
ALTER TABLE `galerija`
  MODIFY `idslika` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT for table `komentar`
--
ALTER TABLE `komentar`
  MODIFY `idkomentar` int(32) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `konferencija`
--
ALTER TABLE `konferencija`
  MODIFY `idkonf` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `mesto`
--
ALTER TABLE `mesto`
  MODIFY `idmesto` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `oblast`
--
ALTER TABLE `oblast`
  MODIFY `idoblast` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `poruka`
--
ALTER TABLE `poruka`
  MODIFY `idporuka` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT for table `predavanje`
--
ALTER TABLE `predavanje`
  MODIFY `idpred` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;
--
-- AUTO_INCREMENT for table `prijava`
--
ALTER TABLE `prijava`
  MODIFY `idPrijava` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT for table `prijava_predavanje`
--
ALTER TABLE `prijava_predavanje`
  MODIFY `idPrijava` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `sala`
--
ALTER TABLE `sala`
  MODIFY `idsala` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT for table `sesija`
--
ALTER TABLE `sesija`
  MODIFY `idsesija` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `galerija`
--
ALTER TABLE `galerija`
  ADD CONSTRAINT `galerija_ibfk_1` FOREIGN KEY (`sesija`) REFERENCES `sesija` (`idsesija`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `komentar_ibfk_1` FOREIGN KEY (`predavanje`) REFERENCES `predavanje` (`idpred`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `komentar_ibfk_2` FOREIGN KEY (`korisnik`) REFERENCES `korisnik` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `konferencija`
--
ALTER TABLE `konferencija`
  ADD CONSTRAINT `konferencija_ibfk_1` FOREIGN KEY (`moderator`) REFERENCES `korisnik` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `konferencija_ibfk_2` FOREIGN KEY (`oblast`) REFERENCES `oblast` (`idoblast`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `konferencija_ibfk_3` FOREIGN KEY (`mesto`) REFERENCES `mesto` (`idmesto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `poruka`
--
ALTER TABLE `poruka`
  ADD CONSTRAINT `poruka_ibfk_1` FOREIGN KEY (`posiljalac`) REFERENCES `korisnik` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `poruka_ibfk_2` FOREIGN KEY (`primalac`) REFERENCES `korisnik` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `predavanje`
--
ALTER TABLE `predavanje`
  ADD CONSTRAINT `predavanje_ibfk_1` FOREIGN KEY (`sesija`) REFERENCES `sesija` (`idsesija`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `predavanje_ibfk_2` FOREIGN KEY (`autor1`) REFERENCES `korisnik` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `predavanje_ibfk_3` FOREIGN KEY (`autor2`) REFERENCES `korisnik` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `predavanje_ibfk_4` FOREIGN KEY (`autor3`) REFERENCES `korisnik` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `predavanje_ibfk_5` FOREIGN KEY (`autor4`) REFERENCES `korisnik` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `prijava`
--
ALTER TABLE `prijava`
  ADD CONSTRAINT `prijava_ibfk_1` FOREIGN KEY (`korisnik`) REFERENCES `korisnik` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `prijava_ibfk_2` FOREIGN KEY (`konferencija`) REFERENCES `konferencija` (`idkonf`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `prijava_predavanje`
--
ALTER TABLE `prijava_predavanje`
  ADD CONSTRAINT `prijava_predavanje_ibfk_1` FOREIGN KEY (`username`) REFERENCES `korisnik` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `prijava_predavanje_ibfk_2` FOREIGN KEY (`sesija`) REFERENCES `sesija` (`idsesija`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sala`
--
ALTER TABLE `sala`
  ADD CONSTRAINT `sala_ibfk_1` FOREIGN KEY (`mesto`) REFERENCES `mesto` (`idmesto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sesija`
--
ALTER TABLE `sesija`
  ADD CONSTRAINT `sesija_ibfk_1` FOREIGN KEY (`konferencija`) REFERENCES `konferencija` (`idkonf`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sesija_ibfk_2` FOREIGN KEY (`sala`) REFERENCES `sala` (`idsala`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
