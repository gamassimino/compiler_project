package ASTClass;

import java.util.ArrayList;
import java.util.List;
import Visitor.ASTVisitor;

public class MethodCallStmt extends Statement{
  private IdName id;
  private Navigation navigation;
  private List<Expression> expression;
  private Boolean extern;
  private Integer attr_number;

  public MethodCallStmt(IdName an_id, Navigation a_navigation, ArrayList<Expression> an_expression, Integer a_line, Integer a_column){
    id = an_id;
    navigation = a_navigation;
    expression = an_expression;
    line = a_line;
    column = a_column;
  }

  public MethodCallStmt(IdName an_id, Navigation a_navigation, Integer a_line, Integer a_column){
    id = an_id;
    navigation = a_navigation;
    expression = null;
    line = a_line;
    column = a_column;
  }

  public MethodCallStmt(IdName an_id, ArrayList<Expression> an_expression, Integer a_line, Integer a_column){
    id = an_id;
    expression = an_expression;
    navigation= null;
    line = a_line;
    column = a_column;
  }
  public MethodCallStmt(IdName an_id, Integer a_line, Integer a_column){
    id = an_id;
    expression = null;
    navigation= null;
    line = a_line;
    column = a_column;
  }

  public void setIdName(IdName an_id){
    id = an_id;
  }

  public void setNavigation(Navigation a_navigation){
    navigation = a_navigation;
  }

  public void setExpressions(ArrayList<Expression> an_expression){
    expression = an_expression;
  }

  public IdName getIdName(){
    return id;
  }

  public Navigation getNavigation(){
    return navigation;
  }

  public List<Expression> getExpressions(){
    return (expression == null) ? new ArrayList<Expression>() : expression;
  }

  public void setExtern(Boolean extern){
    this.extern = extern;
  }

  public Boolean isExtern(){
    return extern;
  }

  public void setAttrNum(Integer attr){
    this.attr_number = attr;
  }
  public Integer getAttrNum(){
    return attr_number;
  }

  public String className(){
    return "MethodCallStmt";
  }

  // @Override
  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}