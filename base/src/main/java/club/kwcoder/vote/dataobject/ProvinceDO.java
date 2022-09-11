package club.kwcoder.vote.dataobject;

import java.io.Serializable;
import lombok.Data;

/**
 * province
 * @author 
 */
@Data
public class ProvinceDO implements Serializable {
    private Integer provinceId;

    private String provinceName;

    private static final long serialVersionUID = 1L;
}