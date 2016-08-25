package net.sf.jColtrane.conditions;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import net.sf.jColtrane.annotations.methods.EndElement;
import net.sf.jColtrane.annotations.methods.StartElement;
import net.sf.jColtrane.handler.ContextVariables;


public class ConditionalMethod implements Comparable<ConditionalMethod> {
	
	private Method method;
	private List<Condition> conditions;
	public ConditionalMethod(Method method) {
		super();
		this.method = method;
		conditions=new LinkedList<Condition>();
	}
	
	public void addCondition(Condition condition){
		if(condition!=null)
			conditions.add(condition);
	}
	
	public void addConditions(List<Condition> conditions){
		if(conditions!=null){
			this.conditions.addAll(conditions);
		}
	}
	
	
	public boolean verify(ContextVariables contextVariables){
		for(Condition c:conditions){
			if(!c.verify(contextVariables))
				return false;
		}
		
		return true;
	}

	public Method getMethod() {
		return method;
	}

	
	public int compareTo(ConditionalMethod otherCMethod) {
		
		int thisPriority=0;
		int otherPriority=0;
		if(this.getMethod().isAnnotationPresent(StartElement.class)){
			thisPriority=this.getMethod().getAnnotation(StartElement.class).priority();
			
		}
		if(otherCMethod.getMethod().isAnnotationPresent(StartElement.class)){
			otherPriority=otherCMethod.getMethod().getAnnotation(StartElement.class).priority();
//			System.out.println("Entrou other start");
		}
		
		if(this.getMethod().isAnnotationPresent(EndElement.class)){
			thisPriority=this.getMethod().getAnnotation(EndElement.class).priority();
//			System.out.println("Entrou this end");
		}
		if(otherCMethod.getMethod().isAnnotationPresent(EndElement.class)){
			otherPriority=otherCMethod.getMethod().getAnnotation(EndElement.class).priority();
//			System.out.println("Entrou other end");
		}
		if(thisPriority==otherPriority)
			return 0;
		else if(thisPriority>otherPriority)
			return -1;
		return 1;
	}

	
	
	

}
