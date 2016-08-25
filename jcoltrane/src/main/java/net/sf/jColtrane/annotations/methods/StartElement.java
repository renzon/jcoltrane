package net.sf.jColtrane.annotations.methods;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.jColtrane.factory.StartElementConditionsFactory;




@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@ConditionFactoryAnnotation(StartElementConditionsFactory.class)
public @interface StartElement {
	String tag()default "";
	String uri() default "";
	String localName() default "";
	int priority() default 0;
	ContainAttribute[] attributes()default {@ContainAttribute};
}
