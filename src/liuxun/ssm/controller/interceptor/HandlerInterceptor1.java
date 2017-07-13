package liuxun.ssm.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试拦截器1
 * @author liuxun
 *
 */
public class HandlerInterceptor1 implements HandlerInterceptor{
	//在执行handler之前执行的
	//用于用户认证校验、用户权限校验
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
		System.out.println("HandlerInterceptor1...preHandle");
		
		//如果返回false表示拦截器不继续执行handler，如果返回true表示放行
		return true;
	}

	//在执行handler返回modelAndView之前执行
	//如果需要向页面提供一些公用的数据或配置一些视图信息，使用此方法实现 从modelAndView入手
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		System.out.println("HandlerInterceptor1...postHandle");
	}

	//执行handler之后执行此方法
	//作为系统统一异常处理，进行方法执行性能监控，在preHandler中设置一个时间点 在afterCompletion设置一个时间点 二者时间差就是执行时长
	//实现系统，统一日志记录
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception modelAndView)
			throws Exception {
		System.out.println("HandlerInterceptor1...afterCompletion");
	}

}
