import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author lixing
 * @version V1.0
 * @ClassName:
 * @Description:
 * @Date 2024/1/21 22:51
 */
public class ApiTest {

    @Test
    public void base64(){
        String cronExpression = new String(Base64.getDecoder().decode("MC81MCAqICogKiAqID8="), StandardCharsets.UTF_8);
        System.out.println(cronExpression);
    }

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/48411118851818/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie", "知识星球个人cookie信息");
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/412884248251548/answer");
        post.addHeader("cookie", "知识星球个人cookie信息");
        post.addHeader("Content-Type", "application/json;charset=utf8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // HttpPost post = new HttpPost("https://api.openai.com/v1/chat/completions");
        // HttpPost post = new HttpPost("https://ai.fakeopen.com/api/conversation");
        HttpPost post = new HttpPost("https://api.openai-proxy.com/v1/chat/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer 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");

        String paramJson = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"写一个Java冒泡排序\"}]}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());

            JSONObject jsonObj = JSONArray.parseObject(jsonStr);
            JSONArray choicesArr = jsonObj.getJSONArray("choices");

            // 处理第一个 choice 的 message
            JSONObject choice0 = choicesArr.getJSONObject(0);
            JSONObject messageObj = choice0.getJSONObject("message");
            String role = messageObj.getString("role");
            String content = messageObj.getString("content");

            System.out.println(jsonStr);
            System.out.println("==================================================");
            System.out.println("Role: " + role);
            System.out.println("Content: " + content);

        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }
}
    /*curl https://api.openai-proxy.com/v1/chat/completions \
        -H "Content-Type: application/json" \
        -H "Authorization: Bearer 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" \
        -d '{
        "model": "gpt-3.5-turbo",
        "messages": [{"role": "user", "content": "Hello!"}]
        }'*/