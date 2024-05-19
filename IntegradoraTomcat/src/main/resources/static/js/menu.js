const containerProductos = document.querySelector('.container-items');

if(containerProductos != null){
    listarProductos();
}

function crearItemProducto(producto) {
    let div = document.createElement('div'); div.classList.add('item');
    let figure = document.createElement('figure');
    let img = document.createElement('img'); img.alt = 'Imagen' + producto.modelo; img.src = devolverImagen(producto);
    let div2 = document.createElement('div'); div2.classList.add('info-product');
    let h2 = document.createElement('h2'); h2.textContent = producto.modelo;
    let marca = document.createElement('p'); marca.classList.add('brand'); marca.textContent = producto.marca;
    let descripcion = document.createElement('p'); descripcion.classList.add('description'); descripcion.textContent = producto.descripcion;
    let p = document.createElement('p'); p.classList.add('price'); p.textContent = producto.precio + ' €';
    let button = document.createElement('button'); button.classList.add('btn-add-cart'); button.textContent = 'Añadir al carrito';
    div2.append(h2, marca,descripcion ,p , button); figure.append(img); div.append(figure, div2);
    return div;
}



function listarProductos(){
    containerProductos.innerHTML = '<div id="search-mobile" class="input-container"><input type="text" id="input" name="searchBox" placeholder="Search for Clothing and Accessories" class="input search-input"></div>';
    $.getJSON("http://tomcat.da2.dva:8080/api/producto/listado", function(result) {
        $.each(result, function(i, field){
            containerProductos.appendChild(crearItemProducto(field));
        });
    });
}

function devolverImagen(producto){

    if(producto.imagenes == null){
        return "https://placehold.co/600x400"
    } else {
        return producto.imagenes[0];
    }
}


// Selecciona el botón del carrito y el contenedor de productos del carrito del DOM
const btnCart = document.querySelector('.container-cart-icon');
const containerCartProducts = document.querySelector('.container-cart-products');
const dropdownMenu = document.getElementById("dropdownMenu");

// Agrega un evento de clic al botón del carrito para alternar la visibilidad del contenedor de productos del carrito
btnCart.addEventListener('click', () => {
    containerCartProducts.classList.toggle('hidden-cart');
    dropdownMenu.classList.remove("show");

});

/* ========================= */
// Declaración de variables para interactuar con elementos del DOM

// Elementos relacionados con la información del producto y el carrito
const cartInfo = document.querySelector('.cart-product');
const rowProduct = document.querySelector('.row-product');
const cartEmpty = document.querySelector('.cart-empty');
const cartTotal = document.querySelector('.cart-total');

// Elementos relacionados con la lista de productos y el contador de productos
const productsList = document.querySelector('.container-items');
const countProducts = document.querySelector('#contador-productos');
const valorTotal = document.querySelector('.total-pagar');


// Array que almacena los productos en el carrito
let allProducts = [];

// Evento que escucha el clic en la lista de productos
productsList.addEventListener('click', e => {
    // Verifica si el clic ocurrió en un botón "Agregar al carrito"
    if (e.target.classList.contains('btn-add-cart')) {
        // Obtiene el elemento padre del botón (el producto)
        const product = e.target.parentElement;

        // Crea un objeto con la información del producto clickeado
        const infoProduct = {
            quantity: 1,
            title: product.querySelector('h2').textContent,
            price: product.querySelector('p').textContent,
        };

        // Verifica si el producto ya está en el carrito
        const exists = allProducts.some(
            product => product.title === infoProduct.title
        );

        // Si el producto existe, aumenta su cantidad en el carrito
        if (exists) {
            const products = allProducts.map(product => {
                if (product.title === infoProduct.title) {
                    product.quantity++;
                }
                return product;
            });
            allProducts = [...products];
        } else {
            // Si el producto no existe, lo añade al carrito
            allProducts = [...allProducts, infoProduct];
        }

        // Actualiza la visualización del carrito
        showHTML();
    }
});

// Evento que escucha el clic en el botón de cierre de un producto en el carrito
rowProduct.addEventListener('click', e => {
    if (e.target.classList.contains('icon-close')) {
        // Obtiene el elemento padre del botón de cierre (el producto)
        const product = e.target.parentElement;
        // Obtiene el título del producto para identificarlo en el arreglo
        const title = product.querySelector('p').textContent;

        // Filtra el arreglo de productos, removiendo el producto clickeado
        allProducts = allProducts.filter(
            product => product.title !== title
        );

        // Actualiza la visualización del carrito
        showHTML();
    }
});

// Función para mostrar el HTML del carrito
const showHTML = () => {
    if (!allProducts.length) {
        // Si no hay productos en el carrito, muestra el mensaje de carrito vacío y oculta otros elementos
        cartEmpty.classList.remove('hidden');
        rowProduct.classList.add('hidden');
        cartTotal.classList.add('hidden');
    } else {
        // Si hay productos en el carrito, muestra los productos y el total, y oculta el mensaje de carrito vacío
        cartEmpty.classList.add('hidden');
        rowProduct.classList.remove('hidden');
        cartTotal.classList.remove('hidden');
    }

    // Limpia el contenido del contenedor de productos
    rowProduct.innerHTML = '';

    let total = 0;
    let totalOfProducts = 0;

    // Itera sobre los productos en el carrito y los muestra en el HTML
    allProducts.forEach(product => {
        const containerProduct = document.createElement('div');
        containerProduct.classList.add('cart-product');

        containerProduct.innerHTML = `
            <div class="info-cart-product">
                <span class="cantidad-producto-carrito">${product.quantity}</span>
                <p class="titulo-producto-carrito">${product.title}</p>
                <span class="precio-producto-carrito">${product.price}</span>
            </div>
            <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke-width="1.5"
                stroke="currentColor"
                class="icon-close"
            >
                <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    d="M6 18L18 6M6 6l12 12"
                />
            </svg>
        `;

        // Agrega el producto al HTML del carrito
        rowProduct.append(containerProduct);

        // Calcula el total a pagar y el total de productos en el carrito
        total +=
            total + parseInt(product.quantity * product.price.slice(1));
        totalOfProducts += product.quantity;
    });

    // Actualiza el total a pagar y el contador de productos
    valorTotal.innerText = `$${total}`;
    countProducts.innerText = totalOfProducts;
};

/*--------------------------------- DESPLEGABLE AREA CLIENTE ---------------------------------*/
function toggleDropdown() {
    dropdownMenu.classList.toggle("show");
    containerCartProducts.classList.add('hidden-cart');

}

// Cerrar el menú desplegable si el usuario hace clic fuera de él
window.onclick = function(event) {
    if (!event.target.matches('.userIcon')) {
        let dropdowns = document.getElementsByClassName("dropdown-content");
        for (let i = 0; i < dropdowns.length; i++) {
            let openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}


//--------------------------------- MENU HAMBURGUESA ---------------------------------
function openNav(x) {
    document.getElementById("mySidenav").classList.toggle("show");
    x.classList.toggle("change");
}
//--------------------------------- OFERTAS ---------------------------------



