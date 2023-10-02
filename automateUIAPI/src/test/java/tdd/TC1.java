package tdd;

import org.testng.annotations.Test;

import base.BaseTestNG;
import bdd.stepDefinitions.Common;

public class TC1 extends BaseTestNG{
	
	Common common=new Common();
	
	@Test
	public void test() {
		common.i("WaitForPageToBeLoad", "5");
	}

}
