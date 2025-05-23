package com.vaadin.starter.business.ui.views.clients;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.backend.Client;
import com.vaadin.starter.business.backend.DummyData;
import com.vaadin.starter.business.backend.service.ClientsService;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.components.ListItem;
import com.vaadin.starter.business.ui.components.navigation.bar.AppBar;
import com.vaadin.starter.business.ui.layout.size.Bottom;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.layout.size.Vertical;
import com.vaadin.starter.business.ui.util.BoxShadowBorders;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.TextColor;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BorderRadius;
import com.vaadin.starter.business.ui.util.css.WhiteSpace;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexWrap;
import com.vaadin.starter.business.ui.views.ViewFrame;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@PageTitle("Client Details")
@Route(value = "client-details", layout = MainLayout.class)
public class ClientDetails extends ViewFrame implements HasUrlParameter<Long> {

	public int RECENT_TRANSACTIONS = 4;

	private ListItem balance;
	private ListItem contactInfo;
	private ListItem address;
	private ListItem registered;

	private Client client;
	private final ClientsService clientsService;

	@Autowired
	public ClientDetails(ClientsService clientsService) {
		this.clientsService = clientsService;
	}

	@Override
	public void setParameter(BeforeEvent beforeEvent, Long id) {
		client = clientsService.getClient(id);
		setViewContent(createContent());
	}

	private Component createContent() {
		FlexBoxLayout content = new FlexBoxLayout(
				createLogoSection(),
				createRecentTransactionsHeader(),
				createRecentTransactionsList(),
				createMonthlyOverviewHeader(),
				createMonthlyOverviewChart()
		);
		content.setFlexDirection(FlexDirection.COLUMN);
		content.setMargin(Horizontal.AUTO, Vertical.RESPONSIVE_L);
		content.setMaxWidth("840px");
		return content;
	}

	private FlexBoxLayout createLogoSection() {
		Image image = new Image(client.getLogoPath(), "Client Avatar");
		image.addClassName(LumoStyles.Margin.Horizontal.L);
		UIUtils.setBorderRadius(BorderRadius._50, image);
		image.setHeight("200px");
		image.setWidth("200px");

		balance = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.DOLLAR), "", "Balance");
		balance.getPrimary().addClassName(LumoStyles.Heading.H2);
		balance.setDividerVisible(true);
		balance.setId("balance");
		balance.setReverse(true);

		contactInfo = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.ENVELOPE), "", "");
		contactInfo.setDividerVisible(true);
		contactInfo.setId("contactInfo");
		contactInfo.setReverse(true);
		contactInfo.setWhiteSpace(WhiteSpace.PRE_LINE);

		address = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.HOME), "", "Address");
		address.setDividerVisible(true);
		address.setId("address");
		address.setReverse(true);
		address.setWhiteSpace(WhiteSpace.PRE_LINE);

		registered = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.CALENDAR), "", "Registered");
		registered.setReverse(true);

		FlexBoxLayout listItems = new FlexBoxLayout(balance, contactInfo, address, registered);
		listItems.setFlexDirection(FlexDirection.COLUMN);

		FlexBoxLayout section = new FlexBoxLayout(image, listItems);
		section.addClassName(BoxShadowBorders.BOTTOM);
		section.setAlignItems(FlexComponent.Alignment.CENTER);
		section.setFlex("1", listItems);
		section.setFlexWrap(FlexWrap.WRAP);
		section.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		section.setPadding(Bottom.L);
		return section;
	}

	private Component createRecentTransactionsHeader() {
		Span title = new Span("Recent Transactions");
		title.addClassName(LumoStyles.Heading.H3);

		Button viewAll = UIUtils.createSmallButton("View All");
		viewAll.addClickListener(
				e -> UIUtils.showNotification("Not implemented yet."));
		viewAll.addClassName(LumoStyles.Margin.Left.AUTO);

		FlexBoxLayout header = new FlexBoxLayout(title, viewAll);
		header.setAlignItems(FlexComponent.Alignment.CENTER);
		header.setMargin(Bottom.M, Horizontal.RESPONSIVE_L, Top.L);
		return header;
	}

	private Component createRecentTransactionsList() {
		Div items = new Div();
		items.addClassNames(BoxShadowBorders.BOTTOM, LumoStyles.Padding.Bottom.L);

		for (int i = 0; i < RECENT_TRANSACTIONS; i++) {
			Double amount = DummyData.getAmount();
			Span amountLabel = new Span(UIUtils.formatAmount(amount));
			amountLabel.addClassName(LumoStyles.FontFamily.MONOSPACE);
			amountLabel.addClassName(LumoStyles.Heading.H5);
			if (amount > 0) {
				UIUtils.setTextColor(TextColor.SUCCESS, amountLabel);
			} else {
				UIUtils.setTextColor(TextColor.ERROR, amountLabel);
			}
			ListItem item = new ListItem(
					DummyData.getLogo(),
					DummyData.getCompany(),
					UIUtils.formatDate(LocalDate.now().minusDays(i)),
					amountLabel
			);
			// Dividers for all but the last item
			item.setDividerVisible(i < RECENT_TRANSACTIONS - 1);
			items.add(item);
		}

		return items;
	}

	private Component createMonthlyOverviewHeader() {
		Span header = new Span("Monthly Overview");
		header.addClassName(LumoStyles.Heading.H3);
		header.addClassName(LumoStyles.Margin.Vertical.L);
		header.addClassName(LumoStyles.Margin.Responsive.Horizontal.L);
		return header;
	}

	private Component createMonthlyOverviewChart() {
		Chart chart = new Chart(ChartType.COLUMN);

		Configuration conf = chart.getConfiguration();
		conf.setTitle("");
		conf.getLegend().setEnabled(true);

		XAxis xAxis = new XAxis();
		xAxis.setCategories("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
		conf.addxAxis(xAxis);

		conf.getyAxis().setTitle("Amount ($)");

		// Withdrawals and deposits
		ListSeries withDrawals = new ListSeries("Withdrawals");
		ListSeries deposits = new ListSeries("Deposits");

		for (int i = 0; i < 8; i++) {
			withDrawals.addData(DummyData.getRandomInt(5000, 10000));
			deposits.addData(DummyData.getRandomInt(5000, 10000));
		}

		conf.addSeries(withDrawals);
		conf.addSeries(deposits);

		FlexBoxLayout card = new FlexBoxLayout(chart);
		card.setHeight("400px");
		return card;
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);

		initAppBar();
		UI.getCurrent().getPage().setTitle(client.getName());

		balance.setPrimaryText(UIUtils.formatAmount(client.getBalance()));
		contactInfo.setPrimaryText(client.getEmail());
		contactInfo.setSecondaryText(client.getPhone());
		address.setPrimaryText(client.getAddress());
		registered.setPrimaryText(UIUtils.formatDate(client.getRegistered()));
	}

	private AppBar initAppBar() {
		AppBar appBar = MainLayout.get().getAppBar();
		appBar.setNaviMode(AppBar.NaviMode.CONTEXTUAL);
		appBar.getContextIcon().addClickListener(e -> UI.getCurrent().navigate(Clients.class));
		appBar.setTitle(client.getName());
		return appBar;
	}
}
