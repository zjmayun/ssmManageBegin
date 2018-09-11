package test;

import org.junit.Test;

public class TestMethod {
    
	
	public boolean testFor() {
		for(int i=0;i<10;i++) {
			if(i==4) {
				return true;
			}
			System.out.println(i);
		}
		return false;
	}
	
	@Test
	public void Test() {
		testFor();
	}
	
}
