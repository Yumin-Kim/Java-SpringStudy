package makeanno; //package Element

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.sun.source.tree.Tree;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Set;

//class Element
@AutoService(Processor.class)
public class GitPushProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(GitPush.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(GitPush.class);
        for (Element element : elements){
            if (element.getKind() != ElementKind.INTERFACE) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Git push annotation can not bo used on " + element.getSimpleName());
            }else{
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing" + element);
            }
            TypeElement typeElement = (TypeElement) element;
            ClassName className = ClassName.get(typeElement);


            MethodSpec push = MethodSpec.methodBuilder("push")
                    .addModifiers(Modifier.PUBLIC)
                    .returns(String.class)
                    .addStatement("return %s", "git push origin master")
                    .build();

            TypeSpec GitBash = TypeSpec.classBuilder("GitBash")
                    .addModifiers(Modifier.PUBLIC)
                    .addSuperinterface(className)
                    .addMethod(push)
                    .build();
            Filer filer = processingEnv.getFiler();
            try {
                JavaFile.builder(className.packageName(),GitBash)
                        .build()
                        .writeTo(filer);
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR, "FATAL ERORR" + e);
                e.printStackTrace();
            }

        }
        return true;
    }

}
