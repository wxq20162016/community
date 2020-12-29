package life.majiang.community.provider;
import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component //使得该类成为springboot的上下文 而不需要new对象
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accesstokenDTO){
        MediaType mediaType =MediaType.get("application/json; charset=utf-8");
        OkHttpClient client=new OkHttpClient();

        RequestBody body=RequestBody.create(mediaType,JSON.toJSONString(accesstokenDTO));
        Request request=new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try(Response response=client.newCall(request).execute()){
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            System.out.println(token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public GithubUser getUser(String accessToken){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+ accessToken)
                .build();
        try{
            Response response=client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            System.out.println(githubUser);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
