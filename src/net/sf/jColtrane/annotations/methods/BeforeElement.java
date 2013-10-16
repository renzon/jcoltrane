package net.sf.jColtrane.annotations.methods;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.jColtrane.factory.BeforeElementConditionsFactory;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@ConditionFactoryAnnotation(BeforeElementConditionsFactory.class)
public @interface BeforeElement {
	String tag()default "";
	String uri() default "";
	String localName() default "";
	int elementDeep();
	ContainAttribute[] attributes()default {@ContainAttribute};
}

