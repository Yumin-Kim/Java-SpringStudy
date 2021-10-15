package upload.uploaddemo.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/spring")
public class SpringUploadController {

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFilev2() {
        return "upload-form";
    }

    @ResponseBody
    @PostMapping("/upload")
    public String createFileInfo(MemberDto memberDto) throws IOException {
        final String originalFilename = memberDto.getFile().getOriginalFilename();
        final String filename = memberDto.getFilename();
        final String[] split = originalFilename.split("\\.");
        final String s1 = split[split.length-1];
        log.info("originalFilename = {}", originalFilename);
        if (!memberDto.getFile().isEmpty()) {
            final String s = fileDir + memberDto.getFilename()+"."+s1;
            memberDto.getFile().transferTo(new File(s));
        }
        log.info("filename = {}",filename);
        return "ok";
    }

    @Getter
    @Setter
    private static class MemberDto {
        private MultipartFile file;
        private String filename;
    }
}
