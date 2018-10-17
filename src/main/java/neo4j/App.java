package neo4j;


import org.neo4j.driver.v1.*;

public class App 
{

    private static Session session;

	public static void main( String[] args )
    {
      Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j2", "neo4j"));
      session = driver.session();
      ejercicio1();
      ejercicio2();
      ejercicio3();
      ejercicio4();
      ejercicio5();
      ejercicio6();
      
      session.close();
      driver.close();
    }
	
	private static void ejercicio6() {
		System.out.println("EJERCICIO 6");
		StatementResult result = 
		          session.run("match (x:Personaje)-[:JUEGA_EN]->(p:Posicion)<-[:JUEGA_EN]-(y:Personaje)\r\n" + 
		          		"WHERE x.Region=y.Region AND x.Nombre<>y.Nombre AND x.Precio=450 AND x.Region='Freijord'\r\n" + 
		          		"RETURN x.Nombre as namex, y.Nombre as namey");
		      while (result.hasNext()) {
		        Record record = result.next();
		        System.out.println(record.get("namex").asString() + " " + record.get("namey").asString());
		      }
		      System.out.println();
	}

	private static void ejercicio5() {
		System.out.println("EJERCICIO 5");
		StatementResult result = 
		          session.run("match (x:Personaje)-[:SALIO_EN]->(a:Fecha)<-[:SALIO_EN]-(y:Personaje)\r\n" + 
		          		"WHERE x.SkinEquipo=y.SkinEquipo AND x.Nombre<>y.Nombre\r\n" + 
		          		"RETURN x.Nombre as namex, y.Nombre as namey");
		      while (result.hasNext()) {
		        Record record = result.next();
		        System.out.println(record.get("namex").asString() + " " + record.get("namey").asString());
		      }
		      System.out.println();
	}

	private static void ejercicio4() {
		System.out.println("EJERCICIO 4");
		StatementResult result = 
		          session.run("MATCH (P:Personaje)-[:SALIO_EN]->(F:Fecha) return F.Año as name, COUNT(F.Año) AS Numero ORDER BY Numero DESC");
		      while (result.hasNext()) {
		        Record record = result.next();
		        System.out.println(record.get("name").asInt() + " " + record.get("Numero").asInt());
		      }
		      System.out.println();
	}

	private static void ejercicio3() {
		System.out.println("EJERCICIO 3");
		StatementResult result = 
		          session.run("MATCH (p:Personaje)-[:JUEGA_EN]->(l:Posicion) RETURN l.Nombre AS name, SUM(p.Precio) AS Precio ORDER BY Precio ASC");
		      while (result.hasNext()) {
		        Record record = result.next();
		        System.out.println(record.get("name").asString() + " " + record.get("Precio").asInt()); 
		      }
		      System.out.println();
	}

	private static void ejercicio2() {
		System.out.println("EJERCICIO 2");
		StatementResult result = 
		          session.run("MATCH (p:Personaje) WHERE (p.Ataque='Melee' AND p.Rol='Mago') RETURN p.Nombre AS name");
		      while (result.hasNext()) {
		        Record record = result.next();
		        System.out.println(record.get("name").asString());
		      }
		      System.out.println();
	}

	public static void ejercicio1() {
		System.out.println("EJERCICIO 1");
		StatementResult result = 
		          session.run("MATCH (p:Personaje) WHERE (p.SkinEquipo='SKT') RETURN p.Nombre AS name");
		      while (result.hasNext()) {
		        Record record = result.next();
		        System.out.println(record.get("name").asString());
		      }
		      System.out.println();
	}
}