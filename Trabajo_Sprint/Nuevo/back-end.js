// Actualiza la URL para que apunte al servidor del back-end
fetch('http://localhost:8080/api/productos')
  .then(response => response.json())
  .then(data => {
    console.log(data);
  })
  .catch(error => {
    console.error('Error:', error);
  });