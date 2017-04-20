package com.example.web.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class I18nConfig extends WebMvcConfigurerAdapter {

//	AcceptHeaderLocaleResolver
//	- http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/servlet/i18n/AcceptHeaderLocaleResolver.html
//	- http의 해더의 Accept-Language 에 의해 선택됩니다. 따라서, setLocale 메소드가 지원되지않습니다.
//	SessionLocaleResolver
//	- 처음 들어갈때에는 AcceptHeaderLocaleResolver 처럼 브라우저의 언어 설정에 의한 Accept-Language 로 값이 결정됩니다.
//	- 물론 setDefaultLocale 을 설정한다면 해당 기본값이 최우선입니다. 
//	CookieLocaleResolver
//	- SessionLocaleResolver와 속성이 동일하지만 lang 값이 바뀔경우 세션이 아닌 쿠키에 저장합니다.
//	- 때문에 세션의 경우 세션이 끊어지면 언어설정이 되돌아오지만 이 리졸버를 이용할 경우 쿠키에 값을 우선으로 불러옵니다.

	@Bean
	public LocaleResolver localeResolver() {
//		SessionLocaleResolver resolver = new SessionLocaleResolver();
//		resolver.setDefaultLocale(Locale.KOREAN);
		
		CookieLocaleResolver resolver = new CookieLocaleResolver();
//		resolver.setDefaultLocale(Locale.KOREAN);
		resolver.setCookieName("lang");
		return resolver;
	}
	
	/*
	 * ?lang={언어코드} 방법으로 언어를 바꿀 수 있다.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		
		registry.addInterceptor(localeChangeInterceptor);
	}
	
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
		// messages_ko_KR.properties 에 없으면 messages.prorperties를 참조한다.
		messageSource.setFallbackToSystemLocale(true);
		return messageSource;
	}
}
