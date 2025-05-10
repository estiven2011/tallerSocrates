-- INSERT para la tabla 'subjects' según DDL especificado
INSERT INTO subjects (code, name, description, credits, faculty) VALUES
                                                                     ('MAT101', 'Cálculo Diferencial', 'Fundamentos de cálculo diferencial para ingeniería', 4, 'basicSciences'),
                                                                     ('FIS201', 'Física Mecánica', 'Estudio de la mecánica newtoniana y aplicaciones', 4, 'basicSciences'),
                                                                     ('PRG101', 'Programación I', 'Introducción a algoritmos y estructuras de datos', 3, 'engineering'),
                                                                     ('RED110', 'Redes de Computadores', 'Principios de comunicación y protocolos de red', 3, 'engineering'),
                                                                     ('DEP205', 'Deporte Formativo', 'Actividad física y desarrollo de habilidades deportivas', 2, 'schoolOfSports'),
                                                                     ('COM101', 'Comunicación Digital', 'Estrategias de comunicación en entornos digitales', 3, 'communication'),
                                                                     ('QUI110', 'Química General', 'Principios fundamentales de la química', 4, 'basicSciences'),
                                                                     ('DIS201', 'Diseño de Sistemas', 'Metodologías de diseño y arquitectura de software', 4, 'engineering'),
                                                                     ('MKT101', 'Marketing Digital', 'Estrategias de marketing en plataformas digitales', 3, 'communication'),
                                                                     ('DEP102', 'Entrenamiento Deportivo', 'Técnicas avanzadas de preparación física', 3, 'schoolOfSports');

-- INSERT para la tabla 'users' según DDL especificado
INSERT INTO users (names, lastName, password, birthdate, email, is_active, phone, gender) VALUES
                                                                                              ('Juan Carlos', 'Rodríguez', sha1('juan123'), '1995-05-15', 'juan.rodriguez@email.com', 1, '3123456789', 'male'),
                                                                                              ('María', 'González', '25f9e794323b453885f5181f1b624d0b', '1997-08-22', 'maria.gonzalez@email.com', 1, '3123456790', 'female'),
                                                                                              ('Pedro', 'Martínez', 'd8578edf8458ce06fbc5bb76a58c5ca4', '1994-03-10', 'pedro.martinez@email.com', 1, '3123456791', 'male'),
                                                                                              ('Ana', 'López', '5f4dcc3b5aa765d61d8327deb882cf99', '1996-11-05', 'ana.lopez@email.com', 1, '3123456792', 'female'),
                                                                                              ('Roberto', 'Fernández', '96e79218965eb72c92a549dd5a330112', '1993-07-30', 'roberto.fernandez@email.com', 0, '3123456793', 'male'),
                                                                                              ('Laura', 'Díaz', '7c6a180b36896a0a8c02787eeafb0e4c', '1998-01-18', 'laura.diaz@email.com', 1, '3123456794', 'female'),
                                                                                              ('Carlos', 'Sánchez', '6cb75f652a9b52798eb6cf2201057c73', '1992-09-25', 'carlos.sanchez@email.com', 1, '3123456795', 'male'),
                                                                                              ('Sofía', 'Torres', '819b0643d6b89dc9b579fdfc9094f28e', '1999-04-12', 'sofia.torres@email.com', 1, '3123456796', 'female'),
                                                                                              ('Miguel', 'Ramírez', '34819d7beeabb9260a5c854bc85b3e44', '1991-12-03', 'miguel.ramirez@email.com', 0, '3123456797', 'male'),
                                                                                              ('Lucía', 'Castro', 'f2a01b647ec7e55d2253c61a5a4c73bb', '1997-06-20', 'lucia.castro@email.com', 1, '3123456798', 'female');

-- INSERT para la tabla 'enrollment' respetando las relaciones con users y subjects
INSERT INTO `enrollment` (`id_user`, `id_subject`, `date_enrollment`, `status`, `term`, `created_at`) VALUES
                                                                                                          (1, 3, '2025-01-15 08:00:00', 'active', '2025-1', '2025-01-15 08:00:00'),
                                                                                                          (1, 7, '2025-01-15 09:30:00', 'active', '2025-1', '2025-01-15 09:30:00'),
                                                                                                          (2, 1, '2025-01-16 10:15:00', 'active', '2025-1', '2025-01-16 10:15:00'),
                                                                                                          (2, 5, '2025-01-16 11:20:00', 'active', '2025-1', '2025-01-16 11:20:00'),
                                                                                                          (3, 4, '2025-01-15 14:30:00', 'active', '2025-1', '2025-01-15 14:30:00'),
                                                                                                          (4, 6, '2025-01-14 16:45:00', 'active', '2025-1', '2025-01-14 16:45:00'),
                                                                                                          (4, 9, '2025-01-14 17:00:00', 'active', '2025-1', '2025-01-14 17:00:00'),
                                                                                                          (5, 10, '2025-01-17 08:30:00', 'canceled', '2025-1', '2025-01-17 08:30:00'),
                                                                                                          (6, 2, '2025-01-15 11:00:00', 'active', '2025-1', '2025-01-15 11:00:00'),
                                                                                                          (6, 8, '2025-01-15 13:20:00', 'active', '2025-1', '2025-01-15 13:20:00'),
                                                                                                          (7, 3, '2025-01-16 09:45:00', 'active', '2025-1', '2025-01-16 09:45:00'),
                                                                                                          (8, 1, '2025-01-14 08:15:00', 'active', '2025-1', '2025-01-14 08:15:00'),
                                                                                                          (8, 7, '2025-01-14 10:30:00', 'active', '2025-1', '2025-01-14 10:30:00'),
                                                                                                          (9, 5, '2025-01-18 14:00:00', 'canceled', '2025-1', '2025-01-18 14:00:00'),
                                                                                                          (10, 2, '2025-01-15 15:30:00', 'active', '2025-1', '2025-01-15 15:30:00'),
                                                                                                          (11, 6, '2025-01-16 13:10:00', 'active', '2025-1', '2025-01-16 13:10:00'),
                                                                                                          (11, 9, '2025-01-16 15:40:00', 'active', '2025-1', '2025-01-16 15:40:00'),
                                                                                                          (1, 4, '2025-01-18 09:20:00', 'scheduled', '2025-2', '2025-01-18 09:20:00'),
                                                                                                          (3, 8, '2025-01-17 11:30:00', 'scheduled', '2025-2', '2025-01-17 11:30:00'),
                                                                                                          (7, 10, '2025-01-19 10:00:00', 'scheduled', '2025-2', '2025-01-19 10:00:00');