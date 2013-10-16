package example6;

import net.sf.jColtrane.annotations.args.CurrentBranch;
import net.sf.jColtrane.annotations.methods.StartElement;

public class Parser2 {
	@StartElement(tag="afterLine")
	public void parse2(@CurrentBranch String branch){
		System.out.println(branch);
		System.out.println("execute another action");
	}
}
