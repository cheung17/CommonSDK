package com.commonsdk.updownloader.data;

import android.text.TextUtils;

import com.commonsdk.file.FileCompare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * 上传，下载时，将文件切割成块，工具类
 *
 * @author fanjiao
 */
public class upDownProcessItemUtils {
    /**
     * 下载时，同时进行的线程数量
     */
    public static final int BLOCKCOUNT = 10;
    /**
     * 下载时每个块的最大值
     */
    public static final int DOWNLOAD_BLOCKSIZE = 1024 * 1024 * 3;
    /**
     * 上传时，文件分割的块的大小
     */
    public static final int BLOCKSIZE = 100 * 1024;

    /**
     * 块对象转字符串
     *
     * @param item upDownProcessItem
     * @return Json字符串
     */
    public static String upDownProcessItem2String(upDownProcessItem item) {
        if (item != null) {
            long count = item.getCount();
            long start = item.getStart();
            long downloadCount = item.getDownloadCount();
            int flag = item.getFlag();
            String md5 = item.getMd5();
            int index = item.getIndex();
            int type = item.getType();
            long len = item.getBlockLength();
            long startPos = item.getStartpos();
            StringBuffer sb = new StringBuffer();
            sb.append("{\"" + DataBean.COUNT + "\"=" + count + ",");
            sb.append("\"" + DataBean.START + "\"=" + start + ",");
            sb.append("\"" + DataBean.DOWNLOADCOUNT + "\"=" + downloadCount + ",");
            sb.append("\"" + DataBean.FLAG + "\"=" + flag + ",");
            sb.append("\"" + DataBean.MD5 + "\"=\"" + md5 + "\",");
            sb.append("\"" + DataBean.INDEX + "\"=" + index + ",");
            sb.append("\"" + DataBean.BLOCKLENGTH + "\"=" + len + ",");
            sb.append("\"" + DataBean.STARTPOS + "\"=" + startPos + ",");
            sb.append("\"" + DataBean.TYPE + "\"=" + type + "}");
            return sb.toString().trim();
        }
        return "";
    }

