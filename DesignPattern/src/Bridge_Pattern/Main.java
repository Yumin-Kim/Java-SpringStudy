package Bridge_Pattern;
/*
  	브릿지 패턴
 	기능 계층과 구현  계층의 분리
 	 
 */
public class Main {
	public static void main(String[] args) {
		
		PrintMorseCode morsecode = new PrintMorseCode(new DefaultMSF());
		morsecode.g().a().r().a().m();
		System.out.println("");
		PrintMorseCode soundCode = new PrintMorseCode(new SoundMSF());
		soundCode.g().a().r().a().m();
	}
}
