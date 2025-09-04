/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.vaadin.starter.business.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.*;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.components.navigation.bar.AppBar;
import com.vaadin.starter.business.ui.components.navigation.bar.TabBar;
import com.vaadin.starter.business.ui.components.navigation.drawer.NaviDrawer;
import com.vaadin.starter.business.ui.components.navigation.drawer.NaviItem;
import com.vaadin.starter.business.ui.components.navigation.drawer.NaviMenu;
import com.vaadin.starter.business.ui.constants.NavigationConstants;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.Overflow;
import com.vaadin.starter.business.ui.views.accounting.Accounts;
import com.vaadin.starter.business.ui.views.clients.Clients;
import com.vaadin.starter.business.ui.views.Home;
import com.vaadin.starter.business.ui.views.security.InternalUsers;
import com.vaadin.starter.business.ui.views.security.RolesPermissions;
import com.vaadin.starter.business.ui.views.security.SecurityPolicies;
import com.vaadin.starter.business.ui.views.products.ProductCatalog;
import com.vaadin.starter.business.ui.views.products.RatesFees;
import com.vaadin.starter.business.ui.views.products.ContractManagement;
import com.vaadin.starter.business.ui.views.products.LendingConfiguration;
import com.vaadin.starter.business.ui.views.customers.CustomerSegmentation;
import com.vaadin.starter.business.ui.views.customers.Onboarding;
import com.vaadin.starter.business.ui.views.accounting.FinancialCalendar;
import com.vaadin.starter.business.ui.views.riskandcompliance.RiskModel;
import com.vaadin.starter.business.ui.views.riskandcompliance.FraudPrevention;
import com.vaadin.starter.business.ui.views.riskandcompliance.RegulatoryCompliance;
import com.vaadin.starter.business.ui.views.channelsandservices.ChannelIntegration;
import com.vaadin.starter.business.ui.views.channelsandservices.ServiceProviders;
import com.vaadin.starter.business.ui.views.systemconfig.GeneralConfiguration;
import com.vaadin.starter.business.ui.views.systemconfig.Workflows;
import com.vaadin.starter.business.ui.views.systemconfig.Notifications;
import com.vaadin.starter.business.ui.views.systemconfig.MasterData;
import com.vaadin.starter.business.ui.views.admintools.SystemMonitoring;
import com.vaadin.starter.business.ui.views.admintools.VersionManagement;
import com.vaadin.starter.business.ui.views.admintools.DatabaseMaintenance;
import com.vaadin.starter.business.ui.views.reports.ReportDesigner;
import com.vaadin.starter.business.ui.views.reports.ExportAndIntegration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CssImport(value = "./styles/components/charts.css", themeFor = "vaadin-chart")
@CssImport(value = "./styles/components/floating-action-button.css", themeFor = "vaadin-button")
@CssImport(value = "./styles/components/grid.css", themeFor = "vaadin-grid")
@CssImport("./styles/lumo/border-radius.css")
@CssImport("./styles/lumo/icon-size.css")
@CssImport("./styles/lumo/margin.css")
@CssImport("./styles/lumo/padding.css")
@CssImport("./styles/lumo/shadow.css")
@CssImport("./styles/lumo/spacing.css")
@CssImport("./styles/lumo/typography.css")
@CssImport("./styles/misc/box-shadow-borders.css")
@CssImport(value = "./styles/styles.css", include = "lumo-badge")
@JsModule("@vaadin/vaadin-lumo-styles/badge")
public class MainLayout extends FlexBoxLayout
		implements RouterLayout, AfterNavigationObserver {

	private static final Logger log = LoggerFactory.getLogger(MainLayout.class);
	private static final String CLASS_NAME = "root";

	private Div appHeaderOuter;

	private FlexBoxLayout row;
	private NaviDrawer naviDrawer;
	private FlexBoxLayout column;

	private Div appHeaderInner;
	private FlexBoxLayout viewContainer;
	private Div appFooterInner;

	private Div appFooterOuter;

	private TabBar tabBar;
	private boolean navigationTabs = false;
	private AppBar appBar;

	public MainLayout() {
		VaadinSession.getCurrent()
				.setErrorHandler((ErrorHandler) errorEvent -> {
					log.error("Uncaught UI exception",
							errorEvent.getThrowable());
					Notification.show(
							"We are sorry, but an internal error occurred");
				});

		addClassName(CLASS_NAME);
		setFlexDirection(FlexDirection.COLUMN);
		setSizeFull();

		// Initialise the UI building blocks
		initStructure();

		// Populate the navigation drawer
		initNaviItems();

		// Configure the headers and footers (optional)
		initHeadersAndFooters();
	}

	/**
	 * Initialise the required components and containers.
	 */
	private void initStructure() {
		naviDrawer = new NaviDrawer();

		viewContainer = new FlexBoxLayout();
		viewContainer.addClassName(CLASS_NAME + "__view-container");
		viewContainer.setOverflow(Overflow.HIDDEN);

		column = new FlexBoxLayout(viewContainer);
		column.addClassName(CLASS_NAME + "__column");
		column.setFlexDirection(FlexDirection.COLUMN);
		column.setFlexGrow(1, viewContainer);
		column.setOverflow(Overflow.HIDDEN);

		row = new FlexBoxLayout(naviDrawer, column);
		row.addClassName(CLASS_NAME + "__row");
		row.setFlexGrow(1, column);
		row.setOverflow(Overflow.HIDDEN);
		add(row);
		setFlexGrow(1, row);
	}

	/**
	 * Initialise the navigation items.
	 */
	private void initNaviItems() {
		NaviMenu menu = naviDrawer.getMenu();
		NaviItem home = menu.addNaviItem(VaadinIcon.HOME, NavigationConstants.HOME, Home.class);
		home.setTitle(NavigationConstants.HOME);

		NaviItem accounting = menu.addNaviItem(VaadinIcon.MONEY, NavigationConstants.ACCOUNTING_AND_FINANCE,
				null);
		accounting.setTitle(NavigationConstants.ACCOUNTING_AND_FINANCE);
		menu.addNaviItem(accounting, NavigationConstants.ACCOUNTS, Accounts.class);
		menu.addNaviItem(accounting, NavigationConstants.FINANCIAL_CALENDAR, FinancialCalendar.class);
		accounting.setSubItemsVisible(false);

		NaviItem customers = menu.addNaviItem(VaadinIcon.USERS, NavigationConstants.CUSTOMERS,
				null);
		customers.setTitle(NavigationConstants.CUSTOMERS);
		menu.addNaviItem(customers, NavigationConstants.CLIENTS, Clients.class);
		menu.addNaviItem(customers, NavigationConstants.CONTRACT_MANAGEMENT, ContractManagement.class);
		menu.addNaviItem(customers, NavigationConstants.CUSTOMER_SEGMENTATION, CustomerSegmentation.class);
		menu.addNaviItem(customers, NavigationConstants.ONBOARDING_PROCESSES, Onboarding.class);
		customers.setSubItemsVisible(false);

		NaviItem security = menu.addNaviItem(VaadinIcon.SHIELD, NavigationConstants.USERS_AND_SECURITY,
				null);
		security.setTitle(NavigationConstants.USERS_AND_SECURITY);
		menu.addNaviItem(security, NavigationConstants.INTERNAL_USERS, InternalUsers.class);
		menu.addNaviItem(security, NavigationConstants.ROLES_AND_PERMISSIONS, RolesPermissions.class);
		menu.addNaviItem(security, NavigationConstants.SECURITY_POLICIES, SecurityPolicies.class);
		security.setSubItemsVisible(false);

		NaviItem products = menu.addNaviItem(VaadinIcon.PACKAGE, NavigationConstants.PRODUCTS_AND_SERVICES,
				null);
		products.setTitle(NavigationConstants.PRODUCTS_AND_SERVICES);
		menu.addNaviItem(products, NavigationConstants.PRODUCT_CATALOG, ProductCatalog.class);
		menu.addNaviItem(products, NavigationConstants.RATES_AND_FEES, RatesFees.class);
		menu.addNaviItem(products, NavigationConstants.LENDING_CONFIGURATION, LendingConfiguration.class);
		products.setSubItemsVisible(false);

		NaviItem riskCompliance = menu.addNaviItem(VaadinIcon.WARNING, NavigationConstants.RISK_AND_COMPLIANCE,
				null);
		riskCompliance.setTitle(NavigationConstants.RISK_AND_COMPLIANCE);
		menu.addNaviItem(riskCompliance, NavigationConstants.RISK_MODEL, RiskModel.class);
		menu.addNaviItem(riskCompliance, NavigationConstants.FRAUD_PREVENTION, FraudPrevention.class);
		menu.addNaviItem(riskCompliance, NavigationConstants.REGULATORY_COMPLIANCE, RegulatoryCompliance.class);
		riskCompliance.setSubItemsVisible(false);

		NaviItem channelsServices = menu.addNaviItem(VaadinIcon.CONNECT, NavigationConstants.CHANNELS_AND_EXTERNAL_SERVICES,
				null);
		channelsServices.setTitle(NavigationConstants.CHANNELS_AND_EXTERNAL_SERVICES);
		menu.addNaviItem(channelsServices, NavigationConstants.CHANNEL_INTEGRATION, ChannelIntegration.class);
		menu.addNaviItem(channelsServices, NavigationConstants.SERVICE_PROVIDERS, ServiceProviders.class);
		channelsServices.setSubItemsVisible(false);

		NaviItem systemConfig = menu.addNaviItem(VaadinIcon.COG, NavigationConstants.SYSTEM_CONFIGURATION,
				null);
		systemConfig.setTitle(NavigationConstants.SYSTEM_CONFIGURATION);
		menu.addNaviItem(systemConfig, NavigationConstants.GENERAL_CONFIGURATION, GeneralConfiguration.class);
		menu.addNaviItem(systemConfig, NavigationConstants.WORKFLOWS, Workflows.class);
		menu.addNaviItem(systemConfig, NavigationConstants.NOTIFICATIONS, Notifications.class);
		menu.addNaviItem(systemConfig, NavigationConstants.MASTER_DATA, MasterData.class);
		systemConfig.setSubItemsVisible(false);

		NaviItem adminTools = menu.addNaviItem(VaadinIcon.TOOLS, NavigationConstants.ADMINISTRATION_TOOLS,
				null);
		adminTools.setTitle(NavigationConstants.ADMINISTRATION_TOOLS);
		menu.addNaviItem(adminTools, NavigationConstants.SYSTEM_MONITORING, SystemMonitoring.class);
		menu.addNaviItem(adminTools, NavigationConstants.VERSION_MANAGEMENT, VersionManagement.class);
		menu.addNaviItem(adminTools, NavigationConstants.DATABASE_MAINTENANCE, DatabaseMaintenance.class);
		adminTools.setSubItemsVisible(false);

		NaviItem reportsConfig = menu.addNaviItem(VaadinIcon.FILE_TEXT, NavigationConstants.REPORTS_CONFIGURATION,
				null);
		reportsConfig.setTitle(NavigationConstants.REPORTS_CONFIGURATION);
		menu.addNaviItem(reportsConfig, NavigationConstants.REPORT_DESIGNER, ReportDesigner.class);
		menu.addNaviItem(reportsConfig, NavigationConstants.EXPORT_AND_INTEGRATION, ExportAndIntegration.class);
		reportsConfig.setSubItemsVisible(false);
	}

	/**
	 * Configure the app's inner and outer headers and footers.
	 */
	private void initHeadersAndFooters() {
		// setAppHeaderOuter();
		// setAppFooterInner();
		// setAppFooterOuter();

		// Default inner header setup:
		// - When using tabbed navigation the view title, user avatar and main menu button will appear in the TabBar.
		// - When tabbed navigation is turned off they appear in the AppBar.

		appBar = new AppBar("");

		// Tabbed navigation
		if (navigationTabs) {
			tabBar = new TabBar();
			UIUtils.setTheme(Lumo.DARK, tabBar);

			// Shift-click to add a new tab
			for (NaviItem item : naviDrawer.getMenu().getNaviItems()) {
				item.addClickListener(e -> {
					if (e.getButton() == 0 && e.isShiftKey()) {
						tabBar.setSelectedTab(tabBar.addClosableTab(item.getText(), item.getNavigationTarget()));
					}
				});
			}
			appBar.getAvatar().setVisible(false);
			setAppHeaderInner(tabBar, appBar);

			// Default navigation
		} else {
			UIUtils.setTheme(Lumo.DARK, appBar);
			setAppHeaderInner(appBar);
		}
	}

	private void setAppHeaderOuter(Component... components) {
		if (appHeaderOuter == null) {
			appHeaderOuter = new Div();
			appHeaderOuter.addClassName("app-header-outer");
			getElement().insertChild(0, appHeaderOuter.getElement());
		}
		appHeaderOuter.removeAll();
		appHeaderOuter.add(components);
	}

	private void setAppHeaderInner(Component... components) {
		if (appHeaderInner == null) {
			appHeaderInner = new Div();
			appHeaderInner.addClassName("app-header-inner");
			column.getElement().insertChild(0, appHeaderInner.getElement());
		}
		appHeaderInner.removeAll();
		appHeaderInner.add(components);
	}

	private void setAppFooterInner(Component... components) {
		if (appFooterInner == null) {
			appFooterInner = new Div();
			appFooterInner.addClassName("app-footer-inner");
			column.getElement().insertChild(column.getElement().getChildCount(),
					appFooterInner.getElement());
		}
		appFooterInner.removeAll();
		appFooterInner.add(components);
	}

	private void setAppFooterOuter(Component... components) {
		if (appFooterOuter == null) {
			appFooterOuter = new Div();
			appFooterOuter.addClassName("app-footer-outer");
			getElement().insertChild(getElement().getChildCount(),
					appFooterOuter.getElement());
		}
		appFooterOuter.removeAll();
		appFooterOuter.add(components);
	}

	@Override
	public void showRouterLayoutContent(HasElement content) {
		this.viewContainer.getElement().appendChild(content.getElement());
	}

	public NaviDrawer getNaviDrawer() {
		return naviDrawer;
	}

	public static MainLayout get() {
		return (MainLayout) UI.getCurrent().getChildren()
				.filter(component -> component.getClass() == MainLayout.class)
				.findFirst().get();
	}

	public AppBar getAppBar() {
		return appBar;
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		if (navigationTabs) {
			afterNavigationWithTabs(event);
		} else {
			afterNavigationWithoutTabs(event);
		}
	}

	private void afterNavigationWithTabs(AfterNavigationEvent e) {
		NaviItem active = getActiveItem(e);
		if (active == null) {
			if (tabBar.getTabCount() == 0) {
				tabBar.addClosableTab("", Home.class);
			}
		} else {
			if (tabBar.getTabCount() > 0) {
				tabBar.updateSelectedTab(active.getText(),
						active.getNavigationTarget());
			} else {
				tabBar.addClosableTab(active.getText(),
						active.getNavigationTarget());
			}
		}
		appBar.getMenuIcon().setVisible(false);
	}

	private NaviItem getActiveItem(AfterNavigationEvent e) {
		for (NaviItem item : naviDrawer.getMenu().getNaviItems()) {
			if (item.isHighlighted(e)) {
				return item;
			}
		}
		return null;
	}

	private void afterNavigationWithoutTabs(AfterNavigationEvent e) {
		NaviItem active = getActiveItem(e);
		if (active != null) {
			getAppBar().setTitle(active.getText());
		}
	}

}
