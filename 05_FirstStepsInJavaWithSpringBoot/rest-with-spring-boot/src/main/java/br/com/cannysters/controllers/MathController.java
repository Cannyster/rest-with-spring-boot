package br.com.cannysters.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.cannysters.Util.Util;
import br.com.cannysters.calculator.Calculator;
import br.com.cannysters.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {
	
	@GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value = "numberTwo")String numberTwo
		)throws Exception{
		
		if(!Util.isNumeric(numberOne) || !Util.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!!");
		}
		
		return Calculator.sum(Util.convertToDouble(numberOne), Util.convertToDouble(numberTwo));
	}

	@GetMapping("/subtraction/{numberOne}/{numberTwo}")
	public Double subtraction(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value = "numberTwo")String numberTwo
			)throws Exception{
		
		if(!Util.isNumeric(numberOne) || !Util.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!!");
		}
		
		return Calculator.subtraction(Util.convertToDouble(numberOne),Util.convertToDouble(numberTwo));
	}
	
	@GetMapping("/division/{numberOne}/{numberTwo}")
	public Double division(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value = "numberTwo")String numberTwo
		)throws Exception{
		
		if(!Util.isNumeric(numberOne) || !Util.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!!");
		}
		
		return Calculator.division(Util.convertToDouble(numberOne), Util.convertToDouble(numberTwo));
	}
	
	@GetMapping(value = "/multiply/{numberOne}/{numberTwo}")
	public Double multiply(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value = "numberTwo")String numberTwo
		)throws Exception{
		
		if(!Util.isNumeric(numberOne) || !Util.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!!");
		}
		
		return Calculator.multiply(Util.convertToDouble(numberOne), Util.convertToDouble(numberTwo));
	}
	
	@GetMapping(value = "/average/{numberOne}/{numberTwo}")
	public Double average(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value = "numberTwo")String numberTwo

		)throws Exception{
		
		if(!Util.isNumeric(numberOne) || !Util.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!!");
		}
		
		return Calculator.average(Util.convertToDouble(numberOne), Util.convertToDouble(numberTwo)) ;
	}
	
	@GetMapping(value = "/squareRoot/{numberOne}")
	public Double squareRoot(
			@PathVariable(value = "numberOne")String numberOne
			)throws Exception{
		
		if(!Util.isNumeric(numberOne)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!!");
		}
		
		return Calculator.squareRoot(Util.convertToDouble(numberOne));
	}

			
	
	
	
	
	
}
