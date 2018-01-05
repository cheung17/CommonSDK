package com.commonsdk.data;

import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * 用户信息常量
 *
 * @author ztx
 */
public class DataConstant {
    public static final String XUEDUAN_KEY = "xueduan";
    public static final String PPT_SUFFIX = "ppt";
    public static final String DOCX_SUFFIX = "docx";
    public static final String EXCEL_SUFFIX = "excel";
    public static final String DOC_SUFFIX = "doc";
    public static final String PACKAGE_WPS = "cn.wps.moffice_eng";

    /**
     * 学生
     */
    public static final int student = 0;
    /**
     * 家长
     */
    public static final int parent = student + 1;
    /**
     * 老师
     */
    public static final int teacher = parent + 1;
    /**
     * 用户状态，受控(Black)
     */
    public final static String Black = "Black";
    /**
     * 用户状态，自由(White)
     */
    public final static String White = "White";
    /**
     * 家长信息路径
     */
    public final static String parentsInfoSavePath = ".parents";
    /**
     * 学生信息路径
     */
    public final static String studentsInfoSavePath = ".students";
    /**
     * 作业端的用户信息
     */
    public final static String homeworkstuuser = ".homeworkstuuser";
    /**
     * 作业的cookie
     */
    public final static String homeworkcookie = ".homeworkcookie";
    /**
     * 老师信息路径
     */
    public final static String teachersInfoSavePath = ".teachers";
    /**
     * 本机所安装的app信息
     */
    public final static String installedAppInfoFile = ".installedAppInfoFile";
    /**
     * 用户大头像信息路径
     */
    public final static String bigheadImagePath = ".bigHeadImage";
    /**
     * 用户小头像信息路径
     */
    public final static String smallheadImagePath = ".smallHeadImage";
    /**
     * 用于判断当前登录的用户是哪一个
     */
    public final static String currentInfoSavePath = ".current.message";
    /**
     * 存储上一次登陆的用户id，班级锁在跳过登陆时，判断上一个用户的班级锁状态。
     */
    public final static String lastLoginUserInfoSavePath = ".lastLogin.message";
    /**
     * 用户的cookie信息
     */
    public final static String cookieMessage = ".cookie.message";
    /**
     * 学生桌面，学习工具模块，离线列表（代理商使用，通过一键装机来设置该文件，学生桌面只负责读取并执行该文件中的内容）
     */
    public final static String educationTools = ".educationTools.message";
    /**
     * qimon信息保存路径
     */
    public static File qimon = new File(Environment.getExternalStorageDirectory(), ".qimon/");
    /**
     * qimon用户相关信息保存路径
     */
    public static File sfDir = new File(qimon, ".userinfo/");

    /**
     * qimon学生信息保存路径 ，处于 {@link#sfDir} 目录下。
     */
    public static File studentsDir = new File(sfDir, studentsInfoSavePath);
    /**
     * qimon学生信息(作业的)保存路径 ，处于 {@link#sfDir} 目录下。 文件，只保存一个用户
     */
    public static File homeworkstuDir = new File(sfDir, homeworkstuuser);
    /*
    *保存学生作业产生的cookie
     */
    public static File homeworkCookieDir = new File(sfDir, homeworkcookie);
    /**
     * qimon家长信息保存路径 ，处于 {@link#sfDir} 目录下。
     */
    public static File parentsDir = new File(sfDir, parentsInfoSavePath);
    /**
     * qimon老师信息保存路径 ，处于 {@link#sfDir} 目录下。
     */
    public static File teachersDir = new File(sfDir, teachersInfoSavePath);
    /**
     * 本机所安装的应用信息保存文件 ，处于 {@link#sfDir} 目录下。
     */
    public static File LocalInstalledAppInfo = new File(sfDir, installedAppInfoFile);
    /**
     * 存储当前登录的学生用户特征信息，如：type为学生，账号为1111111，用于判断从哪一个文件读取用户信息数据。 <li>采用json格式存储，例如：{ usertype:1,id:"11111111"}</li>
     */
    public static File currentStudentUserInfo = new File(studentsDir, currentInfoSavePath);
    /**
     * 存储当前登录的家长用户特征信息，如：type为学生，账号为1111111，用于判断从哪一个文件读取用户信息数据。 <li>采用json格式存储，例如：{ usertype:1,id:"11111111"}</li>
     */
    public static File currentParentUserInfo = new File(parentsDir, currentInfoSavePath);
    /**
     * 存储当前登录的老师用户特征信息，如：type为学生，账号为1111111，用于判断从哪一个文件读取用户信息数据。 <li>采用json格式存储，例如：{ usertype:1,id:"11111111"}</li>
     */
    public static File currentTeacherUserInfo = new File(teachersDir, currentInfoSavePath);
    /**
     * 存储上一次登录的学生用户特征信息，班级锁在跳过登陆的状态下，使用。
     */
    public static File lastLoginStudentUserInfo = new File(studentsDir, lastLoginUserInfoSavePath);
    /**
     * 存储上一次登录的家长用户特征信息，班级锁在跳过登陆的状态下，使用。
     */
    public static File lastLoginParentUserInfo = new File(parentsDir, lastLoginUserInfoSavePath);
    /**
     * 存储上一次登录的老师用户特征信息，班级锁在跳过登陆的状态下，使用。
     */
    public static File lastLoginTeacherUserInfo = new File(teachersDir, lastLoginUserInfoSavePath);

