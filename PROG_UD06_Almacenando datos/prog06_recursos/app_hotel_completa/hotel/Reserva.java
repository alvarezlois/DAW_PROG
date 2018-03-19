package hotel;

import hotel.errors.ReservaException;
import hotel.utils.RandomAccessData;
import hotel.utils.DateString;

import java.util.Date;
import java.util.ArrayList;
import java.io.File;
import java.io.EOFException;

public class Reserva {
	public static final int SIZERESERVA=106; // 4 numreserva+ 4 numhabitacion+ 18 DNI Cliente+ 20 entrada reserva + 20 saida reserva+20 entrada +20 saida.
	public static final String FILENAME="hotel/datos/reservas.dat";
	
	private int numReserva;
	private Cliente cliente;
	private Habitacion habitacion;
	private Date entradaReserva;
	private Date saidaReserva;
	private Date entrada=null;
	private Date saida=null;


	private int nh;
	private String dni;

	/** Constructor por defecto
	*/
	public Reserva() {
	}

	/** Constructor
			Crea o obxecto Reserva identificado por numreserva consultando os datos en reservas.dat. 
			Si a reserva non existe, lanzará unha ReservaException.
	*/
	public Reserva(int numReserva) throws ReservaException {
		RandomAccessData f=null;
		if (numReserva==0) throw new ReservaException("Non se poden dar de alta reservas con numero 0");
		try {
			f=new RandomAccessData(new File(FILENAME),"rw");
			this.numReserva=numReserva;
			search(f);
			this.cliente=new Cliente(this.dni);
			this.habitacion=new Habitacion(this.nh);
		} catch(EOFException e) {
			throw new ReservaException("Non se atopa a reserva");
		} catch (Exception e) {
			throw new ReservaException("ERRO cargando reserva ("+e.getMessage()+")");
		} finally {
			close(f);
		}
	}

	/** Constructor
			Crea o obxecto Reserva coa información suministrada e a almacena no ficheiro reservas.dat xenerando o número de reserva apropiado. 
			Si algo falla lanzará unha ReservaException
	*/
	public Reserva(Cliente cliente,Habitacion habitacion,Date entrada, Date saida) throws ReservaException {
		this.cliente=cliente;
		this.habitacion=habitacion;
		this.entradaReserva=entrada;
		this.saidaReserva=saida;
		save();
	}

	public int getNumReserva() {
		return numReserva;
	}

	public Cliente getCliente() {
		//TODO: Se debería devolver unha copia do obxecto, non o propio obxecto
		return cliente;
	}

	public Habitacion getHabitacion() {
		//TODO: Se debería devolver unha copia do obxecto, non o propio obxecto
		return habitacion;
	}

	public Date getEntradaReserva() {
		//TODO: Se debería devolver unha copia do obxecto, non o propio obxecto
		return entradaReserva;
	}

	public Date getSaidaReserva() {
		//TODO: Se debería devolver unha copia do obxecto, non o propio obxecto
		return saidaReserva;
	}

	public Date getEntrada() {
		//TODO: Se debería devolver unha copia do obxecto, non o propio obxecto
		return entrada;
	}

	public Date getSaida() {
		//TODO: Se debería devolver unha copia do obxecto, non o propio obxecto
		return saida;
	}

	/** toString
			Devolve a representación com String dunha Reserva
	*/
	public String toString() {
		String str="Reserva Nº "+numReserva+" a nome de "+cliente+" dende o día "+new DateString(entradaReserva)+" ata "+new DateString(saidaReserva)+": "+habitacion;
		if (entrada!=null) str+="\n\t (Entrada Efectuada: "+new DateString(entrada)+")";
		if (saida!=null) str+="\n\t (Saída Efectuada: "+new DateString(saida)+")";
		return str;
	}

	/** close
			Pecha o ficheiro si está aberto
	*/
	private void close(RandomAccessData f) throws ReservaException {
		if (f!=null) {
			try { 
				f.close(); 
			} catch (Exception e) { 
				throw new ReservaException("ERROR: "+e.getMessage()); 
			}
		}
	}

	/** Se posiciona no primeiro rexistro
	*/
	private void start(RandomAccessData f) throws Exception {
		f.seek(4); // Ao principio está o número para a seguinte reserva
	}

	/**
		Posiciona o ficheiro na posición onde se pode insertar un novo rexistro
	*/
	private void posicionInsercion(RandomAccessData f) throws Exception {
		Reserva r=new Reserva();
		try {
			start(f);
			while(true) {
				r.read(f);
				if (r.getNumReserva() == 0) { // reserva borrada
					f.lseek(-1,SIZERESERVA);
					return;
				}
			}
		} catch (EOFException e) {
		} 
	}

