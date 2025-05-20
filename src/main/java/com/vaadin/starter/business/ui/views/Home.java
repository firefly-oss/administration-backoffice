package com.vaadin.starter.business.ui.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Uniform;

@PageTitle("Banking Backoffice")
@Route(value = "", layout = MainLayout.class)
public class Home extends ViewFrame {

	public Home() {
		setId("home");
		setViewContent(createContent());
	}

	private Component createContent() {
		Html welcome = new Html("<h1>Welcome to Firefly Administration Backoffice</h1>");

		Html intro = new Html("<p>Your centralized platform for managing all banking operations efficiently and securely.</p>");

		Html instructions = new Html("<p>Use the navigation menu on the left to access different sections of the backoffice system. " +
				"Each section is designed to provide you with the tools and information needed to perform your tasks efficiently.</p>");

		FlexBoxLayout content = new FlexBoxLayout(welcome, intro, instructions);
		content.setFlexDirection(FlexDirection.COLUMN);
		content.setMargin(Horizontal.AUTO);
		content.setMaxWidth("840px");
		content.setPadding(Uniform.RESPONSIVE_L);
		return content;
	}

}
