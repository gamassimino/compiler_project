package Visitor;

import ASTClass.*;
import TableOfHash.Hash;
import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.*;

public class ConstantOptimizer implements ASTVisitor<Literal>{

  public ConstantOptimizer(){
  }

  @Override
  public Literal visit(AddAssignment stmt){
    AST right = stmt.getRight().accept(this);
    if(right instanceof IntLiteral){
      IntLiteral r = (IntLiteral)right;
      stmt.setRight(r);
    }
    if(right instanceof FloatLiteral){
      FloatLiteral r = (FloatLiteral)right;
      stmt.setRight(r);
    }
    if(right instanceof BoolLiteral){
      BoolLiteral r = (BoolLiteral)right;
      stmt.setRight(r);
    }
    return null;
  }

  public Literal visit(And stmt){
    AST left = stmt.getLeft().accept(this);
    AST right = stmt.getRight().accept(this);
    if(left instanceof BoolLiteral && right instanceof BoolLiteral){
      BoolLiteral l = (BoolLiteral)left;
      BoolLiteral r = (BoolLiteral)right;
      Boolean value = l.getValue() && r.getValue();
      return new BoolLiteral(value.toString());
    }
    return null;
  }

  public Literal visit(Assignment stmt){
    AST right = stmt.getRight().accept(this);
    if(right instanceof IntLiteral){
      IntLiteral r = (IntLiteral)right;
      stmt.setRight(r);
    }
    if(right instanceof FloatLiteral){
      FloatLiteral r = (FloatLiteral)right;
      stmt.setRight(r);
    }
    if(right instanceof BoolLiteral){
      BoolLiteral r = (BoolLiteral)right;
      stmt.setRight(r);
    }
    return null;
  }

  public Literal visit(Block expr){

    for (Statement statement : expr.getStatements()) {
      statement.accept(this);
    }

    return null;
  }

  public Literal visit(Body expr){
    if(expr.getBlock() != null)
      expr.getBlock().accept(this);
    //elBody
    return null;
  }

  public Literal visit(BreakStmt expr){
    return null;
  }

  public Literal visit(ClassDecl expr){

    for (MethodDecl statement : expr.getMethodDecl()) {
      statement.accept(this);
    }

    return null;
  }

  public Literal visit(ContinueStmt expr){
    return null;
  }

  public Literal visit(Divided expr){
    AST left = expr.getLeft().accept(this);
    AST right = expr.getRight().accept(this);
    if(left instanceof IntLiteral && right instanceof IntLiteral){
      if(left instanceof IntLiteral){
        IntLiteral l = (IntLiteral)left;
        IntLiteral r = (IntLiteral)right;
        Integer value = l.getValue() / r.getValue();
        return new IntLiteral(value.toString());
      }
    }
    if(left instanceof FloatLiteral && right instanceof FloatLiteral){
      if(left instanceof FloatLiteral){
        FloatLiteral l = (FloatLiteral)left;
        FloatLiteral r = (FloatLiteral)right;
        Float value = l.getValue() / r.getValue();
        return new FloatLiteral(value.toString());
      }
    }
    return null;
  }

  public Literal visit(EqualTo expr){
    AST left = expr.getLeft().accept(this);
    AST right = expr.getRight().accept(this);
    if(left instanceof IntLiteral && right instanceof IntLiteral){
      if(left instanceof IntLiteral){
        IntLiteral l = (IntLiteral)left;
        IntLiteral r = (IntLiteral)right;
        Boolean value = l.getValue() == r.getValue();
        return new BoolLiteral(value.toString());
      }
    }
    if(left instanceof FloatLiteral && right instanceof FloatLiteral){
      if(left instanceof FloatLiteral){
        FloatLiteral l = (FloatLiteral)left;
        FloatLiteral r = (FloatLiteral)right;
        Boolean value = l.getValue() == r.getValue();
        return new BoolLiteral(value.toString());
      }
    }
    if(left instanceof BoolLiteral && right instanceof BoolLiteral){
      if(left instanceof BoolLiteral){
        BoolLiteral l = (BoolLiteral)left;
        BoolLiteral r = (BoolLiteral)right;
        Boolean value = l.getValue() == r.getValue();
        return new BoolLiteral(value.toString());
      }
    }
    return null;
  }

  public Literal visit(FieldDecl stmt){
    return null;
  }

  public Literal visit(ForStmt stmt){
    stmt.getStatement().accept(this);
    return null;
  }

  public Literal visit(Greater stmt){
   AST left = stmt.getLeft().accept(this);
    AST right = stmt.getRight().accept(this);
    if(left instanceof IntLiteral && right instanceof IntLiteral){
      if(left instanceof IntLiteral){
        IntLiteral l = (IntLiteral)left;
        IntLiteral r = (IntLiteral)right;
        Boolean value = l.getValue() > r.getValue();
        return new BoolLiteral(value.toString());
      }
    }
    if(left instanceof FloatLiteral && right instanceof FloatLiteral){
      if(left instanceof FloatLiteral){
        FloatLiteral l = (FloatLiteral)left;
        FloatLiteral r = (FloatLiteral)right;
        Boolean value = l.getValue() > r.getValue();
        return new BoolLiteral(value.toString());
      }
    }
    return null;
  }

