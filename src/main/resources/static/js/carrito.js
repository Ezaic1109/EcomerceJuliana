let carrito = [];

function agregarAlCarrito(producto) {
  carrito.push(producto);
  document.getElementById("contador").textContent = carrito.length;
  console.log("Carrito:", carrito);
}

fetch("http://localhost:8080/api/producto")
  .then(res => res.json())
  .then(productos => {
    const grid = document.querySelector(".grid-productos");

    productos.forEach(p => {
      const card = document.createElement("article");
      card.classList.add("producto-card");

      card.innerHTML = `
        <h3>${p.nombre}</h3>
        <p>$${p.precio}</p>
        <button onclick="agregarAlCarrito('${p.nombre}')">Agregar al carrito</button>
        <button onclick="verDetalle('${p.nombre}')">Ver más</button>
      `;

      grid.appendChild(card);
    });
  });

function verDetalle(variante) {
  // Redirige al HTML de producto con el parámetro "variante"
  window.location.href = `../../templates/index.html?variante=${encodeURIComponent(variante)}`;
}