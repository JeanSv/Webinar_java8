package telran.calculator;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.calculator.dto.CalculateData;
import static telran.calculator.dto.CalculatorAPIConstants.*;
import telran.calculator.service.ICalculator;

@RestController
@EnableMBeanExport
public class CalculatorController {

	@Autowired
	ICalculator calculator;
	
	@GetMapping(value=OPERATIONS)
	Set<String> getOperationCodes(){
		return calculator.getOperationCodes();
	}
	@PostMapping(value=CALCULATE)
	int calculate(@RequestBody CalculateData data) {
		return calculator.calculate(data.op1, data.op2, data.operation);
	}
	@GetMapping(value=LENGTH+"/{num}")
	int getDigitsPath(@ PathVariable("num") int number) {
		return calculator.numberlebgth(number);
	}
	@GetMapping(value=LENGTH)
	int getPath(@ RequestParam(NUMBER) int number) {
		return calculator.numberlebgth(number);
	}
}