  public Literal visit(GreaterOrEq stmt){
   AST left = stmt.getLeft().accept(this);
    AST right = stmt.getRight().accept(this);
    if(left instanceof IntLiteral && right instanceof IntLiteral){
      if(left instanceof IntLiteral){
        IntLiteral l = (IntLiteral)left;
        IntLiteral r = (IntLiteral)right;
        Boolean value = l.getValue() >= r.getValue();
        return new BoolLiteral(value.toString());
      }
    }
    if(left instanceof FloatLiteral && right instanceof FloatLiteral){
      if(left instanceof FloatLiteral){
        FloatLiteral l = (FloatLiteral)left;
        FloatLiteral r = (FloatLiteral)right;
        Boolean value = l.getValue() >= r.getValue();
        return new BoolLiteral(value.toString());
      }
    }
    return null;
  }

  public Literal visit(IdName stmt){
    return null;
  }

  public Literal visit(IfStmt stmt){
    stmt.getIfBlock().accept(this);
    if(stmt.getElseBlock() != null)
      stmt.getElseBlock().accept(this);
    return null;
  }

  public Literal visit(IntLiteral stmt){
    //nada
    return stmt;
  }

  public Literal visit(FloatLiteral stmt){
    //nada
    return stmt;
  }

  public Literal visit(BoolLiteral stmt){
    //nada
    return stmt;
  }

  public Literal visit(Less expr){
    AST left = expr.getLeft().accept(this);
    AST right = expr.getRight().accept(this);
    if(left instanceof IntLiteral && right instanceof IntLiteral){
      if(left instanceof IntLiteral){
        IntLiteral l = (IntLiteral)left;
        IntLiteral r = (IntLiteral)right;
        Boolean value = l.getValue() < r.getValue();
        return new BoolLiteral(value.toString());
      }
    }
    if(left instanceof FloatLiteral && right instanceof FloatLiteral){
      if(left instanceof FloatLiteral){
        FloatLiteral l = (FloatLiteral)left;
        FloatLiteral r = (FloatLiteral)right;
        Boolean value = l.getValue() < r.getValue();
        return new BoolLiteral(value.toString());
      }
    }
    return null;
  }

  public Literal visit(LessOrEq expr){
    AST left = expr.getLeft().accept(this);
    AST right = expr.getRight().accept(this);
    if(left instanceof IntLiteral && right instanceof IntLiteral){
      if(left instanceof IntLiteral){
        IntLiteral l = (IntLiteral)left;
        IntLiteral r = (IntLiteral)right;
        Boolean value = l.getValue() <= r.getValue();
        return new BoolLiteral(value.toString());
      }
    }
    if(left instanceof FloatLiteral && right instanceof FloatLiteral){
      if(left instanceof FloatLiteral){
        FloatLiteral l = (FloatLiteral)left;
        FloatLiteral r = (FloatLiteral)right;
        Boolean value = l.getValue() <= r.getValue();
        return new BoolLiteral(value.toString());
      }
    }
    return null;
  }

  public Literal visit(LocationExpr stmt){
    return null;
  }

  public Literal visit(LocationStmt stmt){
    return null;
  }

  public Literal visit(MethodCallStmt stmt){
    return null;
  }

  public Literal visit(MethodCallExpr stmt){
    return null;
  }

  // the body create a level and destroy it
  public Literal visit(MethodDecl stmt){
    stmt.getBody().accept(this);
    return null;
  }

  public Literal visit(Minus stmt){
    AST left = stmt.getLeft().accept(this);
    AST right = stmt.getRight().accept(this);
    if(left instanceof IntLiteral && right instanceof IntLiteral){
      if(left instanceof IntLiteral){
        IntLiteral l = (IntLiteral)left;
        IntLiteral r = (IntLiteral)right;
        Integer value = l.getValue() - r.getValue();
        return new IntLiteral(value.toString());
      }
    }
    if(left instanceof FloatLiteral && right instanceof FloatLiteral){
      if(left instanceof FloatLiteral){
        FloatLiteral l = (FloatLiteral)left;
        FloatLiteral r = (FloatLiteral)right;
        Float value = l.getValue() - r.getValue();
        return new FloatLiteral(value.toString());
      }
    }
    return null;
  }

  public Literal visit(Navigation stmt){
    return null;
  }

  public Literal visit(Not stmt){
    return null;
  }

