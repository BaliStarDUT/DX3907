package top.hunaner.lol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
