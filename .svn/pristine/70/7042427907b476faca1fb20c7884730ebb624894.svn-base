package com.commonsdk.nfc;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Parcelable;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 在activity的XML配置文件中，应添加如下信息：
 * <p/>
 * <intent-filter> <action android:name="android.intent.action.MAIN" />
 * <p/>
 * <category android:name="android.intent.category.LAUNCHER" /> </intent-filter>
 * <intent-filter> <action android:name="android.nfc.action.NDEF_DISCOVERED" >
 * </action>
 * <p/>
 * <category android:name="android.intent.category.DEFAULT" > </category>
 * <p/>
 * <data android:mimeType="text/plain" > </data> </intent-filter>
 * <intent-filter> <action android:name="android.nfc.action.TECH_DISCOVERED" />
 * </intent-filter>
 * <p/>
 * <meta-data android:name="android.nfc.action.TECH_DISCOVERED" android:resource
 * ="@xml/nfc_tech_filter" />
 * <p/>
 * NFC工具类
 *
 * @author ztx
 */
public class NFCOperationUtil {
    /**
     * NFC标签为只读标签
     */
    public static final int NFC_DONT_WRITE = -7;
    /**
     * 写入数据过长
     */
    public static final int MESSAGE_TOO_LONG = -6;
    /**
     * 写入数据失败。
     */
    public static final int WRITE_FAIL = -5;
    /**
     * NFC卡，不支持DNEF格式的数据写入
     */
    public static final int UNPROVIDE_NDEF = -4;
    /**
     * 设置的待写入信息为null
     */
    public static final int MESSAGE_NULL = -3;
    /**
     * activity 为空
     */
    public static final int ACTIVITY_IS_NULL = -2;
    /**
     * 设备不支持NFC
     */
    public static final int NO_NFC = -1;
    /**
     * NFC功能未开启
     */
    public static final int NFC_CLOSED = 0;
    /**
     * NFC正常
     */
    public static final int NFC_OK = 1;

    /**
     * 读取NFC，ID
     */
    public static final int READ_ID = 1;
    /**
     * 读取NFC，的DNEF类型的信息
     */
    public static final int READ_MSG = 2;
    /**
     * 向NFC写入DNEF类型的信息
     */
    public static final int WRITE_MSG = 3;
    /**
     * 写数据失败的返回值
     */
    public static final String WRITE_FAIL_MSG = "false";
    /**
     * 写数据成功的返回值
     */
    public static final String WRITE_SUCCESS_MSG = "true";
    /**
     * 编码
     */
    private String charsetName = "UTF-8";
    /**
     * 对NFC卡的操作：读取id，读取存储的信息，写入信息
     */
    private int operationType = READ_ID;
    /**
     * 等待被写入NFC卡的信息
     */
    private String needWriteMsg;
    /**
     * 所在界面
     */
    private Activity activity;
    /**
     * Nfc适配器
     */
    private NfcAdapter nfcAdapter;
    /**
     * 延迟意图
     */
    private PendingIntent pendingIntent;
    /**
     *
     */
    private IntentFilter[] mFilters;
    /**
     *
     */
    private String[][] mTechLists;
    /**
     *
     */
    private IntentFilter ndef;

    /**
     * 权限： <uses-permission android:name="android.permission.NFC" />
     * <p/>
     * <uses-feature android:name="android.hardware.nfc" android:required="true"
     * />
     *
     * @param activity
     */
    public NFCOperationUtil(Activity activity) {
        this.activity = activity;
    }

