package com.commonsdk.picture;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Drawable和Bitmap转换
 *
 * @author ztx
 */
public class DrawableAndBitmap {
    /**
     * Drawable转Bitmap
     *
     * @param drawable Drawable资源
     * @return Bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;

    }

    /**
     * Bitmap转Drawable
     *
     * @param bmp Bitmap
     * @return Drawable
     */
    public static Drawable bitmapToDrawable(Bitmap bmp) {
        return new BitmapDrawable(bmp);
    }

    /**
     * 从资源文件中直接获取Bitmap对象。
     *
     * @param context  上下文
     * @param resource 资源id
     * @return Bitmap
     */
    public static Bitmap getBitmap(Context context, int resource) {
        Resources res = context.getResources();
        return BitmapFactory.decodeResource(res, resource);
    }

    /**
     * 从文件加载bitmap
     *
     * @param context 上下文
     * @param file    文件
     * @return Bitmap
     */
    public static Bitmap getBitmap(Context context, File file) {
        return getBitmap(context, file, null);
    }

    /**
     * 从文件加载bitmap
     *
     * @param context  上下文
     * @param filePath 文件路径
     * @return Bitmap
     */
    public static Bitmap getBitmap(Context context, String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        return getBitmap(context, new File(filePath));
    }

    /**
     * 从文件加载bitmap
     *
     * @param context  上下文
     * @param filePath 文件路径
     * @param options  属性
     * @return Bitmap
     */
    public static Bitmap getBitmap(Context context, String filePath, Options options) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        return getBitmap(context, new File(filePath), options);
    }

    /**
     * 从文件加载bitmap
     *
     * @param context 上下文
     * @param file    文件
     * @param options 属性
     * @return Bitmap
     */
    public static Bitmap getBitmap(Context context, File file, Options options) {
        if (file == null || !file.exists()) {
            return null;
        }
        String filePath = file.getPath();
        if (options == null) {
            return BitmapFactory.decodeFile(filePath);
        } else {
            return BitmapFactory.decodeFile(filePath, options);
        }
    }

    /**
     * 无损保存Bitmap图片。
     *
     * @param file   文件路径加文件名
     * @param bitmap 待保存的bitmap图片。
     * @throws IOException
     */
    public static void saveBitmap4Bitmap(File file, Bitmap bitmap) throws IOException {
        saveBitmap4Bitmap(file, bitmap, 100);
    }

    /**
     * 按百分比保存Bitmap图片。
     *
     * @param file   文件路径加文件名
     * @param bitmap 待保存的bitmap图片。
     * @param score  压缩比（1<=score<=100），值越大，图片质量越高。
     * @throws IOException
     */
    public static void saveBitmap4Bitmap(File file, Bitmap bitmap, int score) throws IOException {
        String fileName = file.getName().toLowerCase();
        Bitmap.CompressFormat format = null;
        if (fileName.endsWith("jpg")) {
            format = Bitmap.CompressFormat.JPEG;
        } else if (fileName.endsWith("png")) {
            format = Bitmap.CompressFormat.PNG;
        } else if (fileName.endsWith("webp")) {
            format = Bitmap.CompressFormat.WEBP;
        } else {
            format = Bitmap.CompressFormat.PNG;
        }
        if (bitmap == null) {
            return;
        }
        FileOutputStream out = new FileOutputStream(file);
        bitmap.compress(format, score, out);
        out.flush();
        out.close();
    }

    /**
     * 从资源文件中直接获取Drawable对象。
     *
     * @param context  上下文
     * @param resource 资源id
     * @return Drawable
     */
    public static Drawable getDrawable(Context context, int resource) {
        Resources res = context.getResources();
        return res.getDrawable(resource);
    }

    /**
     * 从Bitmap获取byte数组。
     *
     * @param bm Bitmap
     * @return byte数组
     */
    public static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 从byte数组获取Bitmap。
     *
     * @param b byte数组
     * @return Bitmap
     */
    public static Bitmap bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }
}
