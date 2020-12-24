package life.majiang.community.provider;
import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDTO;
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
            System.out.println(string);
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
//    public GithubUser getUser(String accessToken){
//        OkHttpClient client=new OkHttpClient();
//        Request request=new Request.Builder()
//                .url("https://api.github.com/user?access_token=" + accessToken)
//                .build();
//        try {
//            Response response=client.newCall(request).execute();
//            //把请求接口得到的数组 赋给一个变量
//            String string = response.body().string();
//            //把得到的数组 转化成为java类
//            GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
//            return githubUser;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
