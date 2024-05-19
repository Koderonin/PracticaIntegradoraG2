//Al cargar la página, se cargan los departamentos en la tabla haciendo una petición GET a la API


const main = document.getElementById("main");
const buscador = document.getElementById("buscador-entidad");

// variables de la modal que muestra los detalles
// aquí cargo los datos!
let ventanaDetalle = document.getElementById("ventana_detalle");
let cerrarDetalle = document.getElementById("cerrar_detalle");
const detalleEntidad = document.getElementById("detalle_entidad");

document.getElementById('buscar-entidad').addEventListener('click', async function (event) {
    event.preventDefault();

    await datosDevueltos().then( data => {

        let tabla = document.createElement("table");            //borramos tabla

        main.innerHTML = ""

        // pintamos los datos
            let cabeceras = Object.keys(data[0]);

            // Crear la fila de encabezados
            let encabezados = tabla.insertRow(-1);   // -1 significa que la fila se insertará al final de la tabla

            for (let i = 0; i < cabeceras.length; i++) {
                let atributoEntidad = document.createElement("th");   // 'th' significa celda de encabezado
                atributoEntidad.innerHTML = cabeceras[i];
                encabezados.appendChild(atributoEntidad);
            }

            // Crear las filas de datos
            for (let i = 0; i < data.length; i++) {
                let fila = tabla.insertRow(-1);   // Insertar una nueva fila al final de la tabla
                let registro = data[i]
                for (let j = 0; j < cabeceras.length; j++) {
                    let valor = registro[cabeceras[j]];
                    let celda = fila.insertCell(-1);   // Insertar una nueva celda en la fila
                    if (typeof valor === 'object' && valor != null){
                        if (Array.isArray(valor) && valor.length === 2){
                            valor = valor[1];
                        } else {
                            valor = "Múltiples valores"
                        }
                    }
                    celda.innerHTML = valor;
                }
                // Botón para ver más datos
                let celda = fila.insertCell(-1)
                let boton = document.createElement("button");
                boton.innerHTML = "Ver más";
                boton.onclick = function () {
                    mostrarDetalle(registro[cabeceras[0]])
                };
                celda.appendChild(boton);

            }

            // Añadir la tabla al cuerpo del documento HTML
            main.appendChild(tabla);

    }).catch(error => console.error('Hubo un problema con la solicitud fetch:', error))

});

async function datosDevueltos () {
    const response = await fetch(`http://tomcat.da2.dva:8080/admin/api/${buscador.value.toLowerCase()}/listado`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    return await response.json();
}

// Lógica de la ventana modal

function mostrarDatos(data) {
    let tabla = document.createElement('table');
    detalleEntidad.innerHTML = "";
    let entidad;

    tabla.classList.add("tabla_detalle");
    tabla.id = "tabla_detalle"
    //cargo los datos al modal
    for (let clave in data){
        let valor = data[clave]

        let fila = tabla.insertRow(-1);
        let celdaClave = fila.insertCell(-1);
        celdaClave.innerHTML = clave;

        let celdaValor = fila.insertCell(-1);

        if (typeof valor === 'object' && valor != null){
            let tablaAnidada = mostrarDatos(valor);  // Llamada recursiva para objetos anidados
            celdaValor.appendChild(tablaAnidada);
        } else {
            let input = document.createElement('input');
            input.name = clave;
            input.value = valor;
            celdaValor.appendChild(input);
        }

    }

    return tabla;
}

// Función recursiva para recoger los datos de una tabla
function recogerDatos(tabla) {
    let datos = {};
    let inputs = tabla.getElementsByTagName('input');
    for (let i = 0; i < inputs.length; i++) {
        let input = inputs[i];
        datos[input.name] = input.value;
    }

    // Recoge los datos de las tablas anidadas
    let tablasAnidadas = tabla.getElementsByTagName('table');
    for (let i = 0; i < tablasAnidadas.length; i++) {
        let tablaAnidada = tablasAnidadas[i];
        let datosAnidados = recogerDatos(tablaAnidada);  // Llamada recursiva
        Object.assign(datos, datosAnidados);
    }

    return datos;
}

// se le llama al pulsar un botón
function mostrarDetalle(id) {

    $.ajaxSetup({xhrFields: { withCredentials: true } });
    $.getJSON(`http://tomcat.da2.dva:8080/admin/api/${buscador.value.toLowerCase()}/${id}`, function (data) {

    let tabla= mostrarDatos(data);

    let boton = document.createElement('button');
    boton.innerHTML = `Guardar`;
    tabla.appendChild(boton);
    boton.onclick = function () {
        let datos = recogerDatos(tabla);  // Llamada a la función recogerDatos

        let datosJson = JSON.stringify(datos);

        fetch(`http://tomcat.da2.dva:8080/admin/api/${buscador.value.toLowerCase()}/update`, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: datosJson
        }).then(response => response.json())
            .then(data => console.log(data))
            .catch(error => console.error('Error:', error));
    };

    detalleEntidad.appendChild(tabla)


    ventanaDetalle.style.display = "block";
    })
}

// Cuando el usuario hace clic en (x), cierra el modal
cerrarDetalle.onclick = function() {
    ventanaDetalle.style.display = "none";
}

// Cuando el usuario hace clic en cualquier lugar fuera del modal, lo cierra
window.onclick = function(event) {
    if (event.target === ventanaDetalle) {
        ventanaDetalle.style.display = "none";
    }
}