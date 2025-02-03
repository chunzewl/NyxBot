package com.nyx.bot.controller.api.html.warframe.mission;

import com.nyx.bot.service.WarframeWikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class WarframeWikiController {

    @Autowired
    private WarframeWikiService warframeWikiService;

    @GetMapping("/getWarframeData")
    public String getWarframeInfo(@RequestParam String endpoint, Model model) {
        String warframeData = warframeWikiService.queryWarframeData(endpoint);
        
        model.addAttribute("warframeData", warframeData);
        return "html/warframeInfo"; // 返回采用的数据视图
    }
}
