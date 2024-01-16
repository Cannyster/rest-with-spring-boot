package br.com.cannysters.calculator;

public class Calculator {
	
	public static Double sum(Double numberOne, Double numberTwo) {
		return numberOne + numberTwo;
	}
	
	public static Double subtraction(Double numberOne, Double numberTwo) {
		return numberOne - numberTwo;
	}
	
	public static Double division(Double numberOne, Double numberTwo) {
		return numberOne / numberTwo;
	}
	
	public static Double multiply(Double numberOne, Double numberTwo) {
		return numberOne * numberTwo;
	}
	
	public static Double average(Double numberOne, Double numberTwo) {
		return (numberOne + numberTwo) / 2;
	}
	
	public static Double squareRoot(Double number) {
		return Math.sqrt(number);
	}	
	
	
}