	/** write
		Escribe o obxecto actual no ficheiro na posición actual
	*/
	private void write(RandomAccessData f) throws Exception {
		f.writeInt(numReserva);
		f.writeInt(habitacion.getNumero());
		f.writeString(cliente.getDni(),9);
		f.writeString(new DateString(entradaReserva).toString(), 10);
		f.writeString(new DateString(saidaReserva).toString(), 10);
		f.writeString(new DateString(entrada).toString(), 10);
		f.writeString(new DateString(saida).toString(), 10);
		
	}

	/** read
		Lee unha Reserva do ficheiro na posición actual
	*/
	private void read(RandomAccessData f) throws Exception {
		String date;

		this.numReserva=f.readInt();
		this.nh=f.readInt();
		this.dni=f.readString(9);
		this.entradaReserva=new DateString(f.readString(10)).getDate();
		this.saidaReserva=new DateString(f.readString(10)).getDate();
		date=f.readString(10); 
		if (date.equals("")) this.entrada=null;
		else						this.entrada=new DateString(date).getDate();
		date=f.readString(10);
		if (date.equals("")) this.saida=null;
		else						this.saida=new DateString(date).getDate();

	}

	/**
		Lee o rexistro  numreserva
	*/
	private void search(RandomAccessData f) throws ReservaException {
		int numreserva=this.numReserva;
		try {
			start(f);
			read(f);
			while(this.numReserva!=numreserva) read(f);
			f.lseek(-1,SIZERESERVA);
		} catch(EOFException e) {
			throw new ReservaException("Non se atopa a reserva");
		} catch (Exception e) {
			throw new ReservaException("ERRO cargando reserva ("+e.getMessage()+")");
		}
	}

	/** save
		Almacena a información do obxecto no ficheiro reservas.dat de xeito apropiado, 
		reutilizando o espacio das reservas borradas (numero de reserva a 0), 
		xenerando o número de reserva e actualizando o contador do inicio do ficheiro.
		si funciona correctamente devolverá o número de reserva xenerado, en caso de erro lanza unha ReservaException.
	*/
	private int save() throws ReservaException {
		RandomAccessData f=null;
		numReserva=0;
		try {
			f=new RandomAccessData(new File(FILENAME),"rw");
			if (f.length()==0) {
				numReserva=1;
				f.writeInt(2);
			} else {
				numReserva=f.readInt();									
				f.seek(0);
				f.writeInt(numReserva+1);
			}
			posicionInsercion(f);
			write(f);
		} catch(EOFException e) {
			throw new ReservaException("O Ficheiro non ten reservas");
		} catch (Exception e) {
			throw new ReservaException("ERRO cargando reserva ("+e.getMessage()+")");
		} finally {
			close(f);
		}
		return numReserva;
	}

	/** listaReservas
			Devolve un ArrayList cos obxectos Reserva activos correspondentes ao cliente co dni indicado
	*/
	public static ArrayList <Reserva> listaReservas(String dni) throws ReservaException {
		RandomAccessData f=null;
		ArrayList <Reserva> lista=new ArrayList <Reserva>();
		Reserva r=new Reserva();
		try {
			f=new RandomAccessData(new File(FILENAME),"rw");
			r.start(f);
			while(true) {
				r.read(f);
				// A reserva corresponde co cliente, e é unha reserva 'activa'
				if ((r.getNumReserva()!=0) && dni.equals(r.dni)) {
					r.cliente=new Cliente(r.dni);
					r.habitacion=new Habitacion(r.nh);
					lista.add(r);
					r=new Reserva();
				}
			}
		} catch(EOFException e) {
		} catch(Exception e) {
			throw new ReservaException("Error cargando Lista ("+e.getMessage()+")");
		} finally {
			r.close(f);
		}
		return lista;
	}

