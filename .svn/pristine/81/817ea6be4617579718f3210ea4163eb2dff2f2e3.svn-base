package com.commonsdk.picture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;

import com.commonsdk.file.FileIOandOperation;
import com.commonsdk.log.QLog;
import com.commonsdk.picture.compressor.Compressor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片存取工具类
 *
 * @author ztx
 */
public class ImageCompressUtil {


    /**
     * @param filePath 需要压缩的图片路径
     * @param quality  压缩质量
     * @throws Exception IO 异常
     */
    /**
     * @param filePath 需要压缩的图片路径
     * @param quality  压缩质量
     * @return 压缩后的图片
     * @throws Exception 文件不存在或或其他
     */
    public static File compressImageFaster(Context context, String filePath, int quality) throws Exception {
        File oldFile = new File(filePath);
        return new Compressor.Builder(context)
                .setQuality(quality)
                .setCompressFormat(Bitmap.CompressFormat.WEBP)
                .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES).getAbsolutePath())
                .build()
                .compressToFile(oldFile);
    }

    public static Bitmap compressImage2BitmapFaster(Context context, String filePath, int quality) throws Exception {
        File oldFile = new File(filePath);
        return new Compressor.Builder(context)
                .setQuality(quality)
                .setCompressFormat(Bitmap.CompressFormat.WEBP)
                .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES).getAbsolutePath())
                .build()
                .compressToBitmap(oldFile);
    }

    /**
     * 等比压缩图片到指定的宽高以内，从文件中。(图片尺寸压缩)
     *
     * @param imagePath 图片路径
     * @param reqWidth  指定的宽度
     * @param reqHeight 指定的高度
     * @return bitmap图片
     */
    public static Bitmap decodeSampledBitmapFromResource(String imagePath, int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(imagePath, options);
    }

    /**
     * 等比压缩图片到指定的宽高以内，从应用的资源文件中加载。(图片尺寸压缩)
     *
     * @param context       上下文
     * @param imageResource 图片资源id
     * @param reqWidth      需要的图片宽度
     * @param reqHeight     需要的图片高度
     * @return Bitmap 按照一个标准尺寸压缩之后的图片。该图片在系统内存不足时，可能会被回收。
     */
    public static Bitmap decodeSampledBitmapFromResource(Context context, int imageResource, int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), imageResource);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        //。当系统内存不够时候图片自动被回收
        options.inInputShareable = true;
        return BitmapFactory.decodeResource(context.getResources(), imageResource);
    }

    /**
     * 获取需要的缩放比等级(图片尺寸压缩)
     *
     * @param options   options
     * @param reqWidth  需要的图片宽度
     * @param reqHeight 需要的图片高度
     * @return 图片质量等级（值越大，图片质量越低）
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        int percent = 1;
        if (height > 0 && width > 0) {
            if (height > width) {
                percent = height / width;
            } else {
                percent = width / height;
            }
        }
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            if (percent < 10) {
                // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
                // 一定都会大于等于目标的宽和高。
                inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
            } else {
                inSampleSize = 1;
            }
        }
        if (inSampleSize < 0) {
            inSampleSize = 1;
        }
        return inSampleSize;
    }

    /**
     * 按照75%质量，压缩图片，（保持原图的尺寸，压缩占用空间的大小）《速度快很多，适合绝大部分情况使用》
     *
     * @param image   需要压缩的图片
     * @param outPath 压缩后图片保存的路径。强烈建议使用webp格式，如：temp.webp。解释：http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/0527/4302.html
     * @throws IOException 文件流错误。
     */
    public static void compressAndGenImage(Bitmap image, String outPath) throws IOException {
        if (image == null || TextUtils.isEmpty(outPath)) {
            return;
        }
        String part = outPath.substring(outPath.lastIndexOf(".") + 1);
        if (TextUtils.isEmpty(part)) {
            return;
        }
        part = part.toLowerCase();
        Bitmap.CompressFormat format = null;
        if (part.contains("jpg") || part.contains("jpeg")) {
            format = Bitmap.CompressFormat.JPEG;
        } else if (part.contains("png")) {
            format = Bitmap.CompressFormat.PNG;
        } else if (part.contains("webp")) {
            format = Bitmap.CompressFormat.WEBP;
        }
        if (format == null) {
            return;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        image.compress(format, 75, os);
        FileIOandOperation.writeByte2File(outPath, os.toByteArray());
    }

    /**
     * 按照一定的文件体积大小，压缩图片。（保持原图的尺寸，压缩占用空间的大小）《速度很慢，建议使用另一个同名函数，直接按照75%的质量压缩》
     *
     * @param image   需要压缩的图片
     * @param outPath 压缩后图片保存的路径。强烈建议使用webp格式，如：temp.webp。解释：http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/0527/4302.html
     * @param maxSize 需要压缩到某个体积。（单位：kb）如：1024，代表图片体积不超过1MB
     * @throws IOException
     */
    public static void compressAndGenImage(Bitmap image, String outPath, int maxSize) throws IOException {
        if (image == null || TextUtils.isEmpty(outPath) || maxSize < 0) {
            return;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // scale
        int quality = 100;
        String part = outPath.substring(outPath.lastIndexOf(".") + 1);
        if (TextUtils.isEmpty(part)) {
            return;
        }
        part = part.toLowerCase();
        Bitmap.CompressFormat format = null;
        if (part.contains("jpg") || part.contains("jpeg")) {
            format = Bitmap.CompressFormat.JPEG;
        } else if (part.contains("png")) {
            format = Bitmap.CompressFormat.PNG;
        } else if (part.contains("webp")) {
            format = Bitmap.CompressFormat.WEBP;
        }
        if (format == null) {
            return;
        }
        // Store the bitmap into output stream(no compress)
        image.compress(format, quality, os);
        // Compress by loop
        int size = os.toByteArray().length / 1024;
        if (size <= maxSize) {
            // 如果原图就小于目标体积，则默认按照80%缩小
            quality = 80;
            image.compress(format, quality, os);
        } else {
            // Compress by loop
            while (size > maxSize) {
                // Clean up os
                os.reset();
                // interval 10
                quality -= 10;
                image.compress(format, quality, os);
                size = os.toByteArray().length / 1024;
            }
            FileIOandOperation.writeByte2File(outPath, os.toByteArray());
        }
        QLog.d("压缩图片质量比：" + quality);
        FileIOandOperation.writeByte2File(outPath, os.toByteArray());
    }

    /**
     * 无损保存Bitmap图片。《该方法已经移动到DrawableAndBitmap类中，不再维护该函数》
     *
     * @param file   文件路径加文件名
     * @param bitmap 待保存的bitmap图片。
     * @throws IOException
     */
    @Deprecated
    public static void saveBitmap4Bitmap(File file, Bitmap bitmap) throws IOException {
        saveBitmap4Bitmap(file, bitmap, 100);
    }

    /**
     * 按百分比保存Bitmap图片。《该方法已经移动到DrawableAndBitmap类中，不再维护该函数》
     *
     * @param file   文件路径加文件名
     * @param bitmap 待保存的bitmap图片。
     * @param score  压缩比（1<=score<=100），值越大，图片质量越高。
     * @throws IOException
     */
    @Deprecated
    public static void saveBitmap4Bitmap(File file, Bitmap bitmap, int score) throws IOException {
        FileOutputStream out = new FileOutputStream(file);
        // out.write(bitmap2Bytes(bitmap));
        bitmap.compress(Bitmap.CompressFormat.JPEG, score, out);
        out.flush();
        out.close();
    }
}
