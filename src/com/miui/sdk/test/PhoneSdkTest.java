package com.miui.sdk.test;

/**
 * Project name: miui-sdk-test
 * Package name: com.miui.sdk.test
 * Created by jiahuixing
 * Created at 2015--06-05 21:57
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

public class PhoneSdkTest extends UiAutomatorTestCase {

    //    device
    private static UiDevice uiDevice;

    @Override
    protected void setUp() throws Exception {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        super.setUp();
        if (uiDevice == null) {
            uiDevice = UiDevice.getInstance();
            initPhone();
        }
    }

    private void initPhone() throws RemoteException, UiObjectNotFoundException {
        SdkUtils.unLock(uiDevice);
        SdkUtils.clearOpenedApps(uiDevice);
    }

    public void test001_Camera() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_CAMERA);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_CAMERA)) {
            UiObject shutterButton;
            shutterButton = new UiObject(new UiSelector().className("android.widget.ImageView")
                    .resourceId("com.android.camera:id/v6_shutter_button_internal"));
            shutterButton.click();
            SdkUtils.waitFor(1);
        }

    }

    public void test002_Gallery() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_GALLERY);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_GALLERY)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_GALLERY, true);
        }
    }

    public void test003_Music() throws UiObjectNotFoundException {
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

    public void test004_Theme() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_THEME);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_THEME)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_THEME, true);
            UiObject search;
            search = new UiObject(new UiSelector().className(""));
            search.click();
            SdkUtils.waitFor(1);
        }
    }

    public void test005_AppMarket() throws UiObjectNotFoundException {
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

    public void test006_MiCloud() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_MI_CLOUD);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_MI_CLOUD)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_MI_CLOUD, true);
        }
    }

    public void test007_Mail() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_MAIL);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_MAIL)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_MAIL, true);
        }
    }

    public void test008_Updater() throws UiObjectNotFoundException {
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
            SdkUtils.immersionMenu(Constants.PACKAGE_NAME_UPDATER);
            UiObject closeImmersionMenu;
            closeImmersionMenu = new UiObject(new UiSelector().className(""));
            closeImmersionMenu.click();
            SdkUtils.waitFor(1);
            checkUpdate.click();
        }
    }

    public void test009_Clock() throws UiObjectNotFoundException {
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

    public void test010_SoundRecorder() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_SOUND_RECORDER);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_SOUND_RECORDER)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_SOUND_RECORDER, true);
            UiObject record;
            record = new UiObject(new UiSelector().className(""));
            record.click();
            Random random;
            random = new Random();
            int rnd;
            UiObject stop, pause, markPoint;
            stop = new UiObject(new UiSelector().className(""));
            pause = new UiObject(new UiSelector().className(""));
            markPoint = new UiObject(new UiSelector().className(""));
            for (int i = 0; i < 3; i++) {
                markPoint.click();
                rnd = random.nextInt(2);
                SdkUtils.waitFor(rnd);
            }
            pause.click();
            SdkUtils.waitFor(1);
            stop.click();
            SdkUtils.waitFor(1);
        }
    }

    public void test011_FMRadio() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.turnOffWlan(uiDevice);
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_FM_RADIO);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_FM_RADIO)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_FM_RADIO, true);
            SdkUtils.waitingProgressBar(10);
            UiObject fmRadio;
            fmRadio = new UiObject(new UiSelector().className(""));
            fmRadio.click();
            SdkUtils.waitFor(1);
            UiObject fmStationList;
            fmStationList = new UiObject(new UiSelector().className(""));
            fmStationList.click();
            SdkUtils.waitFor(1);
            SdkUtils.waitingProgressBar(20);
            UiObject cancel;
            cancel = new UiObject(new UiSelector().className(""));
            if (cancel.exists()) {
                cancel.click();
                SdkUtils.waitFor(1);
            }
            uiDevice.pressBack();
            SdkUtils.waitFor(1);
            SdkUtils.immersionMenu(Constants.PACKAGE_NAME_FM_RADIO);
            UiObject quit;
            quit = new UiObject(new UiSelector().className(""));
            quit.click();
            SdkUtils.waitFor(1);
        }
    }

    public void test012_FlashLight() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_FLASH_LIGHT);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_FLASH_LIGHT)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_FLASH_LIGHT, true);
        }
    }

    public void test013_Calculator() throws UiObjectNotFoundException {
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

    public void test014_FileExplorer() throws UiObjectNotFoundException {
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
            SdkUtils.immersionMenu(Constants.PACKAGE_NAME_FILE_EXPLORER);
            UiObject closeImmersionMenu;
            closeImmersionMenu = new UiObject(new UiSelector().className(""));
            closeImmersionMenu.click();
        }
    }

    public void test015_Compass() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_COMPASS);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_COMPASS)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_COMPASS, true);
            SdkUtils.swipePhone(uiDevice, Constants.LEFT, 2);
            SdkUtils.swipePhone(uiDevice, Constants.RIGHT, 1);
        }
    }

    public void test016_MiFinance() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_MI_FINANCE);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_MI_FINANCE)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_MI_FINANCE, true);
        }
    }

    public void test017_MiWallet() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_MI_WALLET);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_MI_WALLET)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_MI_WALLET, true);
        }
    }

    public void test018_DownloadManager() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_DOWNLOAD_MANAGER);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_DOWNLOAD_MANAGER)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_DOWNLOAD_MANAGER, true);
        }
    }

    public void test019_BarCode() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_BAR_CODE);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_BAR_CODE)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_BAR_CODE, true);
        }
    }

    public void test020_UserReport() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_USER_REPORTER);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_USER_REPORTER)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_USER_REPORTER, true);
        }
    }

    public void test021_SecurityCenter() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_SECURITY_CENTRE);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_SECURITY_CENTRE)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_SECURITY_CENTRE, true);
        }
    }

    public void test022_Backup() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_BACKUP);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_BACKUP)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_BACKUP, true);
        }
    }

    public void test023_Phone() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_CONTACTS);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_CONTACTS)) {
            UiObject dial;
            dial = new UiObject(new UiSelector().className("").textContains("拨号"));
            dial.click();
        }
    }

    public void test024_Contacts() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_CONTACTS);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_CONTACTS)) {
            UiObject contacts;
            contacts = new UiObject(new UiSelector().className("").textContains("联系人"));
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
            SdkUtils.waitFor(1);
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_CONTACTS, true);
            SdkUtils.waitingProgressBar(5);
        }
    }

    public void test025_YellowPage() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_CONTACTS);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_CONTACTS)) {
            UiObject yellowPage;
            yellowPage = new UiObject(new UiSelector().className("").textContains("黄页"));
            yellowPage.click();
        }
    }

    public void test026_Browser() throws UiObjectNotFoundException {
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


    public void test027_SMS() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_SMS);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_SMS)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_SMS, true);
            UiObject search;
            search = new UiObject(new UiSelector().className(""));
            search.click();
            SdkUtils.waitFor(1);
            uiDevice.pressBack();
            SdkUtils.waitFor(1);
            UiObject newSMS;
            newSMS = new UiObject(new UiSelector().className("").textContains("写短信"));
            newSMS.click();
            SdkUtils.waitFor(1);
            uiDevice.pressBack();
        }
    }

    public void test028_Calendar() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_CALENDAR);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_CALENDAR)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_CALENDAR, true);
        }
    }

    public void test029_Notes() throws UiObjectNotFoundException {
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

    public void test030_Weather() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_WEATHER);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_WEATHER)) {
            SdkUtils.immersionMenu(Constants.PACKAGE_NAME_WEATHER);
        }
    }

    public void test031_GameCenter() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_GAME_CENTER);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_GAME_CENTER)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_GAME_CENTER, true);
        }
    }

    public void test032_Video() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_VIDEO);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_VIDEO)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_VIDEO, true);
        }
    }

    public void test033_MiLife() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_MI_LIFE);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_MI_LIFE)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_MI_LIFE, true);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        super.tearDown();
        SdkUtils.clearOpenedApps(uiDevice);
    }
}
