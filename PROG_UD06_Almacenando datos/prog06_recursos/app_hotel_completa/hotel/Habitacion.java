package hotel;

import hotel.utils.DateString;
import hotel.errors.ReservaException;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Habitacion implements Serializable {
	public static final String FILENAME="hotel/datos/habitacions.dat";

	int numero;
	int plazas;
	double precio;
	
	/** Constructor
			Crea o obxecto Habitacion
	*/
	public Habitacion(int numero, int plazas, double precio) {
		this.numero=numero;
		this.plazas=plazas;
		this.precio=precio;
	}

	/** Constructor
			Crea o obxecto Habitacion a partir do número de habitación consultando na BBDD
	*/
	public Habitacion(int numero) throws Exception {
		ObjectInputStream ois=null;
		Habitacion h;
		try {
			// Buscamos no ficheiro a habitación co número indicado
			ois=new ObjectInputStream(new FileInputStream(FILENAME));
			do {
				h=(Habitacion)ois.readObject(); 	// Leemos Habitación
				if (numero==h.getNumero()) {  	// COINCIDE
					this.numero=h.getNumero();		// Copiamos o valor dos atributos
					this.plazas=h.getPlazas();
					this.precio=h.getPrecio();
					return;
				}
			} while(true);
		} catch (EOFException e) {
			throw new Exception("ERRO: Habitación non existente!!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ois.close();
		}
	}

	/** lista
			Devolve un ArrayList con todas as habitacións do ficheiro habitacions.dat
	*/
	public static ArrayList <Habitacion> listaTodas() throws Exception {
		ObjectInputStream ois=null;
		ArrayList <Habitacion> todas=new ArrayList <Habitacion>(); // ArrayList para almacenar as Habitacions
		try {
			ois=new ObjectInputStream(new FileInputStream(FILENAME));
			while(true) todas.add((Habitacion)ois.readObject()); // Leemos e gardamos no ArrayList
		} catch (EOFException e) {
		} finally {
			if (ois!=null) ois.close();
		}
		return todas;
	}

	/** listaLibres
			Devolve un ArrayList coas habitacións de habitacions.dat que non estan reservadas nin ocupadas (non teñen reserva) nas datas indicadas.. 
	*/
	public static ArrayList <Habitacion> listaLibres(Date entrada, Date salida) throws ReservaException,Exception {
		ArrayList <Habitacion> libres=new ArrayList <Habitacion>();	// Para gardar as Habitacions libres
		ArrayList <Reserva> reservas;											// Gardamos as reservas
		ArrayList <Habitacion> todas=Habitacion.listaTodas();			// Lista de Todas as Habitacións

		for(Habitacion h: todas) { // Recorremos todas as habitacións
			reservas=Reserva.listaReservas(h.getNumero()); // Obtemos as reservas da habitación
			boolean isreserved=false;
			for (Reserva r: reservas) {
				if ((DateString.compareTo(entrada,r.getSaidaReserva())>=0)||
					 (DateString.compareTo(salida,r.getEntradaReserva())<=0)) continue; // A Reserva non ocupa a habitación, pasamos á seguinte
				isreserved=true; // A reserva SI Ocupa a habitación
			}
			if (!isreserved) libres.add(h); // Si a habitación non está ocupada por ningunha reserva, a engadimos á lista
		}
		return libres;		
	}

	/** toString
			Devolve a representación String dunha Habitacion
	*/
	public String toString() {
		return "Habitación "+numero+" ("+plazas+" plazas) - "+precio+"€";
	}

	/** getNumero
			Devolve o número de Habitación
	*/
	public int getNumero() {
		return numero;
	}

	/** getPlazas
			Devolve o número de plazas da Habitación
	*/
	public int getPlazas() {
		return plazas;
	}

	/** getPrecio
			Devolve o precio da Habitacion
	*/
	public double getPrecio() {
		return precio;
	}

	/** Crea habitacions.dat, para probar a aplicación
	*/
	public static void main(String[] args) throws Exception {
		Habitacion h;

		int[] nums={10,11,12,13,14,15,20,21,22,23,24,25,30,31,32,33,34,35};
		int[] plazas={1,2,2,1,1,1,2,2,2,2,1,1,3,2,2,1,2,3};
		double[] precios={60.0,90.0,90.0,60.0,60.0,60.0,90.0,90.0,100.0,100.0,65.0,65.0,110.0,90.0,100.0,80.0,150.0,110.0};
		ObjectOutputStream oss=new ObjectOutputStream(new FileOutputStream("hotel/datos/habitacions.dat"));
		for (int idx=0; idx<nums.length; idx++) {
			h=new Habitacion(nums[idx],plazas[idx],precios[idx]);
			oss.writeObject(h);
		}
		oss.close();
		System.out.println("HABITACIÓNS CREADAS: \n");
		ArrayList lh=Habitacion.listaTodas();
		int idx=0;
		for (Object nh: lh) {
			System.out.print(nh+"\t");
			idx++;
			if ((idx%2)==0) {
				System.out.println();
				idx=0;
			}
		}
	}
}
