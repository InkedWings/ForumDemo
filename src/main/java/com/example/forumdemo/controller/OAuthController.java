package com.example.forumdemo.controller;

import com.example.forumdemo.dto.AccessTokenDTO;
import com.example.forumdemo.dto.GitHubUser;
import com.example.forumdemo.mapper.UserMapper;
import com.example.forumdemo.model.User;
import com.example.forumdemo.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class OAuthController {
    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_url(redirectUrl);
        accessTokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);
        if(gitHubUser != null){
            // Log in successfully, create session and cookie
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(gitHubUser.getName());
            user.setAccoutId(String.valueOf(gitHubUser.getId()));
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(System.currentTimeMillis());
            userMapper.insert(user);
            request.getSession().setAttribute("user", gitHubUser);
            return "redirect:/";
        }else{
            // Log in failed
            return "redirect:/";
        }
    }
}
