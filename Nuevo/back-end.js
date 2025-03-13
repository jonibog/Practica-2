async function crearProducto() {
  const nuevoProducto = {
      categoriaId: 1,
      nombre: "Producto Nuevo",
      stock: 100,
      precio: 999.99
  };

  try {
      const response = await fetch("http://localhost:8080/productos", {
          method: "POST",
          headers: {
              "Content-Type": "application/json"
          },
          body: JSON.stringify(nuevoProducto)
      });

      const data = await response.json();
      console.log("Producto creado:", data);
  } catch (error) {
      console.error("Error al crear producto:", error);
  }
}

crearProducto();
