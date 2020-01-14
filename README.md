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
> **[&nbsp;&nbsp;&nbsp;&nbsp;  2.1 Casos de Usos](#5):**<br></p>
> **[3. Actividad 4](#6):**<br></p>
> **[4. Actividad 5](#7):**<br></p>

<a name="8"></a>

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
   	 <td></td>
    </tr>
    <tr>
   	 <td><b>ID: </b></td>
   	 <td></td>
    </tr>
    <tr>
   	 <td><b>Descripción:</b></td>
   	 <td></td>
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
      <td><br></td>
    </tr>
    <tr>
      <td><b>Postcondiciones:</b></td>
      <td><br></td>
    </tr>
    <tr>
      <td><b>Alternativas/Excepciones:</b></td>
      <td></td>
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
<img src="/recursos/digramaModelo1.PNG"/>
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

