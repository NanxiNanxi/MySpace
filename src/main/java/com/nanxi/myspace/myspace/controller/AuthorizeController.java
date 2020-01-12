package com.nanxi.myspace.myspace.controller;

import com.nanxi.myspace.myspace.dto.AccessTokenDTO;
import com.nanxi.myspace.myspace.dto.GitHubUser;
import com.nanxi.myspace.myspace.mapper.UserMapper;
import com.nanxi.myspace.myspace.model.User;
import com.nanxi.myspace.myspace.serviceProvider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setCode(code);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);
        System.out.println(gitHubUser.getName());

        if(gitHubUser != null){
            //login success, get cookie and session
            User user = new User();
            String token  = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            // logingged in, write cookie and session
            response.addCookie(new Cookie("token",token));

            //request.getSession().setAttribute("user", gitHubUser);
            return "redirect:/"; //重定向至根目录
        }
        else{
            //login failed, relogin
            return "redirect:/";
        }

       // return "index";
    }
}
