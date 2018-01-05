package com.commonsdk.file;

import android.text.TextUtils;

import com.commonsdk.string.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * File Utils
 * <ul>
 * Read or write file
 * <li>{@link #readFile(String, String)} read file</li>
 * <li>{@link #readFileToList(String, String)} read file to string list</li>
 * <li>{@link #writeFile(String, String, boolean)} write file from String</li>
 * <li>{@link #writeFile(String, String)} write file from String</li>
 * <li>{@link #writeFile(String, List, boolean)} write file from String List
 * </li>
 * <li>{@link #writeFile(String, List)} write file from String List</li>
 * <li>{@link #writeFile(String, InputStream)} write file</li>
 * <li>{@link #writeFile(String, InputStream, boolean)} write file</li>
 * <li>{@link #writeFile(File, InputStream)} write file</li>
 * <li>{@link #writeFile(File, InputStream, boolean)} write file</li>
 * </ul>
 * <ul>
 * Operate file
 * <li>{@link #moveFile(File, File)} or {@link #moveFile(String, String)}</li>
 * <li>{@link #copyFile(String, String)}</li>
 * <li>{@link #getFileExtension(String)}</li>
 * <li>{@link #getFileName(String)}</li>
 * <li>{@link #getFileNameWithoutExtension(String)}</li>
 * <li>{@link #deleteFile(String)}</li>
 * <li>{@link #isFileExist(String)}</li>
 * <li>{@link #isFolderExist(String)}</li>
 * <li>{@link #makeFolders(String)}</li>
 * <li>{@link #makeDirs(String)}</li>
 * </ul>
 * 文件读写，复制移动操作工具类
 *
 * @author ztx
 */
public class FileIOandOperation {
    /**
     *
     */
    public static final String FILE_EXTENSION_SEPARATOR = ".";
    /**
     * UTF_8
     */
    public static final String UTF_8 = "utf-8";

    private FileIOandOperation() {
        throw new AssertionError();
    }

    /**
     * read file
     * 读文件的内容
     *
     * @param filePath    目标文件路径
     * @param charsetName The name of a supported
     *                    {@link java.nio.charset.Charset </code>charset<code>}
     * @return if file not exist, return null, else return content of file
     * @throws RuntimeException if an error occurs while operator BufferedReader
     */
    public static StringBuilder readFile(String filePath, String charsetName) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        File file = new File(filePath);
        return readFile(file, charsetName);
    }

    /**
     * read file
     * 读文件的内容
     *
     * @param file        目标文件
     * @param charsetName The name of a supported
     *                    {@link java.nio.charset.Charset </code>charset<code>}
     * @return if file not exist, return null, else return content of file
     * @throws RuntimeException if an error occurs while operator BufferedReader
     */
    public static StringBuilder readFile(File file, String charsetName) {
        if (TextUtils.isEmpty(charsetName)) {
            charsetName = "UTF-8";
        }
        StringBuilder fileContent = new StringBuilder("");
        if (file == null || !file.isFile() || !file.exists()) {
            return null;
        }
        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(file), charsetName);
            reader = new BufferedReader(is);
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!fileContent.toString().equals("")) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * write file
     * 将content写入目标文件。
     *
     * @param filePath 文件
     * @param content  内容
     * @param append   is append, if true, write to the end of file, else clear
     *                 content of file and write into it
     * @return return false if content is empty, true otherwise
     * @throws RuntimeException if an error occurs while operator FileWriter
     */
    public static boolean writeFile(String filePath, String content, boolean append) {
        if (StringUtils.isEmpty(content)) {
            return false;
        }

        FileWriter fileWriter = null;
        try {
            makeDirs(filePath);
            fileWriter = new FileWriter(filePath, append);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            //throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
        return false;
    }

    /**
     * write file
     * 将list写入目标文件路径，保持list中一个item占用一行的格式。可设置写入模式：覆盖／追加。
     *
     * @param filePath    文件路径
     * @param contentList list内容
     * @param append      is append, if true, write to the end of file, else clear
     *                    content of file and write into it
     * @return return false if contentList is empty, true otherwise
     * @throws RuntimeException if an error occurs while operator FileWriter
     */

    public static boolean writeFile(String filePath, List<String> contentList,
                                    boolean append) {
        if (contentList == null || contentList.size() == 0) {
            return false;
        }

        FileWriter fileWriter = null;
        try {
            makeDirs(filePath);
            fileWriter = new FileWriter(filePath, append);
            int i = 0;
            for (String line : contentList) {
                if (i++ > 0) {
                    fileWriter.write("\r\n");
                }
                fileWriter.write(line);
            }
            fileWriter.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * write file, the string will be written to the begin of the file
     * 将content写入目标文件。
     *
     * @param file    文件
     * @param content 内容
     * @return 成功／失败
     */
    public static boolean writeFile(File file, String content) {
        return writeFile(file.getAbsolutePath(), content, false);
    }

    /**
     * 以字节的形式写入到文件。常用于多媒体
     *
     * @param filePath 文件路径
     * @param content  字节内容
     * @return 成功／失败
     */
    public static boolean writeByte2File(String filePath, byte[] content) {
        if (content == null) {
            return false;
        }
        FileOutputStream fos = null;
        try {
            makeDirs(filePath);
            fos = new FileOutputStream(filePath);
            fos.write(content);
            return true;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * write file, the string will be written to the begin of the file
     * 将content写入目标文件路径。
     *
     * @param filePath 文件路径
     * @param content  内容
     * @return 成功／失败
     */
    public static boolean writeFile(String filePath, String content) {
        return writeFile(filePath, content, false);
    }

    /**
     * write file, the string list will be written to the begin of the file
     * 将list中的内容写入文件，并且保持一个listItem占用一行的格式。
     *
     * @param filePath    目标文件路径
     * @param contentList 待写入的列表
     * @return 成功／失败。
     */
    public static boolean writeFile(String filePath, List<String> contentList) {
        return writeFile(filePath, contentList, false);
    }

    /**
     * write file, the bytes will be written to the begin of the file
     * 将输入流，写入到文件路径中，覆盖模式
     *
     * @param filePath 目标文件路径
     * @param stream   输入流
     * @return return true
     * @see {@link #writeFile(String, InputStream, boolean)}
     */
    public static boolean writeFile(String filePath, InputStream stream) {
        return writeFile(filePath, stream, false);
    }

    /**
     * write file
     * 将输入流，写入到文件路径中，可选择覆盖和追加，两种模式
     *
     * @param filePath the file to be opened for writing.
     * @param stream   the input stream
     * @param append   if <code>true</code>, then bytes will be written to the end of
     *                 the file rather than the beginning
     * @return return true
     * @throws RuntimeException if an error occurs while operator FileOutputStream
     */
    public static boolean writeFile(String filePath, InputStream stream, boolean append) {
        if (TextUtils.isEmpty(filePath) || stream == null) {
            return false;
        }
        return writeFile(new File(filePath), stream, append);
    }

    /**
     * write file, the bytes will be written to the begin of the file
     * 将输入流，写入到文件中，覆盖模式
     *
     * @param file   目标文件
     * @param stream 输入流
     * @return 成功／失败
     * @see {@link #writeFile(File, InputStream, boolean)}
     */
    public static boolean writeFile(File file, InputStream stream) {
        return writeFile(file, stream, false);
    }

    /**
     * write file
     * 将输入流，写入到文件中，可选择覆盖和追加，两种模式
     *
     * @param file   the file to be opened for writing.
     * @param stream the input stream
     * @param append if <code>true</code>, then bytes will be written to the end of
     *               the file rather than the beginning
     * @return return true
     * @throws RuntimeException if an error occurs while operator FileOutputStream
     */
    public static boolean writeFile(File file, InputStream stream, boolean append) {
        if (file == null || stream == null) {
            return false;
        }
        OutputStream o = null;
        try {
            makeDirs(file.getAbsolutePath());
            o = new FileOutputStream(file, append);
            byte[] data = new byte[1024];
            int length = -1;
            while ((length = stream.read(data)) != -1) {
                o.write(data, 0, length);
            }
            o.flush();
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (o != null) {
                try {
                    o.close();
                    stream.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * 移动文件
     *
     * @param sourceFilePath 需要被移动的文件路径
     * @param destFilePath   目标文件路径
     */
    public static void moveFile(String sourceFilePath, String destFilePath) {
        if (TextUtils.isEmpty(sourceFilePath) || TextUtils.isEmpty(destFilePath)) {
            throw new RuntimeException("Both sourceFilePath and destFilePath cannot be null.");
        }
        moveFile(new File(sourceFilePath), new File(destFilePath));
    }

    /**
     * 移动文件
     *
     * @param srcFile  需要被移动的文件
     * @param destFile 目标文件
     */
    public static void moveFile(File srcFile, File destFile) {
        boolean rename = srcFile.renameTo(destFile);
        if (!rename) {
            copyFile(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
            deleteFile(srcFile.getAbsolutePath());
        }
    }

    /**
     * 复制文件
     *
     * @param sourceFilePath 需要复制的文件
     * @param destFilePath   目标文件路径
     * @return 是否复制成功
     * @throws RuntimeException if an error occurs while operator FileOutputStream
     */
    public static boolean copyFile(String sourceFilePath, String destFilePath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sourceFilePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        }
        return writeFile(destFilePath, inputStream);
    }

    /**
     * read file to string list, a element of list is a line
     * 读取文件的内容到List中，List中一个Item放文件中的行内容。
     *
     * @param filePath    文件全路径
     * @param charsetName 编码
     * @return if file not exist, return null, else return content of file
     * @throws RuntimeException if an error occurs while operator BufferedReader
     */
    public static List<String> readFileToList(String filePath, String charsetName) {
        File file = new File(filePath);
        List<String> fileContent = new ArrayList<>();
        if (file == null || !file.isFile()) {
            return null;
        }

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(file), charsetName);
            reader = new BufferedReader(is);
            String line = null;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * get file name from path, not include suffix
     * <p/>
     * <pre>
     *      getFileNameWithoutExtension(null)               =   null
     *      getFileNameWithoutExtension("")                 =   ""
     *      getFileNameWithoutExtension("   ")              =   "   "
     *      getFileNameWithoutExtension("abc")              =   "abc"
     *      getFileNameWithoutExtension("a.mp3")            =   "a"
     *      getFileNameWithoutExtension("a.b.rmvb")         =   "a.b"
     *      getFileNameWithoutExtension("c:\\")              =   ""
     *      getFileNameWithoutExtension("c:\\a")             =   "a"
     *      getFileNameWithoutExtension("c:\\a.b")           =   "a"
     *      getFileNameWithoutExtension("c:a.txt\\a")        =   "a"
     *      getFileNameWithoutExtension("/home/admin")      =   "admin"
     *      getFileNameWithoutExtension("/home/admin/a.txt/b.mp3")  =   "b"
     * </pre>
     * 获取文件的名称
     *
     * @param filePath 文件全路径
     * @return 文件的名称和后缀
     */
    public static String getFileNameWithoutExtension(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }

        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (filePosi == -1) {
            return (extenPosi == -1 ? filePath : filePath.substring(0, extenPosi));
        }
        if (extenPosi == -1) {
            return filePath.substring(filePosi + 1);
        }
        return (filePosi < extenPosi ? filePath.substring(filePosi + 1, extenPosi) : filePath.substring(filePosi + 1));
    }

    /**
     * get file name from path, include suffix
     * <p/>
     * <pre>
     *      getFileName(null)               =   null
     *      getFileName("")                 =   ""
     *      getFileName("   ")              =   "   "
     *      getFileName("a.mp3")            =   "a.mp3"
     *      getFileName("a.b.rmvb")         =   "a.b.rmvb"
     *      getFileName("abc")              =   "abc"
     *      getFileName("c:\\")              =   ""
     *      getFileName("c:\\a")             =   "a"
     *      getFileName("c:\\a.b")           =   "a.b"
     *      getFileName("c:a.txt\\a")        =   "a"
     *      getFileName("/home/admin")      =   "admin"
     *      getFileName("/home/admin/a.txt/b.mp3")  =   "b.mp3"
     * </pre>
     * 获取文件的名称和后缀
     *
     * @param filePath 文件全路径
     * @return 文件的名称和后缀
     */
    public static String getFileName(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
    }

    /**
     * get folder name from path
     * <p/>
     * <pre>
     *      getFolderName(null)               =   null
     *      getFolderName("")                 =   ""
     *      getFolderName("   ")              =   ""
     *      getFolderName("a.mp3")            =   ""
     *      getFolderName("a.b.rmvb")         =   ""
     *      getFolderName("abc")              =   ""
     *      getFolderName("c:\\")              =   "c:"
     *      getFolderName("c:\\a")             =   "c:"
     *      getFolderName("c:\\a.b")           =   "c:"
     *      getFolderName("c:a.txt\\a")        =   "c:a.txt"
     *      getFolderName("c:a\\b\\c\\d.txt")    =   "c:a\\b\\c"
     *      getFolderName("/home/admin")      =   "/home"
     *      getFolderName("/home/admin/a.txt/b.mp3")  =   "/home/admin/a.txt"
     * </pre>
     * 获取所在文件的文件夹
     *
     * @param filePath 文件全路径
     * @return 文件的文件夹
     */
    public static String getFolderName(String filePath) {

        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }

    /**
     * get suffix of file from path
     * <p/>
     * <pre>
     *      getFileExtension(null)               =   ""
     *      getFileExtension("")                 =   ""
     *      getFileExtension("   ")              =   "   "
     *      getFileExtension("a.mp3")            =   "mp3"
     *      getFileExtension("a.b.rmvb")         =   "rmvb"
     *      getFileExtension("abc")              =   ""
     *      getFileExtension("c:\\")              =   ""
     *      getFileExtension("c:\\a")             =   ""
     *      getFileExtension("c:\\a.b")           =   "b"
     *      getFileExtension("c:a.txt\\a")        =   ""
     *      getFileExtension("/home/admin")      =   ""
     *      getFileExtension("/home/admin/a.txt/b")  =   ""
     *      getFileExtension("/home/admin/a.txt/b.mp3")  =   "mp3"
     * </pre>
     * 获取文件路径的后缀
     *
     * @param filePath 文件路径
     * @return 文件后缀
     */
    public static String getFileExtension(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return filePath;
        }

        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (extenPosi == -1) {
            return "";
        }
        return (filePosi >= extenPosi) ? "" : filePath.substring(extenPosi + 1);
    }

    /**
     * Creates the directory named by the trailing filename of this file,
     * including the complete directory path required to create this directory.
     * <br/>
     * <br/>
     * <ul>
     * <strong>Attentions:</strong>
     * <li>makeDirs("C:\\Users\\Trinea") can only create users folder</li>
     * <li>makeFolder("C:\\Users\\Trinea\\") can create Trinea folder</li>
     * </ul>
     * 创建文件夹
     *
     * @param filePath 文件路径字符串
     * @return true if the necessary directories have been created or the target
     * directory already exists, false one of the directories can not be
     * created.
     * <ul>
     * <li>if {@link FileIOandOperation#getFolderName(String)} return
     * null, return false</li>
     * <li>if target directory already exists, return true</li>
     * <li>return {@link File#makeFolder}</li>
     * </ul>
     */
    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);
        if (StringUtils.isEmpty(folderName)) {
            return false;
        }

        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }

    /**
     * 创建文件夹
     *
     * @param filePath 文件夹路径
     * @return 创建是否成功
     * @see #makeDirs(String)
     */
    public static boolean makeFolders(String filePath) {
        return makeDirs(filePath);
    }

    /**
     * Indicates if this file represents a file on the underlying file system.
     * 文件是否存在
     *
     * @param filePath 文件夹路径字符串
     * @return 文件是否存在
     */
    public static boolean isFileExist(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return false;
        }

        File file = new File(filePath);
        return (file.exists() && file.isFile());
    }

    /**
     * Indicates if this file represents a directory on the underlying file
     * system.
     * 文件夹是否存在
     *
     * @param directoryPath 文件夹路径字符串
     * @return 文件夹是否存在。
     */
    public static boolean isFolderExist(String directoryPath) {
        if (StringUtils.isBlank(directoryPath)) {
            return false;
        }

        File dire = new File(directoryPath);
        return (dire.exists() && dire.isDirectory());
    }

    /**
     * delete file or directory
     * <ul>
     * <li>if path is null or empty, return true</li>
     * <li>if path not exist, return true</li>
     * <li>if path exist, delete recursion. return true</li>
     * <ul>
     *
     * @param path 文件路径字符串
     * @return 成功／失败
     */
    public static boolean deleteFile(String path) {
        if (StringUtils.isBlank(path)) {
            return true;
        }

        File file = new File(path);
        return deleteFile(file);
        // if (!file.exists()) {
        // return true;
        // }
        // if (file.isFile()) {
        // return file.delete();
        // }
        // if (!file.isDirectory()) {
        // return false;
        // }
        // for (File f : file.listFiles()) {
        // if (f.isFile()) {
        // f.delete();
        // } else if (f.isDirectory()) {
        // deleteFile(f.getAbsolutePath());
        // }
        // }
        // return file.delete();
    }

    /**
     * 删除文件，或文件夹
     *
     * @param file 文件
     * @return 成功／失败
     */
    public static boolean deleteFile(File file) {
        if (file != null) {
            if (!file.exists()) {
                return true;
            }
            if (file.isFile()) {
                return file.delete();
            }
            if (!file.isDirectory()) {
                return false;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File f : listFiles) {
                    if (f.isFile()) {
                        f.delete();
                    } else if (f.isDirectory()) {
                        deleteFile(f.getAbsolutePath());
                    }
                }
                return file.delete();
            }
        }
        return false;
    }
}
