/**
 * IndexController.java
 * 
 * $Author: cysun $
 * $Date: 2012-09-19 12:07:52 -0700 (Wed, 19 Sep 2012) $
 */
package bookpub.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

}
