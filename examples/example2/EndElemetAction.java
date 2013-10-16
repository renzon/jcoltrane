package example2;

import net.sf.jColtrane.annotations.args.CurrentBranch;
import net.sf.jColtrane.annotations.methods.ContainAttribute;
import net.sf.jColtrane.annotations.methods.EndElement;

public class EndElemetAction {
	

	@EndElement(priority=2,attributes={@ContainAttribute(name="mandatory")})
	public void executeInEndElement1(@CurrentBranch String currentBranch){
		System.out.println(currentBranch);
		System.out.println("Executing something in end element with attribute mandatory\n");
	}
	
	@EndElement(attributes={@ContainAttribute(name="mandatory"),@ContainAttribute(name="page",value="4")})
	public void executeInEndElement2(@CurrentBranch String currentBranch){
		System.out.println(currentBranch);
		System.out.println("Executing something in end element with some attribute with name mandatory and another with name page and value 4\n");
	}
	
	@EndElement(priority=3,attributes={@ContainAttribute(value="pt")})
	public void executeInEndElement3(@CurrentBranch String currentBranch){
		System.out.println(currentBranch);
		System.out.println("Executing something in end element with some attribute with value pt\n");
	}
}
