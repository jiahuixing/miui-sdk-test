package com.miui.sdk.test;
/**
 * Project name : miui-sdk-test
 * Package name : com.miui.sdk.test
 * Created by jiahuixing
 * Created on 2015-06-09
 * Created at 20:02
 */

import android.os.RemoteException;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.sdk.constants.Constants;
import com.miui.sdk.utils.SdkUtils;

import java.util.Random;

public class PadSdkTest extends UiAutomatorTestCase {
    //    device
    private static UiDevice uiDevice;

    @Override
    protected void setUp() throws Exception {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        super.setUp();
        if (uiDevice == null) {
            uiDevice = UiDevice.getInstance();
            initPad();
        }
    }

    private void initPad() throws RemoteException, UiObjectNotFoundException {
        SdkUtils.unLock(uiDevice);
        SdkUtils.clearOpenedApps(uiDevice);
    }

    public void test001_Camera() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_CAMERA);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_CAMERA)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_CAMERA, true);
            UiObject shutterButton;
            shutterButton = new UiObject(new UiSelector().className("android.widget.ImageView")
                    .resourceId("com.android.camera:id/v6_shutter_button_internal"));
            shutterButton.click();
            SdkUtils.waitFor(1);
        }
    }

    public void test002_Contacts() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_CONTACTS);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_CONTACTS)) {
            UiObject dial, contacts, yellowPage;
            dial = new UiObject(new UiSelector().className("").textContains("拨号"));
            contacts = new UiObject(new UiSelector().className("").textContains("联系人"));
            yellowPage = new UiObject(new UiSelector().className("").textContains("黄页"));
            dial.click();
            SdkUtils.waitFor(1);
            UiObject showDial;
            showDial = new UiObject(new UiSelector().className("").textContains("拨号"));
            if (showDial.exists()) {
                showDial.click();
                SdkUtils.waitFor(1);
            }
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
            contacts.click();
            SdkUtils.waitFor(1);
            UiObject newContact;
            newContact = new UiObject(new UiSelector().className(""));
            newContact.click();
            SdkUtils.waitFor(1);
            UiObject cancel;
            cancel = new UiObject(new UiSelector().className("").textContains("取消"));
            cancel.click();
            SdkUtils.waitFor(1);
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_CONTACTS, true);
            yellowPage.click();
            SdkUtils.waitFor(1);
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_CONTACTS, true);
            SdkUtils.waitingProgressBar(5);
        }
    }

    public void test003_Calendar() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_CALENDAR);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_CALENDAR)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_CALENDAR, true);
        }
    }

    public void test004_Weather() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_WEATHER);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_WEATHER)) {
            SdkUtils.immersionMenu();
        }
    }

    public void test005_Clock() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_CLOCK);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_CLOCK)) {
            UiObject alarmClock, clock, stopwatch, timer;
            alarmClock = new UiObject(new UiSelector().className("").textContains("闹钟"));
            clock = new UiObject(new UiSelector().className("").textContains("时钟"));
            stopwatch = new UiObject(new UiSelector().className("").textContains("秒表"));
            timer = new UiObject(new UiSelector().className("").textContains("计时器"));
            clock.click();
            SdkUtils.waitFor(1);
            stopwatch.click();
            SdkUtils.waitFor(1);
            timer.click();
            SdkUtils.waitFor(1);
            alarmClock.click();
            SdkUtils.waitFor(1);
            UiObject addAlarmClock, clockSettings;
            addAlarmClock = new UiObject(new UiSelector().className("").textContains("添加"));
            clockSettings = new UiObject(new UiSelector().className("").textContains("设置"));
            addAlarmClock.click();
            SdkUtils.waitFor(1);
            UiObject cancel;
            cancel = new UiObject(new UiSelector().className("").textContains("取消"));
            cancel.click();
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_CLOCK, true);
            SdkUtils.waitFor(1);
            clockSettings.click();
            SdkUtils.waitFor(1);
            uiDevice.pressBack();
        }
    }

    public void test006_Mail() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_MAIL);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_MAIL)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_MAIL, true);
        }
    }

    public void test007_Calculator() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_CALCULATOR);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_CALCULATOR)) {
            UiObject calculatorMode;
            calculatorMode = new UiObject(new UiSelector().className(""));
            UiObject panel;
            int childCount;
            Random random;
            random = new Random();
            int rnd;
            UiObject calculatorButton;
            panel = new UiObject(new UiSelector().className(""));
            childCount = panel.getChildCount();
            for (int i = 0; i < childCount; i++) {
                rnd = random.nextInt(childCount);
                calculatorButton = panel.getChild(new UiSelector().className("").index(rnd));
                calculatorButton.click();
            }
            calculatorMode.click();
            SdkUtils.waitFor(1);
            panel = new UiObject(new UiSelector().className(""));
            childCount = panel.getChildCount();
            for (int i = 0; i < childCount; i++) {
                rnd = random.nextInt(childCount);
                calculatorButton = panel.getChild(new UiSelector().className("").index(rnd));
                calculatorButton.click();
            }
        }
    }

    public void test008_Notes() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_NOTES);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_NOTES)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_NOTES, false);
            UiObject richEditor;
            while (true) {
                richEditor = new UiObject(new UiSelector().className(""));
                if (richEditor.exists()) {
                    uiDevice.pressBack();
                    SdkUtils.waitFor(1);
                } else {
                    break;
                }
            }
        }
    }

    public void test009_MiCloud() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_MI_CLOUD);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_MI_CLOUD)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_MI_CLOUD, true);
        }
    }

    public void test010_AppMarket() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_APP_MARKET);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_APP_MARKET)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_APP_MARKET, true);
            UiObject competitive, ranking, assortment, myMarket;
            competitive = new UiObject(new UiSelector().className("").textContains("精品"));
            ranking = new UiObject(new UiSelector().className("").textContains("排行"));
            assortment = new UiObject(new UiSelector().className("").textContains("分类"));
            myMarket = new UiObject(new UiSelector().className("").textContains("我的"));
            competitive.click();
            SdkUtils.waitFor(1);
            ranking.click();
            SdkUtils.waitFor(1);
            assortment.click();
            SdkUtils.waitFor(1);
            myMarket.click();
            SdkUtils.waitFor(1);
        }
    }

    public void test011_GameCenter() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_GAME_CENTER);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_GAME_CENTER)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_GAME_CENTER, true);
        }
    }

    public void test012_FileExplorer() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_FILE_EXPLORER);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_FILE_EXPLORER)) {
            UiObject fileList;
            fileList = new UiObject(new UiSelector().className(""));
            fileList.click();
            SdkUtils.waitingProgressBar(5);
            SdkUtils.enterMultiChoiceMode(uiDevice);
            UiObject cancel;
            cancel = new UiObject(new UiSelector().className(""));
            cancel.click();
            SdkUtils.waitFor(1);
            SdkUtils.immersionMenu();
            UiObject closeImmersionMenu;
            closeImmersionMenu = new UiObject(new UiSelector().className(""));
            closeImmersionMenu.click();
        }
    }

    public void test013_DownloadManager() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_DOWNLOAD_MANAGER);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_DOWNLOAD_MANAGER)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_DOWNLOAD_MANAGER, true);
        }
    }

    public void test014_Updater() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_UPDATER);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_UPDATER)) {
            UiObject checkUpdate;
            while (true) {
                checkUpdate = new UiObject(new UiSelector().className(""));
                if (!checkUpdate.exists()) {
                    SdkUtils.debugMsg("正在检查更新");
                    SdkUtils.waitFor(1);
                } else {
                    break;
                }
            }
            checkUpdate.click();
            while (true) {
                checkUpdate = new UiObject(new UiSelector().className(""));
                if (!checkUpdate.exists()) {
                    SdkUtils.debugMsg("正在检查更新");
                    SdkUtils.waitFor(1);
                } else {
                    break;
                }
            }
            SdkUtils.immersionMenu();
            UiObject closeImmersionMenu;
            closeImmersionMenu = new UiObject(new UiSelector().className(""));
            closeImmersionMenu.click();
            SdkUtils.waitFor(1);
            checkUpdate.click();
        }
    }

    public void test015_UserReport() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_USER_REPORTER);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_USER_REPORTER)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_USER_REPORTER, true);
        }
    }

    public void test016_Gallery() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_GALLERY);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_GALLERY)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_GALLERY, true);
        }
    }

    public void test017_Video() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_VIDEO);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_VIDEO)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_VIDEO, true);
        }
    }

    public void test018_Music() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_MUSIC);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_MUSIC)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_MUSIC, true);
            UiObject onlineMusic, myMusic;
            onlineMusic = new UiObject(new UiSelector().className("").textContains("音乐库"));
            myMusic = new UiObject(new UiSelector().className("").textContains("我的音乐"));
            onlineMusic.click();
            SdkUtils.waitFor(1);
            SdkUtils.waitingProgressBar(5);
            myMusic.click();
            SdkUtils.waitFor(1);
            UiObject search;
            search = new UiObject(new UiSelector().className(""));
            search.click();
            SdkUtils.waitFor(1);
        }
    }

    public void test019_Browser() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_BROWSER);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_BROWSER)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_BROWSER, true);
            UiObject more;
            more = new UiObject(new UiSelector().className(""));
            more.click();
            SdkUtils.waitFor(1);
            UiObject quit;
            quit = new UiObject(new UiSelector().className(""));
            quit.click();
        }
    }

    public void test020_MiFinance() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_MI_FINANCE);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_MI_FINANCE)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_MI_FINANCE, true);
        }
    }

    public void test021_BarCode() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_BAR_CODE);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_BAR_CODE)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_BAR_CODE, true);
        }
    }

    public void test022_Backup() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_BACKUP);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_BACKUP)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_BACKUP, true);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        super.tearDown();
        SdkUtils.clearOpenedApps(uiDevice);
    }

}
