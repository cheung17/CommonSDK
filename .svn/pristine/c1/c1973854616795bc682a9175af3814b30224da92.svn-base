package com.commonsdk.updownloader.data;

import java.io.Serializable;

/**
 * 上传，下载时，将文件切割成块。块对象。
 *
 * @author fanjiao
 */
public class upDownProcessItem implements Serializable {
    /**
     * SerializableID
     */
    public static final long serialVersionUID = 2623925078784713579L; //assign a long value
    /**
     * 正在下载的标志
     */
    public static final int UPDATA_ING = 2;
    /**
     * 上传成功的标志
     */
    public static final int UPDATA_SUCCESS = 1;
    /**
     * 上传失败，或未上传的标志
     */
    public static final int UPDATA_FAIL = 0;
    /**
     * 起始位置
     */
    private long start;
    /**
     * 已下载的长度
     */
    private long downloadCount;
    /**
     * 总长度
     */
    private long count;
    /**
     * 文件块的MD5值
     */
    private String md5;
    /**
     * 0表示未上传，1表示已经上传成功了，2表示正在下载
     */
    private int type = UPDATA_FAIL;
    /**
     * 该item的类别，用于判断上传的先后顺序,0表示必须第一个上传；1表示在0之后，在2之前上传；2表示等到0和1都上传了之后，再上传
     */
    private int flag;
    /**
     * 文件块的序号，从0开始
     */
    private int index;
    /**
     * 块的长度
     */
    private long blockLength;
    /**
     * 相对文件开始位置的偏移量
     */
    private long startpos;

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public long getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(long downloadCount) {
        this.downloadCount = downloadCount;
    }

    public long getBlockLength() {
        return blockLength;
    }

    public void setBlockLength(long blockLength) {
        this.blockLength = blockLength;
    }

    public long getStartpos() {
        return startpos;
    }

    public void setStartpos(long startpos) {
        this.startpos = startpos;
    }

    @Override
    public String toString() {
        return "upDownProcessItem [start=" + start + ", downloadCount=" + downloadCount + ", count=" + count + ", md5=" + md5 + ", type="
                + type + ", flag=" + flag + ", index=" + index + ", blockLength=" + blockLength + ", startpos=" + startpos + "]";
    }

}
