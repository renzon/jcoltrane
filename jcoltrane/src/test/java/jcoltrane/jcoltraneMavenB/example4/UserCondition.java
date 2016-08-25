package jcoltrane.jcoltraneMavenB.example4;

import java.util.regex.Pattern;

import net.sf.jColtrane.conditions.Condition;
import net.sf.jColtrane.handler.ContextVariables;

public class UserCondition  implements Condition {

	private Pattern pattern;
	 
	public UserCondition(String regularExpression) {
		pattern=Pattern.compile(regularExpression);
	}

	@Override
	public boolean verify(ContextVariables contextVariables) {
		return pattern.matcher(contextVariables.getCurrentBranch()).matches();
	}
}
