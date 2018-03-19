public class ejercicio10 {
  public static void main (String[] args) {
    System.out.println("------- Conversiones entre enteros e coma flotante -------");
    float x = 4.5f;
    float y = 3.0f;
    int i = 2;
    int j;
    j = (int) (i * x); // conversion explícita a int de un int * float
    System.out.print("Producto de int por float: j = i * x = "+j);
    double dx = 2.0d;
    double dz;
    dz = dx * y; // double * float = double
    System.out.print("\nProducto de float por double: dz=dx * y = "+dz);
    System.out.print("\n\n------- Operacions con byte -------");
    byte bx = 5;
    byte by = 2;
    byte bz;
    bz = (byte) (bx - by); // as operacions entre tipos byte dan como ersultado int
    System.out.print("\nbyte: "+bx+" - "+by+" = "+bz);
    bx = -128; // rango -128 a 127
    by = 1;
    bz = (byte) (bx - by); // conversión explícita a byte
    System.out.print("\nbyte: "+bx+" - "+by+" = "+bz);  
    int entero = (bx - by); // usamos unha variable intermedia de tipo enteiro para a conversión de tipos
    System.out.print("\n(int)("+bx+" - "+by+") = "+entero);  
    System.out.print("\n\n------- Operacions con short -------");
    short sx = 5;
    short sy = 2;
    short sz = (short) (sx - sy); // conversión explícita a short
    System.out.print("\nshort: "+sx+" - "+sy+" = "+sz);
    sx = 32767; // rango de short -32,768 a 32,767
    sy = 1;
    sz = (short) (sx + sy);
    System.out.print("\nshort: "+sx+" + "+sy+" = "+sz);
    System.out.print("\n\n------- Operacions con char -------");
    char cx = '\u000F';
    char cy = '\u0001';
    int z = (char) (cx - cy);
    System.out.print("\nchar: "+cx+" - "+cy+" = "+z);
    z = ((int) cx) - 1;
    System.out.printf("\nchar(%X) - 1 = %d ",(int) cx,z);
    cx = '\uFFFF';
    z = cx;
    System.out.print("\n(int)= "+z);
    sx = (short) cx; // conversión explicita requerida
    System.out.print("\n(short)= "+sx);
    sx = -32768;
    cx = (char) sx;
    z = (int) sx;
    sx = (short) cx; // conversión explicita requerida
    System.out.print("\n"+z+" short-char-int = "+z);
    sx = -1;
    cx = (char) sx; // conversion explicita
    z = (int) cx; // conversion explicita
    System.out.print("\n"+sx+" short-char-int = "+z);
  }
}