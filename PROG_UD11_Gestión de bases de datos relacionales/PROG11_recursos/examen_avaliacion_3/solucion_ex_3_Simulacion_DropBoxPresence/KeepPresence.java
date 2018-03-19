package ControlPresencial;

public interface KeepPresence {
	public void registerPresence(Persoal p,long time, int type) throws Exception;
	public Persoal getPersonal(String id) throws Exception ;
}
