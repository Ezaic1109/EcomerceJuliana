let carrito = JSON.parse(localStorage.getItem('carrito')) || [];

function agregarAlCarrito(producto) {
    carrito.push(producto);
    localStorage.setItem('carrito', JSON.stringify(carrito));
    document.getElementById("contador").textContent = carrito.length;
}

function cargarCarrito() {
    document.getElementById("contador").textContent = carrito.length;
    const lista = document.getElementById("listaCarrito");
    if (lista) {
        carrito.forEach(p => {
            const li = document.createElement('li');
            li.textContent = p;
            li.className = 'list-group-item';
            lista.appendChild(li);
        });
    }
}

cargarCarrito();
