package com.miui.sdk.test;

/**
 * Project name : miui-sdk-test
 * Package name : com.miui.sdk.test
 * Created by jiahuixing
 * Created on 2015-06-24
 * Created at 16:51
 */


import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.sdk.constants.Constants;
import com.miui.sdk.utils.SdkUtils;

public class LaunchTimeTest extends UiAutomatorTestCase {
    //    device
    private static UiDevice uiDevice;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        if (uiDevice == null) {
            uiDevice = UiDevice.getInstance();
        }
        SdkUtils.unLock(uiDevice);
    }

    public void test001_SoundRecorder() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_SOUND_RECORDER);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_SOUND_RECORDER)) {
//            SdkUtils.alertDialog(Constants.PACKAGE_NAME_SOUND_RECORDER, true);
//            UiObject record;
//            record = new UiObject(new UiSelector().className(""));
//            record.click();
//            Random random;
//            random = new Random();
//            int rnd;
//            UiObject stop, pause, markPoint;
//            stop = new UiObject(new UiSelector().className(""));
//            pause = new UiObject(new UiSelector().className(""));
//            markPoint = new UiObject(new UiSelector().className(""));
//            for (int i = 0; i < 3; i++) {
//                markPoint.click();
//                rnd = random.nextInt(2);
//                SdkUtils.waitFor(rnd);
//            }
//            pause.click();
//            SdkUtils.waitFor(1);
//            stop.click();
//            SdkUtils.waitFor(1);
            SdkUtils.debugMsg("1");
        }
    }

    public void test002_Calculator() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_CALCULATOR);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_CALCULATOR)) {
////            SdkUtils.alertDialog(Constants.PACKAGE_NAME_CALCULATOR, true);
//            UiObject calculatorMode;
//            calculatorMode = new UiObject(new UiSelector().className(""));
//            UiObject panel;
//            int childCount;
//            Random random;
//            random = new Random();
//            int rnd;
//            UiObject calculatorButton;
//            panel = new UiObject(new UiSelector().className(""));
//            childCount = panel.getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                rnd = random.nextInt(childCount);
//                calculatorButton = panel.getChild(new UiSelector().className("").index(rnd));
//                calculatorButton.click();
//            }
//            calculatorMode.click();
//            SdkUtils.waitFor(1);
//            panel = new UiObject(new UiSelector().className(""));
//            childCount = panel.getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                rnd = random.nextInt(childCount);
//                calculatorButton = panel.getChild(new UiSelector().className("").index(rnd));
//                calculatorButton.click();
//            }
            SdkUtils.debugMsg("2");
        }
    }

    public void test003_FMRadio() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_FM_RADIO);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_FM_RADIO)) {
//            SdkUtils.alertDialog(Constants.PACKAGE_NAME_FM_RADIO, true);
            SdkUtils.debugMsg("3");
        }
    }

    public void test004_Compass() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_COMPASS);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_COMPASS)) {
//            SdkUtils.alertDialog(Constants.PACKAGE_NAME_COMPASS, true);
            SdkUtils.debugMsg("4");
        }
    }

    public void test005_Notes() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_NOTES);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_SOUND_RECORDER)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_NOTES, true);
//            SdkUtils.debugMsg("5");
        }
    }

    public void test006_Backup() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_BACKUP);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_BACKUP)) {
//            SdkUtils.alertDialog(Constants.PACKAGE_NAME_BACKUP, true);
            SdkUtils.debugMsg("6");
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        SdkUtils.clearOpenedApps(uiDevice);
    }
}
