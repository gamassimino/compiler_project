package Errors;

import java.util.LinkedList;

public class Error{
  private LinkedList<String> list = null;

  public Error() {
    list = new LinkedList<String>();
  }

  public LinkedList<String> getErrors(){
    return list;
  }

  public void error1(String left, String right, String op){
  	list.add("");
  }

  public void error2(){
  	list.add("mtodo debe terne return");
  }

  public void terror1(String operator, String expec, String found){
    list.add(operator+":expecting "+expec+" but found "+found);
  }

  public void terror2(String operator, String suport){
    list.add(operator+": not suport "+suport);
  }

  public void terror3(String operator, String expec){
    list.add(operator+": "+expec);
  }

}