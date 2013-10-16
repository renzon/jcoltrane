package example4;

import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;

import net.sf.jColtrane.conditions.Condition;
import net.sf.jColtrane.factory.ConditionsFactory;

public class UserConditionsFactory implements ConditionsFactory{

	@Override
	public List<Condition> getConditions(Annotation annotation) {
		if(annotation instanceof UserAnnotation){
			List<Condition> conditions=new LinkedList<Condition>();
			UserAnnotation userAnnotation=(UserAnnotation) annotation;
			conditions.add(new UserCondition(userAnnotation.currentBranch()));
			return conditions;
		}
		return null;
	}
}
