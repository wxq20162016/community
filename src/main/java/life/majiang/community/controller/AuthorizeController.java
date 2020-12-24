package life.majiang.community.controller;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state)
    {
        AccessTokenDTO accesstokenDTO=new AccessTokenDTO();
        //返回html页面说明参数传错了 注意检查参数
        accesstokenDTO.setClient_id("c1613313d2e5b5169a96");
        accesstokenDTO.setClient_secret("2544cfbfdcdaea2a25c836d91ec12921d13adbce");
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accesstokenDTO.setState(state);
        githubProvider.getAccessToken(accesstokenDTO);
        return "index";
    }
}
