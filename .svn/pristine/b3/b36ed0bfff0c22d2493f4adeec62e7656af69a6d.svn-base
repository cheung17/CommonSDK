package com.commonsdk.zeunpro.appupdatecheck;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * Created by zhangtx on 2017/12/29.
 */
public class AppUpdateModel implements Serializable {
    public final static int ISFORCE_UPDATE_TRUE = 1;
    public final static int ISFORCE_UPDATE_false = 0;
    public static final int NO_NEW_VERSION = -1;
    private String message;
    private int apkid;
    private String apptype;
    private String apkname;
    private String packagename;
    private String versioncode;
    private String channel_id;
    private String md5;
    private String updatecontent;
    private String apk_download_path;
    private long newtime;
    private String apksize;
    private int is_forced_update;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getApkid() {
        return apkid;
    }

    public void setApkid(int apkid) {
        this.apkid = apkid;
    }

    public String getApptype() {
        return apptype;
    }

    public void setApptype(String apptype) {
        this.apptype = apptype;
    }

    public String getApkname() {
        return apkname;
    }

    public void setApkname(String apkname) {
        this.apkname = apkname;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(String versioncode) {
        this.versioncode = versioncode;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getUpdatecontent() {
        return updatecontent;
    }

    public void setUpdatecontent(String updatecontent) {
        this.updatecontent = updatecontent;
    }

    public String getApk_download_path() {
        return apk_download_path;
    }

    public void setApk_download_path(String apk_download_path) {
        this.apk_download_path = apk_download_path;
    }

    public long getNewtime() {
        return newtime;
    }

    public void setNewtime(long newtime) {
        this.newtime = newtime;
    }

    public String getApksize() {
        return apksize;
    }

    public void setApksize(String apksize) {
        this.apksize = apksize;
    }

    public int getIs_forced_update() {
        return is_forced_update;
    }

    public void setIs_forced_update(int is_forced_update) {
        this.is_forced_update = is_forced_update;
    }

    public static AppUpdateModel json2obj(String json) {
        try {
            ObjectMapper objectMapper = objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(json, AppUpdateModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
