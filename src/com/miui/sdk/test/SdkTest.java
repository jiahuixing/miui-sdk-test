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
    private static final String PACKAGE_NAME_GALLERY = "com.miui.gallery";
    private static final String PACKAGE_NAME_MUSIC = "com.miui.music";
    private static final String PACKAGE_NAME_THEME = "com.miui.theme";
    private static final String PACKAGE_NAME_APP_MARKET = "com.miui.market";
    private static final String PACKAGE_NAME_MAIL = "com.android.mail";
    private static final String PACKAGE_NAME_UPDATER = "com.miui.updater";
    private static final String PACKAGE_NAME_CLOCK = "com.miui.clock";
    private static final String PACKAGE_NAME_SOUND_RECORDER = "com.android.soundrecorder";
    private static final String PACKAGE_NAME_FM_RADIO = "com.android.fmradio";
    private static final String PACKAGE_NAME_CALCULATOR = "com.android.calculator2";
    private static final String PACKAGE_NAME_FILE_EXPLORER = "com.android.fileexplorer";
    private static final String PACKAGE_NAME_COMPASS = "com.miui.compass";
    private static final String PACKAGE_NAME_SECURITY_CENTRE = "com.android.security.centre";
    private static final String PACKAGE_NAME_CONTACTS = "com.android.contacts";
    private static final String PACKAGE_NAME_SMS = "com.android.sms";
    private static final String PACKAGE_NAME_BROWSER = "com.android.browser";
    private static final String PACKAGE_NAME_WEATHER = "com.miui.weather";
    private static final String PACKAGE_NAME_NOTES = "com.miui.notes";

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
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
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
        times = (times > 1) ? times : 1;
        for (int i = 0; i < times; i++) {
            uiDevice.drag(startX, startY, endX, endY, DRAG_STEPS);
        }
        waitFor(1);
    }

    private void unLock() throws RemoteException {
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
        switch (packageName) {
            case PACKAGE_NAME_CAMERA:
                confirmButtonText = "开始";
                break;
            case PACKAGE_NAME_SETTINGS:
                confirmButtonText = "";
                break;
            case PACKAGE_NAME_CLOCK:
                confirmButtonText = "舍弃";
                break;
            case PACKAGE_NAME_SOUND_RECORDER:
                confirmButtonText = "确定";
                break;
            case PACKAGE_NAME_FM_RADIO:
                confirmButtonText = "同意";
                break;
            case PACKAGE_NAME_COMPASS:
                confirmButtonText = "同意";
                break;
        }
        return confirmButtonText;
    }

    private String getCancelButtonText(String packageName) {
        String cancelButtonText = "取消";
        switch (packageName) {
            case PACKAGE_NAME_CAMERA:
                cancelButtonText = "开始";
                break;
            case PACKAGE_NAME_SETTINGS:
                cancelButtonText = "";
                break;
            case PACKAGE_NAME_CLOCK:
                cancelButtonText = "取消";
                break;
            case PACKAGE_NAME_SOUND_RECORDER:
                cancelButtonText = "取消";
                break;
            case PACKAGE_NAME_FM_RADIO:
                cancelButtonText = "拒绝";
                break;
            case PACKAGE_NAME_COMPASS:
                cancelButtonText = "退出";
                break;
        }
        return cancelButtonText;
    }

    private void waitingProgressBar(int waitingTime) {
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

    private void enterMultiChoiceMode() throws UiObjectNotFoundException {
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
        debugMsg(String.format("activityName = %s", activityName));
        uiDevice.pressHome();
        try {
            String sCommand = String.format("am start -n %s", activityName);
            Runtime.getRuntime().exec(sCommand).wait();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        waitFor(2);
    }

    private void clearOpenedApps() throws UiObjectNotFoundException, RemoteException {
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
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_CAMERA);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_CAMERA)) {
            UiObject shutterButton;
            shutterButton = new UiObject(new UiSelector().className("android.widget.ImageView")
                    .resourceId("com.android.camera:id/v6_shutter_button_internal"));
            shutterButton.click();
            waitFor(1);
        }

    }

    public void test002_Gallery() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_GALLERY);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_GALLERY)) {
            alertDialog(PACKAGE_NAME_GALLERY, true);
        }
    }

    public void test003_Music() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_MUSIC);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_MUSIC)) {
            alertDialog(PACKAGE_NAME_MUSIC, true);
            UiObject onlineMusic, myMusic;
            onlineMusic = new UiObject(new UiSelector().className("").textContains("音乐库"));
            myMusic = new UiObject(new UiSelector().className("").textContains("我的音乐"));
            onlineMusic.click();
            waitFor(1);
            waitingProgressBar(5);
            myMusic.click();
            waitFor(1);
            UiObject search;
            search = new UiObject(new UiSelector().className(""));
            search.click();
            waitFor(1);
        }
    }

    public void test004_Theme() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_THEME);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_THEME)) {
            alertDialog(PACKAGE_NAME_THEME, true);
            UiObject search;
            search = new UiObject(new UiSelector().className(""));
            search.click();
            waitFor(1);
        }
    }

    public void test005_AppMarket() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_APP_MARKET);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_APP_MARKET)) {
            alertDialog(PACKAGE_NAME_APP_MARKET, true);
            UiObject competitive, ranking, assortment, myMarket;
            competitive = new UiObject(new UiSelector().className("").textContains("精品"));
            ranking = new UiObject(new UiSelector().className("").textContains("排行"));
            assortment = new UiObject(new UiSelector().className("").textContains("分类"));
            myMarket = new UiObject(new UiSelector().className("").textContains("我的"));
            competitive.click();
            waitFor(1);
            ranking.click();
            waitFor(1);
            assortment.click();
            waitFor(1);
            myMarket.click();
            waitFor(1);
        }
    }

    public void test006_Mail() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_MAIL);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_MAIL)) {
            alertDialog(PACKAGE_NAME_MAIL, true);
        }
    }

    public void test007_Updater() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_UPDATER);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_UPDATER)) {
            UiObject checkUpdate;
            while (true) {
                checkUpdate = new UiObject(new UiSelector().className(""));
                if (!checkUpdate.exists()) {
                    debugMsg("正在检查更新");
                    waitFor(1);
                } else {
                    break;
                }
            }
            checkUpdate.click();
            while (true) {
                checkUpdate = new UiObject(new UiSelector().className(""));
                if (!checkUpdate.exists()) {
                    debugMsg("正在检查更新");
                    waitFor(1);
                } else {
                    break;
                }
            }
            immersionMenu();
            UiObject closeImmersionMenu;
            closeImmersionMenu = new UiObject(new UiSelector().className(""));
            closeImmersionMenu.click();
            waitFor(1);
            checkUpdate.click();
        }
    }

    public void test008_Clock() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_CLOCK);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_CLOCK)) {
            UiObject alarmClock, clock, stopwatch, timer;
            alarmClock = new UiObject(new UiSelector().className("").textContains("闹钟"));
            clock = new UiObject(new UiSelector().className("").textContains("时钟"));
            stopwatch = new UiObject(new UiSelector().className("").textContains("秒表"));
            timer = new UiObject(new UiSelector().className("").textContains("计时器"));
            clock.click();
            waitFor(1);
            stopwatch.click();
            waitFor(1);
            timer.click();
            waitFor(1);
            alarmClock.click();
            waitFor(1);
            UiObject addAlarmClock, clockSettings;
            addAlarmClock = new UiObject(new UiSelector().className("").textContains("添加"));
            clockSettings = new UiObject(new UiSelector().className("").textContains("设置"));
            addAlarmClock.click();
            waitFor(1);
            UiObject cancel;
            cancel = new UiObject(new UiSelector().className("").textContains("取消"));
            cancel.click();
            alertDialog(PACKAGE_NAME_CLOCK, true);
            waitFor(1);
            clockSettings.click();
            waitFor(1);
            uiDevice.pressBack();
        }
    }

    public void test008_SoundRecorder() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_SOUND_RECORDER);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_SOUND_RECORDER)) {
            UiObject record;
            record = new UiObject(new UiSelector().className(""));
            record.click();
            Random random;
            random = new Random();
            int rnd;
            UiObject stop, pause, markpoint;
            stop = new UiObject(new UiSelector().className(""));
            pause = new UiObject(new UiSelector().className(""));
            markpoint = new UiObject(new UiSelector().className(""));
            for (int i = 0; i < 3; i++) {
                markpoint.click();
                rnd = random.nextInt(2);
                waitFor(rnd);
            }
            pause.click();
            waitFor(1);
            stop.click();
            waitFor(1);
            alertDialog(PACKAGE_NAME_SOUND_RECORDER, true);
        }
    }

    public void test009_FMRadio() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        turnOffWlan();
        launchApp(ACTIVITY_NAME_FM_RADIO);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_FM_RADIO)) {
            alertDialog(PACKAGE_NAME_FM_RADIO, true);
            waitingProgressBar(10);
            UiObject fmRadio;
            fmRadio = new UiObject(new UiSelector().className(""));
            fmRadio.click();
            waitFor(1);
            UiObject fmStationList;
            fmStationList = new UiObject(new UiSelector().className(""));
            fmStationList.click();
            waitFor(1);
            waitingProgressBar(20);
            UiObject cancel;
            cancel = new UiObject(new UiSelector().className(""));
            if (cancel.exists()) {
                cancel.click();
                waitFor(1);
            }
            uiDevice.pressBack();
            waitFor(1);
            immersionMenu();
            UiObject quit;
            quit = new UiObject(new UiSelector().className(""));
            quit.click();
            waitFor(1);
        }
    }

    public void test010_Calculator() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_CALCULATOR);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_CALCULATOR)) {
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
            waitFor(1);
            panel = new UiObject(new UiSelector().className(""));
            childCount = panel.getChildCount();
            for (int i = 0; i < childCount; i++) {
                rnd = random.nextInt(childCount);
                calculatorButton = panel.getChild(new UiSelector().className("").index(rnd));
                calculatorButton.click();
            }
        }
    }

    public void test011_FileExplorer() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_FILE_EXPLORER);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_FILE_EXPLORER)) {
            UiObject fileList;
            fileList = new UiObject(new UiSelector().className(""));
            fileList.click();
            waitingProgressBar(5);
            enterMultiChoiceMode();
            UiObject cancel;
            cancel = new UiObject(new UiSelector().className(""));
            cancel.click();
            waitFor(1);
            immersionMenu();
            UiObject closeImmersionMenu;
            closeImmersionMenu = new UiObject(new UiSelector().className(""));
            closeImmersionMenu.click();
        }
    }

    public void test012_Compass() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_COMPASS);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_COMPASS)) {
            alertDialog(PACKAGE_NAME_COMPASS, true);
            swipePhone(LEFT, 2);
            swipePhone(RIGHT, 1);
        }
    }

    public void test013_SecurityCenter() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_SECURITY_CENTRE);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_SECURITY_CENTRE)) {
            alertDialog(PACKAGE_NAME_SECURITY_CENTRE, true);
        }
    }

    public void test014_Contacts() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_CONTACTS);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_CONTACTS)) {
            UiObject dial, contacts, yellowPage;
            dial = new UiObject(new UiSelector().className("").textContains("拨号"));
            contacts = new UiObject(new UiSelector().className("").textContains("联系人"));
            yellowPage = new UiObject(new UiSelector().className("").textContains("黄页"));
            dial.click();
            waitFor(1);
            UiObject showDial;
            showDial = new UiObject(new UiSelector().className("").textContains("拨号"));
            if (showDial.exists()) {
                showDial.click();
                waitFor(1);
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
            waitFor(1);
            UiObject newContact;
            newContact = new UiObject(new UiSelector().className(""));
            newContact.click();
            waitFor(1);
            UiObject cancel;
            cancel = new UiObject(new UiSelector().className("").textContains("取消"));
            cancel.click();
            waitFor(1);
            alertDialog(PACKAGE_NAME_CONTACTS, true);
            yellowPage.click();
            waitFor(1);
            alertDialog(PACKAGE_NAME_CONTACTS, true);
            waitingProgressBar(5);
        }
    }

    public void test015_SMS() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_SMS);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_SMS)) {
            alertDialog(PACKAGE_NAME_SMS, true);
            UiObject search;
            search = new UiObject(new UiSelector().className(""));
            search.click();
            waitFor(1);
            uiDevice.pressBack();
            waitFor(1);
            UiObject newSMS;
            newSMS = new UiObject(new UiSelector().className("").textContains("写短信"));
            newSMS.click();
            waitFor(1);
            uiDevice.pressBack();
        }
    }

    public void test016_Browser() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_BROWSER);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_BROWSER)) {
            alertDialog(PACKAGE_NAME_BROWSER, true);
            UiObject more;
            more = new UiObject(new UiSelector().className(""));
            more.click();
            waitFor(1);
            UiObject quit;
            quit = new UiObject(new UiSelector().className(""));
            quit.click();
        }
    }

    public void test017_Weather() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        launchApp(ACTIVITY_NAME_WEATHER);
        if (uiDevice.getCurrentPackageName().equals(PACKAGE_NAME_WEATHER)) {
            immersionMenu();
        }
    }

    public void test018_Notes() throws UiObjectNotFoundException {
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
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
        debugMsg(String.format("methodName = %s", new Throwable().getStackTrace()[0].getMethodName()));
        super.tearDown();
        clearOpenedApps();
    }
}
