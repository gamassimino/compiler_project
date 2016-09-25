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

  public void error7(String operator, Integer line, Integer column){
    list.add("In Line "+line+" Column "+column+ " "+operator+": you can only use one navigation");
  }

  public void error8(String operator, Integer line, Integer column){
    list.add("In Line "+line+" Column "+column+ " "+operator+": wrong number of params");
  }

  public void error9(String operator, Integer line, Integer column){
    list.add("In Line "+line+" Column "+column+ " "+operator+": array size must be greater than zero");
  }

  public void error10(String operator, Integer line, Integer column){
    list.add("In Line "+line+" Column "+column+ " "+operator+": return is missing");
  }

  public void error11(String operator, Integer line, Integer column){
    list.add("In Line "+line+" Column "+column+ " "+operator+": found Break outside of cicle");
  }

  public void error12(String operator, Integer line, Integer column){
    list.add("In Line "+line+" Column "+column+ " "+operator+": found Continue outside of cicle");
  }
}