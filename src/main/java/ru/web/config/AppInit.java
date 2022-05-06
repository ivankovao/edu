package ru.web.config;

import java.util.EnumSet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.FilterRegistration.Dynamic;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ru.web.config.listener.AppContextListener;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    public AppInit() {
    }

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MvcConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        Dynamic filterRegistration = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter("UTF-8", true, true));
        filterRegistration.addMappingForUrlPatterns((EnumSet)null, false, new String[]{"/*"});
        filterRegistration = servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter());
        filterRegistration.addMappingForUrlPatterns((EnumSet)null, false, new String[]{"/*"});
        servletContext.addListener(AppContextListener.class);
    }
}
