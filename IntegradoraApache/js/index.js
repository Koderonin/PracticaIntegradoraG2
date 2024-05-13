const containerProductos = document.querySelector('.container-items');
listarProductos();
function crearItemProducto(producto) {
    var itemHTML = `
        <div class="item">
            <figure>
                <img src="https://via.placeholder.com/300" class="product-image" alt="producto">
            </figure>
            <div class="info-product">
                <h2>${producto.descripcion}</h2>
                <p class="price">$${producto.precio}</p>
                <button class="btn-add-cart">Añadir al carrito</button>
            </div>
        </div>
    `;
    return itemHTML;
}



function listarProductos(){
    for (var i = 0; i < 9 ; i++) { 
        var itemHTML = crearItemProducto(listaProductos[i]);
        containerProductos.innerHTML += itemHTML;
        console.log(listaProductos[1].descripcion);
    }

}





// Selecciona el botón del carrito y el contenedor de productos del carrito del DOM
const btnCart = document.querySelector('.container-cart-icon');
const containerCartProducts = document.querySelector('.container-cart-products');

// Agrega un evento de clic al botón del carrito para alternar la visibilidad del contenedor de productos del carrito
btnCart.addEventListener('click', () => {
	containerCartProducts.classList.toggle('hidden-cart');
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

