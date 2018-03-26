# TestProtocoloRayita
Proyecto que prueba la correcta implementación del protocolo definido en el equipo para el desarrollo del juego rayita taller 2 de informática 1 en la especialización de ing. software de la UD

##Potocolo
Formato de respuesta:

~~~
QDTAAAAMMDDhhmmssCMDPARAM
~~~
Donde

* **QDT** : id que identifica la cabecera.
* **AAAAMMDD** : fecha de la conección AAAA (año), MM (mes), DD (dia).
* **hhmmss** : hora de la conección hh (hora), mm (minutos), ss (segundos).
* **CMD** : comando que puede ser INI, SNM, TUR, JUG.
* **PARAM** : parametro variable que acompaña al comando

##Lista de comandos:
###INI
Establece la coneccion con el servidor. Este comando se debe enviar cuando se solicita conexion con el servidor
	
	Respuesta:
	OK,FF,CC
	
* OK : la conección fue correcta en caso de error envia NK (No OK)
* FF : numero de filas del juego es un valor que va de 10 a 50
* CC : numero de columnas del juego un valor que va de 10 a 50

###SNM
Alias o nombre del jugador

	Param:
	Nombre del jugador
	
	Respuesta:
	OK,Nombre del contricante
	
###TUR
Consulta el turno en el que se encuentra segun el servidor

	Respuesta:
	OK,X
	
* X : coresponde al turno y tiene de valores 1 (el servidor) y 2 (el cliente)

###JUG
Notifica al servidor/cliente que hizó una jugada y cual fue la jugada.

	Param:
	FF,CC,L
	
* FF : fila en la que hizo la jugada.
* CC : columna en la que hizo la jugada.
* L : Coresponde a la jugada que hizo siendo: 0 (arriba), 1 (derecha), 2(abajo), 3 (izquierda).

_

	Respuesta:
	OK,T,F
	NK,E
		
* T : Estado de la jugada: si aun no se cierra una celda (0), si cerro una celda el servidor, si cerro una celda el cliente.
* F : responde 0 si el juego sigue, 1 se alguien gano.

* E : reponde si no es posible realizar la jugada: ya estaba hecha (0), no es turno (1), no existe la jugada (0).
