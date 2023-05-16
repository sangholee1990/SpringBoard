package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@EnableWebMvc
@Configuration
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver beanNameViewResolver() {
        BeanNameViewResolver resolver = new BeanNameViewResolver();
        return resolver;
    }

    @Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }


    @Bean
    public View jsonView() {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        return view;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML);
    }

//    @Bean
//    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
//        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
//        resolver.setContentNegotiationManager(manager);
//
//        // Define all possible view resolvers
//        List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
//
//        resolvers.add(jaxb2MarshallingXmlViewResolver());
//        resolvers.add(jsonViewResolver());
//        resolvers.add(jspViewResolver());
//        resolvers.add(pdfViewResolver());
//        resolvers.add(excelViewResolver());
//
//        resolver.setViewResolvers(resolvers);
//        return resolver;
//    }

    /*
     * Configure View resolver to provide XML output Uses JAXB2 marshaller to
     * marshall/unmarshall POJO's (with JAXB annotations) to XML
     */
//    @Bean
//    public ViewResolver jaxb2MarshallingXmlViewResolver() {
//        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        marshaller.setClassesToBeBound(Pizza.class);
//        return new Jaxb2MarshallingXmlViewResolver(marshaller);
//    }
//
//    /*
//     * Configure View resolver to provide JSON output using JACKSON library to
//     * convert object in JSON format.
//     */
//    @Bean
//    public ViewResolver jsonViewResolver() {
//        return new JsonViewResolver();
//    }
//
//    /*
//     * Configure View resolver to provide PDF output using lowagie pdf library to
//     * generate PDF output for an object content
//     */
//    @Bean
//    public ViewResolver pdfViewResolver() {
//        return new PdfViewResolver();
//    }
//
//    /*
//     * Configure View resolver to provide XLS output using Apache POI library to
//     * generate XLS output for an object content
//     */
//    @Bean
//    public ViewResolver excelViewResolver() {
//        return new ExcelViewResolver();
//    }
//
//    /*
//     * Configure View resolver to provide HTML output This is the default format
//     * in absence of any type suffix.
//     */
//    @Bean
//    public ViewResolver jspViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/WEB-INF/views/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }

}
