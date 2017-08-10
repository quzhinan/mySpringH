package com.qzn.helper;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

public class ApplicationContextHelper implements ApplicationContextAware, ApplicationContext {

	private ApplicationContext applicationContext;
	
	private static ApplicationContextHelper applicationContextSupport;
	
	public static void setInstance (ApplicationContextHelper applicationContextSupport) {
		ApplicationContextHelper.applicationContextSupport = applicationContextSupport;
	}
	
	public static ApplicationContextHelper getInstance() {
		return applicationContextSupport;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public boolean containsBean(String arg0) {
		return applicationContext.containsBean(arg0);
	}

	public boolean containsBeanDefinition(String arg0) {
		return applicationContext.containsBeanDefinition(arg0);
	}

	public boolean containsLocalBean(String arg0) {
		return applicationContext.containsLocalBean(arg0);
	}

	public <A extends Annotation> A findAnnotationOnBean(String arg0, Class<A> arg1)
			throws NoSuchBeanDefinitionException {
		return applicationContext.findAnnotationOnBean(arg0, arg1);
	}

	public String[] getAliases(String arg0) {
		return applicationContext.getAliases(arg0);
	}

	public String getApplicationName() {
		return applicationContext.getApplicationName();
	}

	public AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException {
		return applicationContext.getAutowireCapableBeanFactory();
	}

	public <T> T getBean(Class<T> arg0, Object... arg1) throws BeansException {
		return applicationContext.getBean(arg0, arg1);
	}

	public <T> T getBean(Class<T> arg0) throws BeansException {
		return applicationContext.getBean(arg0);
	}

	public <T> T getBean(String arg0, Class<T> arg1) throws BeansException {
		return applicationContext.getBean(arg0, arg1);
	}

	public Object getBean(String arg0, Object... arg1) throws BeansException {
		return applicationContext.getBean(arg0, arg1);
	}

	public Object getBean(String arg0) throws BeansException {
		return applicationContext.getBean(arg0);
	}

	public int getBeanDefinitionCount() {
		return applicationContext.getBeanDefinitionCount();
	}

	public String[] getBeanDefinitionNames() {
		return applicationContext.getBeanDefinitionNames();
	}

	public String[] getBeanNamesForAnnotation(Class<? extends Annotation> arg0) {
		return applicationContext.getBeanNamesForAnnotation(arg0);
	}

	public String[] getBeanNamesForType(Class<?> arg0, boolean arg1, boolean arg2) {
		return applicationContext.getBeanNamesForType(arg0, arg1, arg2);
	}

	public String[] getBeanNamesForType(Class<?> arg0) {
		return applicationContext.getBeanNamesForType(arg0);
	}

	public String[] getBeanNamesForType(ResolvableType arg0) {
		return applicationContext.getBeanNamesForType(arg0);
	}

	public <T> Map<String, T> getBeansOfType(Class<T> arg0, boolean arg1, boolean arg2) throws BeansException {
		return applicationContext.getBeansOfType(arg0, arg1, arg2);
	}

	public <T> Map<String, T> getBeansOfType(Class<T> arg0) throws BeansException {
		return applicationContext.getBeansOfType(arg0);
	}

	public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> arg0) throws BeansException {
		return applicationContext.getBeansWithAnnotation(arg0);
	}

	public ClassLoader getClassLoader() {
		return applicationContext.getClassLoader();
	}

	public String getDisplayName() {
		return applicationContext.getDisplayName();
	}

	public Environment getEnvironment() {
		return applicationContext.getEnvironment();
	}

	public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		return applicationContext.getMessage(code, args, defaultMessage, locale);
	}

	public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
		return applicationContext.getMessage(code, args, locale);
	}

	public String getId() {
		return applicationContext.getId();
	}

	public ApplicationContext getParent() {
		return applicationContext.getParent();
	}

	public BeanFactory getParentBeanFactory() {
		return applicationContext.getParentBeanFactory();
	}

	public Resource getResource(String arg0) {
		return applicationContext.getResource(arg0);
	}

	public Resource[] getResources(String arg0) throws IOException {
		return applicationContext.getResources(arg0);
	}

	public void publishEvent(ApplicationEvent event) {
		applicationContext.publishEvent(event);
	}

	public long getStartupDate() {
		return applicationContext.getStartupDate();
	}

	public Class<?> getType(String arg0) throws NoSuchBeanDefinitionException {
		return applicationContext.getType(arg0);
	}

	public boolean isPrototype(String arg0) throws NoSuchBeanDefinitionException {
		return applicationContext.isPrototype(arg0);
	}

	public boolean isSingleton(String arg0) throws NoSuchBeanDefinitionException {
		return applicationContext.isSingleton(arg0);
	}

	public boolean isTypeMatch(String arg0, Class<?> arg1) throws NoSuchBeanDefinitionException {
		return applicationContext.isTypeMatch(arg0, arg1);
	}

	public boolean isTypeMatch(String arg0, ResolvableType arg1) throws NoSuchBeanDefinitionException {
		return applicationContext.isTypeMatch(arg0, arg1);
	}

	public void publishEvent(Object event) {
		applicationContext.publishEvent(event);
	}

	public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
		return applicationContext.getMessage(resolvable, locale);
	}
	
}
