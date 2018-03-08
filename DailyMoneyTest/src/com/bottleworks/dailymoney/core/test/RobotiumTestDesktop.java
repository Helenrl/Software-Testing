package com.bottleworks.dailymoney.core.test;

import com.bottleworks.dailymoney.calculator2.Calculator;
import com.bottleworks.dailymoney.ui.DesktopActivity;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;


	public class RobotiumTestDesktop extends
	ActivityInstrumentationTestCase2<DesktopActivity> {
		public RobotiumTestDesktop(){
			super(DesktopActivity.class);
}

@Override 
	protected void setUp() throws Exception {  
	super.setUp();  
	solo = new Solo(getInstrumentation(),getActivity()); 
	mActivity = getActivity();  }

	private Solo solo;
	private DesktopActivity mActivity;

@Override 
	protected void tearDown() throws Exception{
	solo.finishOpenedActivities(); 
}

private static final int TIMEOUT_IN_MS = 5000;  
	public void testAddDetailCreateFailScenario()
{
	solo.assertCurrentActivity("Expected DesktopActivity", DesktopActivity.class);
	solo.clickOnText("Add Detail");
	assertTrue(solo.waitForText("From"));
	assertTrue(solo.waitForText("To"));
	assertTrue(solo.waitForText("Date"));
	assertTrue(solo.waitForText("Money"));
	assertTrue(solo.waitForText("Note"));
	solo.clickOnButton("Create");
	assertTrue(solo.waitForText("Money is empty"));
}

	public void testAddDetailOKScenario()
	{
		solo.sleep(TIMEOUT_IN_MS);
		solo.assertCurrentActivity("Expected DesktopActivity", DesktopActivity.class);
		solo.clickOnText("Add Detail");
		assertTrue(solo.waitForText("From"));
		assertTrue(solo.waitForText("To"));
		assertTrue(solo.waitForText("Date"));
		assertTrue(solo.waitForText("Money"));
		assertTrue(solo.waitForText("Note"));
		solo.enterText(1, "123.45");
		solo.clickOnButton("Create");
		solo.goBackToActivity("DesktopActivity");
		assertTrue(solo.waitForText("Add Detail"));
		
	}
	public void testRecordDisplayScenario()
{
		solo.sleep(TIMEOUT_IN_MS);
		solo.assertCurrentActivity("Expected DesktopActivity", DesktopActivity.class);
		solo.clickOnText("Daily list");
		assertTrue(solo.waitForText("Entertainment"));
		solo.goBackToActivity("DesktopActivity");
		assertTrue(solo.waitForText("Add Detail"));
}
	public void testPreferenceScenario()
	{
		solo.sleep(TIMEOUT_IN_MS);
		solo.assertCurrentActivity("Expected DesktopActivity", DesktopActivity.class);
		solo.clickOnText("Preferences");
		assertTrue(solo.waitForText("Last Data Backup Time"));
		solo.clickOnText("Password protection-1");
		solo.enterText(0, "PSW1");
		solo.clickOnButton("OK");
	}

}
