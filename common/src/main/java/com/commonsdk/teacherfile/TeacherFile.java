package com.commonsdk.teacherfile;

/**
 * Created by z.houbin on 2017/5/23.
 */
public class TeacherFile {
    private int id;
    private String text = "";
    private String time = "";
    private String url = "";
    private String name = "";
    private int type;
    private String desc = "";
    private float size;
    private String localpath;
    private String sendUserName;

    public String getSendUserName() {
        return sendUserName;
    }

    public TeacherFile setSendUserName(String pSendUserName) {
        sendUserName = pSendUserName;
        return this;
    }

    public String getLocalpath() {
        return localpath;
    }

    public TeacherFile setLocalpath(String pLocalpath) {
        localpath = pLocalpath;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "TeacherFile{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", desc='" + desc + '\'' +
                ", size=" + size +
                '}';
    }
}
