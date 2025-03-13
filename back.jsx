import React, { useEffect, useState } from 'react';

function App() {
    const [productos, setProductos] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/productos")
            .then(response => response.json())
            .then(data => setProductos(data))
            .catch(error => console.error("Error:", error));
    }, []);

    return (
        <div>
            <h1>Productos</h1>
            <ul>
                {productos.map(producto => (
                    <li key={producto.id}>
                        {producto.nombre} - Stock: {producto.stock} - Precio: ${producto.precio}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default App;
