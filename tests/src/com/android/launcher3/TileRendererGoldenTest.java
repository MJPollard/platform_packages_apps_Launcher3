/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.launcher3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.Gravity;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;

import androidx.test.filters.LargeTest;
import platform.test.screenshot.PlatformScreenshotTestRule;
import platform.test.screenshot.matchers.MSSIMMatcher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;

@LargeTest
public class TileRendererGoldenTest {
    @Rule
    public PlatformScreenshotTestRule screenshotRule =
            new PlatformScreenshotTestRule(
                "packages/apps/Launcher3/tests/",
                "/data/user/0/com.android.launcher3/files"
            );

    // This isn't totally ideal right now. The screenshot tests run on a phone, so emulate some
    // watch dimensions here.
    private static final int SCREEN_WIDTH = 390;
    private static final int SCREEN_HEIGHT = 390;

    private static final int INLINE_IMAGE_WIDTH = 8;
    private static final int INLINE_IMAGE_HEIGHT = 8;
    private static final int INLINE_IMAGE_PIXEL_STRIDE = 2; // RGB565 = 2 bytes per pixel

    @Test
    public void renderer_goldenTest() throws Exception {
        Bitmap bmp = Bitmap.createBitmap(SCREEN_WIDTH, SCREEN_HEIGHT, Bitmap.Config.ARGB_8888);
        screenshotRule.assertBitmapAgainstGolden(
            bmp, "expected-key-ihcinihsdk", new MSSIMMatcher());
    }

    private void saveBitmapToFile(Bitmap bmp, String filename) throws IOException {
        try (FileOutputStream out = new FileOutputStream(filename)) {
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
