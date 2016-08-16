package com.zwcwlw.safe.utils;

/**
 * 作者：zwcwlw on 2016/8/4 16:20
 * 邮箱:zwcwlw@126.com
 */

import java.io.File;

import android.os.Environment;
import android.os.StatFs;

/**
 * SD卡相关的辅助类
 */
public class SDCardUtils {
    private SDCardUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断SDCard是否可用
     *
     * @return
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

    }

    /**
     * 获取SD卡路径
     *
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    /**
     * 获取SD卡的剩余容量 单位byte
     *
     * @return
     */
    public static long getSDCardAllSize() {
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSDCardPath());
            // 获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // 获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte
     *
     * @param filePath
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath) {
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath();
        } else {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * 获取系统存储路径
     *
     * @return
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    /**
     * 获取SD卡存储目录
     *
     * @return
     */
    public static File getExternalStorDirectory() {
        return Environment.getExternalStorageDirectory();
    }

    /**
     * 截取文件后缀名
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

/**
 * 以下程序实现的功能是批量修改文件后缀：
 import java.io.*;
 * JAVA实现的批量更改文件后缀名的程序。
 *
 * @author rommnn

public class ExtBatchRename {

     * 修改程序。<br>
     * 内部递归调用，进行子目录的更名
     *
     * @param path
     * 路径
     * @param from
     * 原始的后缀名，包括那个(.点)
     * @param to
     * 改名的后缀，也包括那个(.点)

    public void reName(String path, String from, String to) {
        File f = new File(path);
        File[] fs = f.listFiles();
        for (int i = 0; i < fs.length; ++i) {
            File f2 = fs[i];
            if (f2.isDirectory()) {
                reName(f2.getPath(), from, to);
            } else {
                String name = f2.getName();
                if (name.endsWith(from)) {
                    f2.renameTo(new File(f2.getParent() + "/" + name.substring(0, name.indexOf(from)) + to));
                }
            }
        }
    }
    public static void main(String[] args) {
        ExtBatchRename rf = new ExtBatchRename();
        rf.reName("d:/www.laozizhu.com", ".jsp", ".html");
    }
}
 */
}