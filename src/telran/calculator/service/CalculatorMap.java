package telran.calculator.service;

import java.util.*;
import java.util.function.BinaryOperator;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(description = "my resourse")
@EnableMBeanExport
public class CalculatorMap implements ICalculator
{
	@Value("${wrongvalue:-10}")
	int wrongvalue;
	static Map<String, BinaryOperator<Integer>> mapOperations;
	static
	{
		mapOperations = new HashMap<>();
		mapOperations.put("+", (op1, op2) -> op1 + op2);
		mapOperations.put("*", (op1, op2) -> op1 * op2);
		mapOperations.put("-", (op1, op2) -> op1 - op2);
		mapOperations.put("/", (op1, op2) -> op1 / op2);
	}
    @ManagedAttribute
	public int getWrongvalue() {
		return wrongvalue;
	}

	@ManagedAttribute
    public void setWrongvalue(int wrongvalue) {
		this.wrongvalue = wrongvalue;
	}

	@Override
	public Set<String> getOperationCodes()
	{
		return mapOperations.keySet();
	}

	@Override
	public int calculate(int op1, int op2, String operation)
	{
		try
		{
			return mapOperations.get(operation).apply(op1, op2);
		} catch (NullPointerException e)
		{
			System.out.println(operation + " unknown operation");
			return wrongvalue;
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			return wrongvalue;
		}
	}
	@ManagedOperation
	@ManagedOperationParameter(description = "first number",name ="x")
	@ManagedOperationParameter(description = "second number",name ="y")
	public int sum(int x, int y) {
		return x+y;
	}

	@Override
	public int numberlebgth(int number) {
		// TODO Auto-generated method stub
		return Integer.toString(number).length();
	}
	
	public CalculatorMap() {
	}
	@PostConstruct
	public void welcomeMessage() {
		System.out.println("Wrong value = "+wrongvalue);
	}
	@PreDestroy
	public void endMessage() {
		System.out.println("Wrong value = "+wrongvalue);
	}
}
