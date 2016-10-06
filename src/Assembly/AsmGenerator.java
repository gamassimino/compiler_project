package Assembly;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.LinkedList;

public class AsmGenerator{

  public AsmGenerator(){
  }

  public static void writeAssembler(LinkedList<Sentence> sentence_list){
    PrintWriter writer;

    try {
      writer = new PrintWriter("prueba.asm", "UTF-8");
      for (Sentence s : sentence_list) {
        switch (s.getOperation()) {
          //fill with all cases
        }
      }
      writer.close();
    } catch (IOException ex) {
      // report
    }
  }
}