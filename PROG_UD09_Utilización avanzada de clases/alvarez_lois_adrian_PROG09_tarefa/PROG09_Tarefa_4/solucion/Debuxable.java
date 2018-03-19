public interface Debuxable {
	public void show();
	public void putPixel(int y,int x);
	void linea(int y,int x, int y1, int x1); 
	// A maiores, non o pedía o enunciado...
	public void writeText(int y,int x,String s);
	public int getWidth();
	public int getHeight();
}
