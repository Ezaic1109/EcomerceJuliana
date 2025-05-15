let carrito = [];

function agregarAlCarrito(producto) {
  carrito.push(producto);
  document.getElementById("contador").textContent = carrito.length;
  console.log("Carrito:", carrito);
}

