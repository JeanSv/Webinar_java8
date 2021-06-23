package telran.calculator.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import account.configuration.Authenticator;
import account.service.AccountManagementMongo;
import account.service.IAccountingManagement;
import telran.calculator.dto.CalculatorAPIConstants;

@Configuration
@EnableMongoRepositories("account.repo")
public class CalculatorSecurityConfiguration extends WebSecurityConfigurerAdapter{

	//@Bean
//	AccountSecurityConfiguration getAccountSecurityConfiguration() {
	//	return new AccountSecurityConfiguration();
	//}
	@Bean
	Authenticator getAutheticator() {
		return new Authenticator();
	}
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	@Bean
	IAccountingManagement getIAccountingManagement() {
		return new AccountManagementMongo();
	}
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.httpBasic();
		httpSecurity.csrf().disable();
		httpSecurity.authorizeRequests().antMatchers(CalculatorAPIConstants.OPERATIONS).permitAll();//authenticated();
		httpSecurity.authorizeRequests().antMatchers(CalculatorAPIConstants.CALCULATE).hasAnyRole("ADMIN","USER");
		httpSecurity.authorizeRequests().antMatchers(CalculatorAPIConstants.LENGTH).hasRole("ADMIN");

	}
}
