package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Servlet Filter implementation class PerformanceFilter
 */
@WebFilter("/studentInfo")
public class PerformanceFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(PerformanceFilter.class); // SLF4J

	/**
	 * Default constructor.
	 */
	public PerformanceFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getServletPath();
		long st = System.currentTimeMillis();
		chain.doFilter(request, response);
		long et = System.currentTimeMillis();

		long tt = (et - st);
		logger.info("Time take to execute action " + path + "   is  :  " + tt);
		System.out.println("Time take to execute action " + path + "   is  :  " + tt);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
