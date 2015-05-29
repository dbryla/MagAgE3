package org.age.mag.server;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {

    @RequestMapping("/greeting")
    public String greeting(Model model) { //preferable way to do controllers, even for so simple ones
        model.addAttribute("name", "Kotek");
        return "greeting";
    }

}