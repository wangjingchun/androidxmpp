package com.wjc.message.util;

import android.content.Context;
import android.content.res.Resources;
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


}
