package upload.uploaddemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/servlet/v2")
public class ServletV2UploadController {

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFilev2(){
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFileV2(HttpServletRequest request) throws ServletException, IOException {
        log.info("request = {}", request);

        String itemName = request.getParameter("itemName");
        log.info(itemName);

        final Collection<Part> parts = request.getParts();
        log.info("part = {}", parts);

        for (Part part : parts) {
            log.info("===Part===");
            log.info("name = {}", part.getName());
            final Collection<String> headerNames = part.getHeaderNames();
            for (String headerName : headerNames) {
                log.info("header {} : {}" , headerName,part.getHeader(headerName));
            }

            log.info("submitFilename = {} ", part.getSubmittedFileName());
            log.info("size = {}", part.getSize());

            final InputStream inputStream = part.getInputStream();
            final String s = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            log.info("body = {}" , s);

            if (StringUtils.hasText(part.getSubmittedFileName())) {
                final String s1 = fileDir + part.getSubmittedFileName();
                log.info("파일 저장 fullpath - {}" , s1);
                part.write(s1);
            }

        }

        return "upload-form";
    }

}
