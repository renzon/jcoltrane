package example3;

import net.sf.jColtrane.annotations.args.CurrentBranch;
import net.sf.jColtrane.annotations.methods.BeforeElement;
import net.sf.jColtrane.annotations.methods.StartElement;

public class BeforeElementClass {
	
	
	@BeforeElement(elementDeep=1,tag="paragraphy")
	@StartElement
	public void executeInStartElement(@CurrentBranch String currentBranch){
		System.out.println(currentBranch);
		System.out.println("Executing something in start element\n");
	}

	
}
