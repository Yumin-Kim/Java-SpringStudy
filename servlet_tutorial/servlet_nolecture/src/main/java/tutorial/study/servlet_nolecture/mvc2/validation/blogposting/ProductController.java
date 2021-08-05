package tutorial.study.servlet_nolecture.mvc2.validation.blogposting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(name = "/product")
public class ProductController {

    @GetMapping
    public Product productToReturnV1(@RequestBody @Valid Product product) {
        return product;
    }

}
