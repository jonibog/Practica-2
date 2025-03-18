document.addEventListener("DOMContentLoaded", () => {
   obtenerProductos();
});


async function obtenerProductos(){
    try{
        const response = await fetch('/api/v1/productos',{
            method: 'GET',
        });
        if (response.ok) {
            const jsonProd = await response.json(); // Obtener la respuesta en formato JSON
            mostrarProductos(jsonProd);
            /*console.log(jsonProd);*/
        }
    }catch(error){
        console.error("Respuesta con error", error.message);
    }
}


function mostrarProductos(jsonProd){
    const tbody = document.querySelector("#tDatos tbody");
    tbody.innerHTML="";

    jsonProd.forEach(producto=>{
        const fila = tbody.insertRow(); // Crear una nueva FILA
        //Columna 1: ID producto
        const celdaId=document.createElement("td");
        celdaId.textContent=producto.id;
        fila.appendChild(celdaId);
        //Columna 2: Nombre
        const celdaNombre=document.createElement("td");
        const txtNom = document.createElement("input");
        txtNom.value=producto.nombre;
        celdaNombre.appendChild(txtNom);
        fila.appendChild(celdaNombre);
        //Columna 3: Precio
        const celdaPrecio=document.createElement("td");
        const txtPrecio = document.createElement("input");
        txtPrecio.value=producto.precio;
        celdaPrecio.appendChild(txtPrecio);
        fila.appendChild(celdaPrecio);
        //Columna 4: Stock
        const celdaStock=document.createElement("td");
        const txtStock = document.createElement("input");
        txtStock.value=producto.stock;
        celdaStock.appendChild(txtStock);
        fila.appendChild(celdaStock);
        //Columna 5: Guardar / Editar
        const celdaGuardar=document.createElement("td");
        const btnGuardar = document.createElement("button");
        btnGuardar.textContent="Guardar";
        btnGuardar.addEventListener("click", function () {
            guardarProducto(this);
        });
        celdaGuardar.appendChild(btnGuardar);
        fila.appendChild(celdaGuardar);
        tbody.appendChild(fila);
    });
}

async function guardarProducto(boton){
    const fila = boton.closest("tr"); // Obtener la fila donde se encuentra el botón presionado
    const pId = parseInt(fila.cells[0].innerHTML); // Obtener el ID del Producto
    const pNombre = fila.cells[1].querySelector("input").value;
    const pPrecio = fila.cells[2].querySelector("input").value;
    const pCantidad =fila.cells[3].querySelector("input").value;
    const producto={
        id: pId,
        nombre: pNombre,
        precio: pPrecio,
        stock: pCantidad
    };
    try {
        const response = await fetch('/api/v1/productos', {
            method: 'PUT',
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(producto)
        });
            if (!response.ok) throw new Error(`Error: ${response.status}`);
            const respuesta = await response.json(); // Obtener la respuesta en formato JSON
            if (response.ok) {
                alert("Producto Modificado 👌");
            } else {
                alert(`Error al guardar el Producto. Errror: [${respuesta}]`);
            }
    } catch (error) {
        console.error("Error:", error);
    }finally {
    }
}

async function nuevoProducto(){
    const producto={
        nombre: document.querySelector('#txtNomNuevProd').value,
        precio: document.querySelector('#txtPrecio').value,
        stock: document.querySelector('#txtCantidad').value
    };
    try {
        const response = await fetch('/api/v1/productos', {
            method: 'PUT',
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(producto)
        });
            if (!response.ok) throw new Error(`Error: ${response.status}`);
            const respuesta = await response.json(); // Obtener la respuesta en formato JSON
            if (response.ok) {
                alert("Nuevo Prodcuto cargado 🪒");
                obtenerProductos();
            } else {
                alert(`Error al guardar el Producto. Errror: [${respuesta}]`);
            }
    } catch (error) {
        console.error("Error:", error);
    }finally {
    }
}