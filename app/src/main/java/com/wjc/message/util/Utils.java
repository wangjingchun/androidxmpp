package com.wjc.message.util;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.wjc.message.dao.Emoji;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    /*
     * 获取emoji对象列表
     */
    public static List<Emoji> getEmojiFilename() {

        List<Emoji> list = new ArrayList<>();

        list.add(new Emoji("0001_smiley_03a7b38", "0001_smiley_03a7b38.png"));
        list.add(new Emoji("0002_smiley_13a7b38", "0002_smiley_13a7b38.png"));
        list.add(new Emoji("0003_smiley_23a7b38", "0003_smiley_23a7b38.png"));
        list.add(new Emoji("0004_smiley_33a7b38", "0004_smiley_33a7b38.png"));
        list.add(new Emoji("0005_smiley_43a7b38", "0005_smiley_43a7b38.png"));
        list.add(new Emoji("0006_smiley_53a7b38", "0006_smiley_53a7b38.png"));
        list.add(new Emoji("0007_smiley_63a7b38", "0007_smiley_63a7b38.png"));
        list.add(new Emoji("0008_smiley_73a7b38", "0008_smiley_73a7b38.png"));
        list.add(new Emoji("0009_smiley_83a7b38", "0009_smiley_83a7b38.png"));
        list.add(new Emoji("0010_smiley_93a7b38", "0010_smiley_93a7b38.png"));
        list.add(new Emoji("0011_smiley_103a7b38", "0011_smiley_103a7b38.png"));
        list.add(new Emoji("0012_smiley_113a7b38", "0012_smiley_113a7b38.png"));
        list.add(new Emoji("0013_smiley_123a7b38", "0013_smiley_123a7b38.png"));
        list.add(new Emoji("0014_smiley_133a7b38", "0014_smiley_133a7b38.png"));
        list.add(new Emoji("0015_smiley_143a7b38", "0015_smiley_143a7b38.png"));
        list.add(new Emoji("0016_smiley_153a7b38", "0016_smiley_153a7b38.png"));
        list.add(new Emoji("0017_smiley_163a7b38", "0017_smiley_163a7b38.png"));
        list.add(new Emoji("0018_smiley_173a7b38", "0018_smiley_173a7b38.png"));
        list.add(new Emoji("0019_smiley_183a7b38", "0019_smiley_183a7b38.png"));
        list.add(new Emoji("0020_smiley_193a7b38", "0020_smiley_193a7b38.png"));
        list.add(new Emoji("0021_smiley_203a7b38", "0021_smiley_203a7b38.png"));
        list.add(new Emoji("0022_smiley_213a7b38", "0022_smiley_213a7b38.png"));
        list.add(new Emoji("0023_smiley_223a7b38", "0023_smiley_223a7b38.png"));
        list.add(new Emoji("0024_smiley_233a7b38", "0024_smiley_233a7b38.png"));
        list.add(new Emoji("0025_smiley_243a7b38", "0025_smiley_243a7b38.png"));
        list.add(new Emoji("0026_smiley_253a7b38", "0026_smiley_253a7b38.png"));
        list.add(new Emoji("0027_smiley_263a7b38", "0027_smiley_263a7b38.png"));
        list.add(new Emoji("0028_smiley_273a7b38", "0028_smiley_273a7b38.png"));
        list.add(new Emoji("0029_smiley_283a7b38", "0029_smiley_283a7b38.png"));
        list.add(new Emoji("0030_smiley_293a7b38", "0030_smiley_293a7b38.png"));
        list.add(new Emoji("0031_smiley_303a7b38", "0031_smiley_303a7b38.png"));
        list.add(new Emoji("0032_smiley_313a7b38", "0032_smiley_313a7b38.png"));
        list.add(new Emoji("0033_smiley_323a7b38", "0033_smiley_323a7b38.png"));
        list.add(new Emoji("0034_smiley_333a7b38", "0034_smiley_333a7b38.png"));
        list.add(new Emoji("0035_smiley_343a7b38", "0035_smiley_343a7b38.png"));
        list.add(new Emoji("0036_smiley_353a7b38", "0036_smiley_353a7b38.png"));
        list.add(new Emoji("0037_smiley_363a7b38", "0037_smiley_363a7b38.png"));
        list.add(new Emoji("0038_smiley_373a7b38", "0038_smiley_373a7b38.png"));
        list.add(new Emoji("0039_smiley_383a7b38", "0039_smiley_383a7b38.png"));
        list.add(new Emoji("0040_smiley_393a7b38", "0040_smiley_393a7b38.png"));
        list.add(new Emoji("0041_smiley_403a7b38", "0041_smiley_403a7b38.png"));
        list.add(new Emoji("0042_smiley_413a7b38", "0042_smiley_413a7b38.png"));
        list.add(new Emoji("0043_smiley_423a7b38", "0043_smiley_423a7b38.png"));
        list.add(new Emoji("0044_smiley_433a7b38", "0044_smiley_433a7b38.png"));
        list.add(new Emoji("0045_smiley_443a7b38", "0045_smiley_443a7b38.png"));
        list.add(new Emoji("0046_smiley_453a7b38", "0046_smiley_453a7b38.png"));
        list.add(new Emoji("0047_smiley_463a7b38", "0047_smiley_463a7b38.png"));
        list.add(new Emoji("0048_smiley_473a7b38", "0048_smiley_473a7b38.png"));
        list.add(new Emoji("0049_smiley_483a7b38", "0049_smiley_483a7b38.png"));
        list.add(new Emoji("0050_smiley_493a7b38", "0050_smiley_493a7b38.png"));
        list.add(new Emoji("0051_smiley_503a7b38", "0051_smiley_503a7b38.png"));
        list.add(new Emoji("0052_smiley_513a7b38", "0052_smiley_513a7b38.png"));
        list.add(new Emoji("0053_smiley_523a7b38", "0053_smiley_523a7b38.png"));
        list.add(new Emoji("0054_smiley_533a7b38", "0054_smiley_533a7b38.png"));
        list.add(new Emoji("0055_smiley_543a7b38", "0055_smiley_543a7b38.png"));
        list.add(new Emoji("0056_smiley_553a7b38", "0056_smiley_553a7b38.png"));
        list.add(new Emoji("0057_smiley_563a7b38", "0057_smiley_563a7b38.png"));
        list.add(new Emoji("0058_smiley_573a7b38", "0058_smiley_573a7b38.png"));
        list.add(new Emoji("0059_smiley_583a7b38", "0059_smiley_583a7b38.png"));
        list.add(new Emoji("0060_smiley_593a7b38", "0060_smiley_593a7b38.png"));
        list.add(new Emoji("0061_smiley_603a7b38", "0061_smiley_603a7b38.png"));
        list.add(new Emoji("0062_smiley_613a7b38", "0062_smiley_613a7b38.png"));
        list.add(new Emoji("0063_smiley_623a7b38", "0063_smiley_623a7b38.png"));
        list.add(new Emoji("0064_smiley_633a7b38", "0064_smiley_633a7b38.png"));
        list.add(new Emoji("0065_smiley_643a7b38", "0065_smiley_643a7b38.png"));
        list.add(new Emoji("0066_smiley_653a7b38", "0066_smiley_653a7b38.png"));
        list.add(new Emoji("0067_smiley_663a7b38", "0067_smiley_663a7b38.png"));
        list.add(new Emoji("0068_smiley_673a7b38", "0068_smiley_673a7b38.png"));
        list.add(new Emoji("0069_smiley_683a7b38", "0069_smiley_683a7b38.png"));
        list.add(new Emoji("0070_smiley_693a7b38", "0070_smiley_693a7b38.png"));
        list.add(new Emoji("0071_smiley_703a7b38", "0071_smiley_703a7b38.png"));
        list.add(new Emoji("0072_smiley_713a7b38", "0072_smiley_713a7b38.png"));
        list.add(new Emoji("0073_smiley_723a7b38", "0073_smiley_723a7b38.png"));
        list.add(new Emoji("0074_smiley_733a7b38", "0074_smiley_733a7b38.png"));
        list.add(new Emoji("0075_smiley_743a7b38", "0075_smiley_743a7b38.png"));
        list.add(new Emoji("0076_smiley_753a7b38", "0076_smiley_753a7b38.png"));
        list.add(new Emoji("0077_smiley_763a7b38", "0077_smiley_763a7b38.png"));
        list.add(new Emoji("0078_smiley_773a7b38", "0078_smiley_773a7b38.png"));
        list.add(new Emoji("0079_smiley_783a7b38", "0079_smiley_783a7b38.png"));
        list.add(new Emoji("0080_smiley_793a7b38", "0080_smiley_793a7b38.png"));
        list.add(new Emoji("0081_smiley_803a7b38", "0081_smiley_803a7b38.png"));
        list.add(new Emoji("0082_smiley_813a7b38", "0082_smiley_813a7b38.png"));
        list.add(new Emoji("0083_smiley_823a7b38", "0083_smiley_823a7b38.png"));
        list.add(new Emoji("0084_smiley_833a7b38", "0084_smiley_833a7b38.png"));
        list.add(new Emoji("0085_smiley_843a7b38", "0085_smiley_843a7b38.png"));
        list.add(new Emoji("0086_smiley_853a7b38", "0086_smiley_853a7b38.png"));
        list.add(new Emoji("0087_smiley_863a7b38", "0087_smiley_863a7b38.png"));
        list.add(new Emoji("0088_smiley_873a7b38", "0088_smiley_873a7b38.png"));
        list.add(new Emoji("0089_smiley_883a7b38", "0089_smiley_883a7b38.png"));
        list.add(new Emoji("0090_smiley_893a7b38", "0090_smiley_893a7b38.png"));

        list.add(new Emoji("101_smile", "101_smile.png"));
        list.add(new Emoji("102_blush", "102_blush.png"));
        list.add(new Emoji("103_smiley", "103_smiley.png"));
        list.add(new Emoji("104_sob", "104_sob.png"));
        list.add(new Emoji("105_joy", "105_joy.png"));
        list.add(new Emoji("106_grin", "106_grin.png"));
        list.add(new Emoji("107_sleeping", "107_sleeping.png"));
        list.add(new Emoji("108_heart_eyes", "108_heart_eyes.png"));
        list.add(new Emoji("109_scream", "109_scream.png"));
        list.add(new Emoji("110_stuck_out_tongue_closed_eyes", "110_stuck_out_tongue_closed_eyes.png"));
        list.add(new Emoji("111_flushed", "111_flushed.png"));
        list.add(new Emoji("112_relaxed", "112_relaxed.png"));
        list.add(new Emoji("113_mask", "113_mask.png"));
        list.add(new Emoji("114_kissing_heart", "114_kissing_heart.png"));
        list.add(new Emoji("115_unamused", "115_unamused.png"));
        list.add(new Emoji("116_fearful", "116_fearful.png"));
        list.add(new Emoji("117_sunglasses", "117_sunglasses.png"));
        list.add(new Emoji("118_disappointed", "118_disappointed.png"));
        list.add(new Emoji("119_cry", "119_cry.png"));
        list.add(new Emoji("120_hushed", "120_hushed.png"));
        list.add(new Emoji("121_sweat_smile", "121_sweat_smile.png"));
        list.add(new Emoji("122_astonished", "122_astonished.png"));
        list.add(new Emoji("123_innocent", "123_innocent.png"));
        list.add(new Emoji("124_rage", "124_rage.png"));
        list.add(new Emoji("125_persevere", "125_persevere.png"));
        list.add(new Emoji("126_triumph", "126_triumph.png"));
        list.add(new Emoji("127_yum", "127_yum.png"));
        list.add(new Emoji("128_new_moon_with_face", "128_new_moon_with_face.png"));
        list.add(new Emoji("129_full_moon_with_face", "129_full_moon_with_face.png"));
        list.add(new Emoji("130_sun_with_face", "130_sun_with_face.png"));

        list.add(new Emoji("201_dog", "201_dog.png"));
        list.add(new Emoji("202_tiger", "202_tiger.png"));
        list.add(new Emoji("203_cat", "203_cat.png"));
        list.add(new Emoji("204_mouse", "204_mouse.png"));
        list.add(new Emoji("205_pig", "205_pig.png"));
        list.add(new Emoji("206_panda_face", "206_panda_face.png"));
        list.add(new Emoji("207_bear", "207_bear.png"));
        list.add(new Emoji("208_monkey_face", "208_monkey_face.png"));
        list.add(new Emoji("209_bird", "209_bird.png"));
        list.add(new Emoji("210_chicken", "210_chicken.png"));
        list.add(new Emoji("211_boar", "211_boar.png"));
        list.add(new Emoji("212_camel", "212_camel.png"));
        list.add(new Emoji("213_cow", "213_cow.png"));
        list.add(new Emoji("214_elephant", "214_elephant.png"));
        list.add(new Emoji("215_frog", "215_frog.png"));
        list.add(new Emoji("216_horse", "216_horse.png"));
        list.add(new Emoji("217_rabbit", "217_rabbit.png"));
        list.add(new Emoji("218_sheep", "218_sheep.png"));
        list.add(new Emoji("219_turtle", "219_turtle.png"));
        list.add(new Emoji("220_wolf", "220_wolf.png"));

        list.add(new Emoji("301_rose", "301_rose.png"));
        list.add(new Emoji("302_cactus", "302_cactus.png"));
        list.add(new Emoji("303_cherry_blossom", "303_cherry_blossom.png"));
        list.add(new Emoji("304_deciduous_tree", "304_deciduous_tree.png"));
        list.add(new Emoji("305_evergreen_tree", "305_evergreen_tree.png"));
        list.add(new Emoji("306_tulip", "306_tulip.png"));
        list.add(new Emoji("307_four_leaf_clover", "307_four_leaf_clover.png"));
        list.add(new Emoji("308_herb", "308_herb.png"));
        list.add(new Emoji("309_hibiscus", "309_hibiscus.png"));
        list.add(new Emoji("310_sunflower", "310_sunflower.png"));
        list.add(new Emoji("311_mushroom", "311_mushroom.png"));
        list.add(new Emoji("312_palm_tree", "312_palm_tree.png"));
        list.add(new Emoji("313_leaves", "313_leaves.png"));
        list.add(new Emoji("314_maple_leaf", "314_maple_leaf.png"));
        list.add(new Emoji("315_fallen_leaf", "315_fallen_leaf.png"));

        list.add(new Emoji("401_thumbsup", "401_thumbsup.png"));
        list.add(new Emoji("402_thumbsdown", "402_thumbsdown.png"));
        list.add(new Emoji("403_ok_hand", "403_ok_hand.png"));
        list.add(new Emoji("404_v", "404_v.png"));
        list.add(new Emoji("405_clap", "405_clap.png"));

        list.add(new Emoji("501_sunny", "501_sunny.png"));
        list.add(new Emoji("502_cloud", "502_cloud.png"));
        list.add(new Emoji("503_umbrella", "503_umbrella.png"));
        list.add(new Emoji("504_zap", "504_zap.png"));
        list.add(new Emoji("505_snowflake", "505_snowflake.png"));

        return list;
    }

    /*
     * px转dp
     */
    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /*
     * dp转px
     */
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /*
     * 获取屏幕高度
     */
    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;

        /*
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point.y;
        */
    }

    /*
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int height = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = context.getResources().getDimensionPixelSize(resourceId);
        }

        return height;
    }

    /*
     * 获取虚拟键盘高度
     */
    public static int getNavigationBarHeight(Context context) {
        int height = 0;
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId > 0) {
            int resId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resId > 0) {
                height = resources.getDimensionPixelSize(resId);
            }
        }

        return height;
    }

    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


}
