CREATE TABLE IF NOT EXISTS libranzas (
    id INT AUTO_INCREMENT,
    cedula VARCHAR(20) UNIQUE NOT NULL,
    total_prestamo DECIMAL(20,2) NOT NULL,
    total_consumido DECIMAL(20,2) NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS libre_inversiones (
    id INT AUTO_INCREMENT,
    cedula VARCHAR(20) UNIQUE NOT NULL,
    total_prestamo DECIMAL(20,2) NOT NULL,
    consumido DECIMAL(20,2) NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tarjetas_creditos (
    id INT AUTO_INCREMENT,
    cedula VARCHAR(20) UNIQUE NOT NULL,
    cupo DECIMAL(20,2) NOT NULL,
    consumido DECIMAL(20,2) NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS crediagiles (
    id INT AUTO_INCREMENT,
    cedula VARCHAR(20) UNIQUE NOT NULL,
    cupo DECIMAL(20,2) NOT NULL,
    total_consumido DECIMAL(20,2) NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO libranzas (cedula, total_prestamo, total_consumido, fecha_vencimiento) VALUES
    ('123456789', 80000000, 0, '2025-12-31'),
    ('123456788', 7000000, 0, '2025-12-31'),
    ('123456787', 80000000, 0, '2025-12-31'),
    ('123456786', 1000000, 0, '2025-12-31'),
    ('123456785', 80000000, 0, '2025-12-31'),
    ('123456784', 1000000, 0, '2027-12-31'),
    ('123456783', 1000000, 0, '2027-12-31'),
    ('123456782', 80000000, 0, '2027-12-31'),
    ('123456781', 1000000, 0, '2027-12-31'),
    ('123456780', 2500000, 0, '2027-12-31');

INSERT INTO libre_inversiones (cedula, total_prestamo, consumido, fecha_vencimiento) VALUES
    ('51234699', 2500000, 0, '2024-12-31'),
    ('51234698', 1000000, 0, '2024-12-31'),
    ('51234697', 80000000, 0, '2024-12-31'),
    ('51234696', 1000000, 0, '2024-12-31'),
    ('51234695', 1000000, 0, '2024-12-31'),
    ('51234694', 7000000, 0, '2023-12-31'),
    ('51234693', 1000000, 0, '2023-12-31'),
    ('51234692', 1000000, 0, '2023-12-31'),
    ('51234691', 7000000, 0, '2023-12-31'),
    ('51234690', 1000000, 0, '2023-12-31');

INSERT INTO tarjetas_creditos (cedula, cupo, consumido, fecha_vencimiento) VALUES
    ('989814349', 1000000, 0, '2024-12-31'),
    ('989814348', 1000000, 0, '2024-12-31'),
    ('989814347', 80000000, 0, '2024-12-31'),
    ('989814346', 1000000, 0, '2024-12-31'),
    ('989814345', 1000000, 0, '2024-12-31'),
    ('989814344', 2500000, 0, '2021-12-31'),
    ('989814343', 1000000, 0, '2021-12-31'),
    ('989814342', 10000000, 0, '2021-12-31'),
    ('989814341', 7000000, 0, '2021-12-31'),
    ('989814340', 1000000, 0, '2021-12-31');

INSERT INTO crediagiles (cedula, cupo, total_consumido, fecha_vencimiento) VALUES
    ('4313579', 1000000, 0, '2021-12-31'),
    ('4313578', 1000000, 0, '2021-12-31'),
    ('4313577', 80000000, 0, '2021-12-31'),
    ('4313576', 1000000, 0, '2021-12-31'),
    ('4313575', 1000000, 0, '2021-12-31'),
    ('4313574', 7000000, 0, '2021-12-31'),
    ('4313573', 1000000, 0, '2021-12-31'),
    ('4313572', 80000000, 0, '2021-12-31'),
    ('4313571', 1000000, 0, '2021-12-31'),
    ('4313570', 10000000, 0, '2021-12-31');
