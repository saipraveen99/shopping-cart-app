package handson.practice.shoppingcartapp;

import handson.practice.shoppingcartapp.entity.Apparel;
import handson.practice.shoppingcartapp.entity.Book;
import handson.practice.shoppingcartapp.entity.Cart;
import handson.practice.shoppingcartapp.entity.Product;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
public class ShoppingCartAppApplication {

	private final String ENCODING = "UTF-8";
	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartAppApplication.class, args);
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:/messages/shopping_cart_error");
		messageSource.setDefaultEncoding(ENCODING);
		return messageSource;
	}

	@Bean(name = "entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory() {

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(this.dataSource());
		sessionFactory.setHibernateProperties(this.hibernateProperties());
		sessionFactory.setAnnotatedClasses(Product.class, Apparel.class, Book.class, Cart.class);
		return sessionFactory;
	}

	public Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.show-sql", "true");
		hibernateProperties.setProperty("hibernate.ddl-auto", "update");
		return hibernateProperties;
	}

	@Bean
	public DataSource dataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.username("root");
		dataSourceBuilder.password("Welcome123$");
		dataSourceBuilder.url("jdbc:mysql://localhost:3306/shopping_cart");
		dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
		return dataSourceBuilder.build();
	}
}
