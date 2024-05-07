package top.study.ydoc.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.IOException;

/**
 * 简单的防止xss注入
 *
 * @author tjy
 * @date 2024/04/22
 */
@WebFilter(filterName = "XssFilter", urlPatterns = "/*")
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化操作，可以留空
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 对HttpServletRequest进行包装，重写getParameter()等方法，过滤用户输入
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(httpServletRequest) {
            @Override
            public String[] getParameterValues(String parameter) {
                // 过滤参数值
                String[] values = super.getParameterValues(parameter);
                if (values == null) {
                    return null;
                }
                int count = values.length;
                String[] encodedValues = new String[count];
                for (int i = 0; i < count; i++) {
                    encodedValues[i] = cleanXSS(values[i]);
                }
                return encodedValues;
            }

            @Override
            public String getParameter(String parameter) {
                // 过滤参数值
                String value = super.getParameter(parameter);
                return cleanXSS(value);
            }

            @Override
            public String getHeader(String name) {
                // 过滤请求头信息
                String value = super.getHeader(name);
                return cleanXSS(value);
            }

            private String cleanXSS(String value) {
                // XSS过滤逻辑
                // 可根据实际需求进行修改和扩展
                if (value != null) {
                    // 使用常见的替换规则进行XSS过滤，也可以使用安全框架或工具类来进行XSS过滤
                    value = value.replaceAll("<", "&lt;")
                            .replaceAll(">", "&gt;")
                            .replaceAll("\\(", "&#40;")
                            .replaceAll("\\)", "&#41;")
                            .replaceAll("'", "&#39;")
                            .replaceAll("\"", "&quot;")
                            .replaceAll("eval\\((.*)\\)", "")
                            .replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
                }
                return value;
            }
        };

        // 继续处理过滤后的请求
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {
        // 销毁操作，可以留空
    }
}
