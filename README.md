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
<table class="egt">

  <tr>

    <th scope="row">Día</th>

    <th>Hoy</th>

    <th>Mañana</th>

    <th>Jueves</th>

  </tr>

  <tr>

    <th>Condición</th>

    <td>Soleado</td>

    <td>Mayormente soleado</td>

    <td>Parcialmente nublado</td>

  </tr>

  <tr>

    <th>Temperatura</th>

    <td>19°C</td>

    <td>17°C</td>

    <td>12°C</td>

  </tr>

  <tr>

    <th>Vientos</th>

    <td>E 13 km/h</td>

    <td>E 11 km/h</td>

    <td>S 16 km/h</td>

  </tr>

</table>

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


	<table>
		<tr>
			<td>Nombre</td>
			<td>Registrarse</td>
		</tr>
		<tr>
			<td>ID</td>
			<td>CU - 1</td>
		</tr>
		<tr>
			<td>Descripción</td>
			<td>El usuario podrá registrarse en nuestro sistema si no tiene cuenta.<br />Para registrarse en el sistema el usuario debe rellenar un formulario con una serie de datos que serán validados.</td>
		</tr>
		<tr>
			<td>Actores</td>
			<td>Usuario</td>
		</tr>
		<tr>
			<td>Precondiciones</td>
			<td>Nada</td>
		</tr>
		<tr>
			<td>Curso normal</td>
			<td>El usuario rellenará un formulario, los datos introducidos serán validados.<br />Si los datos introducidos son correctos se generará el usuario y contraseña  con el que el usuario podrá loguearse.</td>
		</tr>
		<tr>
			<td>Postcondiciones</td>
			<td>El usuario se loguea automáticamente</td>
		</tr>
		<tr>
			<td>Alternativas</td>
			<td>Nada</td>
		</tr>
	</table>

<p align="center">
| Nombre         |       |
|----------------|-------|
| ID             | CU -  |
| Descripción    |       |
| Actores        |       |
| Precondiciones |       |
| Curso normal   |       |
| Postcondicones |       |
| Alternativas   |       |
<img src="/recursos/diagramaCU.PNG"/>
</p>
<p align="center">
<img src="/recursos/cu1.PNG"/>
</p>
<p align="center">
<img src="/recursos/cu2.PNG"/>
</p>
<p align="center">
<img src="/recursos/cu3.PNG"/>
</p>
<p align="center">
<img src="/recursos/cu4.PNG"/>
</p>
<p align="center">
<img src="/recursos/cu5.PNG"/>
</p>
<p align="center">
<img src="/recursos/cu6.PNG"/>
</p>
<p align="center">
<img src="/recursos/cu7.PNG"/>
</p>

<a name="6"></a>
## 3. Actividad 4(Diagramas)

<p align="center">
<img src="/recursos/digramaModelo.PNG"/>
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

