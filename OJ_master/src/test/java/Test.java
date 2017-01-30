import top.yenvhang.util.DigestUtils;

public class Test {
	@org.junit.Test
	public void getPassword(){
		System.out.println(DigestUtils.md5Hex("123456"));
	}
}
