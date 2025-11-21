-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 21, 2025 at 06:35 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `circo_robertogarcia`
--

-- --------------------------------------------------------

--
-- Table structure for table `artista`
--

CREATE TABLE `artista` (
  `idArt` bigint(2) NOT NULL,
  `apodo` varchar(25) DEFAULT NULL,
  `especialidades` enum('ACROBACIA','HUMOR','MAGIA','EQUILIBRISMO','MALABARISMO') NOT NULL,
  `Id_Persona` bigint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `artista`
--

INSERT INTO `artista` (`idArt`, `apodo`, `especialidades`, `Id_Persona`) VALUES
(1, 'lgf', 'HUMOR', 5);

-- --------------------------------------------------------

--
-- Table structure for table `coordinacion`
--

CREATE TABLE `coordinacion` (
  `idCoord` bigint(2) NOT NULL,
  `senior` tinyint(1) NOT NULL DEFAULT 0,
  `fechasenior` date DEFAULT NULL,
  `Id_Persona` bigint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `coordinacion`
--

INSERT INTO `coordinacion` (`idCoord`, `senior`, `fechasenior`, `Id_Persona`) VALUES
(1, 1, '2025-11-21', 1),
(2, 0, NULL, 2);

-- --------------------------------------------------------

--
-- Table structure for table `credenciales`
--

CREATE TABLE `credenciales` (
  `ID` bigint(2) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `perfil` enum('ADMIN','COORDINACION','ARTISTA') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `credenciales`
--

INSERT INTO `credenciales` (`ID`, `nombre`, `password`, `perfil`) VALUES
(1, 'roberto', 'prueba', ''),
(2, 'prueba', 'prueba', 'ARTISTA'),
(3, 'alvaro', 'sd', 'COORDINACION'),
(7, 'lgf', 'lgf', 'ARTISTA');

-- --------------------------------------------------------

--
-- Table structure for table `espectaculo`
--

CREATE TABLE `espectaculo` (
  `id` bigint(2) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `fechaini` date NOT NULL,
  `fechafin` date NOT NULL,
  `id_coordinador` bigint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `espectaculo`
--

INSERT INTO `espectaculo` (`id`, `nombre`, `fechaini`, `fechafin`, `id_coordinador`) VALUES
(1, 'espectaculotest', '2025-11-21', '2025-11-22', 1),
(2, 'a', '2025-11-22', '2025-11-23', 2);

-- --------------------------------------------------------

--
-- Table structure for table `numero`
--

CREATE TABLE `numero` (
  `id` bigint(3) NOT NULL,
  `orden` int(2) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `duracion` double(10,2) NOT NULL,
  `id_espectaculo` bigint(2) NOT NULL,
  `id_artista` bigint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `numero`
--

INSERT INTO `numero` (`id`, `orden`, `nombre`, `duracion`, `id_espectaculo`, `id_artista`) VALUES
(1, 1, 'numerouno', 2.00, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `persona`
--

CREATE TABLE `persona` (
  `ID` bigint(2) NOT NULL,
  `email` varchar(25) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `nacionalidad` varchar(25) NOT NULL,
  `id_credenciales` bigint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `persona`
--

INSERT INTO `persona` (`ID`, `email`, `nombre`, `nacionalidad`, `id_credenciales`) VALUES
(1, 'robertogf@circo.es', 'Roberto Garcia', 'España', 1),
(2, 'sfa@circo.es', 'alvaro', 'España', 3),
(5, 'lgf@circo.es', 'lucia', 'España', 7);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `artista`
--
ALTER TABLE `artista`
  ADD PRIMARY KEY (`idArt`),
  ADD KEY `FK_IdPersonaArtista` (`Id_Persona`);

--
-- Indexes for table `coordinacion`
--
ALTER TABLE `coordinacion`
  ADD PRIMARY KEY (`idCoord`),
  ADD KEY `FK_IdPersonaCoordinador` (`Id_Persona`);

--
-- Indexes for table `credenciales`
--
ALTER TABLE `credenciales`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UQ_nombreCredenciales` (`nombre`);

--
-- Indexes for table `espectaculo`
--
ALTER TABLE `espectaculo`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UQ_nombreEspectaculo` (`nombre`),
  ADD KEY `FK_IdCoordinadorEspectaculo` (`id_coordinador`);

--
-- Indexes for table `numero`
--
ALTER TABLE `numero`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_IdEspectaculoNumero` (`id_espectaculo`),
  ADD KEY `FK_IdArtistaNumero` (`id_artista`);

--
-- Indexes for table `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UQ_emailPersona` (`email`),
  ADD KEY `FK_IdCredencialesPersona` (`id_credenciales`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `artista`
--
ALTER TABLE `artista`
  MODIFY `idArt` bigint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `coordinacion`
--
ALTER TABLE `coordinacion`
  MODIFY `idCoord` bigint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `credenciales`
--
ALTER TABLE `credenciales`
  MODIFY `ID` bigint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `espectaculo`
--
ALTER TABLE `espectaculo`
  MODIFY `id` bigint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `numero`
--
ALTER TABLE `numero`
  MODIFY `id` bigint(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `persona`
--
ALTER TABLE `persona`
  MODIFY `ID` bigint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `artista`
--
ALTER TABLE `artista`
  ADD CONSTRAINT `FK_IdPersonaArtista` FOREIGN KEY (`Id_Persona`) REFERENCES `persona` (`ID`);

--
-- Constraints for table `coordinacion`
--
ALTER TABLE `coordinacion`
  ADD CONSTRAINT `FK_IdPersonaCoordinador` FOREIGN KEY (`Id_Persona`) REFERENCES `persona` (`ID`);

--
-- Constraints for table `espectaculo`
--
ALTER TABLE `espectaculo`
  ADD CONSTRAINT `FK_IdCoordinadorEspectaculo` FOREIGN KEY (`id_coordinador`) REFERENCES `coordinacion` (`idCoord`);

--
-- Constraints for table `numero`
--
ALTER TABLE `numero`
  ADD CONSTRAINT `FK_IdArtistaNumero` FOREIGN KEY (`id_artista`) REFERENCES `artista` (`idArt`),
  ADD CONSTRAINT `FK_IdEspectaculoNumero` FOREIGN KEY (`id_espectaculo`) REFERENCES `espectaculo` (`id`);

--
-- Constraints for table `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `FK_IdCredencialesPersona` FOREIGN KEY (`id_credenciales`) REFERENCES `credenciales` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
