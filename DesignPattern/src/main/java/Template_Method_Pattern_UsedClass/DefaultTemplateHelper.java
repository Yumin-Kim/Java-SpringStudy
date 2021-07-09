package Template_Method_Pattern_UsedClass;

public class DefaultTemplateHelper extends AbstTemplateHelper {

	@Override
	protected String doSecurity(String string) {
		System.out.println("강화된 디코드 중");
		return string;
	}

	@Override
	protected boolean authentication(String id, String password) {
		System.out.println("아이디 암호 확인 과정");
		return true;
	}

	@Override
	protected int authorization(String userName) {
		System.out.println("권한 확인 과정");
		return 0;
	}

	@Override
	protected String connection(String ingo) {
		System.out.println("마지막 접속 단계");
		return ingo;
	}

}
