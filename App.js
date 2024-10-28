const express= require ('express');
const config = require ('./config')

const clientes = require ('./modulos/clientes/rutas')
const App = express ();
//configuraciones
App.set('port', config.App.port)

//rutas
App.use('/api/clientes', clientes)

module.exports = App;