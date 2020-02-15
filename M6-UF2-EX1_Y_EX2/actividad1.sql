-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-02-2020 a las 18:16:40
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 7.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `actividad1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumne`
--

CREATE TABLE `alumne` (
  `Id` int(11) NOT NULL,
  `nom` text COLLATE utf8_spanish_ci NOT NULL,
  `dni` text COLLATE utf8_spanish_ci NOT NULL,
  `data_naixement` text COLLATE utf8_spanish_ci NOT NULL,
  `direccio_postal` text COLLATE utf8_spanish_ci NOT NULL,
  `codi_postal` int(5) NOT NULL,
  `poblacion` text COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `poblacio`
--

CREATE TABLE `poblacio` (
  `codi_postal` int(5) NOT NULL,
  `poblacio` text COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `poblacio`
--

INSERT INTO `poblacio` (`codi_postal`, `poblacio`) VALUES
(40002, 'Montblanc');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumne`
--
ALTER TABLE `alumne`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `alumne_ibfk_1` (`codi_postal`);

--
-- Indices de la tabla `poblacio`
--
ALTER TABLE `poblacio`
  ADD PRIMARY KEY (`codi_postal`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumne`
--
ALTER TABLE `alumne`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumne`
--
ALTER TABLE `alumne`
  ADD CONSTRAINT `alumne_ibfk_1` FOREIGN KEY (`codi_postal`) REFERENCES `poblacio` (`codi_postal`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
