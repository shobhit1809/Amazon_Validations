package Listeners;

import org.testng.*;

public class CustomListeners implements ITestListener {

    public void onStart(ITestContext arg){
//     System.out.println("Started test Execution...."+arg.getName());
     Reporter.log("Started test Execution...."+arg.getName(),true);
    }

    @Override
    public void onFinish(ITestContext arg) {

//        System.out.println("Finished test Execution......"+arg.getName());
        Reporter.log("Finished test Execution......"+ " " +arg.getName(),true);
    }

    @Override
    public void onTestStart(ITestResult arg) {
//        System.out.println("TestCase started...."+" "+arg.getName());
        Reporter.log("TestCase started...."+" "+arg.getName(),true);

    }

    public void onTestSuccess(ITestResult arg) {
//        System.out.println("TestCase Passed....."+" "+arg.getName());
        Reporter.log("TestCase Passed....."+" "+arg.getName(),true);

    }

    public void onTestSkipped(ITestResult arg) {
//        System.out.println("TestCase skipped...."+" "+arg.getName());
        Reporter.log("TestCase skipped...."+" "+arg.getName(),true);

    }

    public void onTestFailure(ITestResult arg) {
//        System.out.println("TestCase Failed....."+" "+arg.getName());
        Reporter.log("TestCase Failed....."+" "+arg.getName(),true);

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg) {
//        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }
}
