package DuAnTotNghiep.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import DuAnTotNghiep.service.CategoryService;
import DuAnTotNghiep.service.CatesmallService;

@Component
public class GlobalInterceptor implements HandlerInterceptor{

	@Autowired CatesmallService catesmallService;
	@Autowired CategoryService categoryService;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("cates", categoryService.findAll());
		request.setAttribute("catesmall", catesmallService.findAll());
	}
	
}
