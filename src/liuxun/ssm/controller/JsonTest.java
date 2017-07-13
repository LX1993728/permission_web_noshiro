package liuxun.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import liuxun.ssm.po.ItemsCustom;

/**
 * json测试
 * @author liuxun
 *
 */
@Controller
public class JsonTest {
	//请求的json响应json,请求商品信息，商品信息使用json格式，输出商品信息
	@RequestMapping("/requestJson")
	public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom) throws Exception{
		return itemsCustom;
	}
	
	@RequestMapping("/responseJson")
	public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom) throws Exception{
		return itemsCustom;
	}
}
