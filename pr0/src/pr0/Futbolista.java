package pr0;

public class Futbolista {
	
	public enum Equipos {RANADA, BETIS, SEVILLA, OSASUNA, DEPOR};
	
	private int dorsal;								//int normal
	private String nombre;							//strin normal
	private Equipos equipo = Equipos.RANADA;		//usar el "enum" equipo y ponerlo deafault en RANADA
	private boolean enActivo = true;				//booleano normal
	private final String profesion = "futbolista";	//final(constante) para profesion
	
	public Futbolista() {//constructor
		//este es el constructor por defecto, aunque no este es asi 
	}
	
	public Futbolista(String nombre, int dorsal){//constructor
		this.nombre = nombre;
		this.dorsal = dorsal;
	}
	
	/*public Futbolista(String n, int d) {//constructor
		this.nombre = n;
		this.dorsal = d;
	}*/
	
	public void retirar() {
		this.enActivo = false;
	}
	
	public static void main(String[] args) {
		Futbolista f1 = new Futbolista("CR7", 7);
		Futbolista f2 = new Futbolista("Alexia", 11);
		f1.retirar();
		System.out.println("CR7 esta en activo? " + f1.enActivo);
		System.out.println("Alexia esta en activo? " + f2.enActivo);
	}

}
