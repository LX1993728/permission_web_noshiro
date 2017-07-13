package liuxun.ssm.controller.converter;

import org.springframework.core.convert.converter.Converter;
/**
 * 自定义去除字符串前后空格的转换器
 * @author liuxun
 *
 */
public class StringTrimConverter implements Converter<String, String>{

	public String convert(String source) {
		
		//去掉字符串两边空格，如果去除后为空设置为null
		if (source!=null) {
			source = source.trim();
			if (source.equals("")) {
				return null;
			}
		}
		
		return source;
	}

}
