class ValidadorFormulario {
    static validarCorreo(correo) {
        const regexCorreo = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        return regexCorreo.test(correo);
    }

    static validarDni(dni) {
        const regexDni = /^\d+$/;
        return regexDni.test(dni);
    }

    static validarContraseña(contraseña) {
        const regexContraseña = /^(?=.*[A-Z]).{8,}$/;
        return regexContraseña.test(contraseña);
    }
}

function validarFormulario() {
    const correo = document.getElementById('correo').value;
    const dni = document.getElementById('dni').value;
    const contraseña = document.getElementById('contraseña').value;
    const confirmarContraseña = document.getElementById('confirmar-contraseña').value;

    let esValido = true;

    // Limpiar mensajes de error
    document.getElementById('correoError').textContent = '';
    document.getElementById('dniError').textContent = '';
    document.getElementById('contraseñaError').textContent = '';
    document.getElementById('confirmarContraseñaError').textContent = '';

    if (!ValidadorFormulario.validarCorreo(correo)) {
        document.getElementById('correoError').textContent = 'Correo inválido.';
        esValido = false;
    }

    if (!ValidadorFormulario.validarDni(dni)) {
        document.getElementById('dniError').textContent = 'El DNI debe contener solo números.';
        esValido = false;
    }

    if (!ValidadorFormulario.validarContraseña(contraseña)) {
        document.getElementById('contraseñaError').textContent = 'La contraseña debe tener al menos 8 caracteres y una letra mayúscula.';
        esValido = false;
    }

    if (contraseña !== confirmarContraseña) {
        document.getElementById('confirmarContraseñaError').textContent = 'Las contraseñas no coinciden.';
        esValido = false;
    }

    if (esValido) {
        alert('Formulario válido. ¡Registro exitoso!');
        window.location.href = './crear.html'; // Redirigir a la página de "Crear"
    }
}
