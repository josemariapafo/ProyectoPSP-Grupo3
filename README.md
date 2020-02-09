## PROYECTO PSP
### <p align="center"><b>GESTION DE BOCATERÍA</b></p>
<p align="center">
<img src="/recursos/bocateriaFotoP.png"/>
</p><br/>
<p>
<a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/"><img alt="Licencia de Creative Commons" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-nd/4.0/88x31.png" /></a><br />Este obra está bajo una <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">licencia de Creative Commons Reconocimiento-NoComercial-SinObraDerivada 4.0 Internacional</a>.</p>

<p>Alumnos: <br>
  -José María Parrilla Forte</br>
  -Sebastián Cuesta Molto</p>

# <p align="center"><b>Índice:</b></br>
> **[1. Actividad 1 y 2](#1):**<br>
> **[&nbsp;&nbsp;&nbsp;&nbsp;  1.1 Resumen](#2):**<br>
> **[&nbsp;&nbsp;&nbsp;&nbsp;  1.2 Funcionalidades](#3):**<br>
> **[2. Actividad 3](#4):**<br>
> **[&nbsp;&nbsp;&nbsp;&nbsp;  2.1 Casos de Usos](#5):**<br>
> **[3. Actividad 4](#6):**<br>
> **[4. Actividad 5](#7):**<br>
> **[5. Actividad 6](#8):**<br>
> **[6. Actividad 7. Interfaces y Clases](#9):**<br>
> **[7. Actividad 8](#10):**<br></p>


<a name="1"></a>
## 1. Actividad 1 y 2
<a name="2"></a>
##  1.1 Resumen
Nuestro proyecto será de la gestión de entregas de bocadillos en horas puntas, agilizando así la venta de bocadillos, haciendo que el usuario no tenga que esperar dentro del local más que para recoger el bocadillo.

<a name="3"></a>
##  1.2 Funcionalidades
***Funcionalidades***
- **Ingresar al sistema:** El usuario deberá de haberse registrado anteriormente para poder loguearse, una vez logueado tendrá permisos a todas las funciones de la aplicación.
- **Registro:** El usuario debe crearse una cuenta para usar nuestro servicio, uno de los datos que se pedirá para evitar pedidos sin abonar, es un número de teléfono, y para poder completar el registro se mandará al usuario un sms con un código de verificación.
- **Consulta productos:** El usuario tendrá una vista disponible de todos los productos que ofrecemos para así poder elegir su pedido.
- **Realizar pedidos :** El usuario al completar el pedido con los productos del carrito.
- **Pagar pedidos :** El usuario pagará el pedido para que se pueda generar la comanda.
- **Pedido y Orden Generadas :** Una vez el usuario paga el pedido, se genera una orden o comanda que recogerán en el local para tener listo el pedido del cliente a su hora.
- **Chat de texto que difunde mensajes (descuentos u otros):** En este chat se notificará a todos los usuarios de la aplicación de ofertas, descuentos o asistencia en caso de querer un bocadillo personalizado.
<a name="4"></a>
## 2. Actividad 3
<a name="5"></a>
## 2.1 Casos de Usos
<p align="center"><img src="/recursos/diagramaCU.PNG"/></p>
<table style="width:100%">
    <tr>
   	 <td><b>Nombre: </b></td>
   	 <td>Registrarse</td>
    </tr>
    <tr>
   	 <td><b>ID: </b></td>
   	 <td>CU - 1</td>
    </tr>
    <tr>
   	 <td><b>Descripción:</b></td>
   	 <td>El usuario podrá registrarse en nuestro sistema si no tiene cuenta.
Para registrarse en el sistema el usuario debe rellenar un formulario con una serie de datos que serán validados.
</td>
    </tr>
    <tr>
   	 <td><b>Actores:</b></td>
   	 <td>Usuario.</td>
    </tr>
    <tr>
      <td><b>Precondiciones:</b></td>
      <td>Nada.</td>
    </tr>
    <tr>
      <td><b>Curso normal del caso de uso:</b></td>
      <td>El usuario rellenará un formulario, los datos introducidos serán validados.<br>
Si los datos introducidos son correctos se generará el usuario y contraseña  con el que el usuario podrá loguearse.
</td>
    </tr>
    <tr>
      <td><b>Postcondiciones:</b></td>
      <td>El usuario se loguea automáticamente</td>
    </tr>
    <tr>
      <td><b>Alternativas/Excepciones:</b></td>
      <td></td>
    </tr>
</table>

<table style="width:100%">
    <tr>
   	 <td><b>Nombre: </b></td>
   	 <td>Loguearse</td>
    </tr>
    <tr>
   	 <td><b>ID: </b></td>
   	 <td>CU - 2</td>
    </tr>
    <tr>
   	 <td><b>Descripción:</b></td>
   	 <td>Si el usuario tiene cuenta en el sistema se logueará para poder acceder a él.<br><br>Para loguearse en el sistema solo hará falta que el usuario proporcione su usuario y contraseña.</td>
    </tr>
    <tr>
   	 <td><b>Actores:</b></td>
   	 <td>Usuario.</td>
    </tr>
    <tr>
      <td><b>Precondiciones:</b></td>
      <td>Que el usuario se encuentre registrado en nuestro sistema</td>
    </tr>
    <tr>
      <td><b>Curso normal del caso de uso:</b></td>
      <td>1. Si el usuario se encuentra registrado rellenará un formulario con su usuario y contraseña.<br>2. Si los datos son correctos podrá ingresar al sistema.</td>
    </tr>
    <tr>
      <td><b>Postcondiciones:</b></td>
      <td>Nada</td>
    </tr>
    <tr>
      <td><b>Alternativas/Excepciones:</b></td>
      <td>1A. Si el usuario no tiene cuenta en el sistema no podrá loguearse, por lo que será redirigido al formulario de registro.</td>
    </tr>
</table>

<table style="width:100%">
    <tr>
   	 <td><b>Nombre: </b></td>
   	 <td>Consultar Productos</td>
    </tr>
    <tr>
   	 <td><b>ID: </b></td>
   	 <td>CU-3</td>
    </tr>
    <tr>
   	 <td><b>Descripción:</b></td>
   	 <td>El usuario podrá acceder a ver una lista de los productos que ofrece la bocatería sin la necesidad de tener que estar logueado.</td>
    </tr>
    <tr>
   	 <td><b>Actores:</b></td>
   	 <td>Usuario / Usuario Autentificado</td>
    </tr>
    <tr>
      <td><b>Precondiciones:</b></td>
      <td>Estar Registrado</td>
    </tr>
    <tr>
      <td><b>Curso normal del caso de uso:</b></td>
      <td>1.El cliente hará una consulta a la base de datos<br>
2.Dependiendo del número de consultas se generarán sus correspondiente productos visualmente.
</td>
    </tr>
    <tr>
      <td><b>Postcondiciones:</b></td>
      <td>Entrar al sistema(Caso de Uso: Loguearse)</td>
    </tr>
    <tr>
      <td><b>Alternativas/Excepciones:</b></td>
      <td>Nada</td>
    </tr>
</table>

<table style="width:100%">
    <tr>
   	 <td><b>Nombre: </b></td>
   	 <td>Realizar Pedido</td>
    </tr>
    <tr>
   	 <td><b>ID: </b></td>
   	 <td>CU-4</td>
    </tr>
    <tr>
   	 <td><b>Descripción:</b></td>
   	 <td>El usuario una vez consultado los productos ofertados elegirá aquellos 
        que desee incluir en su pedido.
        </td>
    </tr>
    <tr>
   	 <td><b>Actores:</b></td>
   	 <td>Usuario Autentificado</td>
    </tr>
    <tr>
      <td><b>Precondiciones:</b></td>
      <td>El usuario debe de tener una cuenta en el sistema y haberse logueado.</td>
    </tr>
    <tr>
      <td><b>Curso normal del caso de uso:</b></td>
      <td>1.El usuario elige productos que se van añadiendo a una lista<br>2.El precio aparecerá reflejado junto a una lista y será la suma del valor de los productos añadidos a dicha lista</td>
    </tr>
    <tr>
      <td><b>Postcondiciones:</b></td>
      <td>El usuario debe de pagar el pedido.</td>
    </tr>
    <tr>
      <td><b>Alternativas/Excepciones:</b></td>
      <td>1.A. El usuario selecciona un producto no disponible<br>1.B. El sistema notificará al usuario de que el producto no se añadirá y el motivo por el que el producto no está disponible.</td>
    </tr>
</table>

<table style="width:100%">
    <tr>
   	 <td><b>Nombre: </b></td>
   	 <td>Pagar pedido</td>
    </tr>
    <tr>
   	 <td><b>ID: </b></td>
   	 <td>CU - 5</td>
    </tr>
    <tr>
   	 <td><b>Descripción:</b></td>
   	 <td>El usuario pagará el pedido para que se pueda generar la comanda</td>
    </tr>
    <tr>
   	 <td><b>Actores:</b></td>
   	 <td>Usuario autentificado</td>
    </tr>
    <tr>
      <td><b>Precondiciones:</b></td>
      <td>El usuario debe haber realizado un pedido.</td>
    </tr>
    <tr>
      <td><b>Curso normal del caso de uso:</b></td>
      <td>1. El usuario introduce los datos de tarjeta<br>2. Se valida la tarjeta<br>3. Se procede al cobro y a la generación de la comanda.</td>
    </tr>
    <tr>
      <td><b>Postcondiciones:</b></td>
      <td>Nada</td>
    </tr>
    <tr>
      <td><b>Alternativas/Excepciones:</b></td>
      <td>3.A La tarjeta no pudo ser validada<br>4.A Se notifica al cliente que introduzca otros datos o cancele el pedido.</td>
    </tr>
</table>

<table style="width:100%">
    <tr>
   	 <td><b>Nombre: </b></td>
   	 <td>Genera Comanda </td>
    </tr>
    <tr>
   	 <td><b>ID: </b></td>
   	 <td>CU - 6</td>
    </tr>
    <tr>
   	 <td><b>Descripción:</b></td>
   	 <td>Una vez el usuario ha pagado el pedido, se genera una orden o comando que recogerá en el local para tener listo el pedido del cliente a su hora.<br>Y se generará un ticket o resguardo para el usuario.</td>
    </tr>
    <tr>
   	 <td><b>Actores:</b></td>
   	 <td>Usuario Autentificado</td>
    </tr>
    <tr>
      <td><b>Precondiciones:</b></td>
      <td>El usuario debe de haber pagado el pedido realizado</td>
    </tr>
    <tr>
      <td><b>Curso normal del caso de uso:</b></td>
      <td>1.El sistema generará una comanda que será impresa en el local.<br>2.El sistema generará un resguardo o comprobante para el usuario.</td>
    </tr>
    <tr>
      <td><b>Postcondiciones:</b></td>
      <td>Generación de comanda</td>
    </tr>
    <tr>
      <td><b>Alternativas/Excepciones:</b></td>
      <td>Nada</td>
    </tr>
</table>

<table style="width:100%">
    <tr>
   	 <td><b>Nombre: </b></td>
   	 <td>Chat de texto</td>
    </tr>
    <tr>
   	 <td><b>ID: </b></td>
   	 <td>CU - 7</td>
    </tr>
    <tr>
   	 <td><b>Descripción:</b></td>
   	 <td>El chat de texto tendrá como objetivo dar soporte a todas las personas que se encuentran conectadas a nuestra app. En este se notificarán los descuentos, días festivos, etc.</td>
    </tr>
    <tr>
   	 <td><b>Actores:</b></td>
   	 <td>Usuario autentificado y administrador</td>
    </tr>
    <tr>
      <td><b>Precondiciones:</b></td>
      <td>Ingresar en el sistema</td>
    </tr>
    <tr>
      <td><b>Curso normal del caso de uso:</b></td>
      <td>1. El usuario chat<br>2. El usuario recibirá descuentos.<br>3. El usuario podrá enviar mensajes al personal de la bocatería.<br>4. El usuario será contestado por el personal.</td>
    </tr>
    <tr>
      <td><b>Postcondiciones:</b></td>
      <td>Nada</td>
    </tr>
    <tr>
      <td><b>Alternativas/Excepciones:</b></td>
      <td>Nada</td>
    </tr>
</table>

<table style="width:100%">
    <tr>
   	 <td><b>Nombre: </b></td>
   	 <td>Consultar comandas</td>
    </tr>
    <tr>
   	 <td><b>ID: </b></td>
   	 <td>CU - 8</td>
    </tr>
    <tr>
   	 <td><b>Descripción:</b></td>
   	 <td>El administrador tiene permiso para consultar las comandas que se han ido generando conforme a los pedidos realizados por los clientes.<br>Estas podrán ser impresas.</td>
    </tr>
    <tr>
   	 <td><b>Actores:</b></td>
   	 <td>Administrador</td>
    </tr>
    <tr>
      <td><b>Precondiciones:</b></td>
      <td>Usuario autenticado debe haber realizado algun pedido.</td>
    </tr>
    <tr>
      <td><b>Curso normal del caso de uso:</b></td>
      <td>1. Se genera un archivo que puede ser consultado<br>2. El fichero generado se muestra (pantalla o impreso)</td>
    </tr>
    <tr>
      <td><b>Postcondiciones:</b></td>
      <td>Nada</td>
    </tr>
    <tr>
      <td><b>Alternativas/Excepciones:</b></td>
      <td>1. Si no hay comandas el sistema así lo indicará.</td>
    </tr>
</table>


<a name="6"></a>
## 3. Actividad 4(Diagramas)

<p align="center">
<img src="/recursos/clasdigv2.png"/>
</p>
<p align="center">
<img src="/recursos/diagramaEntidadRelacion.PNG"/>
</p>

<a name="3"></a>
## 4. Actividad 5(Vistas)
<p align="center">
<img src="/recursos/viewMain.png"/>
</p>
<p align="center">
<img src="/recursos/Cart.png"/>
</p>
<p align="center">
<img src="/recursos/viewRegister.png"/>
</p>
<p align="center">
<img src="/recursos/AdministratorSandwichRegister.png"/>
</p>
<p align="center">
<img src="/recursos/viewChat.png"/>
</p>
 
 
<a name="8"></a>
## Actividad 6 Hilos
- Realizar Pedido: El usuario a la hora de realizar un pedido actuará de consumidor, ya que bajará el stock dependiendo de cuantas compre, mientras que el proveedor/admin será el productor encargado de actualizar el stock, la cola en este caso será el stock.
- Chat: El administrador de la aplicación actuará de productor porque será el encargado de poner las ofertas, la cual será vista por todos los usuarios que serán los consumidores, el mensaje del administrador será la cola.

<a name="10"></a>
## Actividad 7 Interfaces y Clases
<p align="center">
  <img src="/recursos/clases/interfaces/ifazDAO.PNG"/>
  <img src="/recursos/clases/interfaces/ifazDAOManager.PNG"/>
  <img src="/recursos/clases/interfaces/ifazUsuarioDAO.PNG"/>
  <img src="/recursos/clases/interfaces/ifazProductoDAO.PNG"/>
  <img src="/recursos/clases/interfaces/ifazPedidoDAO.PNG"/>
  <img src="/recursos/clases/vo/UsuarioVO.PNG"/>
  <img src="/recursos/clases/vo/ProductoVO.PNG"/>
  <img src="/recursos/clases/vo/PedidoVO.PNG"/>
  <img src="/recursos/clases/bd/BDManager.PNG"/>
  <img src="/recursos/clases/bd/BDUsuario.PNG"/>
  <img src="/recursos/clases/bd/BDProducto.PNG"/>
  <img src="/recursos/clases/bd/BDPedido.PNG"/>
 </p>

<a name="10"></a>
## Actividad 8 Diagramas de secuencia
<p align="center">
  <img src="/recursos/seqdig/cu1.PNG"/>
  <img src="/recursos/seqdig/cu2.PNG"/>
  <img src="/recursos/seqdig/cu3.PNG"/>
  <img src="/recursos/seqdig/cu4.PNG"/>
  <img src="/recursos/seqdig/cu56.PNG"/>
  <img src="/recursos/seqdig/cu7.PNG"/>
 </p>
 
 ## Actividad: Clases y Diagrama se Sequencia de un Caso de Uso
 <h1>Caso de Uso: Registro de Sandwich</h1>
 <h1>Clases que intervienen:</h1>
  <p align="center">
  <img src="/recursos/clase1.PNG"/>
  <img src="/recursos/clase2.PNG"/>
  <img src="/recursos/clase3.PNG"/>
  <img src="/recursos/clase4.PNG"/>
  <img src="/recursos/clase5.PNG"/>
  <img src="/recursos/clase6.PNG"/>
  <img src="/recursos/clase7.PNG"/>
 </p>
 <h1>Diagrama de Sequencia</h1>
 <p align="center">
  <img src="/recursos/dc1.PNG"/>
  <img src="/recursos/dc2.PNG"/>
  <img src="/recursos/dc3.PNG"/>
 </p>
