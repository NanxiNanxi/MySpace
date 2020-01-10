package com.nanxi.myspace.myspace.serviceProvider;

import com.alibaba.fastjson.JSON;
import com.nanxi.myspace.myspace.dto.AccessTokenDTO;
import com.nanxi.myspace.myspace.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvider {
    //using okhttp to post a request https://square.github.io/okhttp/
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaTypeJson = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(mediaTypeJson,JSON.toJSONString(accessTokenDTO));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                String accessToken = string.split("=")[1].split("&")[0];
                return accessToken;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }
    //after post the request, get the redirect url
    public GitHubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string =response.body().string();
            GitHubUser gitHubUser =  JSON.parseObject(string, GitHubUser.class);
            return gitHubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
