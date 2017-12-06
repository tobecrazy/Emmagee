/*
 * Copyright (c) 2012-2013 NetEase, Inc. and other contributors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.netease.qa.emmagee.utils;

import android.app.Application;
import android.os.Environment;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;

import es.dmoral.toasty.Toasty;

/**
 * my application class
 *
 * @author andrewleo
 */
public class MyApplication extends Application {

    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    public WindowManager.LayoutParams getMywmParams() {
        return wmParams;
    }

    @Override
    public void onCreate() {
        initAppConfig();
        initToastly();
        super.onCreate();
    }

    private void initToastly() {
        Toasty.Config.getInstance().apply();

    }

    private void initAppConfig() {
        // create directory of emmagee
        boolean isExternalStorage = false;
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            isExternalStorage = true;
        }
        try {
            File dir = new File(Settings.EMMAGEE_RESULT_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
                Toasty.success(getApplicationContext(), Settings.EMMAGEE_RESULT_DIR + " created", Toast.LENGTH_SHORT, true).show();
            } else {
                Toasty.warning(getApplicationContext(), Settings.EMMAGEE_RESULT_DIR + " is exists", Toast.LENGTH_SHORT, true).show();

            }
        } catch (Exception e) {
            Toasty.error(getApplicationContext(), "Unable to Create " + Settings.EMMAGEE_RESULT_DIR, Toast.LENGTH_SHORT, true).show();
        }

    }

}
