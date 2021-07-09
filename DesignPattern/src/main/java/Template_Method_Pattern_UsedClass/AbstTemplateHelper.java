package Template_Method_Pattern_UsedClass;

public abstract class AbstTemplateHelper {
	
	protected abstract String doSecurity(String string);
	protected abstract boolean authentication(String id,String password);
	protected abstract int authorization(String userName);
	protected abstract String connection(String ingo);
	
	//템플릿 메소드
	public String requestConnection(String encodedInfo) {
		
		//보안 작업 >> 암호화된 문자열 디코드
		String decodedInfo = doSecurity(encodedInfo);
		//인증과정 반환된 아이디 암호를 할당한다.
		String id ="aaa" , password ="bbb";
		if (!authentication(id,password)) {
			throw new Error("아이디 암호 불일치");
		}
		String userName = "UserName";
		int i = authorization(userName);
		switch(i){
		case 0 :
			System.out.println("user1");
			break;
		case 1 :
			System.out.println("user2");
			break;
		case 2 :
			System.out.println("user3");
			break;
		default:
			break;
		}
		return connection(decodedInfo);
	}

}
