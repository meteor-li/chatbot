package cn.lxing.zsxq.mode.res;

import cn.lxing.zsxq.mode.vo.Topics;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 结果数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespData {
    private List<Topics> topics;
}
