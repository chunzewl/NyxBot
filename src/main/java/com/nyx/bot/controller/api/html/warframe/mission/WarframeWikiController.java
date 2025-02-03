package com.nyx.bot.controller.api.html.warframe.mission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/private")
public class WarframeWikiController {

    private static final String API_URL = "https://wf.snekw.com/api";
    
    @GetMapping("/getWarframeData")
    public String getWarframeInfo(@RequestParam String endpoint, Model model) {
        String warframeData = queryWarframeData(endpoint);
        
        model.addAttribute("warframeData", warframeData);
        return "html/warframeInfo"; // 返回采用的数据视图
    }

    private String queryWarframeData(String endpoint) {
        RestTemplate restTemplate = new RestTemplate();
        
        // 构建请求 URL
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                .path(endpoint)
                .build()
                .toUriString();

        // 发起GET请求并获取响应
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            // 处理异常，可以记录日志，也可以返回默认值或错误信息
            e.printStackTrace();
            return "请求异常或无响应。";
        }
    }
}
