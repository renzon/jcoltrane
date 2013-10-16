package net.sf.jColtrane.conditions;

import net.sf.jColtrane.handler.ContextVariables;

public interface Condition {
	
	public boolean verify(ContextVariables contextVariables);
	
	
}