    /**
     * 存储当前登录的学生cookie信息。
     */
    public static File studentCookieMessage = new File(studentsDir, cookieMessage);
    /**
     * 存储当前登录的家长cookie信息。
     */
    public static File parentCookieMessage = new File(parentsDir, cookieMessage);
    /**
     * 存储当前登录的老师cookie信息。
     */
    public static File teacherCookieMessage = new File(teachersDir, cookieMessage);

    /**
     * 用于存储用户信息的文件。其路径为：{@link#studentsDir}/{@link#parentsDir}/ {@link#teachersDir} + 以账号命名的文件夹 + {@link#userInfoFile}
     */
    public static final String userInfoFile = ".userdata.provider";
    /**
     * 用于存储用户日志开关状态的文件。其路径为：{@link#studentsDir}/{@link#parentsDir}/ {@link#teachersDir} + 以账号命名的文件夹 + {@link#userInfoFile}
     */
    public static final String logSwitchFileName = ".logSwitch.message";
    /**
     * 用于存储用户白名单的文件。其路径为：{@link#studentsDir}/{@link#parentsDir}/ {@link#teachersDir} + 以账号命名的文件夹 + {@link#userInfoFile}
     */
    public static final String whiteListFileName = ".whiteListInfo.message";
    /**
     * 用于存储用户必装名单的文件。其路径为：{@link#studentsDir}/{@link#parentsDir}/ {@link#teachersDir} + 以账号命名的文件夹 + {@link#userInfoFile}
     */
    public static final String necessaryListFileName = ".necessaryListInfo.message";
    /**
     * 用于存储用户gps的文件。其路径为：{@link#studentsDir}/{@link#parentsDir}/ {@link#teachersDir} + 以账号命名的文件夹 + {@link#userInfoFile}
     */
    public static final String GpsFileName = ".gps.message";
    /**
     * 用于存储用户定位服务状态的文件。其路径为：{@link#studentsDir}/{@link#parentsDir}/ {@link#teachersDir} + 以账号命名的文件夹 + {@link#userInfoFile}
     */
    public static final String LocationServiceFileName = ".locationService.message";
    /**
     * 用于存储用户移动数据状态的文件。其路径为：{@link#studentsDir}/{@link#parentsDir}/ {@link#teachersDir} + 以账号命名的文件夹 + {@link#userInfoFile}
     */
    public static final String WirelessFileName = ".wireless.message";
    /**
     * 用于存储用户系统锁屏密码的文件。其路径为：{@link#studentsDir}/{@link#parentsDir}/ {@link#teachersDir} + 以账号命名的文件夹 + {@link#userInfoFile}
     */
    public static final String SystemLockFileName = ".systemLock.message";
    /**
     * 用于存储用户手势解锁信息。其路径为：{@link#studentsDir}/{@link#parentsDir}/ {@link#teachersDir} + 以账号命名的文件夹 + {@link#LOCK_PATTERN_FILE}
     */
    public static final String LOCK_PATTERN_FILE = ".gesture.key";
    /**
     * 用于存储用户课程表信息。其路径为：{@link#studentsDir}/{@link#parentsDir}/ {@link#teachersDir} + 以账号命名的文件夹 + {@link#COURSE_FILE}
     */
    public static final String COURSE_FILE = ".course.message";
    /**
     * 用于存储要强制用户卸载的应用。其路径为：{@link#studentsDir}/{@link#parentsDir}/ {@link#teachersDir} + 以账号命名的文件夹 + {@link#COURSE_FILE}
     */
    public static final String UNINSTALL_APP = ".uninstall_app.message";
    /**
     * 用于存储要强制用户卸载的应用。其路径为：{@link#studentsDir}/{@link#parentsDir}/ {@link#teachersDir} + 以账号命名的文件夹 + {@link#COURSE_FILE}
     */
    public static final String GESTURE_WRONG = ".gesturewrong.message";
    /**
     * 用于存储用户学习工具应用列表信息的文件。其路径为：{@link#studentsDir}/{@link#parentsDir}/ {@link#teachersDir} + LearnTools + {@link#LearnToolsAppListFileName}
     */
    public static final String LearnToolsAppListFileName = ".learnToolsAppList.message";
    /**
     * 用于存储用户同步教材应用列表信息的文件。其路径为：{@link#studentsDir}/{@link#parentsDir}/ {@link#teachersDir} + SynchMaterial + {@link#LearnToolsAppListFileName}
     */
    public static final String SynchMaterialAppListFileName = ".SynchMaterial.message";
    /**
     * 存储用户的圆形头像。其路径为：{@link#studentsDir}/{@link#parentsDir}/ {@link#teachersDir} + 以账号命名的文件夹
     */
    public static final String userInfoHeadImage = "headImage.png";
    //////////
    ////////////////////////管控的一些文件名
    public static final String QimonStoreWhiteList = ".qimonstorewhitelist";
    public static final String QimonNetworkWhiteList = ".qimonnetworkwhitelist";
    public static final String QimonNetworkBlackList = ".qimonnetworkblacklist";
    //
    public static final String QimonAtOnceLockScreen = ".qimonatoncelockscreen";
    public static final String QimonAtClassMateLock = ".qimonClassMateLocking";
    public static final String QimonAtOnceNetwork = ".qimonatoncenetwork";
    public static final String QimonAtOnceGame = ".qimonatoncegame";
    public static final String QimonAtOnceApp = ".qimonatonceapp";
    public static final String QimonAtOncePhone = ".qimonatoncephone";
    public static final String QimonAtOnceLocation = ".qimonatoncelocation";
    public static final String QIMON_AT_ONCE_EYE = ".qimonatonceeye";
    //
    public static final String QimonControlLocation = ".qimoncontrollocation";
    public static final String QimonControlLockScreen = ".qimoncontrollockscreen";
    public static final String QimonControlGame = ".qimoncontrolgame";
    public static final String QimonControlNetwork = ".qimoncontrolnetwork";
    public static final String QimonControlPhone = ".qimoncontrolphone";
    public static final String QimonControlApp = ".qimoncontrolapp";
    //
    // ********************************************userInfoFile里面的数据解析时使用。
    /**
     * 从用户中心读取过来的人员对象KEY
     */
    public static final String PEOPLE = "people";
    /**
     * 从用户中心读取过来的家长对象KEY
     */
    public static final String PARENTS = "parents";
    /**
     * 从用户中心读取过来的学生对象KEY
     */
    public static final String STUDENTS = "students";
    /**
     * 从用户中心读取过来的老师对象KEY
     */
    public static final String TEACHERS = "teachers";
    /**
     * 从用户中心读取过来的学校对象KEY
     */
    public static final String SCHOOLS = "schools";
    /**
     * 从用户中心读取过来的设备对象KEY
     */
    public static final String MACHINE = "MACHINE";
    /**
     * 数据操作的状态
     */
    public static final String DATAOPERATESTATE = "DATAOPERATESTATE";
    /**
     * 数据操作的状态成功
     */
    public static final String DATAOPERATESTATE_SUCCESS = "DATAOPERATESTATE_SUCCESS";
    /**
     * 数据操作的状态失败
     */
    public static final String DATAOPERATESTATE_FAIL = "DATAOPERATESTATE_FAIL";
    /**
     * 数据操作的提示信息
     */
    public static final String DATAOPERATEMSG = "DATAOPERATEMSG";

