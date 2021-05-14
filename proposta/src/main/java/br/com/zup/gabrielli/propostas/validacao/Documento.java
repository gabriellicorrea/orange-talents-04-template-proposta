package br.com.zup.gabrielli.propostas.validacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@CPF 
@ConstraintComposition(CompositionType.OR)
@CNPJ
@ReportAsSingleViolation
@Constraint(validatedBy = { })
public @interface Documento {
	String message() default "Documento cpf ou cnpj invalido";
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default {};
	
}