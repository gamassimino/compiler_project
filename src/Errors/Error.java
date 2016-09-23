package Errors;

import java.util.LinkedList;

public class Error{
  private LinkedList<String> list = null;

  public Error() {
    list = new LinkedList<String>();
  }

  public void error1(String left, String right, String op){
  	list.add("");
  }

  public void error2(){
  	list.add("mtodo debe terne return");
  }

}