    /**
     * 初始化NFC，并根据NFC的状态，返回相对应的信息
     *
     * @return 并根据NFC的状态，返回相对应的信息
     * @throws UnsupportedEncodingException
     * @throws Exception                    返回报错编号，－2:代表：activity为null，－1代表设备不支持NFC，0代表设备NFC功能未打开，
     *                                      详情见本类中常量。
     */
    public String init() throws UnsupportedEncodingException, Exception {
        if (activity == null) {
            // return ACTIVITY_IS_NULL;
            throw new Exception(ACTIVITY_IS_NULL + "");
        }
        int nfcType = ifNFCUse();
        if (nfcType == NO_NFC || nfcType == NFC_CLOSED) {
            // return nfcType;
            throw new Exception(nfcType + "");
        }
        // 将被调用的Intent，用于重复被Intent触发后将要执行的跳转
        pendingIntent = PendingIntent.getActivity(activity, 0,
                new Intent(activity, activity.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        // 设定要过滤的标签动作，这里只接收ACTION_NDEF_DISCOVERED类型和ACTION_TECH_DISCOVERED类型
        ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        ndef.addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
        ndef.addCategory("*/*");
        mFilters = new IntentFilter[]{ndef};// 过滤器
        mTechLists = new String[][]{new String[]{NfcA.class.getName()}, new String[]{NfcF.class.getName()},
                new String[]{NfcB.class.getName()}, new String[]{NfcV.class.getName()}};// 允许扫描的标签类型

        Intent intent = activity.getIntent();
        return doingOnNewIntent(intent);
    }

    /**
     * 写在Activity的onNewIntent中，返回值根据operationType类型不同，分别返回NFC卡的ID或Message。
     * 如果是写入数据的模式，写成功返回“true”，写失败返回“false”。
     * <p/>
     * Activity必须设置：android:launchMode="singleTop"，才能出发onNewIntent
     *
     * @param intent Intent
     * @return 并根据NFC的状态，返回相对应的信息
     * @throws UnsupportedEncodingException
     */
    public String doingOnNewIntent(Intent intent) throws UnsupportedEncodingException, Exception {
        if (intent != null) {
            if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())
                    || NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
                // System.out.println(intent.getAction());
                if (operationType == READ_ID) {
                    return byteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID));
                } else if (operationType == READ_MSG) {
                    return readNFCMessage(intent);
                } else if (operationType == WRITE_MSG) {
                    return writeNFCMessageNDEF(intent);
                }
            }
        }
        return null;
    }

    /**
     * 写在Activity中的onResume里。
     */
    public void doingOnResume() {
        // 前台分发系统,这里的作用在于第二次检测NFC标签时该应用有最高的捕获优先权.
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch(activity, pendingIntent, mFilters, mTechLists);
        }
    }

    /**
     * 写在Activity中的onPause里。
     */
    public void doingOnPause() {
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(activity);
        }
    }

    /**
     * 读取NFC标签NDEF数据的操作
     *
     * @param intent Intent
     * @return 标签中的内容
     * @throws UnsupportedEncodingException
     */
    private String readNFCMessage(Intent intent) throws UnsupportedEncodingException {
        Parcelable[] rawArray = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        if (rawArray != null) {
            NdefMessage mNdefMsg = (NdefMessage) rawArray[0];
            NdefRecord mNdefRecord = mNdefMsg.getRecords()[0];
            // try {
            if (mNdefRecord != null) {
                // readResult += "Tnf : " + mNdefRecord.getTnf() + " , ";
                // readResult += "Type : " + new
                // String(mNdefRecord.getType(), "UTF-8") + " , ";
                // readResult += "Message : " + new
                // String(mNdefRecord.getPayload(), "UTF-8") + " , ";
                // readResult += "Time : " + System.currentTimeMillis();
                return new String(mNdefRecord.getPayload(), charsetName);
            }
            // } catch (UnsupportedEncodingException e) {
            // e.printStackTrace();
            // }
        } else {

        }
        return null;
    }

    /**
     * 读取NFC中的NDEF类型的数据信息。
     *
     * @param intent Intent
     * @return 标签中的内容
     * @throws Exception
     */
    private String writeNFCMessageNDEF(Intent intent) throws Exception {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        Ndef ndef = Ndef.get(tag);
        // 构建数据包，也就是你要写入标签的数据
        NdefRecord ndefRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA, "text/plain".getBytes(), new byte[]{}, needWriteMsg.getBytes());
        NdefRecord[] records = {ndefRecord};
        NdefMessage ndefMessage = new NdefMessage(records);
        try {
            if (ndef != null) {
                // 数据的写入过程一定要有连接操作
                ndef.connect();
                int size = ndefMessage.toByteArray().length;
                if (!ndef.isWritable()) {
                    // Toast.makeText(this, "NFC Tag是只读的！",
                    // Toast.LENGTH_LONG).show();
                    // return;
                    throw new Exception(NFC_DONT_WRITE + "");
                }
                if (ndef.getMaxSize() < size) {
                    // Toast.makeText(this, "NFC Tag的空间不足！",
                    // Toast.LENGTH_LONG).show();
                    // return;
                    throw new Exception(MESSAGE_TOO_LONG + "");
                }
                ndef.writeNdefMessage(ndefMessage);
                // System.out.println("3....");
                // Toast.makeText(getApplicationContext(), "数据写入成功!",
                // Toast.LENGTH_SHORT).show();
                // displayControl(false);
                return WRITE_SUCCESS_MSG;
            } else {
                // 获取可以格式化和向标签写入数据NdefFormatable对象
                NdefFormatable format = NdefFormatable.get(tag);
                // 向非NDEF格式或未格式化的标签写入NDEF格式数据
                if (format != null) {
                    try {
                        // 允许对标签进行IO操作
                        format.connect();
                        format.format(ndefMessage);
                        // Toast.makeText(this, "已成功写入数据！",
                        // Toast.LENGTH_LONG).show();
                        // displayControl(false);
                        // return;
                        return WRITE_SUCCESS_MSG;
                    } catch (Exception e) {
                        // Toast.makeText(this, "写入NDEF格式数据失败！",
                        // Toast.LENGTH_LONG).show();
                        throw new Exception(WRITE_FAIL + "");
                    }
                } else {
                    // Toast.makeText(this, "NFC标签不支持NDEF格式！",
                    // Toast.LENGTH_LONG).show();
                    // return;
                    throw new Exception(UNPROVIDE_NDEF + "");
                }
                // displayControl(false);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (FormatException e) {

        }
        return WRITE_FAIL_MSG;
    }

    /**
     * 检测工作,判断设备的NFC支持情况
     *
     * @return －1:表示设备不支持NFC，0:表示NFC功能未开启，1:表示正常。
     */
    private int ifNFCUse() {
        nfcAdapter = NfcAdapter.getDefaultAdapter(activity);
        if (nfcAdapter == null) {
            // ifo_NFC.setText("设备不支持NFC！");
            return NO_NFC;
        }
        if (nfcAdapter != null && !nfcAdapter.isEnabled()) {
            // ifo_NFC.setText("请在系统设置中先启用NFC功能！");
            return NFC_CLOSED;
        }
        return NFC_OK;
    }

    /**
     * byte转String
     *
     * @param inarray byte数组
     * @return String字符串
     */
    private String byteArrayToHexString(byte[] inarray) { // converts byte
        // arrays to string
        int i, j, in;
        String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        String out = "";

        for (j = 0; j < inarray.length; ++j) {
            in = inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }

    public String getCharsetName() {
        return charsetName;
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationTypeReadID() {
        operationType = READ_ID;
    }

    public void setOperationTypeReadMsg() {
        operationType = READ_MSG;
    }

    /**
     * 设置需要写入NFC的数据。（不支持NULL）
     *
     * @param msg 待写入的信息
     * @throws Exception
     */
    public void setOperationTypeWriteMsg(String msg) throws Exception {
        if (msg == null) {
            throw new Exception(MESSAGE_NULL + "");
        } else {
            needWriteMsg = msg;
            operationType = WRITE_MSG;
        }
    }
}
