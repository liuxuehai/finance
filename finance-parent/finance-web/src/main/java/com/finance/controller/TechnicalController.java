package com.finance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/technical")
public class TechnicalController {

	@GetMapping("")
	public ModelAndView view(@RequestParam("symbol") String symbol, @RequestParam("interval") String interval, ModelMap map) {

		map.addAttribute("symbol", symbol);
		map.addAttribute("interval", interval);
		return new ModelAndView("tech/view");
	}

}
