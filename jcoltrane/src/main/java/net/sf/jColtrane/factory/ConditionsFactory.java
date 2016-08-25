package net.sf.jColtrane.factory;

import java.lang.annotation.Annotation;
import java.util.List;

import net.sf.jColtrane.conditions.Condition;

public interface ConditionsFactory {
	public List<Condition> getConditions(Annotation annotation);
}
