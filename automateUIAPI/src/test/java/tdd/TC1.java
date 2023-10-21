package tdd;

import org.testng.annotations.Test;

import api.services.UserService;
import base.BaseTestNG;
import ui.bdd.stepDefinitions.Common;

public class TC1 extends BaseTestNG{
	
	Common common=new Common();
	
	@Test
	public void test() {
		UserService.getUsers();
		UserService.printResponse();
		UserService.validateUserStatusCode(200);
	}

}
