package com.miui.sdk.test;

/**
 * Project name : miui-sdk-test
 * Package name : com.miui.sdk.test
 * Created by jiahuixing
 * Created on 2015-06-24
 * Created at 16:51
 */


import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.sdk.constants.Constants;
import com.miui.sdk.utils.SdkUtils;

import java.util.Random;

public class LaunchTimeTest extends UiAutomatorTestCase {
    //    device
    private UiDevice uiDevice;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        uiDevice = UiDevice.getInstance();
        SdkUtils.unLock(uiDevice);
    }

    public void test001_SoundRecorder() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_SOUND_RECORDER);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_SOUND_RECORDER)) {
            UiObject recordListButton;
            recordListButton = new UiObject(new UiSelector().className("android.widget.ImageButton")
                    .resourceId("com.android.soundrecorder:id/btn_list"));
            recordListButton.click();
            SdkUtils.waitFor(1);
            UiObject recordFileList;
            recordFileList = new UiObject(new UiSelector().className("android.widget.ImageButton")
                    .resourceId("com.android.soundrecorder:id/btn_folder_icon"));
            recordFileList.click();
            SdkUtils.waitFor(1);
            while (true) {
                if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_FILE_EXPLORER)) {
                    uiDevice.pressBack();
                    SdkUtils.waitFor(1);
                } else {
                    break;
                }
            }
            UiObject cloudRecordList;
            cloudRecordList = new UiObject(new UiSelector().className("android.widget.TextView").textContains("云录音"));
            if (cloudRecordList.click()) {
                cloudRecordList.click();
                SdkUtils.waitFor(1);
                UiObject cloudRecordDownloadList;
                cloudRecordDownloadList = new UiObject(new UiSelector().className("android.widget.ImageButton")
                        .resourceId("com.android.soundrecorder:id/btn_download_list"));
                cloudRecordDownloadList.click();
                SdkUtils.waitFor(1);
            }
            uiDevice.pressHome();
