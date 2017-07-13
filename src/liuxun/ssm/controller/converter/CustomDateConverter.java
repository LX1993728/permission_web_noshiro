package liuxun.ssm.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 自定义日期转换器
 * @author liuxun
 *
 */
public class CustomDateConverter implements Converter<String, Date> {

	public Date convert(String source) {
		if (source!=null&&source.trim().length()>0) {
			// 进行日期转换
			try {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
