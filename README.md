# Supermercado


DESCRIPCIÓN del CASO y ALCANCE:  
•	Existen tres tipos de carritos:  
-	el común  
-	el promocional por fecha especial  
-	el promocional por cliente VIP  
•	Cuando el usuario solicita crear un nuevo carrito la aplicación determina el carrito correspondiente   
•	No se pueden combinar las promociones (Un carrito NO puede ser promocional VIP y por fecha  especial al mismo tiempo)  
•	El carrito promocional por cliente VIP es el tipo prioritario   
•	Manejo de promociones (Para calcular el valor del carrito)  
a.	Si se compran exactamente 10 productos: Se hace un descuento del 10 % sobre el  total a pagar  
b.	Si se compran más de 5 productos:  
1.	Si el carrito es promocional por fecha especial, se aplica un descuento   general de 500 $ (en caso de poder)  
2.	Si el carrito promocional por cliente vip, se bonifica el producto más barato  y se aplica un descuento general de $700.- (en caso de poder)  
•	La aplicación debe permitir:  
a.	crear un carrito nuevo  
b.	eliminar un carrito  
c.	agregar productos al carrito  
d.	eliminar productos del carrito  
e.	consultar estado del carrito (incluir el total a pagar) 
 
•	Reportes: Dado el número de documento del cliente se necesita saber los 4 productos más caros que compró considerando todo su historial de compras   
CONSIDERACIONES:  
•	No es necesario desarrollar el ABM de los productos ni de los usuarios, con el hecho de tenerlos  en la base de datos cargados y que el servicio reciba el id para funcionar es suficiente.   
•	Es necesario que estén modelados en objetos (No hace falta modelar el pago sino simplemente  que quede guardado con los ítems correspondientes)  
•	No se deberá manejar usuarios (sesiones)   
•	El usuario tiene entre sus atributos la opción de verificar si es VIP. También sería necesario simular algún parámetro del sistema que nos indique si estamos en una fecha promocional sin  demasiada complejidad (puede ser una variable local que nos devuelva true o false).  
Ejemplo de llamadas a implementar:  
1)	Dar de alta un nuevo carrito:  
Se envía: DNI del usuario.  
Devuelve: id del nuevo carrito.  
2)	Eliminar un carrito:  
Se envía: id del carrito.  
3)	Agregar un producto a un carrito:  
Se envía: id del producto, id del carrito.  
Devuelve: Estado del carrito con su id, lista de items y total.  
4)	Eliminar un producto de un carrito:  
Se envía: id del producto, id del carrito.  
 Devuelve: Estado del carrito con su id, lista de items y total.  
5)	Devolver Productos más caros:  
Se envía: DNI del usuario.  
Devuelve: Lista con los 4 productos más caros que compro en su historia.   
