package net.sf.jColtrane.annotations.methods;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.jColtrane.factory.InsideElementConditionsFactory;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@ConditionFactoryAnnotation(InsideElementConditionsFactory.class)
public @interface InsideElement {
	String tag()default "";
	String uri() default "";
	String localName() default "";
	ContainAttribute[] attributes()default {@ContainAttribute};
	
}

