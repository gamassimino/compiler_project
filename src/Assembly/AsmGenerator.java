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
      writer = new PrintWriter("program.asm", "UTF-8");
      for (Sentence s : sentence_list) {
        switch (s.getOperation()) {
          case "ADD": writer.println("ADD %"+s.getOperatorOne().getName()+", %"+s.getOperatorTwo().getName()+", %"+s.getResult().getName());
                      break;
          case "SUB": writer.println("SUB %"+s.getOperatorOne().getName()+", %"+s.getOperatorTwo()+", %"+s.getResult());
                      break;
          case "MUL": writer.println("MUL %"+s.getOperatorOne()+", %"+s.getOperatorTwo()+", %"+s.getResult());
                      break;
          case "DIV": writer.println("DIV %"+s.getOperatorOne()+", %"+s.getOperatorTwo()+", %"+s.getResult());
                      break;
          case "CMP": writer.println("CMP %"+s.getOperatorOne().getName()+", %"+s.getOperatorTwo().getName());
                      break;
          case "JE": writer.println("JE "+s.getOperatorOne().getName());
                      break;
          case "JNE": writer.println("JNE "+s.getOperatorOne().getName());
                      break;
          case "JG": writer.println("JG "+s.getOperatorOne().getName());
                      break;
          case "JGE": writer.println("JGE "+s.getOperatorOne().getName());
                      break;
          case "JL": writer.println("JL "+s.getOperatorOne().getName());
                      break;
          case "JLE": writer.println("JLE "+s.getOperatorOne().getName());
                      break;
          case "JZ": writer.println("JZ "+s.getOperatorOne().getName());
                      break;
          case "MOVL": if (s.getOperatorTwo().isNumber())
                        writer.println("MOVL $"+s.getOperatorTwo().getName()+", %"+s.getOperatorOne().getName());
                       else
                        writer.println("MOVL %"+s.getOperatorTwo().getName()+", %"+s.getOperatorOne().getName());
                      break;
          case "LABEL": writer.println(s.getOperatorOne().getName()+":");
                      break;
          case "JMP": writer.println("JMP "+s.getOperatorOne().getName());
                      break;
          case "Break": writer.println("JMP "+s.getOperatorOne().getName());
                      break;
          case "Continue": writer.println("JMP "+s.getOperatorOne().getName());
                      break;
          case "INC": writer.println("INC %"+s.getOperatorOne().getName());
                      break;
          case "CALL": writer.println("CALL %"+s.getOperatorOne().getName());
                      break;
          case "POPQ": writer.println("POPQ");
                      break;
          case "RETQ": writer.println("RETQ");
                      break;
          case "PUSHQ": writer.println("PUSHQ %"+s.getOperatorOne().getName());
                      break;
        }
      }
      writer.close();
    } catch (IOException ex) {
      // report
    }
  }


}