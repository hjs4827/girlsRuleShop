package com.jshan.girlsRule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/production")
public class ProductionController {
	
	@RequestMapping(value="settingTopClothes", method= RequestMethod.GET)
	public String settingTopClothes(){
		
		return "production/production_tag_config";
	}
}