//            SdkUtils.alertDialog(Constants.PACKAGE_NAME_SOUND_RECORDER, true);
        }
    }

    public void test002_Calculator() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_CALCULATOR);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_CALCULATOR)) {
//            SdkUtils.alertDialog(Constants.PACKAGE_NAME_CALCULATOR, true);
            UiObject calculatorMode;
            calculatorMode = new UiObject(new UiSelector().className("android.widget.ImageView")
                    .resourceId("com.android.calculator2:id/switch_btn"));
            UiObject panel;
            int childCount;
            Random random;
            random = new Random();
            int rnd;
            UiObject calculatorButton;
            panel = new UiObject(new UiSelector().className("android.widget.FrameLayout")
                    .resourceId("com.android.calculator2:id/pad_container"));
            childCount = panel.getChildCount();
            for (int i = 0; i < childCount; i++) {
                rnd = random.nextInt(childCount);
                calculatorButton = panel.getChild(new UiSelector().className("android.widget.Button").index(rnd));
                SdkUtils.debugMsg(String.format("calculatorButton: %s", calculatorButton.getText()));
                calculatorButton.click();
            }
            calculatorMode.click();
            SdkUtils.waitFor(1);
            panel = new UiObject(new UiSelector().className("android.widget.FrameLayout")
                    .resourceId("com.android.calculator2:id/pad_container"));
            childCount = panel.getChildCount();
            for (int i = 0; i < childCount; i++) {
                rnd = random.nextInt(childCount);
                calculatorButton = panel.getChild(new UiSelector().className("").index(rnd));
                calculatorButton.click();
            }
        }
    }

    public void test003_FMRadio() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_FM_RADIO);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_FM_RADIO)) {
//            SdkUtils.alertDialog(Constants.PACKAGE_NAME_FM_RADIO, true);
            UiObject fmStationList;
            fmStationList = new UiObject(new UiSelector().className("android.widget.ImageButton")
                    .resourceId("com.miui.fm:id/btn_stations_list"));
            fmStationList.click();
            SdkUtils.waitFor(1);
            if (!SdkUtils.waitingProgressBar(5)) {
                UiObject cancel;
                cancel = new UiObject(new UiSelector().className("android.widget.Button")
                        .textContains("取消"));
                cancel.click();
                SdkUtils.waitFor(1);
            }
            uiDevice.pressBack();
            SdkUtils.immersionMenu(Constants.PACKAGE_NAME_FM_RADIO);
            UiObject recordListButton;
            recordListButton = new UiObject(new UiSelector().className("android.widget.TextView")
                    .textContains("录音列表"));
            recordListButton.click();
            SdkUtils.waitFor(1);
            while (true) {
                if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_SOUND_RECORDER)) {
                    uiDevice.pressBack();
                    SdkUtils.waitFor(1);
                } else {
                    break;
                }
            }
            SdkUtils.immersionMenu(Constants.PACKAGE_NAME_FM_RADIO);
            UiObject quit;
            quit = new UiObject(new UiSelector().className("android.widget.TextView")
                    .textContains("退出"));
            quit.click();
            SdkUtils.waitFor(1);
        }
    }

    public void test004_Compass() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_COMPASS);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_COMPASS)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_COMPASS, true);

        }
    }

    public void test005_Notes() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_NOTES);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_SOUND_RECORDER)) {
            SdkUtils.alertDialog(Constants.PACKAGE_NAME_NOTES, false);
            UiObject richEditor;
            while (true) {
                richEditor = new UiObject(new UiSelector().className("android.widget.EditText")
                        .resourceId("com.miui.notes:id/rich_editor"));
                if (richEditor.exists()) {
                    uiDevice.pressBack();
                    SdkUtils.waitFor(1);
                } else {
                    break;
                }
            }
            UiObject noteList;
            noteList = new UiObject(new UiSelector().className("android.widget.ListView"));
            UiObject note;
            note = noteList.getChild(new UiSelector().className("android.widget.TextView")
                    .resourceId("com.miui.notes:id/title")
                    .index(0));
            note.click();
            SdkUtils.waitFor(1);
            UiObject photo;
            photo = new UiObject(new UiSelector().className("android.widget.ImageView")
                    .resourceId("com.miui.notes:id/photo"));
            photo.click();
            SdkUtils.waitFor(2);
            if (uiDevice.getCurrentPackageName().equals(Constants.ACTIVITY_NAME_CAMERA)) {
                SdkUtils.alertDialog(Constants.PACKAGE_NAME_CAMERA, true);
                UiObject shoot;
                shoot = new UiObject(new UiSelector().className("android.widget.ImageView")
                        .resourceId("com.android.camera:id/v6_shutter_button_internal"));
                shoot.click();
                SdkUtils.waitFor(3);
                UiObject done;
                done = new UiObject(new UiSelector().className("com.android.camera:id/v6_btn_done")
                        .resourceId("com.android.camera:id/v6_btn_done"));
                done.click();
                SdkUtils.waitFor(2);
            }
            SdkUtils.immersionMenu(Constants.PACKAGE_NAME_NOTES);
            UiObject alarm;
            alarm = new UiObject(new UiSelector().className("android.widget.CheckBox")
                    .resourceId("com.miui.notes:id/alarm_enabler"));
            alarm.click();
            SdkUtils.waitFor(1);
            UiObject picker;
            picker = new UiObject(new UiSelector().className("miui.widget.NumberPicker"));
            if (picker.exists()) {
                uiDevice.pressBack();
                SdkUtils.waitFor(1);
            }
            UiObject send;
            send = new UiObject(new UiSelector().className("android.widget.TextView").text("发送"));
            send.click();
            SdkUtils.waitFor(1);
            UiObject sendChooser;
            sendChooser = new UiObject(new UiSelector().className("android.widget.GridView")
                    .resourceId("android.miui:id/resolver_grid"));
            if (sendChooser.exists()) {
                uiDevice.pressBack();
                SdkUtils.waitFor(1);
            }
            SdkUtils.immersionMenu(Constants.PACKAGE_NAME_NOTES);
            UiObject sendSinaWeibo;
            sendSinaWeibo = new UiObject(new UiSelector().className("android.widget.TextView")
                    .textContains("新浪微博"));
            sendSinaWeibo.click();
            SdkUtils.waitFor(1);
            UiObject title;
            title = new UiObject(new UiSelector().className("android.widget.TextView")
                    .text("编辑标题"));
            if (title.exists()) {
                UiObject confirm;
                confirm = new UiObject(new UiSelector().className("android.widget.Button").text("确定"));
                confirm.click();
                SdkUtils.waitFor(1);
                SdkUtils.waitingProgressBar(5);
            }
            uiDevice.pressHome();
        }
    }

    public void test006_Backup() throws UiObjectNotFoundException {
        SdkUtils.debugMsg(String.format("methodName = %s", SdkUtils.getCurrentMethodName()));
        SdkUtils.launchApp(uiDevice, Constants.ACTIVITY_NAME_BACKUP);
        if (uiDevice.getCurrentPackageName().equals(Constants.PACKAGE_NAME_BACKUP)) {
//            SdkUtils.alertDialog(Constants.PACKAGE_NAME_BACKUP, true);
            UiObject encryption;
            encryption = new UiObject(new UiSelector().className("android.widget.ImageButton")
                    .resourceId("com.miui.backup:id/bakcup_start_encrypt"));
            encryption.click();
            SdkUtils.waitFor(1);
            UiObject enableEncrypt;
            enableEncrypt = new UiObject(new UiSelector().className("android.widget.Button")
                    .resourceId("com.miui.backup:id/enable_encrypt"));
            if (enableEncrypt.exists()) {
                uiDevice.pressBack();
                SdkUtils.waitFor(1);
            }
            UiObject newBackup;
            newBackup = new UiObject(new UiSelector().className("android.widget.Button")
                    .resourceId("com.miui.backup:id/bakcup_new_backup"));
            newBackup.click();
            SdkUtils.waitFor(1);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        SdkUtils.clearOpenedApps(uiDevice);
    }
}
