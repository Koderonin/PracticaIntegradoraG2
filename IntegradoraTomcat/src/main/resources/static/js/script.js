const claveInput = document.getElementById("clave");
const confirmarClaveInput = document.getElementById("confirmClave");
function mostrarClaves() {
    if (claveInput && confirmarClaveInput) {
        claveInput.type = "text";
        confirmarClaveInput.type = "text";
    }
}
function ocultarClaves() {

    if (claveInput && confirmarClaveInput) {
        claveInput.type = "password";
        confirmarClaveInput.type = "password";
    }
}


/* ---------------------- Barra de Navegación ---------------------- */

const ENLACE_LOGIN = $('#enlace_login');
const ENLACE_REGISTRO = $('#enlace_registro');
const ENLACE_PRODUCTOS = $('#enlace_productos');
const PAGINAS_VISITADAS =  $('#pags_visitadas');

$.getJSON('http://tomcat.da2.dva:8080/api/sesion/usuarioEsAdmin', function(response) {
    if(response == null) {
        ENLACE_LOGIN.text("Login");
        ENLACE_REGISTRO.text("Registrarse");
    }
    else {
        if (response) {
            ENLACE_LOGIN.text("Área de Administrador");
            ENLACE_LOGIN.attr('href', 'http://tomcat.da2.dva:8080/admin/area-admin');
            ENLACE_PRODUCTOS.attr('href', 'http://tomcat.da2.dva:8080/admin/api/producto/listado');
        } else {
            ENLACE_LOGIN.text("Área de Cliente");
        }
        ENLACE_REGISTRO.text("Logout"); ENLACE_REGISTRO.attr('href', 'http://tomcat.da2.dva:8080/logout/invalidate');
    }
});
$.getJSON('http://tomcat.da2.dva:8080/api/sesion/paginas-visitadas', function(response) {
    if(response != null && response > 0) {
        PAGINAS_VISITADAS.text("Páginas visitadas: " + response);
    }
});
$.get('http://tomcat.da2.dva:8080/api/sesion/agregar-visita');

const SELECTOR_IDIOMAS = document.getElementById("language-select");

location.addEventListener("change", (event) => {
    seleccionarIdioma();
})

function seleccionarIdioma() {
    if (location.search.split("=")[1] === undefined)
        SELECTOR_IDIOMAS.value = 'es';
    else
        SELECTOR_IDIOMAS.value = location.search.split("=")[1];
}

SELECTOR_IDIOMAS.addEventListener("change", (event) => {
    const idioma = event.target.value;
    location.search = `?lang=${idioma}`
});

/* ---------------------- Tarjetad de Credito y Direcciones ---------------------- */


/* REGISTRO DE CLIENTE */
const btn_deseleccionar_genero = document.getElementById("btn_deseleccionar_genero");
const btn_seleccionar_primer_genero = document.getElementById("btn_seleccionar_primer_genero");
const btn_deseleccionar_pais = document.getElementById("btn_deseleccionar_pais");
const btn_deseleccionar_tipoDocumento = document.getElementById("btn_deseleccionar_tipoDocumento");
const btn_mostrar_claves = document.getElementById("btn_mostrar_claves");
const form = document.getElementById("form");

//const claveInput = document.querySelector('input[name="clave"]');
//const confirmarClaveInput = document.querySelector('input[name="confirmClave"]');

document.addEventListener('DOMContentLoaded', (event) => {
    // Inicializa todas las imágenes a su versión inactiva
    const pasos = ['numero-1', 'numero-2', 'numero-3', 'numero-4'];
    pasos.forEach(paso => document.getElementById(paso).src = `/img/numeros/${paso}-deselec.png`);

    // Obtiene la ruta de la página actual
    let rutaActual = window.location.pathname;

    // Determina qué imagen debe ser activa basándose en la ruta actual
    if(rutaActual.includes('paso1')) {
        document.getElementById('numero-1').src = '/img/numeros/numero-1.png';
    } else if(rutaActual.includes('paso2')) {
        document.getElementById('numero-2').src = '/img/numeros/numero-2.png';
    } else if(rutaActual.includes('paso3')) {
        document.getElementById('numero-3').src = '/img/numeros/numero-3.png';
    } else if(rutaActual.includes('paso4')) {
        document.getElementById('numero-4').src = '/img/numeros/numero-4.png';
    }
    // Repite para otros pasos si es necesario
});


asignarEventos();
function asignarEventos(){
    btn_deseleccionar_genero.addEventListener("click", deseleccionarGenero);
    btn_seleccionar_primer_genero.addEventListener("click", seleccionarPrimerGenero);
    btn_deseleccionar_pais.addEventListener("click", deseleccionarPais);
    btn_deseleccionar_tipoDocumento.addEventListener("click", deseleccionarTipoDocumento);
}

function deseleccionarGenero() {
    // Encuentra todos los elementos de entrada de radio con el nombre 'genero'
    let generoRadios = document.querySelectorAll('input[name="genero"]');

    // Itera sobre los elementos de entrada de radio y establece 'checked' en false
    generoRadios.forEach(function(radio) {
        radio.checked = false;
    });
}

function seleccionarPrimerGenero() {
    // Encuentra el primer elemento de entrada de radio con el nombre 'genero'
    let primerGeneroRadio = document.querySelector('input[name="genero"]:first-child');

    // Establece 'checked' en true para el primer elemento de entrada de radio
    primerGeneroRadio.checked = true;
}

function deseleccionarPais() {
    // Encuentra el elemento select con el nombre 'pais'
    let paisSelect = document.querySelector('select[name="pais"]');

    // Establece el valor del elemento select en vacío
    paisSelect.value = '';
}

function deseleccionarTipoDocumento() {
    let documentoRadios = document.querySelectorAll('input[name="tipoDocumento"]');

    // Itera sobre los elementos de entrada de radio y establece 'checked' en false
    documentoRadios.forEach(function(radio) {
        radio.checked = false;
    });
}

function vaciarFormulario() {
    if(form === null) {
        alert("El formulario no existe");
        return false;
    }
    if(confirm("¿Desea vaciar el formulario?")) {
        let elementos = form.elements;
        //recorrer elementos
        for (let i = 0; i < elementos.length; i++) {
            let tipo = elementos[i].type;
            if (tipo === "text" || tipo === "password" || tipo === "select-one" || tipo === "select-multiple") {
                elementos[i].value = ''; // Borrar el valor de los campos de texto, contraseñas y select(s)
            } else if (tipo === "checkbox" || tipo === "radio") {
                elementos[i].checked = false; // Desmarcar los checkboxes y radios
            }
        }
    }
}

