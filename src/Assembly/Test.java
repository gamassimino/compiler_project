import java.io.PrintWriter;
import java.io.IOException;
class Test{
  public static void main(String[] args) {

    PrintWriter writer;

    try {
      writer = new PrintWriter("prueba.asm", "UTF-8");
      writer.println("The first line");
      writer.println("The second line");
      writer.close();

    } catch (IOException ex) {
      // report
    }
  }
}