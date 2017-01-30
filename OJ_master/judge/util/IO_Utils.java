package util;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

public class IO_Utils {
	public static String getString(InputStream ins){
		BufferedInputStream buf =new BufferedInputStream(ins);
		byte [] errs =new byte[1024];
		StringBuilder sb =new StringBuilder();
		try {
			while(buf.read(errs)!=-1){
				sb.append(new String(errs));
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ins!=null)
					ins.close();
				if(buf!=null)
				buf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		
		return sb.toString();
	}
	
	
}
