package guru.springframework.sfgpetclinic.controllers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Profile("controlleractive")
@Controller
public class IndexController {

    @RequestMapping({"","/","index","index.html","welcome"})
    public String index(){
        return "welcome";
    }

    @RequestMapping("/oups")
    public String oupsHandler(){
        return "notimplemented";
    }
}
