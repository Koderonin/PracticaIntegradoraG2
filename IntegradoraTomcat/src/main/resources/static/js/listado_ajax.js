//Al cargar la página, se cargan los departamentos en la tabla haciendo una petición GET a la API

const tabla = document.createElement("table");
const main = document.getElementById("main");
const buscador = document.getElementById("buscador-entidad");

document.getElementById('buscar-entidad').addEventListener('click', async function (event) {
    event.preventDefault();

    let datos = await datosDevueltos().then(
        function (data) {
            return data;
        },
        function (error) {
            console.log(error);
        }
    );

    console.log(datos);


    // $.getJSON(`http://localhost:8080/admin/api/${buscador.value}/listado`, function (data) {
    //     console.log(data);
    // });



});

async function datosDevueltos () {
    const response = await fetch(`http://localhost:8081/admin/api/${buscador.value}/listado`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    return await response.json();
}

// fetch('http://localhost:8080/api-rest/departamentos/listado')
//     .then(response => response.json())
//     .then(data => {
//         // Una vez que se reciben los datos del API, los utilizaremos para completar la tabla de departamentos
//         let tabla = document.getElementById('tabla');
//
//         data.forEach(departamento => {
//             let row = tabla.insertRow(-1);
//             let codigoCell = row.insertCell(0);
//             let nombreCell = row.insertCell(1);
//             let localidadCell = row.insertCell(2);
//             let fechaCreacionCell = row.insertCell(3);
//
//             codigoCell.innerHTML = departamento.codigo;
//             localidadCell.innerHTML = departamento.localidad;
//             fechaCreacionCell.innerHTML = departamento.fechaCreacion;
//
//             // Crear un enlace para el nombre del departamento que redirigirá a http://localhost:8080/departamentos/detalle-rest/{{id}}
//             let nombreLink = document.createElement('a');
//             nombreLink.href = `http://localhost:8080/departamentos/detalle-rest?id=${departamento.id_departamento}`;
//             nombreLink.textContent = departamento.nombre;
//             nombreCell.appendChild(nombreLink);
//
//             // Añadir botón de editar
//             let editarCell = row.insertCell(4);
//             editarCell.innerHTML = `<button class="btn" onclick="editarDepartamento(${departamento.id_departamento})">Editar</button>`;
//
//             // Añadir botón de borrar
//             let borrarCell = row.insertCell(5);
//             borrarCell.innerHTML = `<button class="btn" onclick="borrarDepartamento(${departamento.id_departamento}, '${departamento.nombre}')">Borrar</button>`;
//         });
//     })
//     .catch(error => console.error('Hubo un problema con la solicitud fetch:', error));
//
// // Función para editar un departamento
// function editarDepartamento(id) {
//     // Código para redireccionar a la página de edición
//     console.log(id);
//     window.location.href = `http://localhost:8080/departamentos/detalle-rest?id=${id}&editable=true`;
// }
//
// // Función para borrar un departamento
// function borrarDepartamento(id, nombre) {
//     if(confirm("¿Seguro que quieres borrar el departamento " + nombre + "?")) {
//         // Código para enviar la solicitud de borrado al API REST
//         fetch(`http://localhost:8080/api-rest/departamentos/borrar/${id}`, {
//             method: 'DELETE'
//         })
//             .then(response => {
//                 if(response.ok) {
//                     // Si el borrado es exitoso, recargar la página para reflejar los cambios
//                     alert("El departamento '" + nombre + "' se ha eliminado.");
//                     location.reload();
//                 } else {
//                     // Si hubo un error en la solicitud, mostrar un mensaje de error
//                     alert("Hubo un problema al intentar borrar el departamento: " + response.statusText);
//                 }
//             })
//             .catch(error => console.error('Hubo un problema al intentar borrar el departamento:', error));
//     }
// }