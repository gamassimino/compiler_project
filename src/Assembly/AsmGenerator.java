package Assembly;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.LinkedList;
import ASTClass.ExpressionAlgo;

public class AsmGenerator{

  public AsmGenerator(){
  }

  public static String init(){
    String init = ".section  __TEXT,__text,regular,pure_instructions\n";
    init += ".macosx_version_min 10, 12\n";
    init += ".globl  _main\n";
    init += ".align 4, 0x90\n";
    init += ".cfi_startproc\n";
    return init;
  }

  public static String end(){
    String end = "\ncallq ___stack_chk_fail\n";
    end += ".cfi_endproc\n";
    end += ".subsections_via_symbols";
    return end;
  }


  public static String oneDir(String op, ExpressionAlgo fst){
    String sentence = "  "+op;
    switch (fst.getType()){
      case "record" : sentence += " %"+fst.getValue();
        break;
      case "offset" : sentence += " "+fst.getValue()+"(%RBP)";
        break;
      case "value" : sentence += " $"+fst.getValue();
        break;
      case "label" : sentence += " "+fst.getValue();
        break;
      case "array" : sentence += " "+fst.getValue()+"(%RBP,%RCX,8)";
        break;
      case "instance" : sentence += " (%R10,%RCX,8)";
        break;
      case "null" : break;
    }
    return sentence;
  }

  public static String twoDir(String op, ExpressionAlgo fst, ExpressionAlgo snd){
    String sentence = oneDir(op,fst);
    if(snd != null){
      sentence +=", ";
      switch (snd.getType()){
        case "record" : sentence += "%"+snd.getValue();
          break;
        case "offset" : sentence += ""+snd.getValue()+"(%RBP)";
          break;
        case "array" : sentence += " "+snd.getValue()+"(%RBP,%RCX,8)";
          break;
        case "value" : sentence += "$"+snd.getValue();
          break;
        case "instance" : sentence += "(%R10,%RCX,8)";
          break;
      }
    }
    return sentence;
  }

  public static String threeDir(String op, ExpressionAlgo fst, ExpressionAlgo snd, ExpressionAlgo thr){
    String sentence = twoDir(op,fst,snd);
    if(thr != null){
      sentence +=", ";
      switch (thr.getType()){
        case "record" : sentence += "%"+snd.getValue();
          break;
        case "offset" : sentence += ""+snd.getValue()+"(%RBP)";
          break;
        case "value" : sentence += "$"+snd.getValue();
          break;
      }
    }
    return sentence;
  }

  public static void writeAssembler(LinkedList<Sentence> sentence_list){
    PrintWriter writer;

    try {
      writer = new PrintWriter("program.s", "UTF-8");
      writer.println(AsmGenerator.init());
      for (Sentence s : sentence_list) {
        switch (s.getOperation()) {
          case "ADDQ": writer.println(AsmGenerator.twoDir("ADDQ", s.getOperatorTwo(), s.getOperatorOne()));
                      break;
          case "ADDL": writer.println(AsmGenerator.twoDir("ADDQ", s.getOperatorTwo(), s.getOperatorOne()));
                      break;
          case "SUBQ": writer.println(AsmGenerator.threeDir("SUBQ", s.getOperatorTwo(), s.getOperatorOne(), s.getResult()));
                      break;
          case "IMULQ": writer.println(AsmGenerator.twoDir("IMULQ", s.getOperatorTwo(), s.getOperatorOne()));
                      break;
          case "IDIVL": writer.println(AsmGenerator.oneDir("IDIVL", s.getOperatorOne()));
                      break;
          case "CMPL": writer.println(AsmGenerator.twoDir("CMPL", s.getOperatorTwo(), s.getOperatorOne()));
                      break;
          case "JE": writer.println(AsmGenerator.oneDir("JE", s.getOperatorOne()));
                      break;
          case "JNE": writer.println(AsmGenerator.oneDir("JNE", s.getOperatorOne()));
                      break;
          case "JG": writer.println(AsmGenerator.oneDir("JG", s.getOperatorOne()));
                      break;
          case "JGE": writer.println(AsmGenerator.oneDir("JGE", s.getOperatorOne()));
                      break;
          case "JL": writer.println(AsmGenerator.oneDir("JL", s.getOperatorOne()));
                      break;
          case "JLE": writer.println(AsmGenerator.oneDir("JLE", s.getOperatorOne()));
                      break;
          case "JZ": writer.println(AsmGenerator.oneDir("JZ", s.getOperatorOne()));
                      break;
          case "MOVQ": writer.println(AsmGenerator.twoDir("MOVQ", s.getOperatorTwo(), s.getOperatorOne()));
                        break;
          case "MOVL": writer.println(AsmGenerator.twoDir("MOVL", s.getOperatorTwo(), s.getOperatorOne()));
                      break;
          case "LABEL": writer.println(s.getOperatorOne().getValue()+":");
                      break;
          case "JMP": writer.println(AsmGenerator.oneDir("JMP", s.getOperatorOne()));
                      break;
          case "Break": writer.println(AsmGenerator.oneDir("JMP", s.getOperatorOne()));
                      break;
          case "Continue": writer.println(AsmGenerator.oneDir("JMP", s.getOperatorOne()));
                      break;
          case "INC": writer.println(AsmGenerator.oneDir("INC", s.getOperatorOne()));
                      break;
          case "INCQ": writer.println(AsmGenerator.oneDir("INCQ", s.getOperatorOne()));
                      break;
          case "INCL": writer.println(AsmGenerator.oneDir("INCL", s.getOperatorOne()));
                      break;
          case "CALL": writer.println(AsmGenerator.oneDir("CALL", s.getOperatorOne()));
                      break;
          case "POPQ": writer.println(AsmGenerator.oneDir("POPQ", s.getOperatorOne()));
                      break;
          case "RET": writer.println(AsmGenerator.oneDir("RET", s.getOperatorOne()));
                      break;
          case "PUSHQ": writer.println(AsmGenerator.oneDir("PUSHQ", s.getOperatorOne()));
                      break;
          case "LEAVE": writer.println(AsmGenerator.oneDir("LEAVE", s.getOperatorOne()));
                      break;
          case "CLTD" : writer.println("  CLTD");
                      break;
          case "LEAQ" : writer.println(AsmGenerator.twoDir("LEAQ", s.getOperatorTwo(), s.getOperatorOne()));
                      break;
          default : System.out.println("DEFAULT :"+s.getOperation());
                    writer.println();
                    break;
        }
      }
      writer.println(AsmGenerator.end());
      writer.close();
    } catch (IOException ex) {
      // report
    }
  }


}