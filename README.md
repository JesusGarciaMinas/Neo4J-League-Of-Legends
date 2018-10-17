# Neo4J-League-Of-Legends
La base de datos contiene información de los personajes del videojuego de ordenador “League of Legends”. Los personajes serán un nodo, cada uno con unos atributos especiales en función de ciertas características que poseerán. Otro tipo de nodos será la posición que utilizan en el juego. Y finalmente el último tipo de nodo será el año de salida de los personajes en el juego. Habrá relaciones entre estos 3 tipos de nodos y entre nodos de personajes si estos comparten alguna relación común.
El personaje tendrá una relación con un año según la fecha en la que salió.
El campeón tendrá una relación con la posición según su posición actual (O posición pasada pero que ya no).
El campeón tendrá una relación con otros campeones.
Los personajes tendrán atributos en relación a su región de nacimiento, si tienen aspectos de los equipos campeones del mundo, su precio en el juego, su tipo de ataque y su rol.

## Creación de la base de datos
En la carpeta con este nombre se encuentra graph.db y un txt con todo el código para la creación de la base de datos.

## Ejemplos de consultas
(Las imágenes de las consultas están en la carpeta consultas).

###	Consultas elementales:

1- Encuéntrame todos los campeones que tienen una Skin de SKT
MATCH (p:Personaje) WHERE (p.SkinEquipo='SKT') RETURN p

2- Encuéntrame los campeones que sean magos que ataquen cuerpo a cuerpo
MATCH (p:Personaje) WHERE (p.Ataque='Melee' AND p.Rol='Mago') RETURN p


###	Consultas intermedias:

3- Súmame el precio de todos los campeones del juego según su posición y ordenarlos según la posición que me resultaría más barata
MATCH (p:Personaje)-[:JUEGA_EN]->(l:Posicion) RETURN l.Nombre, SUM(p.Precio) AS Precio ORDER BY Precio ASC
ADC sería la posición más barata con 63900 Pis (Puntos de Influencia).

4- Ordename los años según la cantidad de campeones salidos en el juego
MATCH (P:Personaje)-[:SALIO_EN]->(F:Fecha) return F.Año, COUNT(F.Año) AS Numero ORDER BY Numero DESC
2009 es el año con más campeones salidos en el juego con 42.
(Excel en carpeta con las tablas con todos los datos).



### Consultas avanzadas:
5- Campeones que hayan salido en el mismo año y que tengan ambos SKIN de SKT
match (x:Personaje)-[:SALIO_EN]->(a:Fecha)<-[:SALIO_EN]-(y:Personaje)
WHERE x.SkinEquipo=y.SkinEquipo AND x.Nombre<>y.Nombre
RETURN x.Nombre, y.Nombre

6- Campeones que hayan nacido en Freijord ,compartan posición en el juego y al menos uno de ellos cueste 450 PIs.
match (x:Personaje)-[:JUEGA_EN]->(p:Posicion)<-[:JUEGA_EN]-(y:Personaje)
WHERE x.Region=y.Region AND x.Nombre<>y.Nombre AND x.Precio=450 AND x.Region='Freijord'
RETURN x.Nombre, y.Nombre

# JDBC
Se incluye un proyecto de java donde se utiliza esta aplicación