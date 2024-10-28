import mysql from 'mysql2/promise';

const connectDB = async () => {
    const connection = await mysql.createConnection({
        host: 'localhost',
        user: 'Admin',
        password: 'Ferreteria',
        database: 'merndb'
    });

    console.log('Conectado a la base de datos MySQL');
    return connection;
};
