
--
-- Base de datos: `examenad`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asistentes`
--

CREATE TABLE `asistentes` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `id_evento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `asistentes`
--

INSERT INTO `asistentes` (`id`, `nombre`, `email`, `id_evento`) VALUES
(1, 'Laura Martín', 'laura.martin@mail.com', 1),
(2, 'Sergio López', 'sergio.lopez@mail.com', 1),
(3, 'Carla Núñez', 'carla.nunez@mail.com', 2),
(4, 'Mario del Castillo', 'mario.castillo@mail.com', 2),
(5, 'Nerea González', 'nerea.gonzalez@mail.com', 3),
(6, 'Iván Romero', 'ivan.romero@mail.com', 4),
(7, 'Alba Sanz', 'alba.sanz@mail.com', 5),
(8, 'Rubén Vázquez', 'ruben.vazquez@mail.com', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `eventos`
--

CREATE TABLE `eventos` (
  `id` int(11) NOT NULL,
  `titulo` varchar(150) NOT NULL,
  `fecha` date NOT NULL,
  `aforo_max` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `eventos`
--

INSERT INTO `eventos` (`id`, `titulo`, `fecha`, `aforo_max`) VALUES
(1, 'Conferencia de Desarrollo Web', '2025-02-15', 150),
(2, 'Festival de Música Indie', '2025-03-20', 800),
(3, 'Jornada de Ciberseguridad', '2025-04-05', 200),
(4, 'Feria de Videojuegos Retro', '2025-05-12', 300),
(5, 'Taller de Inteligencia Artificial', '2025-06-01', 40);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asistentes`
--
ALTER TABLE `asistentes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_evento` (`id_evento`);

--
-- Indices de la tabla `eventos`
--
ALTER TABLE `eventos`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asistentes`
--
ALTER TABLE `asistentes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `eventos`
--
ALTER TABLE `eventos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asistentes`
--
ALTER TABLE `asistentes`
  ADD CONSTRAINT `asistentes_ibfk_1` FOREIGN KEY (`id_evento`) REFERENCES `eventos` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
