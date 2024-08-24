package cn.lxing.ai.mode.aggregates;

import cn.lxing.ai.mode.vo.Choices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lixing
 * @version V1.0
 * @ClassName:AI Answer
 * @Description:
 * @Date 2024/1/21 22:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AIAnswer {


    private String id;

    private String object;

    private int created;

    private String model;

    private List<Choices> choices;


}
