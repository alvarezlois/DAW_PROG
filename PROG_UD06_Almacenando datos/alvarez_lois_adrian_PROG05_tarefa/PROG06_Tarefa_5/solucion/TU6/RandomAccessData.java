/**
	Esta clase hereda de RandomAccessFile para proporcionarlle
	algúns métodos útiles que non ten RandomAccessFile:
		- writeString - Escribir un string cun número de letras fixo
		- readString - Leer un string cun número de letras fixo
		- lseek - Moverse un número de rexistros adiante ou atras dende a posición actual
*/
package TU6;

import java.io.RandomAccessFile;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

class RandomAccessData extends RandomAccessFile {
	/**
		Constructor: Creamos un RandomAccessFile 
	*/
   RandomAccessData(File f,String mode) throws FileNotFoundException {
      super(f,mode);
   }

	/** Escribe o String str ocupando dim Chars (cada Char ocupará 2 bytes)
	*/
   public void writeString (String str, int dim) throws IOException  {
      StringBuffer buffer = new StringBuffer();
      if (str!=null) buffer.append(str);
      buffer.setLength(dim);
      writeChars(buffer.toString());
   }

	/**
		Lee dim Chars do ficheiro e devolve o String correspondente
	*/
   public String readString (int dim) throws IOException {
      char campo[] = new char[dim];
      for (int i=0; i<dim; i++) campo[i] = readChar();
      return new String(campo).replace('\0',' ');
   }

	/**
		Move a posición do ficheiro d rexistros de lonxitude recordsize
		valores positivos moven adiante e negativos atrás.
	*/
   public void lseek(long d,long recordsize) throws IOException {
      long cp=getFilePointer();
      cp+=d*recordsize;
      seek(cp);
   }

}
