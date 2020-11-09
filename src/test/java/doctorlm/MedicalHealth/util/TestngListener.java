package doctorlm.MedicalHealth.util;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import doctorlm.MedicalHealth.test.CaseBase;

public class TestngListener extends TestListenerAdapter{

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		// 对于dataProvider的用例，每次成功后，重置Retry次数
		try {
			Retry retry = (Retry)tr.getMethod().getRetryAnalyzer();	
			retry.resetRetryCnt();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		
		String testMethodName = String.valueOf(tr.getName());
		CaseBase caseBase = (CaseBase)tr.getInstance();
		AutoTakeScreenShot.takeScreenShot(caseBase.driver.driver, testMethodName);
		// 对于dataProvider的用例，每次失败后，重置Retry次数
		Retry retry = (Retry)tr.getMethod().getRetryAnalyzer();
		retry.resetRetryCnt();
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		
		String testMethodName = String.valueOf(tr.getName());
		CaseBase caseBase = (CaseBase)tr.getInstance();
		AutoTakeScreenShot.takeScreenShot(caseBase.driver.driver, testMethodName);
		
		Retry retry = (Retry)tr.getMethod().getRetryAnalyzer();
		retry.resetRetryCnt();
	}

	@Override
	public void onConfigurationFailure(ITestResult itr) {
		super.onConfigurationFailure(itr);
	}

	@Override
	public void onConfigurationSkip(ITestResult itr) {
		super.onConfigurationSkip(itr);
	}

	
}
