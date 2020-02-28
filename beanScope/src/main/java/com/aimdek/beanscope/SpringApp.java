package com.aimdek.beanscope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApp {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(BeanConfig.class);
		ctx.refresh();

		MyBeans mb1 = ctx.getBean(MyBeans.class);
		System.out.println(mb1.hashCode());

		MyBeans mb2 = ctx.getBean(MyBeans.class);
		System.out.println(mb2.hashCode());
		
		MyBeans2 mb3 = ctx.getBean(MyBeans2.class);
		System.out.println(mb3.hashCode());
		
		MyBeans2 mb4 = ctx.getBean(MyBeans2.class);
		System.out.println(mb4.hashCode());

		ctx.close();
	}
}
