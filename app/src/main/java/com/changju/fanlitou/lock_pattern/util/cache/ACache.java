package com.changju.fanlitou.lock_pattern.util.cache;


import android.content.Context;
import android.text.TextUtils;

import com.changju.fanlitou.util.FileUtils;

import java.io.FileOutputStream;

public class ACache {

    private Context context;
    private static ACache mACahe;
	public static ACache get(Context context) {
		if (mACahe == null){
            synchronized (ACache.class){
                if (mACahe == null){
                    mACahe = new ACache();
                }
            }

        }
        mACahe.context = context;
		return mACahe;
	}

	private ACache(){}


    public byte[] getAsBinary(String gesturePassword) {
        return FileUtils.getDataByte(context,gesturePassword);
    }

    public void put(String gesturePassword, byte[] bytes) {

        FileOutputStream outputStream;
        try{
            outputStream = context.openFileOutput(gesturePassword, Context.MODE_PRIVATE);
            outputStream.write(bytes);
            outputStream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean hasGesture(String gesturePassword){
        byte[] bytes = getAsBinary(gesturePassword);
        if (bytes == null)
            return false;
        String password = new String(bytes);
        return !(TextUtils.isEmpty(password));
    }

    public void clearGesture(String gesturePassword){
        FileUtils.saveData(context,gesturePassword,"");
    }
}
