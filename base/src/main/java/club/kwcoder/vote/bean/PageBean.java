package club.kwcoder.vote.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {

    private Integer page = 1;
    private Integer size = 8;
    private Long total;
    private List<T> data;

}
