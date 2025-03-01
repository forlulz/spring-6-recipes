package com.apress.spring6recipes.shop;

import com.apress.spring6recipes.shop.config.ShopConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) throws Exception {
    ApplicationContext context = new AnnotationConfigApplicationContext(ShopConfiguration.class);

    Product aaa = context.getBean("aaa", Product.class);
    Product cdrw = context.getBean("cdrw", Product.class);
    Product dvdrw = context.getBean("dvdrw", Product.class);

    ShoppingCart cart1 = context.getBean("shoppingCart", ShoppingCart.class);
    cart1.addItem(aaa);
    cart1.addItem(cdrw);
    System.out.println("Shopping cart 1 contains " + cart1.getItems());

    ShoppingCart cart2 = context.getBean("shoppingCart", ShoppingCart.class);
    cart2.addItem(dvdrw);
    System.out.println("Shopping cart 2 contains " + cart2.getItems());

  }

}
