package ssf.assessment.controller;

import java.util.List;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ssf.assessment.services.NewsService;
import ssf.assessment.model.Arrays;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;
    
    @GetMapping("/")
    public String articlesPage(Model model) {
        Arrays arrays = new Arrays();
        Arrays newsArrays = newsService.getArticles();

        if(newsArrays == null) {
            model.addAttribute("news", new Arrays());
            return "news";
        }

        List<Data> fields = Arrays.getFields();
        model.addAttribute("arrays", arrays);
        model.addAttribute("fields", fields);
        return "news";
    }
}
