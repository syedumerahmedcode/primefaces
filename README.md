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

```xhtml

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

- Service is created during application start-up since it is a singleton.

- https://stackoverflow.com/questions/53690136/the-import-javax-annotation-postconstruct-cannot-be-resolved   ---> this explains the problem with @PostConstrcut annotation not being recognized.

- https://stackoverflow.com/questions/6054692/error-creating-bean-sessionfactory ---> How to avoid hibernate problem with the creation of entitymanagerfactory

_JSF & PrimeFaces & Spring tutorial - Part 10: PrimeFaces DataTable_

- Consider the following code:

```xhtml

<p:dataTable value="#{checkListController.checks}" var="check">
	<p:column headerText="name">
		#{check.name}
	</p:column>
	<p:column headerText="url">
		<a href="#{check.url}" target="_blank">
			#{check.url}
		</a>
	</p:column>
</p:dataTable>
```
- Here _p:dataTable_ creates a JSF type datatable. It is populated via _#{checkListController.checks}_ which is iterated over and the individual value is stored in _var="check"_. 

- _p:column_ represents a JSF style column with the name of the column represented via _headerText="name"_. There is no special tag used for displaying row in the example.


_JSF & PrimeFaces & Spring tutorial - Part 11: JSF Form tag_

- _<h:form>_ generates HTML form.

- _<p:panelGrid columns="2">_ generates a HTML table with two columns.

- _<h:commandButton action="" value="save" />_, this generates a save button.

**How to bind the JSF front-end with the Controller Backend:**

- On the front-end side:
- Input for **url** are captured via _value="#{checkListController.check.url}"_ in input field.
- upon clicking **save** button via _<h:commandButton value="save" action="#{checkListController.save()}"/>_, the request is send to the back-end.
- **Attention:**  By default, the form is not cleared automatically when save button is clicked. Why? Because, out of the box, the prime faces command button component sends the request to the server using **Ajax Post**. Hence, when we refresh the page, we will see the current state of the data from the database. 


- On the back-end side:
- A **check** object is created. **Attention:** By default, all JSF objects are initialized with null hence, we must call new operator to initialize the object.
- We call the **save** method in **Controller** ---> this will call the **save()** method in **Service** ---> this uses **repository** to **save** the element.

_JSF & PrimeFaces & Spring tutorial - Part 12: JSF bean scopes_

- [JSF Bean Scope & Description](https://www.tutorialspoint.com/jsf/jsf_managed_beans.htm)

- Why is @managedBean inefficient? Because @ManagedBean is created again and again on every request to the server. Hence, even when saving a new entry into the database, a select command on the database is executed first.

- @ApplicationScoped: Singleton.
- @SessionScoped: A bean with session scope is created per user.
- @ViewScoped: It is created when the user access the page and it is still in memory when the page calls using Ajax to server, It is only destroyed when the user leaves the page. Why does the class implements Serializable? Because the information is stored in a Session and hence to have it from one request to the next, it must implement serializable.

- We use **javax.faces.bean.ViewScoped** as import class as it works together with ManagedBean. _javax.faces.view_ works with CDI context which we are not using.  

_JSF & PrimeFaces & Spring tutorial - Part 13: PrimeFaces update_

- In order to make data from text box disappear after save button is clickwed, add _update="checkForm"_ in save button. **Attention:** This must match the **form id**.
- In order to dynamically update the data table, we need to add _update="checkForm, :checkTable"_ in save button. Here, we add **,** to indicate that this is an additional component and **:** since this component is outside the form. ---> On the back-end side, in CheckListController class, we add **checks = checkService.findAll();** in **save()** method so that the data is reloaded not only at system start-up but also after save button is pressed. 

_JSF & PrimeFaces & Spring tutorial - Part 14: JSF messages & growl_

- In order to display the success message on save, we added the following line in **CheckListCokntroller.save()** method.

```java

FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Check saved.", null));
```
Additionally, one must add it as a value of update attribute in the save button with a **:** since the component is outside of the HTML form.
- On the front-end side, add

```xhtml

<p:messages id="messages"/>

```
**Attention:** The message**s** tag name must be exact. In case the 's' at the end is missing, one would get a _NullPointerException_ but it will not say that the component name is incorrect. 

- Using **<p:growl />** (Of Mac style based) provides a much nicer save message.

_JSF & PrimeFaces & Spring tutorial - Part 15: JSF Validation With Hibernate Validator_

- Using standard Hibernate validator for checking fields here. One can also think of using javax.validations.

_JSF & PrimeFaces & Spring tutorial - Part 16: complete JSF CRUD application_

- For editing, we created a separate column which contains a command button:
 
```xhtml

<p:commandButton value="edit" action="#{checkListController.setCheck(check)}" update=":checkForm"/>

```

Here, _action="#{checkListController.setCheck(check)}"_ allows us to fetch the current check value from the Controller class whereas _update=":checkForm"_ loads the current record in edit form.

- For removing a record, we created the following command button:

```xhtml

<p:commandButton value="remove" action="#{checkListController.remove(check)}" update=":checkTable, :messages"/>

```

Here: _action="#{checkListController.remove(check)}"_ calls the remove() method in check controller whereas in _update=":checkTable, :messages"_ since we want to update the data table, we pass _checkTable_ as argument and in order to display a 'checks removed' message, we also pass _:messages_ to update attribute.

_JSF & PrimeFaces & Spring tutorial - Part 17: PrimeFaces Confirm Dialog_

- For confirm dialog, we have two parts:

```xhtml

<p:confirm header="Confirmation" message="Do you want to delete this record?" icon="pi pi-info-circle"/>

```
This will create the dialog box.

Second part is:

```xhtml
<h:form>
	<p:confirmDialog global="true" responsive="true" width="350">
        <p:commandButton value="No" type="button" styleClass="ui-confirmdialog-no ui-button-flat"/>
        <p:commandButton value="Yes" type="button" styleClass="ui-confirmdialog-yes" />
    </p:confirmDialog>
</h:form>
```
This creates the options of _yes_ or _no_. **Attention:** This part must be written inside a HTML form. 

_JSF & PrimeFaces & Spring tutorial - Part 18: PrimeFaces Dialog_

- In order to make a modal dialog, the complete _h:form id="checkForm"_ is now under a dialog tag.

```xhtml

<p:dialog widgetVar="checkDialog" closeOnEscape="true" modal="true" header="check">
	<h:form id="checkForm">
		<p:focus />
		<p:panelGrid columns="2">
			name:
			<p:inputText value="#{checkListController.check.name}"/>
			
			url:
			<p:inputText value="#{checkListController.check.url}"/>		
		</p:panelGrid>
		<h:commandButton value="save" action="#{checkListController.save()}" update="checkForm, :checkTable, :messages"/>
	</h:form>
</p:dialog>
```

Here, we also need to create a button which executes this prime faces modal dialog via java script.

```xhtml
<h:form>
	<p:commandButton value="add check" oncomplete="PF('checkDialog').show()" />
</h:form>
```
Here, the important part is _oncomplete="PF('checkDialog').show()"_ which is executed once a Ajax request is send and response is acquired. **Attention:** _checkDialog_ passed an argument to PF() must be same as widgetVar in _p:dialog widgetVar="checkDialog"_.

- In order to make edit button also open the modal dialog, we added _oncomplete="PF('checkDialog').show()"_ to the edit button. 

```xhtml
<p:commandButton value="edit" action="#{checkListController.setCheck(check)}" update=":checkForm" oncomplete="PF('checkDialog').show()"/>
```

-

**TODO Tasks:**

- Code refactoring.
- move all version number to properties section in pom file.
- Use the latest version of all dependencies in pom file.
  

