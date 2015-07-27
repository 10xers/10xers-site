package org.tenxers.site.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * site / Ed
 * 27/07/2015 17:57
 */
@Controller
public class IndexPageController {

    @RequestMapping("/")
    public String index(@RequestParam(value="name", required=false, defaultValue = "world") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }


}
