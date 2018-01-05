package com.commonsdk.naturalcontrol;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * 通过系统自带的功能，对图片，声音，视频，文件，进行选择，播放等功能。（相关uri数据在onActivityResult中获取，解析方法，在：com.
 * commonsdk.uri.GetPathFromUri4kitkat。）
 *
 * @author ztx
 */
public class AndroidNatural {
    /**
     * 从相册查找图片
     *
     * @return Intent
     */
    public static Intent getImageFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");// 相片类型
        return intent;
    }

    /**
     * 从相册查找视频
     *
     * @return Intent
     */
    public static Intent getVideoFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");// 视频类型
        return intent;
    }

    /**
     * 从文件管理器查找文件
     *
     * @return Intent
     */
    public static Intent getFileFromManager() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        return intent;
    }

    /**
     * 录像
     *
     * @return Intent
     */
    public static Intent getVideoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);// 参数设置可以省略
        return intent;
    }

    /**
     * 录音
     *
     * @return Intent
     */
    public static Intent getRecodeFromCamera() {
        Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);// 参数设置可以省略
        return intent;
    }

    /**
     * 调用相机，拍照
     *
     * @param picturePath 照片保存的路径+名字。
     * @return Intent
     */
    public static Intent getImageFromCamera(String picturePath) {
        Intent getImageByCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse("file://" + picturePath));
        getImageByCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        return getImageByCamera;
    }
}
