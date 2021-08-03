package tutorial.study.servlet_nolecture.basic.frontcontroller.v3;

import tutorial.study.servlet_nolecture.basic.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);

}
