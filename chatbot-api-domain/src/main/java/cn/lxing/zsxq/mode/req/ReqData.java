package cn.lxing.zsxq.mode.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqData {
    private String text;
    private String[] image_ids = new String[]{};
    private boolean silenced;

    public ReqData(String text, boolean silenced) {
        this.text = text;
        this.silenced = silenced;
    }
}
