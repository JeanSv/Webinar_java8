package telran.calculator.service;

import java.util.Set;

public interface ICalculator
{
	Set<String> getOperationCodes();
	int calculate(int op1, int op2, String operation);
	int numberlebgth(int number);
}
