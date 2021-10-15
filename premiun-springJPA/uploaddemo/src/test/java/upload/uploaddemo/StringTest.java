package upload.uploaddemo;

import org.junit.jupiter.api.Test;

public class StringTest {

    @Test
    public void test(){
        final String s = "test.png";
        final int i = s.lastIndexOf('.');
        final String substring = s.substring(i);
        System.out.println("substring = " + substring);
    }

}