    /**
     * 块对象列表转字符串
     *
     * @param upDownProcessItems 块对象列表
     * @return JsonArray字符串
     */
    public static String upDownProcessItemList2String(ArrayList<upDownProcessItem> upDownProcessItems) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if (upDownProcessItems != null) {
            for (int i = 0; i < upDownProcessItems.size(); i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(upDownProcessItem2String(upDownProcessItems.get(i)));
            }
        }
        sb.append("]");
        return sb.toString().trim();
    }

    /**
     * 字符串转块对象列表
     *
     * @param itemList 字符串
     * @return upDownProcessItem列表
     */
    public static ArrayList<upDownProcessItem> String2upDownProcessItemList(String itemList) {
        if (!TextUtils.isEmpty(itemList)) {
            try {
                JSONArray array = new JSONArray(itemList);
                ArrayList<upDownProcessItem> arrayList = new ArrayList<upDownProcessItem>();
                for (int i = 0; i < array.length(); i++) {
                    upDownProcessItem item = String2upDownProcessItem(array.getString(i));
                    if (item != null) {
                        arrayList.add(item);
                    }
                }
                return arrayList;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 字符串转块对象
     *
     * @param item 字符串
     * @return upDownProcessItem对象
     */
    public static upDownProcessItem String2upDownProcessItem(String item) {
        if (!TextUtils.isEmpty(item)) {
            try {
                JSONObject jsonObject = new JSONObject(item);
                String md5 = jsonObject.getString(DataBean.MD5);
                int type = jsonObject.getInt(DataBean.TYPE);
                int flag = jsonObject.getInt(DataBean.FLAG);
                int index = jsonObject.getInt(DataBean.INDEX);
                long start = jsonObject.getLong(DataBean.START);
                long downloadCount = jsonObject.getLong(DataBean.DOWNLOADCOUNT);
                long count = jsonObject.getLong(DataBean.COUNT);
                long blockLength = jsonObject.getLong(DataBean.BLOCKLENGTH);
                long startPos = jsonObject.getLong(DataBean.STARTPOS);

                upDownProcessItem downProcessItem = new upDownProcessItem();
                downProcessItem.setCount(count);
                downProcessItem.setDownloadCount(downloadCount);
                downProcessItem.setFlag(flag);
                downProcessItem.setMd5(md5);
                downProcessItem.setStart(start);
                downProcessItem.setType(type);
                downProcessItem.setIndex(index);
                downProcessItem.setStartpos(startPos);
                downProcessItem.setBlockLength(blockLength);
                return downProcessItem;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取一个文件的Block块列表，（上传）。
     *
     * @param filePath 文件路径
     * @return 块列表
     */
    public static ArrayList<upDownProcessItem> getFileBlockList(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            if (file.exists()) {
                long fileSize = file.length();
                InputStream in = null;
                try {
                    in = new FileInputStream(file);
                    if (in != null) {
                        return getFileBlockList(fileSize, in);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取一个文件的Block块列表,(上传)。
     *
     * @param fileSize 文件大小
     * @param in       输入流
     * @return 块列表
     */
    public static ArrayList<upDownProcessItem> getFileBlockList(long fileSize, InputStream in) {
        ArrayList<upDownProcessItem> arrayList = new ArrayList<upDownProcessItem>();
        int itemSize = (int) (fileSize / BLOCKSIZE);
        int number = (int) (fileSize % BLOCKSIZE);
        int index = 0;
        for (int i = 0; i < itemSize; i++) {
            upDownProcessItem item = new upDownProcessItem();
            item.setStartpos(i * BLOCKSIZE);
            item.setBlockLength(BLOCKSIZE);
            if (i == 0) {
                item.setFlag(0);
            } else if (number <= 0 && i == itemSize - 1) {
                item.setFlag(2);
            } else {
                item.setFlag(1);
            }
            item.setIndex(index++);
            item.setType(upDownProcessItem.UPDATA_FAIL);

            if (in != null) {
                // 计算文件的 MD5
                // 值，用于比较两个文件是否相同。in读取之后，游标会停留在上一次使用的位置，因此，循环读取一个in对象时，只需要设置读取的长度，即可从上一次结束的位置继续读取。
                String md5 = FileCompare.getPartFileMD5(in, BLOCKSIZE);
                if (!TextUtils.isEmpty(md5)) {
                    item.setMd5(md5);
                }
            }
            arrayList.add(item);
        }
        if (number > 0) {
            upDownProcessItem item = new upDownProcessItem();
            item.setStartpos(itemSize * BLOCKSIZE);
            item.setBlockLength(number);
            item.setFlag(2);
            item.setIndex(index++);
            item.setType(upDownProcessItem.UPDATA_FAIL);

            if (in != null) {
                // 计算文件的 MD5
                // 值，用于比较两个文件是否相同。in读取之后，游标会停留在上一次使用的位置，因此，循环读取一个in对象时，只需要设置读取的长度，即可从上一次结束的位置继续读取。
                String md5 = FileCompare.getPartFileMD5(in, number);
                if (!TextUtils.isEmpty(md5)) {
                    item.setMd5(md5);
                }
            }
            arrayList.add(item);
        }
        return arrayList;
    }

    /**
     * 获取一个文件的Block块列表,(上传)。
     *
     * @param fileSize 文件大小
     * @return 块列表
     */
    public static ArrayList<upDownProcessItem> getFileBlockList(long fileSize) {
        ArrayList<upDownProcessItem> arrayList = new ArrayList<upDownProcessItem>();
        int itemSize = (int) (fileSize / DOWNLOAD_BLOCKSIZE);
        int number = (int) (fileSize % DOWNLOAD_BLOCKSIZE);
        int index = 0;
        for (int i = 0; i < itemSize; i++) {
            upDownProcessItem item = new upDownProcessItem();
            item.setStart(i * DOWNLOAD_BLOCKSIZE);
            item.setCount(DOWNLOAD_BLOCKSIZE);
            item.setDownloadCount(0);
            if (i == 0) {
                item.setFlag(0);
            } else if (number <= 0 && i == itemSize - 1) {
                item.setFlag(2);
            } else {
                item.setFlag(1);
            }
            item.setIndex(index++);
            item.setType(upDownProcessItem.UPDATA_FAIL);
            arrayList.add(item);
        }
        if (number > 0) {
            upDownProcessItem item = new upDownProcessItem();
            item.setStart(itemSize * DOWNLOAD_BLOCKSIZE);
            item.setCount(number);
            item.setDownloadCount(0);
            item.setFlag(2);
            item.setIndex(index++);
            item.setType(upDownProcessItem.UPDATA_FAIL);
            arrayList.add(item);
        }
        return arrayList;
    }
    /**
     * 获取一个待下载文件的块信息列表
     *
     * @param fileSize 文件大小
     * @return 块列表
     */
//    public static ArrayList<upDownProcessItem> getFileBlockList(long fileSize) {
//        ArrayList<upDownProcessItem> arrayList = new ArrayList<upDownProcessItem>();
//        int itemSize = 0;
//        int number = 0;
//        if (fileSize > 0) {
//            itemSize = (int) (fileSize / BLOCKCOUNT);
//            number = (int) (fileSize % itemSize);
//        } else {
//            return arrayList;
//        }
//        // long start = 0;
//        int index = 0;
//        for (int i = 0; i < BLOCKCOUNT; i++) {
//            upDownProcessItem item = new upDownProcessItem();
//            item.setStart(i * itemSize);
//            item.setCount(itemSize);
//            item.setDownloadCount(0);
//            if (i == 0) {
//                item.setFlag(0);
//            } else if (number <= 0 && i == itemSize - 1) {
//                item.setFlag(2);
//            } else {
//                item.setFlag(1);
//            }
//            item.setIndex(index++);
//            item.setType(upDownProcessItem.UPDATA_FAIL);
//
//            arrayList.add(item);
//            // start = start + itemSize;
//        }
//        if (number > 0) {
//            upDownProcessItem item = new upDownProcessItem();
//            item.setStart(itemSize * BLOCKCOUNT);
//            item.setCount(number);
//            item.setDownloadCount(0);
//            item.setFlag(2);
//            item.setIndex(index++);
//            item.setType(upDownProcessItem.UPDATA_FAIL);
//
//            arrayList.add(item);
//        }
//        return arrayList;
//    }

    /**
     * 获取所有下载成功的块的和。(下载)
     *
     * @param upDownProcessItems 块列表
     * @return 下载成功的块总和
     */
    public static long upDownProcessItemSum(ArrayList<upDownProcessItem> upDownProcessItems) {
        long sum = 0;
        if (upDownProcessItems != null) {
            for (int i = 0; i < upDownProcessItems.size(); i++) {
                upDownProcessItem item = upDownProcessItems.get(i);
                if (item != null && item.getType() != upDownProcessItem.UPDATA_FAIL) {
                    sum += item.getCount();
                }
            }
        }
        return sum;
    }

    /**
     * 获取所有发送成功的块的和。(上传)
     *
     * @param upDownProcessItems 块列表
     * @return 发送成功的块的和
     */
    public static long upDownProcessItemSum_UP(ArrayList<upDownProcessItem> upDownProcessItems) {
        long sum = 0;
        if (upDownProcessItems != null) {
            for (int i = 0; i < upDownProcessItems.size(); i++) {
                upDownProcessItem item = upDownProcessItems.get(i);
                if (item != null && item.getType() != upDownProcessItem.UPDATA_FAIL) {
                    sum += item.getBlockLength();
                }
            }
        }
        return sum;
    }

    /**
     * 获取所有下载成功的块的和。
     *
     * @param upDownProcessItems 块列表
     * @return 下载成功的块的和
     */
    public static long getDownProcessItemSum(ArrayList<upDownProcessItem> upDownProcessItems) {
        long sum = 0;
        if (upDownProcessItems != null) {
            for (int i = 0; i < upDownProcessItems.size(); i++) {
                upDownProcessItem item = upDownProcessItems.get(i);
                if (item != null) {
                    sum += item.getDownloadCount();
                }
            }
        }
        return sum;
    }

    /**
     * 设置某个模块状态为下载成功
     *
     * @param upDownProcessItems 块列表
     * @param start              目标块id
     * @return 成功／失败
     */
    public static boolean setUpDownBlockSuccess(ArrayList<upDownProcessItem> upDownProcessItems, long start) {
        upDownProcessItem result = findUpDownProcessItem4Start(upDownProcessItems, start);
        if (result != null) {
            result.setType(upDownProcessItem.UPDATA_SUCCESS);
            return true;
        }
        return false;
    }

    /**
     * 通过start字段查询对应的模块
     *
     * @param upDownProcessItems 块列表
     * @param start              目标块id
     * @return upDownProcessItem
     */
    public static upDownProcessItem findUpDownProcessItem4Start(ArrayList<upDownProcessItem> upDownProcessItems, long start) {
        if (upDownProcessItems != null) {
            upDownProcessItem result = null;
            for (int i = 0; i < upDownProcessItems.size(); i++) {
                result = upDownProcessItems.get(i);
                if (result.getStart() == start) {
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * 判断文件各个模块是否上传完毕了。
     *
     * @param upDownProcessItems 块列表
     * @return 上传是否完毕
     */
    public static boolean upIsFinish(ArrayList<upDownProcessItem> upDownProcessItems) {
        if (upDownProcessItems != null) {
            for (int i = 0; i < upDownProcessItems.size(); i++) {
                if (upDownProcessItems.get(i).getType() != upDownProcessItem.UPDATA_SUCCESS) {
                    // 未上传完成。
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 判断，除了最后一项，其他项是否都上传完了。
     *
     * @param upDownProcessItems 块列表
     * @return 判断，除了最后一项，其他项是否都上传完了。
     */
    public static boolean upIsFinishWithoutLastOne(ArrayList<upDownProcessItem> upDownProcessItems) {
        if (upDownProcessItems != null) {
            for (int i = 0; i < upDownProcessItems.size() - 1; i++) {
                if (upDownProcessItems.get(i).getType() != upDownProcessItem.UPDATA_SUCCESS) {
                    // 未上传完成。
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 获取所有没有上传成功的项，除了最后一项。
     *
     * @param upDownProcessItems upDownProcessItems列表
     * @return upDownProcessItems列表
     */
    public static ArrayList<upDownProcessItem> upNotFinishWithoutLastOne(ArrayList<upDownProcessItem> upDownProcessItems) {
        ArrayList<upDownProcessItem> items = new ArrayList<upDownProcessItem>();
        if (upDownProcessItems != null) {
            for (int i = 0; i < upDownProcessItems.size() - 1; i++) {
                if (upDownProcessItems.get(i).getType() != upDownProcessItem.UPDATA_FAIL) {
                    items.add(upDownProcessItems.get(i));
                }
            }
        }
        return items;
    }

    /**
     * 返回，需要上传／下载的Block的个数。
     *
     * @param upDownProcessItems upDownProcessItems列表
     * @return 未完成的个数
     */
    public static int needUpdateBlockNumber(ArrayList<upDownProcessItem> upDownProcessItems) {
        int count = 0;
        if (upDownProcessItems != null) {
            for (int i = 0; i < upDownProcessItems.size(); i++) {
                if (upDownProcessItems.get(i).getType() == upDownProcessItem.UPDATA_FAIL) {
                    count = count + 1;
                }
            }
        }
        return count;
    }

    /**
     * 返回没有下载，且没有被任何子线程执行的模块。
     *
     * @param upDownProcessItems upDownProcessItems列表
     * @return 需要被执行的模块
     */
    public static upDownProcessItem getFirstNeedUpdateBlock(ArrayList<upDownProcessItem> upDownProcessItems) {
        if (upDownProcessItems != null) {
            for (int i = 0; i < upDownProcessItems.size(); i++) {
                if (upDownProcessItems.get(i).getType() == upDownProcessItem.UPDATA_FAIL) {
                    return upDownProcessItems.get(i);
                }
            }
        }
        return null;
    }

    /**
     * 设置所有模块状态为失败。
     *
     * @param upDownProcessItems 块列表
     */
    public static void setAllBlockFail(ArrayList<upDownProcessItem> upDownProcessItems) {
        if (upDownProcessItems != null) {
            for (int i = 0; i < upDownProcessItems.size(); i++) {
                upDownProcessItems.get(i).setType(upDownProcessItem.UPDATA_FAIL);
            }
        }
    }

    /**
     * 设置每个模块为未下载状态，以下载大小为零。
     *
     * @param upDownProcessItems 块列表
     */
    public static void setAllBlockDownloadSize2Zero(ArrayList<upDownProcessItem> upDownProcessItems) {
        if (upDownProcessItems != null) {
            for (int i = 0; i < upDownProcessItems.size(); i++) {
                upDownProcessItems.get(i).setType(upDownProcessItem.UPDATA_FAIL);
                upDownProcessItems.get(i).setDownloadCount(0);
            }
        }
    }

    /**
     * 将正在下载的状态清除掉，设置成未完成状态。
     *
     * @param upDownProcessItems 块列表
     */
    public static void clearAllBlockDownloading(ArrayList<upDownProcessItem> upDownProcessItems) {
        if (upDownProcessItems != null) {
            for (int i = 0; i < upDownProcessItems.size(); i++) {
                if (upDownProcessItems.get(i).getType() == upDownProcessItem.UPDATA_ING) {
                    upDownProcessItems.get(i).setType(upDownProcessItem.UPDATA_FAIL);
                }
            }
        }
    }
}
