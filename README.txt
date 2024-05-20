# Práctica Integradora DAM-DAW
Práctica integradora de los módulos DAW, DWES, AD y DIW en la que hemos desarrollado una aplicación para una tienda de zapatillas y su administración.

> [!IMPORTANT]
> La versión final del código de la aplicación es la hospedada en la rama merge-Final, puesto que el merge con _main_ antes de la entrega no ha sido posible.

## 👥 Integrantes

#### Grupo 2

[<img src="https://avatars.githubusercontent.com/u/134518981?v=4" style="display:inline; border-radius: 100%" width=50><a style="text-decoration:none;"> Vicente Álvarez (@Koderonin)</a>](https://github.com/Koderonin)

[<img src="https://avatars.githubusercontent.com/u/60486521?v=4" style="display:inline; border-radius: 100%" width=50><a style="text-decoration:none;"> Diego Recio (@drecioa)</a>](https://www.github.com/drecioa)</span>

[<img src="https://avatars.githubusercontent.com/u/132434651?v=4" style="display:inline; border-radius: 100%" width=50><a style="text-decoration:none;"> Alicia San Julián (@AliciaSJF)</a>](https://github.com/AliciaSJF)

## 👨‍💻 Utilización de la aplicación

> [!IMPORTANT]
> Nuestra aplicación permite a través de Docker Compose, levantar la infraestructura de la aplicación y al mismo tiempo, desplegarla, permitiendo su uso sin necesidad de configurar manualmente nada. Solamente necesita que Docker Compose se encuentre previamente instalado (con la versión 3.3 o superior) en la máquina que ejecute la aplicación, y mediante siguiente el comando, desplegar la aplicación:

```bash
  docker compose -f ./docker-compose.yml -p practicaintegradora up -d
```

## 📦 Entregas

#### Primera Entrega
[<img src="https://img.shields.io/badge/release-24%2F04-green?style=plastic">](https://github.com/Koderonin/PracticaIntegradoraG2/releases/tag/v0.2)

#### Segunda Entrega
[<img src="https://img.shields.io/badge/release-13%2F05-green?style=plastic">](https://github.com/Koderonin/PracticaIntegradoraG2/releases/tag/v0.3)


#### Tercera Entrega y definitiva
[<img src="https://img.shields.io/badge/release%20final-20%2F05-green?style=plastic">](https://github.com/Koderonin/PracticaIntegradoraG2/releases/tag/v0.4)

## 💻 Funcionalidades 

> [!NOTE]
> - Página principal responsiva
> - Catálogo de productos que muestra los productos que existen en la base de datos 
> - Vista del detalle de los productos
> - Carrito al que puedes añadir productos y eliminarlos (no persiste en la base de datos)
> - Login de usuario
> - Impedimento a los usuarios a acceder a áreas a las que no tengan que poder acceder (Ej: no puedes pasar al registro de cliente sin haberte registrado como usuario)
> - Registro de usuario
> - Registro por pasos de cliente
> - Validaciones para todos los registros y el login, validación de tipo de Documento, tarjeta de crédito, para que no se puedan modificar las colecciones (que con las herramientas de administrador pueda enviar género:Caballo)
> - Internacionalización: Página traducida al español, inglés e italiano
> - Conteo de autenticaciones en la base de datos
> - Cookie con los usuarios y sus accesos
> - Visualización de las páginas visitadas en la sesión en el footer de la página
> - Área de cliente con los datos del usuario 
> - Visualización y creación de tarjetas de crédito y direcciones 
> - Página de administración que permite la búsqueda de entidades
> - Visualización del detalle de clientes, productos y nómina
> - Modificación de usuarios (lógica para modificar el resto de entidades implementada  en los servicios, no en uso) 
> - Dar de alta productos con múltiples imágenes

## ⚙️ Tecnologías utilizadas 

> [!TIP]
> - Docker
> - Apache
> - Tomcat
> - Bind9
> - MongoDB
> - Mysql
> - SpringBoot
> - Thymeleaf
> - JPA
> - SpringData JPA, Hibernate
> - HTML
> - CSS
> - JS, JQUERY

## 📈 Diagrama de Gantt

Hemos utilizado la funcionalidad de GitHub que permite realizar un diagrama de Gantt vinculado a los issues del repositorio de Git en el que se desarrolla. Aquí está el enlace a la web del diagrama:

[📈 Diagrama de Gantt] (https://github.com/users/Koderonin/projects/2/views/4)

## 📝 Memoria de la Práctica

[📝 Memoria de la Práctica](./MemoriaG2.pdf)

## ⚓ Docker 

#### ⬆️ Levantar infraestructura

```bash
  docker compose -f ./docker-compose.yml -p practicaintegradora up -d
```  
#### ⬇️ Bajar infraestructura

```bash
  docker compose -f ./docker-compose.yml -p practicaintegradora down --remove-orphans
```
### 🌐 Datos de la Red Docker
- IP: 192.168.100.0/24
- Gateway: 192.168.100.1
- Nombre: practicaintegradora_red_integradora
- Nombre de Dominio: da2.dva
#### Servidor DNS
  - IP: 192.168.100.254
  - Nombre de dominio: ns1.da2.dva
  - Nombre de host: bind
  - Nombre del Contenedor: contiene_bind
#### Servidor MySQL
  - IP: 192.168.100.2
  - Nombre de dominio: mysql.da2.dva
  - Nombre de host: mysql
  - Nombre del Contenedor: contiene_mysql
#### Servidor MongoDB
  - IP: 192.168.100.3
  - Nombre de dominio: mongo.da2.dva
  - Nombre de host: mongo
  - Nombre del Contenedor: contiene_mongo
#### Servidor Apache Tomcat
  - IP: 192.168.100.4
  - Nombre de dominio: tomcat.da2.dva
  - Nombre de host: tomcat
  - Nombre del Contenedor: contiene_tomcat
#### Servidor Apache HTTP
  - IP: 192.168.100.5
  - Nombre de dominio: apache.da2.dva
  - Nombre de host: apache
  - Nombre del Contenedor: contiene_apache