REPLACE INTO tipo_via (siglas, tipo_via) VALUES ('cl', 'Calle'), ('av', 'Avenida'), ('pz', 'Plaza'), ('gl', 'Glorieta'), ('ps', 'Paseo');
REPLACE INTO pais (siglas, nombre_pais) VALUES ('es', 'España'), ('fr', 'Francia'), ('it', 'Italia'), ('uk', 'Reino Unido'), ('us', 'Estados Unidos');
REPLACE INTO genero (siglas, genero) VALUES ('m', 'Masculino'), ('f', 'Femenino'), ('n', 'No Binario'), ('o', 'Otro');
REPLACE INTO tipo_documento (siglas, tipo_documento) VALUES ('dni', 'DNI'), ('nie', 'NIE'), ('pas', 'Nº Pasaporte'), ('ss', 'Nº Seguridad Social');
REPLACE INTO idioma (siglas, idioma) VALUES ('es', 'Español'), ('en', 'Ingles');
REPLACE INTO tipo_cliente (siglas, tipo_cliente) VALUES ('B', 'Bronce'), ('S', 'Plata'), ('G', 'Oro'), ('P', 'Platino');
REPLACE INTO pregunta (id, pregunta) VALUES (1, '¿Cómo se llamaba tu primera mascota?'), (2, '¿Qué tal tu madre?'), (3, '¿Quién es tu mejor amigo?'), (4, '¿Cuál es tu opinión sobre la insoportable levedad del ser?');