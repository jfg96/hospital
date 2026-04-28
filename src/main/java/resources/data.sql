-- 1. PLANTAS (Sin dependencias)
INSERT INTO PLANTA (numero, num_habitaciones, especialidad) VALUES (1, 10, 'Cardiología');
INSERT INTO PLANTA (numero, num_habitaciones, especialidad) VALUES (2, 5, 'Traumatología');

-- 2. TRABAJADORES (Padres)
INSERT INTO TRABAJADOR (nombre, direccion, dni, sueldo, telefono, tipo_trabajador) 
VALUES ('Dr. Gregory House', 'Calle Falsa 123', '11111111A', 3500.00, '600000001', 'MEDICO');

INSERT INTO TRABAJADOR (nombre, direccion, dni, sueldo, telefono, tipo_trabajador) 
VALUES ('Enfermero Manuel', 'Av. Siempre Viva 742', '22222222B', 2200.00, '600000002', 'AUXILIAR');

-- 3. MEDICO y AUXILIAR (Hijos - El ID debe coincidir con el generado arriba)
INSERT INTO MEDICO (id_trabajador, especialidad) VALUES (1, 'Diagnóstico Médico');
INSERT INTO AUXILIAR (id_trabajador, id_planta) VALUES (2, 1);

-- 4. ENFERMOS
INSERT INTO ENFERMO (nombre, direccion, dni, fecha_nacimiento, telefono) 
VALUES ('Juan Pérez', 'Calle Luna 5', '33333333C', '1985-05-15', '600111222');

-- 5. HABITACION
INSERT INTO HABITACION (numero, num_camas, observaciones, id_planta) VALUES (101, 2, 'Habitación VIP', 1);

-- 6. HOSPITALIZACION
INSERT INTO HOSPITALIZACION (id_enfermo, id_habitacion, fecha_ingreso, fecha_alta) 
VALUES (1, 1, '2026-04-01', NULL);

-- 7. DIAGNOSTICO
INSERT INTO DIAGNOSTICO (id_enfermo, id_medico, fecha, informe) 
VALUES (1, 1, '2026-04-02', 'Paciente presenta síntomas de gripe común.');