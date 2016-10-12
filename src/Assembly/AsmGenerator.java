package Assembly;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.LinkedList;
import ASTClass.ExpressionAlgo;

public class AsmGenerator{

  public AsmGenerator(){
  }

  public static String twoDirection(String op, ExpressionAlgo fst, ExpressionAlgo snd){
    String sentence = op;
    switch (fst.getType()){
      case "record" : sentence += " %"+fst.getValue();
        break;
      case "offset" : sentence += " "+fst.getValue()+"(%RBP)";
        break;
      case "value" : sentence += " $"+fst.getValue();
        break;
    }
    sentence +=", ";

    switch (snd.getType()){
      case "record" : sentence += " %"+snd.getValue();
        break;
      case "offset" : sentence += " "+snd.getValue()+"(%RBP)";
        break;
      case "value" : sentence += " $"+snd.getValue();
        break;
    }
    return sentence;
  }

  public static void writeAssembler(LinkedList<Sentence> sentence_list){
    PrintWriter writer;


    try {
      writer = new PrintWriter("program.asm", "UTF-8");
      for (Sentence s : sentence_list) {
        switch (s.getOperation()) {
          case "ADDQ": writer.println(AsmGenerator.twoDirection("ADDQ",s.getOperatorOne(), s.getOperatorTwo()));
                      break;
          // case "SUBQ": writer.println("SUBQ %"+s.getOperatorOne().getName()+", %"+s.getOperatorTwo()+", %"+s.getResult());
          //             break;
          // case "MULQ": writer.println("MULQ %"+s.getOperatorOne()+", %"+s.getOperatorTwo()+", %"+s.getResult());
          //             break;
          // case "DIVQ": writer.println("DIVQ %"+s.getOperatorOne()+", %"+s.getOperatorTwo()+", %"+s.getResult());
          //             break;
          // case "CMPL": if (s.getOperatorTwo().isNumber())
          //                 writer.println("CMPL $"+s.getOperatorTwo().getName()+", %"+s.getOperatorOne().getName());
          //              else
          //                 writer.println("CMPL "+s.getOperatorOne().getName()+"(%RBP), %"+s.getOperatorTwo().getName());
          //             break;
          // case "JE": writer.println("JE "+s.getOperatorOne().getName());
          //             break;
          // case "JNE": writer.println("JNE "+s.getOperatorOne().getName());
          //             break;
          // case "JG": writer.println("JG "+s.getOperatorOne().getName());
          //             break;
          // case "JGE": writer.println("JGE "+s.getOperatorOne().getName());
          //             break;
          // case "JL": writer.println("JL "+s.getOperatorOne().getName());
          //             break;
          // case "JLE": writer.println("JLE "+s.getOperatorOne().getName());
          //             break;
          // case "JZ": writer.println("JZ "+s.getOperatorOne().getName());
          //             break;
          // case "MOVQ": writer.println("MOVQ %"+s.getOperatorTwo().getName()+", %"+s.getOperatorOne().getName());
          //               break;
          // case "MOVL": if (s.getOperatorTwo().isNumber())
          //               if (s.getOperatorOne().getName()=="EAX")
          //                 writer.println("MOVL $"+s.getOperatorTwo().getName()+", %"+s.getOperatorOne().getName());
          //               else
          //                 writer.println("MOVL $"+s.getOperatorTwo().getName()+", "+s.getOperatorOne().getName()+"(%RBP)");
          //              else
          //               writer.println("MOVL "+s.getOperatorTwo().getName()+"(%RBP), %"+s.getOperatorOne().getName());
          //             break;
          // case "LABEL": writer.println(s.getOperatorOne().getName()+":");
          //             break;
          // case "JMP": writer.println("JMP "+s.getOperatorOne().getName());
          //             break;
          // case "Break": writer.println("JMP "+s.getOperatorOne().getName());
          //             break;
          // case "Continue": writer.println("JMP "+s.getOperatorOne().getName());
          //             break;
          // case "INC": writer.println("INC %"+s.getOperatorOne().getName());
          //             break;
          // case "CALL": writer.println("CALL %"+s.getOperatorOne().getName());
          //             break;
          // case "POPQ": writer.println("POPQ");
          //             break;
          // case "RETQ": writer.println("RETQ");
          //             break;
          // case "PUSHQ": writer.println("PUSHQ %"+s.getOperatorOne().getName());
          //             break;
        }
      }
      writer.close();
    } catch (IOException ex) {
      // report
    }
  }


}