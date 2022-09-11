package club.kwcoder.vote.common.controller;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.ImageDO;
import club.kwcoder.vote.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("upload")
    public ResultBean<ImageDO> upload(@RequestBody MultipartFile file,
                                      HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            System.out.println(entry.getKey() + " " + Arrays.toString(entry.getValue()));
        }
        System.out.println(file);
        return imageService.upload(file);
    }

}
