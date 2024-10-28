const App = require('./App');

App.listen(App.get('port'), ()  => {
    console.log ("Servidor escuchando en el puerto" , App.get ('port'))
})
