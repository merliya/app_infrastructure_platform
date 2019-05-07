package com.jbhunt.infrastructure.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class AppController {

  @RequestMapping({
    "/home/**",
    "/error401",
    "/error404",
    "/error500"
  })
  public String test() {
    return "forward:/index.html";
  }


}
