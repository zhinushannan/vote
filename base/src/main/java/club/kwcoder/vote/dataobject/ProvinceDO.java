package club.kwcoder.vote.dataobject;

import lombok.Data;

import java.io.Serializable;

/**
 * province
 *
 * @author
 */
@Data
public class ProvinceDO implements Serializable {
    private Integer provinceId;

    private String provinceName;

    private static final long serialVersionUID = 1L;
}