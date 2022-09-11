package club.kwcoder.vote.dataobject;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * image
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDO implements Serializable {
    private String md5;

    private String url;

    private static final long serialVersionUID = 1L;
}