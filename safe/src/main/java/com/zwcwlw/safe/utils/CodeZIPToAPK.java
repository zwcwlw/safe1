package com.zwcwlw.safe.utils;

/**
 * 作者：zwcwlw on 2016/8/6 14:37
 * 邮箱:zwcwlw@126.com
 * 描述://TODO
 */
public class CodeZIPToAPK {

//    public static void main(String[] args) throws Exception {
//        /*第一步：获取文件目录*/
//        File dir = new File("d:/code/java");
//        if(!(dir.exists()&&dir.isDirectory())) {
//            throw new Exception("目录" + dir.getAbsolutePath() + "不存在");
//        }
//        /*第二步：列出该目录下所有的.java文件*/
//        File[] files = dir.listFiles(new FilenameFilter() {
//            //获取.java文件时使用listFiles(FilenameFilter filter)方法，创建一个过滤文件名的Filter
//            @Override
//            public boolean accept(File dir, String name) {
//                if(name != null && "".equals("")) {
//                    //检测文件名是否是以.java结尾，是返回true，否则继续检测下一个文件
//                    if(name.toLowerCase().endsWith(".java")) {
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });
//        /*第三步：获取目标文件夹，如果不存在就建立该文件夹*/
//        File destDir = new File("d:/code/java/jad");
//        if(!destDir.exists()) {
//            destDir.mkdir();
//        }
//        for(File file : files) {
//            System.out.println(file.getName());
//            FileInputStream fis = new FileInputStream(file);
//            /*第四步：将得到的文件名称的扩展名改为.jad*/
//            String destFileName = file.getName().replaceAll("\\.java$", "\\.jad");
//            FileOutputStream fos = new FileOutputStream(new File(destDir, destFileName));
//            /*第五步：将文件重新写入目标文件夹*/
//            copy(fis, fos);
//            fis.close();
//            fos.close();
//        }
//    }
//
//    public static void copy(InputStream in, OutputStream out) throws Exception {
//        byte[] buf = new byte[1024];
//        int len = 0;
//        /*读取文件内容并写入文件字节流中*/
//        while((len = in.read(buf))!=-1) {
//            out.write(buf, 0, len);
//        }
//    }
}
