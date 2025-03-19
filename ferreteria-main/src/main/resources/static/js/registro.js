function nuevoUsuario() {
    let nombre = document.getElementById("txtNomUs").value;
    let apellido = document.getElementById("txtApeUs").value;
    let email = document.getElementById("txtemailUs").value;
    let password = document.getElementById("txtPswUs").value;
    let rolAdmin = document.getElementById("checkbox").checked ? ["ADMIN"] : ["USER"];

    // Crear el objeto con los datos
    let usuario = {
        nombre: nombre,
        apellido: apellido,
        email: email,
        password: password,
        roles: rolAdmin
    };

    // Enviar la solicitud POST con fetch
    fetch("/api/v1/personas", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(usuario)
    })
    .then(response => response.json())
    .then(data => {
        if (data) {
            alert("Usuario registrado correctamente");
        }
    })
    .catch(error => {
        console.error("Error al registrar usuario:", error);
        alert("Hubo un error al registrar al usuario");
    });
}
