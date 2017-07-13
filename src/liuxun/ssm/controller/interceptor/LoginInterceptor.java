package liuxun.ssm.controller.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import liuxun.ssm.po.ActiveUser;
import liuxun.ssm.util.ResourcesUtil;

/**
 * 测试拦截器1
 * @author liuxun
 *
 */
public class LoginInterceptor implements HandlerInterceptor{
	//在执行handler之前执行的
	//用于用户认证校验、用户权限校验
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
		//得到请求的url
		String url = request.getRequestURI();
		
		//判断是否是公开地址
		//实际开发中需要将公开地址配置在配置文件中
		//从配置文件中取出可以匿名访问的URL
		List<String> open_urls = ResourcesUtil.getKeyList("anonymousURL");
		for (String open_url : open_urls) {
			if (url.indexOf(open_url)>=0) {
				//如果是公开地址 则放行
				return true;
			}
		}
		
		//判断用户身份在Session中是否存在
		HttpSession session = request.getSession();
		ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
		//如果用户身份在session中存在则放行
		if (activeUser!=null) {
			return true;
		}
		//执行到这里拦截，跳转到登录页面，用户进行身份认证
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		
		//如果返回false表示拦截器不继续执行handler，如果返回true表示放行
		return false;
	}

	//在执行handler返回modelAndView之前执行
	//如果需要向页面提供一些公用的数据或配置一些视图信息，使用此方法实现 从modelAndView入手
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		System.out.println("HandlerInterceptor2...postHandle");
	}

	//执行handler之后执行此方法
	//作为系统统一异常处理，进行方法执行性能监控，在preHandler中设置一个时间点 在afterCompletion设置一个时间点 二者时间差就是执行时长
	//实现系统，统一日志记录
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception modelAndView)
			throws Exception {
		System.out.println("HandlerInterceptor2...afterCompletion");
	}

}
