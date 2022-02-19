package co.yixiang.app.filter;


import co.yixiang.app.common.cache.CacheNames;
import co.yixiang.app.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: Administrator
 * @date: 2022/2/3 16:04
 * @description:
 */
@Slf4j
public class ApiFilter  implements Filter {

//    private Map<String,String> notValiUrlMap = new HashMap<>();
    LinkedHashMap<String, String> notValiUrlMap = new LinkedHashMap<>();
    @Autowired
    private RedisUtil redisUtil;



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("AccesLogFilter init");
        notValiUrlMap.put("/oauth/login","true");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       log.info("api filter ==============>");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requesturi = request.getRequestURI();
        log.info("doFilter =============> Request URI:{}",requesturi);
        //不鉴权URL
//      if (null != notValiUrlMap.get(requesturi) ){
//          return;
//      }
        String token = request.getHeader("Authorization");
        try {
            String sureToken = (String)redisUtil.get(token);
        }catch (Exception e){
            log.warn("url sureToken 失效:{}",e.getMessage());
            return;
        }

        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        log.info("\n AccesLogFilter destroy ==================> \n ");
    }
}