	/** listaReservas
			Devolve un ArrayList cos obxectos Reserva activos correspondentes a habitación indicada
	*/
	public static ArrayList <Reserva> listaReservas(int numhabitacion) throws ReservaException {
		RandomAccessData f=null;
		ArrayList <Reserva> lista=new ArrayList <Reserva>();
		Reserva r=new Reserva();
		try {
			f=new RandomAccessData(new File(FILENAME),"rw");
			r.start(f);
			while(true) {
				r.read(f);
				// A reserva corresponde coa habitación cliente, e é unha reserva 'activa'
				if ((r.getNumReserva()!=0) && (r.nh==numhabitacion) && (r.getSaida()==null)) {
					r.cliente=new Cliente(r.dni);
					r.habitacion=new Habitacion(r.nh);
					lista.add(r);
					r=new Reserva();
				}
			}
		} catch(EOFException e) {
		} catch(Exception e) {
			throw new ReservaException("Error cargando Lista ("+e.getMessage()+")");
		} finally {
			r.close(f);
		}
		return lista;
	}

	/** cancel
			Cancela a reserva poñendo en reservas.dat (e no propio obxecto) o número de reserva a 0.
			Se supon que o ficheiro está aberto, e que estamos situados xusto detrás...
	*/
	public void cancel() throws ReservaException {
		RandomAccessData f=null;
		if (this.numReserva==0) throw new ReservaException("ERRO: A Reserva xa está anulada");
		try {
			f=new RandomAccessData(new File(FILENAME),"rw");
			search(f);	
			// Si xa fixo a entrada no hotel, non se pode anular. Se poderían considerar tamén datas límite.
			if (this.entrada!=null) throw new ReservaException("Imposible anular: Xa se efectuou a entrada nesta reserva.");
			f.writeInt(0);					// Anulamos escribindo un 0 encima do número de reserva
			this.numReserva=0;
		} catch(EOFException e) {
			throw new ReservaException("Non se atopa a reserva");
		} catch (Exception e) {
			throw new ReservaException("ERRO cargando reserva ("+e.getMessage()+")");
		} finally {
			close(f);
		}
	}

	/** entrada
			Procesa a reserva creando a entrada correspondente en ocupacion.dat 
	*/
	public void entrada() throws ReservaException {
		RandomAccessData f=null;
		Date hoxe=new Date();
		if (this.numReserva==0) throw new ReservaException("ERRO: A Reserva está anulada");
		try {
			f=new RandomAccessData(new File(FILENAME),"rw");
			search(f);	
			// Si xa fixo a entrada no hotel, non se facer a entrada de novo.
			if (this.entrada!=null) throw new ReservaException("Esta reserva xa foi ocupada");
			// A reserva ten que ser para hoxe
			if (DateString.compareTo(hoxe,this.entradaReserva) != 0) throw new ReservaException("A data da reserva non é para hoxe");
			this.entrada=hoxe;	// Facemos a entrada
			write(f);
		} catch(EOFException e) {
			throw new ReservaException("Non se atopa a reserva");
		} catch (Exception e) {
			throw new ReservaException("ERRO cargando reserva ("+e.getMessage()+")");
		} finally {
			close(f);
		}
	}

	/** saida
			Modifica o rexistro correspondente en ocupacion.dat para poñer a data de saída (a de hoxe)
	*/
	public void saida() throws ReservaException {
		RandomAccessData f=null;
		try {
			f=new RandomAccessData(new File(FILENAME),"rw");
			search(f);
			if (this.entrada==null) throw new ReservaException("A habitación non foi ocupada");
			if (this.saida!=null) throw new ReservaException("A reserva xa non é válida, finalizou o día "+new DateString(this.saida));
			this.saida=new Date();
			write(f);
		} catch(EOFException e) {
			throw new ReservaException("Non se atopa a reserva");
		} catch (Exception e) {
			throw new ReservaException("ERRO cargando reserva ("+e.getMessage()+")");
		} finally {
			close(f);
		}
	}

	/** listaOcupantes
			Devolve un ArrayList coas reservas dos clientes que están ocupando as habitacións.
	*/
	public static ArrayList <Reserva> listaOcupantes() throws Exception {
		RandomAccessData f=null;
		ArrayList <Reserva> lista=new ArrayList <Reserva>();
		Reserva r=new Reserva();
		try {
			f=new RandomAccessData(new File(FILENAME),"rw");
			r.start(f);
			while(true) {
				r.read(f);
				// A habitación foi ocupada, pero non liberada
				if ((r.getEntrada()!=null) && (r.getSaida()==null)) {
					r.cliente=new Cliente(r.dni);
					r.habitacion=new Habitacion(r.nh);
					lista.add(r);
					r=new Reserva();
				}
			}
		} catch(EOFException e) {
		} catch(Exception e) {
			throw new ReservaException("Error cargando Lista ("+e.getMessage()+")");
		} finally {
			r.close(f);
		}
		return lista;
	}

}
