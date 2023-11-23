package com.footsell.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.footsell.domain.FootsellVO;
import com.footsell.service.Footsellservice;

@Controller
public class HomeController {
  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

  @Inject
  private Footsellservice service;

  @RequestMapping(value = {"/"}, method = {RequestMethod.GET})
  public String home(Locale locale, Model model) throws Exception {
    logger.info("Welcome home! The client locale is {}.", locale);
    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(1, 1, locale);
    List<FootsellVO> list = this.service.list();
    String formattedDate = dateFormat.format(date);
    model.addAttribute("list", list);
    model.addAttribute("serverTime", formattedDate);
    return "home";
  }
}
