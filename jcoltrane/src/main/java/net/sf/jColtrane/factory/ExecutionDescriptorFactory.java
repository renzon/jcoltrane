package net.sf.jColtrane.factory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import net.sf.jColtrane.annotations.methods.ConditionFactoryAnnotation;
import net.sf.jColtrane.annotations.methods.EndDocument;
import net.sf.jColtrane.annotations.methods.EndElement;
import net.sf.jColtrane.annotations.methods.StartDocument;
import net.sf.jColtrane.annotations.methods.StartElement;
import net.sf.jColtrane.conditions.ConditionalMethod;
import net.sf.jColtrane.descriptor.ExecutionDescriptor;

public class ExecutionDescriptorFactory {
	static private Class<?> targetClass;
	private ExecutionDescriptorFactory() {}

	static public ExecutionDescriptor getExecutionDescriptor(Class<?> targetClass){
		ExecutionDescriptorFactory.targetClass=targetClass;
		ExecutionDescriptor descriptor=new ExecutionDescriptor();
		descriptor.addStartDocumentMethods(getMethodsWithAnotation(StartDocument.class));
		descriptor.addEndDocumentMethods(getMethodsWithAnotation(EndDocument.class));
		descriptor.addStartElementConditionalMethods(getConditionalMethods(StartElement.class));
		descriptor.addEndElementConditionalMethods(getConditionalMethods(EndElement.class));
		descriptor.sortConditionalMethods();
		return descriptor;
	}

	static private List <Method> getMethodsWithAnotation(Class<? extends Annotation> annotationClass){
		List <Method> methodsList=new LinkedList<Method>();
		if(targetClass!=null){
			for(Method m:targetClass.getDeclaredMethods()){
				if(m.isAnnotationPresent(annotationClass)){
					m.setAccessible(true);
					methodsList.add(m);
				}
			}
		}		
		return methodsList;
	}

	static private List <ConditionalMethod> getConditionalMethods(Class<? extends Annotation> annotationClass){
		List <ConditionalMethod> conditonalMethodsList=new LinkedList<ConditionalMethod>();
		List <Method>methodsList=getMethodsWithAnotation(annotationClass);
		for(Method method:methodsList){
			conditonalMethodsList.add(getConditionalMethod(method));
		}
		return conditonalMethodsList;	
	}

	private static ConditionalMethod getConditionalMethod(Method method) {
		ConditionalMethod conditionalMethod=new ConditionalMethod(method);
		for(Annotation annotation: method.getAnnotations()){
			if(annotation.annotationType().isAnnotationPresent(ConditionFactoryAnnotation.class)){
				String className=new String(annotation.annotationType().getAnnotation(ConditionFactoryAnnotation.class).value().getName());
				try {
					ConditionsFactory conditionFactory=(ConditionsFactory) Class.forName(className).newInstance();
					conditionalMethod.addConditions(conditionFactory.getConditions(annotation));
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
		return conditionalMethod;
	}
}
