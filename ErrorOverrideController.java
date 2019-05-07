package com.jbhunt.infrastructure.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorOverrideController implements org.springframework.boot.autoconfigure.web.ErrorController {

  private static final String PATH = "/error";

  @RequestMapping({PATH})
  public String error(){
    return "forward:/index.html";
  }

  @Override
  public String getErrorPath(){
    return PATH;
  }
}
