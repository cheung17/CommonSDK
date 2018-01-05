package com.commonsdk.picture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * 图片与Base64字符串进行转换
 *
 * @author ztx
 */
public class Base64ForImage {
    /**
     * 图片转64.
     *
     * @param bitmap Bitmap
     * @return Base64字符串
     */
    public static String bitmapToBase64(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            byte[] imgBytes = out.toByteArray();
            String image = Base64.encodeToString(imgBytes, Base64.DEFAULT);
            //System.out.println("Base64图片大小：" + image.length());
            return image;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 64转图片。
     *
     * @param base64Data Base64字符串
     * @return Bitmap
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;
    }

    /**
     * 保存Base64到jpg文件。
     *
     * @param base64   图片的Base64编码字符串
     * @param filePath 文件路径，可以是路径，也可以是具体的某个文件。
     * @return 保存Base64到jpg文件路径
     */
    public static String saveBitMap4Base64(String base64, String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            System.err.println("file path is null");
            return null;
        }
        if (TextUtils.isEmpty(base64)) {
            System.err.println("base64 is null");
            return null;
        }
        File file = new File(filePath);
        File imageFile = null;
        if (file.isFile()) {
            File perantFile = file.getParentFile();
            if (perantFile != null && !perantFile.exists()) {
                perantFile.mkdirs();
            }
            imageFile = file;
        } else {
            if (!file.exists()) {
                file.mkdirs();
            }
            imageFile = new File(file, System.currentTimeMillis() + ".jpg");
        }
        Bitmap bit = Base64ForImage.base64ToBitmap(base64);
        try {
            if (imageFile != null) {
                ImageCompressUtil.saveBitmap4Bitmap(imageFile, bit);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageFile.getPath();
    }
}
