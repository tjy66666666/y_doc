package top.study.ydoc.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 文件上传的filter 避免非法上传过大文件或不合法内容的文件
 *
 * @author tjy
 * @date 2024/04/22
 */
@WebFilter(filterName = "FileUploadFilter", urlPatterns = {"/upload/*"})
public class FileUploadFilter implements Filter {

    // 允许上传的文件类型列表
    private static final List<String> ALLOWED_FILE_TYPES = Arrays.asList("image/jpeg", "image/png", "image/gif","image");

    // 允许上传的最大文件大小（单位：字节）
    private static final long MAX_FILE_SIZE = 100 * 1024 * 1024; // 100MB

    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化操作
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 检查请求是否为文件上传请求
        if ("POST".equalsIgnoreCase(request.getMethod()) && request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
            // 获取上传文件的类型和大小
            String contentType = request.getContentType();
            long contentLength = request.getContentLengthLong();

            // 检查上传文件类型和大小是否合法
            if (!isValidFileType(contentType) || contentLength > MAX_FILE_SIZE) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Invalid file type or file size exceeds limit.");
                return;
            }
        }

        // 继续处理请求
        filterChain.doFilter(request, response);
    }

    public void destroy() {
        // 销毁操作
    }

    // 检查上传文件类型是否合法
    private boolean isValidFileType(String contentType) {
        return ALLOWED_FILE_TYPES.contains(contentType);
    }
}
