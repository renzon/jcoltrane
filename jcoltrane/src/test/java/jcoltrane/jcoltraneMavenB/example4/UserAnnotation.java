package jcoltrane.jcoltraneMavenB.example4;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.jColtrane.annotations.methods.ConditionFactoryAnnotation;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@ConditionFactoryAnnotation(UserConditionsFactory.class)
public @interface UserAnnotation {
	String currentBranch();
}
