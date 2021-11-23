# Site monitoring using primefaces

**Reference:**

Current:
- [1](https://www.youtube.com/watch?v=3RFhjzNdbfA&list=PLmcxdcWPhFqPq23QswZYbKvhs2Eo6XyJA): JSF & PrimeFaces & Spring tutorial 

Future:
https://www.youtube.com/watch?v=EPsIiyJdKts&list=PLCaS22Sjc8YQ0bvX0OLwdS6-Fw6ppr77Z

**Notes:**

_JSF & PrimeFaces & Spring tutorial - Part 1: Hello World JSF _

- In case web.xml is not present in the project, it can be generated via Deployment Descriptor: primefaces ---> Generate Deployment Descriptor Stub.

- The command to run on terminal is: _mvn jetty:run_

- The index page is available at: _http://localhost:8080/index.xhtml_

_JSF & PrimeFaces & Spring tutorial - Part 2: JSF in Eclipse_

- **How to make intelliSense/Code assist work for JSF**(Follow everything defined in part 2 of the readme file)? Help ---> Eclipse Marketplace ---> Search for 'JBoss Tools' ---> We selected luna ---> In the next window, we only select JSF.  

- Right click on Project  ---> Configure ---> Add JSF Capabilities ---> Click on 'Further Configuration Required' link ---> Select Type: Disable Library Configuration ---> Uncheck 'Configure JSF Servlet in deployment descriptor'(Reason: We already did it in web.xml)

- Close all open files ---> Open index.xhtml file with **JBoss Tools HTML Editor**.

_JSF & PrimeFaces & Spring tutorial - Part 4: Java EE welcome file_

- How to make the index.xhtml file the default start page when the jetty server starts? Open web.xml file ---> Under <welcome-file-list> tag, only allow

```xml

<welcome-file>index.xhtml</welcome-file>

```

---> run the jetty server using 'mvn jetty:run' ---> Open localhost:8080 ---> default page is the index.xhtnl page.

_JSF & PrimeFaces & Spring tutorial - Part 5: JSF & Spring integration_

- The reason we do not specify version for _spring-web_ artifact Id is because the version number is already defined in dependencyManagement---> <artifactId>spring-framework-bom</artifactId>

- What to configure in faces-config.xml? ---> Added an application ---> el-resolver tag in faces-config.xml file like the following:

```xml

<application>
    	<el-resolver>org.springframework.web.jsf.el.SpringBeanFacesELResolver</el-resolver>
    </application>
    
```

This helps in integration between JSF Runtime and Spring.


- The initialization of the spring application is done as follows:

```java

// TODO: Please change the class name to something more professional.
public class MyWebAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {
		
		// Create the 'root' spring application context
		// This is done via registering SpringCOnfiguration class which
		// contains the annotations: @Configuration and @ComponentScan
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringConfiguration.class);

		// Manage the life cycle of the root application context
		// This is achieved via ContextLoaderListener
		container.addListener(new ContextLoaderListener(rootContext));
	}

}
```

- How to used @ManagedProperty annotation in the controller class?

```java

// Using expression language
	// Here, we are using Spring Bean name. By default, it is the same as the class
	// name.
	@ManagedProperty("#{helloSpringService}")
	private HelloSpringService helloSpringService;

```

Please note that if the setter for the service bean is not present, the following error occurs when running the application.
**Unable to create managed bean helloController. The following problems were found: - Property helloSpringService for managed bean helloController does not exist. Check that appropriate getter and/or setter methods exist.**

To counter this, please create a setter method as follows:

```java

/**
	 * This is needed so that JSF runtime can inject the instance in the variable.
	 * Otherwise, it will throw an exception on running the project.
	 * 
	 * @param helloSpringService
	 */
	public void setHelloSpringService(HelloSpringService helloSpringService) {
		this.helloSpringService = helloSpringService;
	}

```

_JSF & PrimeFaces & Spring tutorial - Part 6: ManagedBean vs Component vs Named_

- Using _@ManagedBean_ along with _ManagedProperty("#{helloSpringService}")_ means that beans are managed by JSF Runtime. However, if we use _@Component_ along with _@Autowired_, then beans are managed by Spring framework.

- _ManagedBean_ is created for each request i.e. it has request scope whereas _@Component_ is Singleton.

 - In order to make @Compomnent behave with a request scope, we need to add @Scope("request"). 
 
 - If we want to achieve the same result in CDI context, then we need @Named and @Inject.
 
 **Initialization of Beans:**
 
 **JSF Runtime:**
 
 ```java
 
@ManagedBean

public class HelloController {

	// Using expression language
	// Here, we are using Spring Bean name. By default, it is the same as the class
	// name.
//	@ManagedProperty("#{helloSpringService}")

	private HelloSpringService helloSpringService;

	public String showHello() {
		// return "Hello from the Managed bean";
		return helloSpringService.sayHello();
	}

	/**
	 * This is needed so that JSF runtime can inject the instance in the variable.
	 * Otherwise, it will throw an exception on running the project.
	 * 
	 * @param helloSpringService
	 */
	public void setHelloSpringService(HelloSpringService helloSpringService) {
		this.helloSpringService = helloSpringService;
	}
 
```

**Spring context:**

```java

@Component
public class HelloController {

	@Autowired
	private HelloSpringService helloSpringService;
	
	// further lines of code

```

**CDI context:**

```java

@Named
public class HelloController {

	@Inject
	private HelloSpringService helloSpringService;
	
	// further lines of code

```


_JSF & PrimeFaces & Spring tutorial - Part 9: Spring Data JPA & Hibernate_

- https://vocado.tistory.com/entry/javalangExceptionInInitializerError-comsuntoolsjavaccodeTypeTags-%EC%97%90%EB%9F%AC  ---> this fixed the compilation problem with lombok.

- TODO: Describe the purpose of _hikaricp_ and _hsqldb_ dependencies. 

