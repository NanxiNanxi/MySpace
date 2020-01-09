package com.nanxi.myspace.myspace.controller;

import com.nanxi.myspace.myspace.controller.dto.AccessTokenDTO;
import com.nanxi.myspace.myspace.controller.dto.GitHubUser;
import com.nanxi.myspace.myspace.serviceProvider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("9691b9565f23ace9c17a");
        accessTokenDTO.setClient_secret("3f5799f12d5d3d1078338aa05fb02a2040a1d957");
        accessTokenDTO.setRedirect_uri("http://localhost:8882/callback");
        accessTokenDTO.setState(state);
        accessTokenDTO.setCode(code);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser user = gitHubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
