import com.iesrodeira.domotica.*;

class ControlDomotico {
	public static void main(String[] args) {

		try {
			Domotica a=new Domotica("Enchufe Salon","172.20.12.17");
			a.control();
		} catch (Exception e) {
			System.out.println("Enchufe Salón: "+e.getMessage());
		}

		try {
			Domotica b=new DomoticaEnchufe("Enchufe Habitación","172.20.12.18");
			b.control();
		} catch (Exception e) {
			System.out.println("Enchufe Habitación: "+e.getMessage());
		}

		try {
			Domotica c=new DomoticaLampGeneric("Lámpara Salón","172.20.12.19");
			c.control();
		} catch (Exception e) {
			System.out.println("Lámpara Salón: "+e.getMessage());
		}
			
		try {
			Domotica d=new DomoticaLampColor("Lámpara Habitación","172.20.12.20");
			d.control();
		} catch (Exception e) {
			System.out.println("Lámpara Habitación: "+e.getMessage());
		}
	}
}
