# Práctica Integradora DAM-DAW
Práctica integradora de los módulos DAW, DWES, AD y DIW

## 👥 Integrantes

#### Grupo 2

[<img src="https://avatars.githubusercontent.com/u/134518981?v=4" style="display:inline; border-radius: 100%" width=50><a style="text-decoration:none;"> Vicente Álvarez (@Koderonin)</a>](https://github.com/Koderonin)

[<img src="https://avatars.githubusercontent.com/u/60486521?v=4" style="display:inline; border-radius: 100%" width=50><a style="text-decoration:none;"> Diego Recio (@drecioa)</a>](https://www.github.com/drecioa)</span>

[<img src="https://avatars.githubusercontent.com/u/132434651?v=4" style="display:inline; border-radius: 100%" width=50><a style="text-decoration:none;"> Alicia San Julián (@AliciaSJF)</a>](https://github.com/AliciaSJF)

## 📦 Entregas

#### Primera Entrega
[<img src="https://img.shields.io/badge/release-24%2F04-green?style=plastic">](https://github.com/Koderonin/PracticaIntegradoraG2/releases/tag/v0.2)


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
- Nombre: practicaintegradora_red_integradora
- Nombre de Dominio: da2.dva
#### Servidor DNS
  - IP: 192.168.100.254
  - Nombre de dominio: dns
  - Nombre de host: bind
  - Nombre del Contenedor: contiene_bind
#### Servidor MySQL
  - IP: 192.168.100.2
  - Nombre de dominio: mysql
  - Nombre de host: mysql
  - Nombre del Contenedor: contiene_mysql
#### Servidor MongoDB
  - IP: 192.168.100.3
  - Nombre de dominio: mongo
  - Nombre de host: mongo
  - Nombre del Contenedor: contiene_mongo
#### Servidor Apache Tomcat
  - IP: 192.168.100.4
  - Nombre de dominio: tomcat
  - Nombre de host: tomcat
  - Nombre del Contenedor: contiene_tomcat
#### Servidor Apache HTTP
  - IP: 192.168.100.5
  - Nombre de dominio: apache
  - Nombre de host: apache
  - Nombre del Contenedor: contiene_apache
#### Servidor Node.js
  - IP: 192.168.100.6
  - Nombre de dominio: vue
  - Nombre de host: vue
  - Nombre del Contenedor: contiene_vue
