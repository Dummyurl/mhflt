package com.changju.fanlitou.util;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by chengww on 2017/5/16.
 */

public class FileUtils {

    /**
     * 保存数据在本地
     * @param context
     * @param filename
     * @param content
     */
    public static void saveData(Context context,String filename, String content){
        FileOutputStream outputStream;
        try{
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取数据，只能取缓存的json，不能取保存的密码
     * @param context
     * @param filename
     * @return
     */
    public static String getData(Context context,String filename){

        String[] files = context.fileList();
        boolean isExist = false;
        for(String file : files){
            if (file.equals(filename)){
                isExist = true;
            }
        }
        //结果
        String result = null;

        if (isExist){
            FileInputStream in;
            ByteArrayOutputStream bout;
            byte[]buf = new byte[1024];
            bout = new ByteArrayOutputStream();
            int length = 0;
            try {
                in = context.openFileInput(filename); //获得输入流
                while((length=in.read(buf))!=-1){
                    bout.write(buf,0,length);
                }
                byte[] content = bout.toByteArray();
                if (content != null)
                    result = new String(content,"utf-8");
                in.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 取数据，只能取保存的密码，不能取缓存的json
     * @param context
     * @param filename
     * @return
     */
    public static byte[] getDataByte(Context context,String filename){

        String[] files = context.fileList();
        boolean isExist = false;
        for(String file : files){
            if (file.equals(filename)){
                isExist = true;
            }
        }
        //结果
        byte[] content = null;

        if (isExist){
            FileInputStream in;
            ByteArrayOutputStream bout;
            byte[]buf = new byte[1024];
            bout = new ByteArrayOutputStream();
            int length = 0;
            try {
                in = context.openFileInput(filename); //获得输入流
                while((length=in.read(buf))!=-1){
                    bout.write(buf,0,length);
                }
                content = bout.toByteArray();
                in.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    public static boolean deleteData(Context context,String filename) {
        return context.deleteFile(filename);
    }

    //对象保存在本地
    public void fileSave2Local(Context context,Object obj,String fileName) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            //通过openFileOutput方法得到一个输出流
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj); //写入
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) oos.close();
                if (fos != null) fos.close(); //最后关闭输出流
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Object readFileFromLocal(String fileName) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            Object obj = objectInputStream.readObject();

            return obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) fileInputStream.close();
                if (objectInputStream != null) objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
