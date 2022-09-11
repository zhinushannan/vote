package club.kwcoder.vote.service;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.ImageDO;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    ResultBean<ImageDO> upload(MultipartFile file);

}
