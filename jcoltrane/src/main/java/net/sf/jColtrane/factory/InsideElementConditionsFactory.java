package net.sf.jColtrane.factory;

import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;

import net.sf.jColtrane.annotations.methods.ContainAttribute;
import net.sf.jColtrane.annotations.methods.InsideElement;
import net.sf.jColtrane.conditions.Condition;
import net.sf.jColtrane.conditions.InsideCondition;

public class InsideElementConditionsFactory implements ConditionsFactory {

	@Override
	public List<Condition> getConditions(Annotation annotation) {
		if(annotation instanceof InsideElement){
			List<Condition> conditions=new LinkedList<Condition>();
			InsideElement insideElement=(InsideElement) annotation;
			InsideCondition insideCondition=new InsideCondition(insideElement.tag(),insideElement.uri(),insideElement.localName());
			for(ContainAttribute att:insideElement.attributes()){
				insideCondition.addAttributeCondition(att.name(), att.value());
			}
			conditions.add(insideCondition);
			return conditions;
		}
		return null;
	}

	

}
