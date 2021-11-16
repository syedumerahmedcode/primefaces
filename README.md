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

- 

