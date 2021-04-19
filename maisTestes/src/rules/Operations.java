package rules;

import java.io.FileNotFoundException;

import rules.Rule.operator;

public class Operations {
	Rule r;
	
	public boolean logic1(Threshold t, int valor) throws FileNotFoundException {
		return t.result(valor);
	}

	
	public  boolean logic2(Threshold t, Threshold t1, int valor, int valor1) throws FileNotFoundException {
		if (oper.get(0).equals(operator.AND))
			return and(t.result(valor), t1.result(valor1));
		if (oper.get(0).equals(operator.OR))
			return or(t.result(valor), t1.result(valor1));
		throw new IllegalAccessError("Erro ao comparar thresholds\n");
	}



	public boolean logic3 (Threshold t1, Threshold t2, Threshold t3, int valor1, int valor2, int valor3) throws FileNotFoundException{
		boolean aux= false;
		if (oper.get(0).equals(operator.AND))
			aux= and(t1.result(valor1), t2.result(valor2));
		else if (oper.get(0).equals(operator.OR))
			aux= or(t1.result(valor1), t2.result(valor2));
		if (oper.get(1).equals(operator.AND)) 
			return and(aux, t3.result(valor3));
		if (oper.get(1).equals(operator.OR)) 
			return or(aux, t3.result(valor3));
		throw new IllegalAccessError("Erro ao comparar thresholds\n");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
