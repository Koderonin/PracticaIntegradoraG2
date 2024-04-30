
/* REGISTRO DE CLIENTE */
const btn_deseleccionar_genero = document.getElementById("btn_deseleccionar_genero");
const btn_seleccionar_primer_genero = document.getElementById("btn_seleccionar_primer_genero");
const btn_deseleccionar_pais = document.getElementById("btn_deseleccionar_pais");
const btn_deseleccionar_tipoDocumento = document.getElementById("btn_deseleccionar_tipoDocumento");
const btn_mostrar_claves = document.getElementById("btn_mostrar_claves");
const form = document.getElementById("form");
const claveInput = document.querySelector('input[name="clave"]');
const confirmarClaveInput = document.querySelector('input[name="confirmClave"]');

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

}

function deseleccionarPais() {

}

function deseleccionarTipoDocumento() {

}
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
