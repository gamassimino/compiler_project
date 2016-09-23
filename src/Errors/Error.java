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

  public void error1(String stmt, String id, Integer line, Integer column){
    list.add("In Line "+line+" Column "+column+" "+stmt+": "+id+" is not declared");
  }

  public void error2(String stmt,String id, Integer line, Integer column){
    list.add("In Line "+line+" Column "+column+" "+stmt+": "+id+" is allready declared");
  }

  public void error3(String operator, String expec, String found, Integer line, Integer column){
    list.add("In Line "+line+" Column "+column+ " "+operator+": expecting "+expec+" but found "+found);
  }

  public void error4(String operator, String suport, Integer line, Integer column){
    list.add("In Line "+line+" Column "+column+ " "+operator+": not suport "+suport);
  }

  public void error5(String operator, String expec, Integer line, Integer column){
    list.add("In Line "+line+" Column "+column+ " "+operator+": "+expec);
  }

  public void error6(String operator, String suport, Integer line, Integer column){
    list.add("In Line "+line+" Column "+column+ " "+operator+": only suport "+suport);
  }

}