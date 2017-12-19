package top.hunaner.lol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(path = "/")
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping(value = "/hello")
    String home() {
        return "Hello World!"+"你好世界";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    String hello() {
        return "index";
    }
    @RequestMapping("/resource")
    public Map<String,Object> home1() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }
}
