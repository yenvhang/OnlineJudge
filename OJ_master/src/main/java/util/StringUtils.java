package util;

public class StringUtils {
	public static boolean isEmpty(String s){
		if(s!=null&&!s.trim().isEmpty()){
			return false;
		}
		return true;
	}
}
