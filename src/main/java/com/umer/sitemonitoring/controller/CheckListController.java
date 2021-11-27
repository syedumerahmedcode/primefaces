package com.umer.sitemonitoring.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.umer.sitemonitoring.entity.Check;
import com.umer.sitemonitoring.service.CheckService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean
@ViewScoped
public class CheckListController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String CHECK_REMOVED = "Check removed.";
	private static final String CHECK_SAVED = "Check saved.";
	private static final String CHECK_SERVICE_MANAGED_PROPERTY="#{checkService}";


	@ManagedProperty(CHECK_SERVICE_MANAGED_PROPERTY)
	private CheckService checkService;

	private List<Check> checks;

	private Check check = new Check();

	@PostConstruct
	public void loadChecks() {
		getAllEntries();
	}

	public void save() {
		checkService.save(check);
		clearChecks();
		getAllEntries();
		addNotificationMessage(CHECK_SAVED);
	}

	public void clear() {
		clearChecks();
	}

	public void remove(Check check) {
		checkService.remove(check);
		getAllEntries();
		addNotificationMessage(CHECK_REMOVED);
	}	

	private void getAllEntries() {
		checks = checkService.findAll();
	}

	/**
	 * Why do we need this? Because saving something creates an Id and all
	 * subsequent calls to the name will do an update and before a select to see if
	 * something changed. In order to have a new row inserted each time a save is
	 * called, the check object must be initialized again.
	 */
	private void clearChecks() {
		check = new Check();
	}
	
	private void addNotificationMessage(String messageToDisplay) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, messageToDisplay, null));
	}

}
