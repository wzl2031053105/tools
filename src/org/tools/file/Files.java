package org.tools.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Files {
    public static void main(String[] args) {
        String path = "D:\\工作\\一账通文件\\";
        String syncDateStr = DateFormatUtils.format(new Date(), "yyyyMMdd");
        getFile(path+syncDateStr);
    }

    /**
     * 获取指定文件夹下所有文件名
     * @param path
     */
    private static void getFile(String path) {
        // get file list where the path has
        File file = new File(path);
        // get the folder list
        if(!file.exists()){
            System.out.println(path+"不存在");
            return;
        }
        File[] array = file.listFiles();
        List<String> files = new ArrayList<String>();
        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile()) {
                // only take file name
//                System.out.println("^^^^^" + array[i].getName());
                // take file path and name
//                System.out.println("#####" + array[i]);
                // take file path and name
                files.add(array[i].getPath());
                System.out.println("*****" + array[i].getPath());
            } else if (array[i].isDirectory()) {
                //getFile(array[i].getPath());
                System.out.println(array[i].getName()+"为文件夹");
            }
        }
        getCount2(files,1);
    }
    /**
     * 获取指定文件的行数
     * @param files
     * @param a 每个文件不算数
     * @return
     */
    public static int getCount(List<String> files,int a){
        int i = 0;
        if(files == null ||files.size() == 0){
            System.out.println("传入的文件路径为空");
            return 0;
        }
        for (String fileName:files){
            File file = new File(fileName);
            LineIterator iterator = null;
            try {
                iterator = FileUtils.lineIterator(file, "utf-8");
                while (iterator.hasNext()) {
                    i++;
                }
            } catch (Exception e) {
                System.out.println("文件读取异常,fileName=" + file+";e:"+e.getMessage());
            }
        }
        System.out.println(i-files.size()*a);
        return i-files.size()*a;
    }/**
     * 获取指定文件的行数
     * @param files
     * @param a 每个文件不算数
     * @return
     */
    public static int getCount2(List<String> files,int a){
        int i = 0;
        if(files == null ||files.size() == 0){
            System.out.println("传入的文件路径为空");
            return 0;
        }
        for (String fileName:files){

            File file = new File(fileName);//读取文件

            BufferedInputStream fis = null;
            try {
                fis = new BufferedInputStream(new FileInputStream(file));
                BufferedReader buffer = new BufferedReader(new InputStreamReader(fis,"utf-8"),20*1024*1024);//设缓存区
                String temp = ""; // 临时字符串
                while ((temp = buffer.readLine()) != null) { // 读文件,一行读一个
                    i++;
                }
                fis.close();
            } catch (Exception e) {
                System.out.println("文件读取异常,fileName=" + file+";e:"+e.getMessage());
            }
        }
        System.out.println(i-files.size()*a);
        return i-files.size()*a;
    }
}
