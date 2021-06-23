package telran.calculator.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import telran.calculator.service.CalculatorMap;
import telran.calculator.service.ICalculator;

@Configuration
public class CalculatorConfiguration {

	@Bean
	public ICalculator getCalculator() {
		
		return new CalculatorMap();
	}
}
