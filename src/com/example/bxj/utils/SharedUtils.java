package com.example.bxj.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.util.Log;

//实现标记的写入和读取
public class SharedUtils {
	private static final String FILE_NAME="fangdazhongdianping";
	private static final String MODE_NAME="welcome";
	//获取boolean值
	public static boolean isFirstStart(Context context)
	{
		Log.e("jhd", "SharedUtils-isFirstStart   :  "+context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getBoolean(MODE_NAME, true));
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getBoolean(MODE_NAME, true);

	}
	//写入
	public static void putIsFirstStart(Context context,boolean isFirst){
		Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND).edit();
		editor.putBoolean(MODE_NAME, isFirst);
		editor.commit();
		Log.e("jhd", "SharedUtils-put    :  "+isFirst);
	}
}