  public Literal visit(NotEqualTo stmt){
    AST left = stmt.getLeft().accept(this);
    AST right = stmt.getRight().accept(this);
    if(left instanceof IntLiteral && right instanceof IntLiteral){
      if(left instanceof IntLiteral){
        IntLiteral l = (IntLiteral)left;
        IntLiteral r = (IntLiteral)right;
        Boolean value = l.getValue() != r.getValue();
        return new BoolLiteral(value.toString());
      }
    }
    if(left instanceof FloatLiteral && right instanceof FloatLiteral){
      if(left instanceof FloatLiteral){
        FloatLiteral l = (FloatLiteral)left;
        FloatLiteral r = (FloatLiteral)right;
        Boolean value = l.getValue() != r.getValue();
        return new BoolLiteral(value.toString());
      }
    }
    if(left instanceof BoolLiteral && right instanceof BoolLiteral){
      if(left instanceof BoolLiteral){
        BoolLiteral l = (BoolLiteral)left;
        BoolLiteral r = (BoolLiteral)right;
        Boolean value = l.getValue() != r.getValue();
        return new BoolLiteral(value.toString());
      }
    }
    return null;
  }

  public Literal visit(Or stmt){
    AST left = stmt.getLeft().accept(this);
    AST right = stmt.getRight().accept(this);
    if(left instanceof BoolLiteral && right instanceof BoolLiteral){
      BoolLiteral l = (BoolLiteral)left;
      BoolLiteral r = (BoolLiteral)right;
      Boolean value = l.getValue() || r.getValue();
      return new BoolLiteral(value.toString());
    }
    return null;
  }

  // need the new implementation of FieldDecl
  public Literal visit(Param stmt){
    return null;
  }

  public Literal visit(Percentage stmt){
    AST left = stmt.getLeft().accept(this);
    AST right = stmt.getRight().accept(this);
    if(left instanceof IntLiteral && right instanceof IntLiteral){
      if(left instanceof IntLiteral){
        IntLiteral l = (IntLiteral)left;
        IntLiteral r = (IntLiteral)right;
        Integer value = l.getValue() % r.getValue();
        return new IntLiteral(value.toString());
      }
    }
    if(left instanceof FloatLiteral && right instanceof FloatLiteral){
      if(left instanceof FloatLiteral){
        FloatLiteral l = (FloatLiteral)left;
        FloatLiteral r = (FloatLiteral)right;
        Float value = l.getValue() % r.getValue();
        return new FloatLiteral(value.toString());
      }
    }
    return null;
  }

  public Literal visit(Plus stmt){
    AST left = stmt.getLeft().accept(this);
    AST right = stmt.getRight().accept(this);
    if(left instanceof IntLiteral && right instanceof IntLiteral){
      if(left instanceof IntLiteral){
        IntLiteral l = (IntLiteral)left;
        IntLiteral r = (IntLiteral)right;
        Integer value = l.getValue() + r.getValue();
        return new IntLiteral(value.toString());
      }
    }
    if(left instanceof FloatLiteral && right instanceof FloatLiteral){
      if(left instanceof FloatLiteral){
        FloatLiteral l = (FloatLiteral)left;
        FloatLiteral r = (FloatLiteral)right;
        Float value = l.getValue() + r.getValue();
        return new FloatLiteral(value.toString());
      }
    }
    return null;
  }

  public Literal visit(Program stmt){
    for (ClassDecl class_decl : stmt.getClassList()) {
      class_decl.accept(this);
    }
    //nada
    return null;
  }

  public Literal visit(ReturnExpr stmt){
    if (stmt.getExpression() != null)
      stmt.getExpression().accept(this);
    return null;
  }

  public Literal visit(ReturnStmt stmt){
    if (stmt.getExpression() != null)
      stmt.getExpression().accept(this);
    return null;
  }

  public Literal visit(SubAssignment stmt){
    AST right = stmt.getRight().accept(this);
    if(right instanceof IntLiteral){
      IntLiteral r = (IntLiteral)right;
      stmt.setRight(r);
    }
    if(right instanceof FloatLiteral){
      FloatLiteral r = (FloatLiteral)right;
      stmt.setRight(r);
    }
    if(right instanceof BoolLiteral){
      BoolLiteral r = (BoolLiteral)right;
      stmt.setRight(r);
    }
    return null;
  }

  public Literal visit(Times expr){
    AST left = expr.getLeft().accept(this);
    AST right = expr.getRight().accept(this);
    if(left instanceof IntLiteral && right instanceof IntLiteral){
      if(left instanceof IntLiteral){
        IntLiteral l = (IntLiteral)left;
        IntLiteral r = (IntLiteral)right;
        Integer value = l.getValue() * r.getValue();
        return new IntLiteral(value.toString());
      }
    }
    if(left instanceof FloatLiteral && right instanceof FloatLiteral){
      if(left instanceof FloatLiteral){
        FloatLiteral l = (FloatLiteral)left;
        FloatLiteral r = (FloatLiteral)right;
        Float value = l.getValue() * r.getValue();
        return new FloatLiteral(value.toString());
      }
    }
    return null;
  }

  public Literal visit(Type stmt){
    return null;
  }

  public Literal visit(WhileStmt stmt){
    return null;
  }

  public Literal visit(Instance stmt){
    return null;
  }
}
