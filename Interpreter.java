import
java.util.*;
 
interface Expression {
   
   publicvoid interprete(Stack<Integer> s);

}

class ExpresionTerminal_Numero implements Expresion {
  
   private int numero;
   
   public ExpresionTerminal_Numero(int numero){ 
      this.numero = numero; 
   }

   publicvoid interprete(Stack<Integer> s){ 
      s.push(numero);
   }

} 

class ExpresionTerminal_Mas implements Expresion {

   publicvoid interprete(Stack<Integer> s){ 
      s.push( s.pop() + s.pop());
    } 

} 

class ExpresionTerminal_Menos implements Expresion {

   publicvoid interpret(Stack<Integer> s){
      int tmp = s.pop(); 
      s.push( s.pop() - tmp );
   }
} 

class Parser {

   private ArrayList<Expresion> parseTree = new ArrayList<Expresion>();// Solo una ExpresiónNoTerminal 
   
   Parser(String s) { 
      for (String token : s.split(" ")) { 
         if (token.equals("+")) 
            parseTree.add( new ExpresionTerminal_Mas() ); 
         else if (token.equals("-")) 
            parseTree.add( new ExpresionTerminal_Menos() ); // ... 
         else parseTree.add( new ExpresionTerminal_Numero(Integer.valueOf(token))); 
      } 
   }

   public int evalua() {
      Stack<Integer> contexto = new Stack<Integer>();
      for(Expression e : parseTree) e.interprete(contexto); 
      return contexto.pop(); 
   }

} 

class EjemploInterprete { 

   public static void main(String[] args) { 
      System.out.println("'42 2 1 - +' equals " + new Parser("42 2 1 - +").evalua());
   }

}
