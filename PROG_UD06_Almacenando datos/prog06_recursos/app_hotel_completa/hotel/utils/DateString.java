package hotel.utils;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DateString {
	private static final int[] diasmes={31,28,31,30,31,30,31,31,30,31,30,31};
	String datestr;	// Data en formato dd/mm/aaaa
	String datedb;		// Data en formato aaaammdd
	Date date;			// Date

	/** Constructor
			Crea un DateString a partir da data actual
	*/
	public DateString() {
		this(new Date());
	}

	/** Constructor
			Crea un DateString a partir dun String
	*/
	public DateString(String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String[] parts;
		int dia;
		int mes;
		int ano;

		this.datestr=null;
		this.datedb=null;
		this.date=null;

		if (date==null) throw new ParseException("A data é nula",0);
		date=date.replace("-","/"); // Admitimos dd-mm-aaaa
		parts=date.split("/");
		if (parts.length!=3) throw new ParseException("A data debe ser dd/mm/aaaa",0);
		try {
			dia=Integer.parseInt(parts[0]);
			mes=Integer.parseInt(parts[1]);
			if ((mes<1)||(mes>12)) throw new ParseException("O mes debe estar entre 1 e 12",1);
			ano=Integer.parseInt(parts[2]);
			int dm=diasmes[mes-1];
			if (bisiesto(ano) && (mes==2)) dm++;
			if ((dia<1)||(dia>dm)) throw new ParseException("O ano "+ano+" o mes de febreiro ten "+dm+" días",0);
			this.date=df.parse(date);
			makedate(dia,mes,ano);
		} catch(NumberFormatException e) {
			throw new ParseException("A data debe ser dd/mm/aaaa",0);
		}
	}
	
	/** Constructor
			Crea un DateString a partir dunha Date
	*/
	public DateString(Date d) {
		convertDate(d);
	}

	/** makedate
			Construye os String a partir do día, mes e ano en formato numérico
	*/
	private void makedate(int dia,int mes,int ano) {
		String d,m,a;

		d=Integer.toString(dia);
		if (d.length() < 2) d="0"+d;
		m=Integer.toString(mes);
		if (m.length() < 2) m="0"+m;
		a=Integer.toString(ano);
		this.datestr=d+"/"+m+"/"+a;
		this.datedb=a+m+d;
	}

	/** convertDate
			Xenera os String a partir dun Date
	*/
	private void convertDate(Date d) {
		int dia,mes,ano;

		String s=null;
		if (d!=null) {
			Calendar c=Calendar.getInstance();
			c.setTime(d);
			dia=c.get(Calendar.DAY_OF_MONTH);
			mes=c.get(Calendar.MONTH)+1;
			ano=c.get(Calendar.YEAR);
			makedate(dia,mes,ano);
			//TODO: Se debe gardar unha copia, non o obxecto
			date=d;
		}
	}

	/** bisiesto
			Devolve true si o ano é bisiesto
	*/
	public static boolean bisiesto(int ano) {
      if (((ano%4==0) && (ano%100 !=0 ))||(ano%400==0)) return true;
      return false;
	}

	/** getDate
			Devolve o obxecto Date
	*/
	public Date getDate() {
		return date;
	}

	/** toString
			Devolve a data en formato dd/mm/aaaa
	*/
	public String toString() {
		return datestr;
	}

	/** compareTo
			Compara o obxecto actual co Date recibido
	*/
	public int compareTo(Date b) {
		DateString ds=new DateString(b);
		return datedb.compareTo(ds.datedb);
	}

	/** compareTo
			Compara o obxecto actual co DateString recibido
	*/
	public int compareTo(DateString b) {
		return datedb.compareTo(b.datedb);
	}

	/** compareTo
			Compara dous obxectos Date sen ter en conta o tempo
	*/
	public static int compareTo(Date a, Date b) {
		String s;
		String s1;

		Calendar c=Calendar.getInstance();
		c.setTime(a);
		s=Integer.toString(c.get(Calendar.YEAR));
		s+=Integer.toString(c.get(Calendar.MONTH)+1);
		s+=Integer.toString(c.get(Calendar.DAY_OF_MONTH));
		c.setTime(b);
		s1=Integer.toString(c.get(Calendar.YEAR));
		s1+=Integer.toString(c.get(Calendar.MONTH)+1);
		s1+=Integer.toString(c.get(Calendar.DAY_OF_MONTH));
		return s.compareTo(s1);
	}
}
