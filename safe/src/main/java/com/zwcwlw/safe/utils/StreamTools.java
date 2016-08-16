package com.zwcwlw.safe.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 作者：zwcwlw on 2016/8/4 17:19
 * 邮箱:zwcwlw@126.com
 * 描述:流的工具类
 */
public class StreamTools {
    //读取一个流写到内存里面
    public static String readStream(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        is.close();
        return bos.toString();
    }
}
