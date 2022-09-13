package club.kwcoder.vote.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * image
 *
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