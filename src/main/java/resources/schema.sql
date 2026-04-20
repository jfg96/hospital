-- ============================================================
-- 1. CREAR Y SELECCIONAR LA BASE DE DATOS
-- ============================================================
CREATE SCHEMA IF NOT EXISTS gestion_hospitalaria;
USE gestion_hospitalaria;

-- ============================================================
-- 2. TABLAS INDEPENDIENTES (PLANTA Y ENFERMO)
-- ============================================================

-- Tabla PLANTA
CREATE TABLE PLANTA (
    numero INT NOT NULL,
    num_habitaciones INT NOT NULL CHECK (num_habitaciones >= 0),
    especialidad VARCHAR(100) NOT NULL,
    PRIMARY KEY (numero)
) ENGINE=InnoDB;

-- Tabla ENFERMO (sin edad almacenada)
CREATE TABLE ENFERMO (
    codigo_enfermo INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    dni CHAR(9) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    telefono VARCHAR(15),
    PRIMARY KEY (codigo_enfermo),
    UNIQUE INDEX UQ_ENF_DNI (dni)
)  ENGINE=INNODB;

-- ============================================================
-- 3. JERARQUÍA TRABAJADOR (HERENCIA)
-- ============================================================

-- Tabla TRABAJADOR (padre)
CREATE TABLE TRABAJADOR (
    id_trabajador INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    dni CHAR(9) NOT NULL,
    sueldo DECIMAL(10,2) NOT NULL CHECK (sueldo >= 0),
    telefono VARCHAR(15),
    tipo_trabajador ENUM('MEDICO', 'AUXILIAR') NOT NULL,
    PRIMARY KEY (id_trabajador),
    UNIQUE INDEX UQ_TRAB_DNI (dni)
) ENGINE=InnoDB;

-- Tabla MEDICO (hija)
CREATE TABLE MEDICO (
    id_trabajador INT NOT NULL,
    especialidad VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_trabajador),
    CONSTRAINT FK_MEDICO_TRABAJADOR FOREIGN KEY (id_trabajador)
        REFERENCES TRABAJADOR(id_trabajador)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Tabla AUXILIAR (hija)
CREATE TABLE AUXILIAR (
    id_trabajador INT NOT NULL,
    id_planta INT NOT NULL,
    PRIMARY KEY (id_trabajador),
    CONSTRAINT FK_AUX_TRABAJADOR FOREIGN KEY (id_trabajador)
        REFERENCES TRABAJADOR(id_trabajador)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_AUX_PLANTA FOREIGN KEY (id_planta)
        REFERENCES PLANTA(numero)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

-- ============================================================
-- 4. TABLAS DEPENDIENTES
-- ============================================================

-- Tabla HABITACION
CREATE TABLE HABITACION (
    id_habitacion INT NOT NULL AUTO_INCREMENT,
    numero INT NOT NULL,
    num_camas INT NOT NULL CHECK (num_camas >= 1),
    observaciones TEXT,
    id_planta INT NOT NULL,
    PRIMARY KEY (id_habitacion),
    UNIQUE INDEX UQ_HAB_NUM_PLANTA (numero, id_planta),
    CONSTRAINT FK_HAB_PLANTA FOREIGN KEY (id_planta)
        REFERENCES PLANTA(numero)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Tabla HOSPITALIZACION
CREATE TABLE HOSPITALIZACION (
    id_hospitalizacion INT NOT NULL AUTO_INCREMENT,
    id_enfermo INT NOT NULL,
    id_habitacion INT NOT NULL,
    fecha_ingreso DATE NOT NULL,
    fecha_alta DATE,
    PRIMARY KEY (id_hospitalizacion),
    CONSTRAINT CHK_FECHAS CHECK (fecha_alta IS NULL OR fecha_alta >= fecha_ingreso),
    CONSTRAINT FK_HOSP_ENF FOREIGN KEY (id_enfermo)
        REFERENCES ENFERMO(codigo_enfermo)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT FK_HOSP_HAB FOREIGN KEY (id_habitacion)
        REFERENCES HABITACION(id_habitacion)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Tabla DIAGNOSTICO
CREATE TABLE DIAGNOSTICO (
    codigo_diagnostico INT NOT NULL AUTO_INCREMENT,
    id_enfermo INT NOT NULL,
    id_medico INT NOT NULL,
    fecha DATE NOT NULL,
    informe TEXT NOT NULL,
    PRIMARY KEY (codigo_diagnostico),
    CONSTRAINT FK_DIAG_ENF FOREIGN KEY (id_enfermo)
        REFERENCES ENFERMO(codigo_enfermo)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT FK_DIAG_MED FOREIGN KEY (id_medico)
        REFERENCES MEDICO(id_trabajador)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

-- ============================================================
-- 5. VISTA PARA CALCULAR LA EDAD (PARA NO TENER QUE ACTUALIZAR CADA AÑO LA BASE DE DATOS)
-- ============================================================

USE gestion_hospitalaria;

DROP VIEW IF EXISTS v_enfermos_detallado;

CREATE VIEW v_enfermos_detallado AS
SELECT 
    codigo_enfermo,
    nombre,
    direccion,
    dni,
    fecha_nacimiento,
    telefono,
    TIMESTAMPDIFF(YEAR, fecha_nacimiento, CURDATE()) AS edad
FROM ENFERMO;