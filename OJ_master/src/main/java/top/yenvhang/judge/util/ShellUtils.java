package top.yenvhang.judge.util;


import java.io.InputStream;

public class ShellUtils {
	public static boolean isRunOK(InputStream ins ){
		String msg =IOUtils.getString(ins);
		if(msg==null||msg.trim().length()<=0){
			return true;
		}
		System.out.println(msg);
		
		return false;
	}
}