    static {
        if (!sfDir.exists()) {
            sfDir.mkdirs();
        }
        if (!studentsDir.exists()) {
            studentsDir.mkdirs();
        }
        if (!parentsDir.exists()) {
            parentsDir.mkdirs();
        }
        if (!teachersDir.exists()) {
            teachersDir.mkdirs();
        }
        if (!currentStudentUserInfo.exists()) {
            try {
                currentStudentUserInfo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!currentParentUserInfo.exists()) {
            try {
                currentParentUserInfo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!currentTeacherUserInfo.exists()) {
            try {
                currentTeacherUserInfo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!LocalInstalledAppInfo.exists()) {
            try {
                LocalInstalledAppInfo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //////////////////////////////////////////////////////////////////
    /**
     * 缓存图片存放路径
     **/
    public static final String PHOTO_PATH_NAME = "photo";
    /**
     * 缓存录音的目录
     **/
    public static final String AUDIO_PATH_NAME = "audio";
    /**
     * 存放下载应用的目录
     **/
    public static final String APP_PATH_NAME = "app";
    ///////////////////////////////////////////////////////////////////
    //

    /**
     * 用户切换时的Action
     */
    public static final String UserChange = "com.qimon.userdata.accountchange";
    /**
     * UserData数据变化时的Action
     */
    public static final String DataChange = "com.qimon.userdata.datachange";

    /**
     * 通用信息静态常量类。
     *
     * @author ztx
     */
    public class peoplesConstant {
        /**
         * 融云的KEY
         */
        public static final String RONG_TOKEN = "rong_token";
        /**
         * 系统生成的id编号（全局唯一）
         */
        public static final String ID = "id";
        /**
         * 姓名
         */
        public static final String NAME = "name";
        /**
         * 性别
         */
        public static final String SEX = "sex";
        /**
         * 出生日期
         */
        public static final String BIRTHDAY = "birthday";
        /**
         * 民族
         */
        public static final String FOLK = "folk";
        /**
         * 政治面貌
         */
        public static final String POLITICALSTATUS = "politicalstatus";
        /**
         * 手机号
         */
        public static final String MOBILE = "mobile";
        /**
         * 所在城市
         */
        public static final String CITYNAME = "cityname";
        /**
         * 现居地址
         */
        public static final String ADDRESS = "address";
        /**
         * 邮箱
         */
        public static final String EMAIL = "email";
        /**
         * 身份证号码
         */
        public static final String CARDID = "cardid";
        /**
         * 用户状态，受控(Black)/自由(White)
         */
        public static final String USERSTATE = "userstate";
        /**
         * 最后一次登录经度
         */
        public static final String LASTLONGITUDE = "lastlongitude";
        /**
         * 最后一次登录纬度
         */
        public static final String LASTLATITUDE = "lastlatitude";
        /**
         * 最大连续登录天数
         */
        public static final String MAXCONTINUENUMBER = "maxcontinuenumber";
        /**
         * 当前连续登录天数
         */
        public static final String CURCONTINUENUMBER = "curcontinuenumber";
        /****/
        public static final String CODE_KEY = "code";
        /**
         * vip等级
         */
        public static final String VIPLEVEL = "viplevel";
        /**
         * 用户级别
         */
        public static final String USERLEVEL = "userlevel";
        /**
         * 昵称
         */
        public static final String NICKNAME = "nickname";
        /**
         * 小头像（本地路径）
         */
        public static final String BIGHEADPATH = "bigheadpath";
        /**
         * 大头像（本地路径）
         */
        public static final String SMALLHEADPATH = "smallheadpath";
        /**
         * 大头像（服务器下载地址）
         */
        public static final String BIGHEADURL = "bigheadurl";
        /**
         * 小头像（服务器下载地址）
         */
        public static final String SMALLHEADURL = "smallheadurl";
        /**
         * 账号
         */
        public static final String USERNAME = "username";
        /**
         * 密码（MD5值）
         */
        public static final String LOGINPASSWORD = "loginpassword";

        /**
         * 用户积分
         */
        public static final String POINTS = "points";
        /**
         * 启檬币
         */
        public static final String QMCOIN = "qmcoin";
        /***
         * 会员名：白金会员
         */
        public static final String LEVEL_NAME = "level_name";
        /***
         * 会员折扣率
         */
        public static final String VIP_DISCOUNT = "vip_discount";
        /**
         * 是不是管理员
         */
        public static final String MANAGER_KEY = "manager";
    }

    /**
     * 家长信息静态常量类。
     *
     * @author ztx
     */
    public class parentsConstant {
        /**
         * 职业
         */
        public static final String PROFESSIONAL = "professional";
        /**
         * 工作单位
         */
        public static final String WORKUNI = "workuni";
        /**
         * 与孩子的关系，供学生客户端登录时使用。家长登录时，请参考students对象中的TYPE4PARENT字段。
         */
        public static final String TYPE4child = "TYPE4child";
    }

    /**
     * 学生信息静态常量类。
     *
     * @author ztx
     */
    public class studentsConstant {
        /**
         * 年级
         */
        public static final String GRADE = "grade";
        /**
         * 现就读学校（正规文凭学校）
         */
        public static final String ATTENDSCHOOL = "attendschool";
        /**
         * 与家长的关系，供家长客户端登录时使用。学生登录时，请参考parents对象中的TYPE4CHILD字段
         */
        public static final String TYPE4PARENT = "type4parent";
    }

    /**
     * 老师信息静态常量类。
     *
     * @author ztx
     */
    public class teachersConstant {
        /**
         * 职称
         */
        public static final String TITLE = "title";
        /**
         * 任教学校（主要的）
         */
        public static final String TEACHSCHOOL = "teachschool";
        /**
         * 毕业学校
         */
        public static final String GRADUATESCHOOL = "graduateschool";
        /**
         * 学科名字
         **/
        public static final String SUBJECT_KEY = "subjectname";//subjectname
    }

    /**
     * 学校信息静态常量类。
     *
     * @author ztx
     */
    public class schoolsConstant {
        /**
         * 学校名字
         */
        public static final String NAME = "name";
        /**
         * 所在班级名字
         */
        public static final String CLASSNAME = "classname";
        /**
         * 学校地址
         */
        public static final String ADDRESS = "address";
        /**
         * 对于老师讲，是教授的课程（多个，包括兼职）；对于学生讲，是学习的课程（多个，包括培训课）
         */
        public static final String MAJORS = "majors";
        /**
         * 学校类别，培训机构，正规学校
         */
        public static final String TYPE = "type";
        /**
         * 学校编号（唯一标示）
         */
        public static final String ID = "id";
        /***
         * 班级ID
         */
        public static final String CLASS_ID_KEY = "classid";
    }

    /**
     * 机器参数静态常量类。
     *
     * @author ztx
     */
    public class machineConstant {
        /**
         * 手机IMEI号
         */
        public static final String IMEI = "imei";
        /**
         * 手机IMEI号
         */
        public static final String IMSI = "imsi";
        /**
         * 手机型号 Build.MODEL
         */
        public static final String MODEL = "model";
        /**
         * 手机品牌 Build.BRAND
         */
        public static final String BRAND = "brand";
        /**
         * 手机操作系统版本
         */
        public static final String OS = "os";
    }

    /**
     * currentUserInfo文件中包含的数据静态常量类。
     *
     * @author ztx
     */
    public class currentUserInfoContent {
        /**
         * 标示当前登录的用户的类型。{@link#student}=0，{@link#parent}=1，{@link#teacher}=2
         */
        public static final String USERTYPE = "usertype";
        /**
         * 当前用户的账号，通过账号找到对应的文件夹
         */
        public static final String ID = "id";
    }
}
