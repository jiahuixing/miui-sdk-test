package com.miui.sdk.utils;

/**
 * Project name : miui-sdk-test
 * Package name : com.miui.sdk.utils
 * Created by jiahuixing
 * Created on 2015-06-17
 * Created at 17:00
 */

import android.graphics.Rect;
import android.os.RemoteException;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.miui.sdk.constants.Constants;

import java.io.IOException;
import java.util.Random;

public class SdkUtils {

    public static String getCurrentMethodName() {
        String currentMethodName;
        StackTraceElement[] stackTraceElement = new Throwable().getStackTrace();
        currentMethodName = stackTraceElement[1].getMethodName();
        return currentMethodName;
    }

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void debugMsg(String message) {
        System.out.println(String.format("*****************************************\n%s\n" +
                "*****************************************\n", message));
    }

    public static void clickXY(UiDevice uiDevice, int x, int y) {
        debugMsg(String.format("methodName = %s", getCurrentMethodName()));
        uiDevice.click(x, y);
    }

    public static void longClick(UiDevice uiDevice, UiObject uiObject) throws UiObjectNotFoundException {
        Rect rect = uiObject.getBounds();
        uiDevice.drag(rect.centerX(), rect.centerY(), rect.centerX(), rect.centerY(), Constants.DRAG_STEPS);
    }

    public static void swipePhone(UiDevice uiDevice, int swipeDirection, int times) {
        debugMsg(String.format("methodName = %s", getCurrentMethodName()));
        int displayWidth = uiDevice.getDisplayWidth(), displayHeight = uiDevice.getDisplayHeight();
        int startX, startY, endX, endY;
        switch (swipeDirection) {
            case Constants.UP:
                startX = displayWidth / 2;
                startY = displayHeight * 3 / 4;
                endX = displayWidth / 2;
                endY = displayHeight / 4;
                break;
            case Constants.DOWN:
                startX = displayWidth / 2;
                startY = displayHeight / 4;
                endX = displayWidth / 2;
                endY = displayHeight * 3 / 4;
                break;
            case Constants.LEFT:
                startX = displayWidth * 3 / 4;
                startY = displayHeight / 2;
                endX = displayWidth / 4;
                endY = displayHeight / 2;
                break;
            case Constants.RIGHT:
                startX = displayHeight / 4;
                startY = displayHeight / 2;
                endX = displayWidth * 3 / 4;
                endY = displayHeight / 2;
                break;
            default:
                startX = displayWidth / 2;
                startY = displayHeight * 3 / 4;
                endX = displayWidth / 2;
                endY = displayHeight / 4;
                break;
        }
        times = (times > 1) ? times : 1;
        for (int i = 0; i < times; i++) {
            uiDevice.drag(startX, startY, endX, endY, Constants.DRAG_STEPS);
        }
        waitFor(1);
    }

    public static void unLock(UiDevice uiDevice) throws RemoteException {
        debugMsg(String.format("methodName = %s", getCurrentMethodName()));
        if (!uiDevice.isScreenOn()) {
            uiDevice.wakeUp();
        }
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_KEYGUARD)) {
            int displayWidth = uiDevice.getDisplayWidth(), displayHeight = uiDevice.getDisplayHeight();
            int startX, startY, endX, endY;
            startX = displayWidth / 2;
            startY = displayHeight * 3 / 4;
            endX = displayWidth / 2;
            endY = displayHeight / 4;
            uiDevice.drag(startX, startY, endX, endY, Constants.DRAG_STEPS);
            waitFor(1);
        }
    }

    public static void turnOnWlan(UiDevice uiDevice) throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", getCurrentMethodName()));
        launchApp(uiDevice, Constants.ACTIVITY_NAME_SETTINGS);
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

    public static void turnOffWlan(UiDevice uiDevice) throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", getCurrentMethodName()));
        launchApp(uiDevice, Constants.ACTIVITY_NAME_SETTINGS);
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

    public static void immersionMenu() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", getCurrentMethodName()));
        UiObject immersionMenu;
        immersionMenu = new UiObject(new UiSelector().className(""));
        immersionMenu.click();
        waitFor(1);
    }

    public static void alertDialog(String packageName, boolean confirmOrCancel) throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", getCurrentMethodName()));
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

    public static String getConfirmButtonText(String packageName) {
        debugMsg(String.format("methodName = %s", getCurrentMethodName()));
        String confirmButtonText = "确定";
        if (packageName.equals(Constants.PACKAGE_NAME_CAMERA)) {
            confirmButtonText = "开始";
        } else if (packageName.equals(Constants.PACKAGE_NAME_SETTINGS)) {
            confirmButtonText = "";
        } else if (packageName.equals(Constants.PACKAGE_NAME_CLOCK)) {
            confirmButtonText = "舍弃";
        } else if (packageName.equals(Constants.PACKAGE_NAME_SOUND_RECORDER)) {
            confirmButtonText = "确定";
        } else if (packageName.equals(Constants.PACKAGE_NAME_FM_RADIO)) {
            confirmButtonText = "同意";
        } else if (packageName.equals(Constants.PACKAGE_NAME_COMPASS)) {
            confirmButtonText = "同意";
        }
        return confirmButtonText;
    }

    public static String getCancelButtonText(String packageName) {
        debugMsg(String.format("methodName = %s", getCurrentMethodName()));
        String cancelButtonText = "取消";
        if (packageName.equals(Constants.PACKAGE_NAME_CAMERA)) {
            cancelButtonText = "开始";
        } else if (packageName.equals(Constants.PACKAGE_NAME_SETTINGS)) {
            cancelButtonText = "";
        } else if (packageName.equals(Constants.PACKAGE_NAME_CLOCK)) {
            cancelButtonText = "取消";
        } else if (packageName.equals(Constants.PACKAGE_NAME_SOUND_RECORDER)) {
            cancelButtonText = "取消";
        } else if (packageName.equals(Constants.PACKAGE_NAME_FM_RADIO)) {
            cancelButtonText = "拒绝";
        } else if (packageName.equals(Constants.PACKAGE_NAME_COMPASS)) {
            cancelButtonText = "退出";
        }
        return cancelButtonText;
    }

    public static void waitingProgressBar(int waitingTime) {
        debugMsg(String.format("methodName = %s", getCurrentMethodName()));
        UiObject progressBar;
        int times = 0;
        while (true) {
            progressBar = new UiObject(new UiSelector().className("android.widget.ProgressBar"));
            if (progressBar.exists()) {
                times += 1;
                if (times >= waitingTime) {
                    break;
                }
                waitFor(1);
            } else {
                break;
            }
        }
    }

    public static void enterMultiChoiceMode(UiDevice uiDevice) throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", getCurrentMethodName()));
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
            SdkUtils.longClick(uiDevice, child);
        }
    }

    public static void launchApp(UiDevice uiDevice, String activityName) {
        debugMsg(String.format("activityName = %s", activityName));
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

    public static void clearOpenedApps(UiDevice uiDevice) throws UiObjectNotFoundException, RemoteException {
        debugMsg(String.format("methodName = %s", getCurrentMethodName()));
        uiDevice.pressHome();
        uiDevice.pressRecentApps();
        waitFor(1);
        UiObject clearButton;
        clearButton = new UiObject(new UiSelector().className("android.widget.ImageView")
                .resourceId("com.android.systemui:id/clearButton"));
        clearButton.click();
        waitFor(2);
    }

}
