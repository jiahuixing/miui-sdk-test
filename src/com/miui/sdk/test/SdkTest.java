package com.miui.sdk.test;

/**
 * Project name: miui-sdk-test
 * Package name: com.miui.sdk.test
 * Created by jiahuixing
 * Created at 2015--06-05 21:57
 */

import android.os.RemoteException;
import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import java.io.IOException;
import java.util.Random;

public class SdkTest extends UiAutomatorTestCase {

    private static UiDevice uiDevice;

    //    phone property
    private static int mHeight;
    private static int mWidth;
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    private static final int DRAG_STEPS = 10;

    //    package names
    private static final String PACKAGE_NAME_KEYGUARD = "com.android.keyguard";
    private static final String PACKAGE_NAME_SETTINGS = "com.android.settings";
    private static final String PACKAGE_NAME_CAMERA = "com.android.camera";
    private static final String PACKAGE_NAME_CONTACTS = "com.android.contacts";
    private static final String PACKAGE_NAME_SMS = "com.android.sms";
    private static final String PACKAGE_NAME_NOTES = "com.miui.notes";
    private static final String PACKAGE_NAME_WEATHER = "com.miui.weather";

    //    activity names
    private static final String ACTIVITY_NAME_SETTINGS = "";
    private static final String ACTIVITY_NAME_CAMERA = "com.android.camera/.Camera";
    private static final String ACTIVITY_NAME_GALLERY = "";
    private static final String ACTIVITY_NAME_MUSIC = "";
    private static final String ACTIVITY_NAME_THEME = "";
    private static final String ACTIVITY_NAME_APP_MARKET = "";
    private static final String ACTIVITY_NAME_MAIL = "";
    private static final String ACTIVITY_NAME_UPDATER = "";
    private static final String ACTIVITY_NAME_CLOCK = "";
    private static final String ACTIVITY_NAME_SOUND_RECORDER = "";
    private static final String ACTIVITY_NAME_FM_RADIO = "";
    private static final String ACTIVITY_NAME_CALCULATOR = "";
    private static final String ACTIVITY_NAME_FILE_EXPLORER = "";
    private static final String ACTIVITY_NAME_COMPASS = "";
    private static final String ACTIVITY_NAME_SECURITY_CENTRE = "";
    private static final String ACTIVITY_NAME_CONTACTS = "";
    private static final String ACTIVITY_NAME_SMS = "";
    private static final String ACTIVITY_NAME_BROWSER = "";
    private static final String ACTIVITY_NAME_WEATHER = "";
    private static final String ACTIVITY_NAME_NOTES = "";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        if (uiDevice == null) {
            uiDevice = UiDevice.getInstance();
        }
        if (mHeight == 0 || mWidth == 0) {
            mHeight = uiDevice.getDisplayHeight();
            mWidth = uiDevice.getDisplayWidth();
        }
    }

    private void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void debugMsg(String message) {
        System.out.println(String.format("*****************************************\n%s\n" +
                "*****************************************\n", message));
    }

    private void clickXY(int x, int y) {
        uiDevice.click(x, y);
    }

    private void swipePhone(int swipeDirection, int times) {
        int startX, startY, endX, endY;
        switch (swipeDirection) {
            case UP:
                startX = mWidth / 2;
                startY = mHeight * 3 / 4;
                endX = mWidth / 2;
                endY = mHeight / 4;
                break;
            case DOWN:
                startX = mWidth / 2;
                startY = mHeight / 4;
                endX = mWidth / 2;
                endY = mHeight * 3 / 4;
                break;
            case LEFT:
                startX = mWidth * 3 / 4;
                startY = mHeight / 2;
                endX = mWidth / 4;
                endY = mHeight / 2;
                break;
            case RIGHT:
                startX = mHeight / 4;
                startY = mHeight / 2;
                endX = mWidth * 3 / 4;
                endY = mHeight / 2;
                break;
            default:
                startX = mWidth / 2;
                startY = mHeight * 3 / 4;
                endX = mWidth / 2;
                endY = mHeight / 4;
                break;
        }
        times = (times > 0) ? times : 1;
        for (int i = 0; i < times; i++) {
            uiDevice.drag(startX, startY, endX, endY, DRAG_STEPS);
        }
        waitFor(1);
    }

    private void unlock() throws RemoteException {
        int startX, startY, endX, endY;
        if (!uiDevice.isScreenOn()) {
            uiDevice.wakeUp();
        }
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_KEYGUARD)) {
            startX = mWidth / 2;
            startY = mHeight * 3 / 4;
            endX = mWidth / 2;
            endY = mHeight / 4;
            uiDevice.drag(startX, startY, endX, endY, DRAG_STEPS);
            waitFor(1);
        }
    }

    private void turnOnWlan() throws UiObjectNotFoundException {
        launchApp(ACTIVITY_NAME_SETTINGS);
        UiObject wlanSettings;
        wlanSettings = new UiObject(new UiSelector().className("android.widget.TextView").text("WLAN"));
        wlanSettings.click();
        waitFor(1);
        UiObject wlanSwitch;
        wlanSwitch = new UiObject(new UiSelector().className("android.widget.CheckBox")
                .resourceId("android:id/checkbox"));
        if (!wlanSwitch.isChecked()) {
            wlanSwitch.click();
            waitFor(1);
        } else {
            UiObject connectedWlan, connectedSummary;
            connectedWlan = new UiObject(new UiSelector().className("android.widget.CheckedTextView")
                    .resourceId("android:id/title"));
            while (true) {
                connectedSummary = new UiObject(new UiSelector().className("android.widget.TextView")
                        .resourceId("android:id/summary").textContains("已连接"));
                if (connectedWlan.exists() && connectedSummary.exists()) {
                    break;
                } else {
                    debugMsg("Wlan not connect yet.");
                    waitFor(2);
                }
            }
            uiDevice.pressHome();
            waitFor(1);
        }
        waitFor(1);
    }

    private void turnOffWlan() throws UiObjectNotFoundException {
        launchApp(ACTIVITY_NAME_SETTINGS);
        UiObject wlanSettings;
        wlanSettings = new UiObject(new UiSelector().className("android.widget.TextView")
                .text("WLAN"));
        wlanSettings.click();
        waitFor(1);
        UiObject wlanSwitch;
        wlanSwitch = new UiObject(new UiSelector().className("android.widget.CheckBox")
                .resourceId("android:id/checkbox"));
        if (wlanSwitch.isChecked()) {
            wlanSwitch.click();
            waitFor(1);
        }
        uiDevice.pressHome();
        waitFor(1);
    }

    private void immersionMenu() throws UiObjectNotFoundException {
        UiObject immersionMenu;
        immersionMenu = new UiObject(new UiSelector().className(""));
        immersionMenu.click();
        waitFor(1);
    }

    private void alertDialog(String packageName, boolean confirmOrCancel) throws UiObjectNotFoundException {
        UiObject alertTitle;
        alertTitle = new UiObject(new UiSelector().className("miui:id/alertTitle"));
        UiObject confirm, cancel;
        String confirmButtonText = getConfirmButtonText(packageName);
        String cancelButtonText = getCancelButtonText(packageName);
        if (alertTitle.exists()) {
            confirm = new UiObject(new UiSelector().className("android.widget.Button").textContains(confirmButtonText));
            cancel = new UiObject(new UiSelector().className("android.widget.Button").textContains(cancelButtonText));
            if (confirmOrCancel) {
                confirm.click();
            } else {
                cancel.click();
            }
            waitFor(1);
        }
    }

    private String getConfirmButtonText(String packageName) {
        String confirmButtonText = "确定";
        if (packageName.equals(PACKAGE_NAME_CAMERA)) {
            confirmButtonText = "开始";
        } else if (packageName.equals(PACKAGE_NAME_SETTINGS)) {
            confirmButtonText = "";
        }
        return confirmButtonText;
    }

    private String getCancelButtonText(String packageName) {
        String cancelButtonText = "取消";
        if (packageName.equals(PACKAGE_NAME_CAMERA)) {
            cancelButtonText = "开始";
        } else if (packageName.equals(PACKAGE_NAME_SETTINGS)) {
            cancelButtonText = "";
        }
        return cancelButtonText;
    }

    private void progressBar() {
        UiObject progressBar;
        int times = 0;
        while (true) {
            progressBar = new UiObject(new UiSelector().className("android.widget.ProgressBar"));
            if (progressBar.exists()) {
                if (times >= 5) {
                    break;
                }
                times += 1;
                waitFor(1);
            } else {
                break;
            }
        }
    }

    private void multiChoiseMode() throws UiObjectNotFoundException {
        UiObject listView;
        listView = new UiObject(new UiSelector().className("android.widget.ListView"));
        int childCounts;
        childCounts = listView.getChildCount();
        Random random = new Random();
        int rnd;
        UiObject child;
        if (childCounts > 0) {
            rnd = random.nextInt(childCounts);
            child = listView.getChild(new UiSelector().className("android.widget.RelativeLayout").index(rnd));
            if (child.isLongClickable()) {
                child.longClick();
                waitFor(1);
            }
        }
    }

    private void launchApp(String activityName) {
        uiDevice.pressHome();
        try {
            String sCommand = String.format("am start -n %s", activityName);
            Runtime.getRuntime().exec(sCommand).wait();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitFor(2);
    }

    private void clearRecentOpenApps() throws UiObjectNotFoundException, RemoteException {
        uiDevice.pressHome();
        uiDevice.pressRecentApps();
        waitFor(1);
        UiObject clearButton;
        clearButton = new UiObject(new UiSelector().className("android.widget.ImageView")
                .resourceId("com.android.systemui:id/clearButton"));
        clearButton.click();
        waitFor(2);
    }

    public void test001_Camera() throws UiObjectNotFoundException {
        debugMsg(String.format("testName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_CAMERA);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_CAMERA)) {
            UiObject shutterButton;
            shutterButton = new UiObject(new UiSelector().className("android.widget.ImageView")
                    .resourceId("com.android.camera:id/v6_shutter_button_internal"));
            shutterButton.click();
            waitFor(1);
        }

    }

    public void test002_Gallery() {
        launchApp(ACTIVITY_NAME_GALLERY);
    }

    public void test003_Music() {
        launchApp(ACTIVITY_NAME_MUSIC);
    }

    public void test004_Theme() {
        launchApp(ACTIVITY_NAME_THEME);
    }

    public void test005_AppMarket() {
        launchApp(ACTIVITY_NAME_APP_MARKET);
    }

    public void test006_Mail() {
        launchApp(ACTIVITY_NAME_MAIL);
    }

    public void test007_Updater() {
        launchApp(ACTIVITY_NAME_UPDATER);
    }

    public void test008_Clock() {
        launchApp(ACTIVITY_NAME_CLOCK);
    }

    public void test008_SoundRecorder() {
        launchApp(ACTIVITY_NAME_SOUND_RECORDER);
    }

    public void test009_FMRadio() {
        launchApp(ACTIVITY_NAME_FM_RADIO);
    }

    public void test010_Calculator() {
        launchApp(ACTIVITY_NAME_CALCULATOR);
    }

    public void test011_FileExplorer() {
        launchApp(ACTIVITY_NAME_FILE_EXPLORER);
    }

    public void test012_Compass() {
        launchApp(ACTIVITY_NAME_COMPASS);
    }

    public void test013_SecurityCenter() {
        launchApp(ACTIVITY_NAME_SECURITY_CENTRE);
    }

    public void test014_Contacts() throws UiObjectNotFoundException {
        launchApp(ACTIVITY_NAME_CONTACTS);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_CONTACTS)) {
            swipePhone(RIGHT, 1);
            UiObject diaPanel;
            diaPanel = new UiObject(new UiSelector().className(""));
            int childCounts;
            childCounts = diaPanel.getChildCount();
            Random random = new Random();
            int rnd;
            UiObject diaButton;
            for (int i = 0; i < childCounts; i++) {
                rnd = random.nextInt(childCounts);
                diaButton = diaPanel.getChild(new UiSelector().className("").index(rnd));
                diaButton.click();
            }
            swipePhone(LEFT, 3);
            alertDialog(PACKAGE_NAME_CONTACTS, true);
            progressBar();
        }
    }

    public void test015_SMS() throws UiObjectNotFoundException {
        launchApp(ACTIVITY_NAME_SMS);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_SMS)) {
            UiObject newSMS;
            newSMS = new UiObject(new UiSelector().className(""));
            newSMS.click();
            waitFor(1);
        }
    }

    public void test016_Browser() {
        launchApp(ACTIVITY_NAME_BROWSER);
    }

    public void test017_Weather() throws UiObjectNotFoundException {
        launchApp(ACTIVITY_NAME_WEATHER);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_WEATHER)) {
            immersionMenu();
        }
    }

    public void test018_Notes() throws UiObjectNotFoundException {
        launchApp(ACTIVITY_NAME_NOTES);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_NOTES)) {
            alertDialog(PACKAGE_NAME_NOTES, false);
            UiObject richEditor;
            while (true) {
                richEditor = new UiObject(new UiSelector().className(""));
                if (richEditor.exists()) {
                    uiDevice.pressBack();
                    waitFor(1);
                } else {
                    break;
                }
            }
        }
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        clearRecentOpenApps();
    }
}
