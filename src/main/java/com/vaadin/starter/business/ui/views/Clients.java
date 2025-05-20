package com.vaadin.starter.business.ui.views;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.starter.business.backend.Client;
import com.vaadin.starter.business.backend.mapper.ClientMapper;
import com.vaadin.starter.business.backend.service.ClientsService;
import com.vaadin.starter.business.ui.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.starter.business.ui.components.Badge;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.components.ListItem;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Right;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.layout.size.Vertical;
import com.vaadin.starter.business.ui.util.FontSize;
import com.vaadin.starter.business.ui.util.LineHeight;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.TextColor;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.util.css.Overflow;
import com.vaadin.starter.business.ui.util.css.PointerEvents;
import com.vaadin.starter.business.ui.util.css.TextOverflow;
import com.vaadin.starter.business.ui.util.css.lumo.BadgeColor;
import com.vaadin.starter.business.ui.util.css.lumo.BadgeShape;
import com.vaadin.starter.business.ui.util.css.lumo.BadgeSize;

@PageTitle("Clients")
@Route(value = "clients", layout = MainLayout.class)
public class Clients extends ViewFrame {

	public static final int MOBILE_BREAKPOINT = 480;
	private Grid<Client> grid;
	private Registration resizeListener;
	private ListDataProvider<Client> dataProvider;

	// Filter form fields
	private TextField idFilter;
	private TextField nameFilter;
	private TextField emailFilter;
	private TextField phoneFilter;
	private NumberField minBalanceFilter;
	private NumberField maxBalanceFilter;
	private DatePicker startDateFilter;
	private DatePicker endDateFilter;

	private final ClientsService clientsService;

	private final ClientMapper clientMapper;

	@Autowired
	public Clients(ClientsService clientsService, ClientMapper clientMapper) {
        this.clientsService = clientsService;
        this.clientMapper = clientMapper;
        setViewContent(createContent());
	}

	private Component createContent() {
		FlexBoxLayout content = new FlexBoxLayout(createFilterForm(), createGrid());
		content.setBoxSizing(BoxSizing.BORDER_BOX);
		content.setHeightFull();
		content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
		content.setFlexDirection(FlexDirection.COLUMN);
		return content;
	}

	private Component createFilterForm() {
		// Initialize filter fields
		idFilter = new TextField();
		idFilter.setValueChangeMode(ValueChangeMode.EAGER);
		idFilter.setClearButtonVisible(true);

		nameFilter = new TextField();
		nameFilter.setValueChangeMode(ValueChangeMode.EAGER);
		nameFilter.setClearButtonVisible(true);

		emailFilter = new TextField();
		emailFilter.setValueChangeMode(ValueChangeMode.EAGER);
		emailFilter.setClearButtonVisible(true);

		phoneFilter = new TextField();
		phoneFilter.setValueChangeMode(ValueChangeMode.EAGER);
		phoneFilter.setClearButtonVisible(true);

		minBalanceFilter = new NumberField();
		minBalanceFilter.setClearButtonVisible(true);

		maxBalanceFilter = new NumberField();
		maxBalanceFilter.setClearButtonVisible(true);

		startDateFilter = new DatePicker();
		startDateFilter.setClearButtonVisible(true);

		endDateFilter = new DatePicker();
		endDateFilter.setClearButtonVisible(true);

		// Create buttons
		Button searchButton = UIUtils.createPrimaryButton("Search");
		searchButton.addClickListener(e -> applyFilters());

		Button clearButton = UIUtils.createTertiaryButton("Clear");
		clearButton.addClickListener(e -> clearFilters());

		// Create button layout
		HorizontalLayout buttonLayout = new HorizontalLayout(searchButton, clearButton);
		buttonLayout.setSpacing(true);

		// Create form layout
		FormLayout formLayout = new FormLayout();
		formLayout.addFormItem(idFilter, "ID");
		formLayout.addFormItem(nameFilter, "Name");
		formLayout.addFormItem(emailFilter, "Email");
		formLayout.addFormItem(phoneFilter, "Phone");
		formLayout.addFormItem(minBalanceFilter, "Min Balance ($)");
		formLayout.addFormItem(maxBalanceFilter, "Max Balance ($)");
		formLayout.addFormItem(startDateFilter, "From Date");
		formLayout.addFormItem(endDateFilter, "To Date");

		formLayout.setResponsiveSteps(
			new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
			new FormLayout.ResponsiveStep("600px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
			new FormLayout.ResponsiveStep("900px", 4, FormLayout.ResponsiveStep.LabelsPosition.TOP)
		);

		// Create a container for the form and buttons
		Div formContainer = new Div(formLayout, buttonLayout);
		formContainer.getStyle().set("padding", "1em");
		formContainer.getStyle().set("box-shadow", "0 0 0 1px var(--lumo-contrast-10pct)");
		formContainer.getStyle().set("border-radius", "var(--lumo-border-radius)");
		formContainer.getStyle().set("background-color", "var(--lumo-base-color)");
		formContainer.getStyle().set("margin-bottom", "1em");

		return formContainer;
	}

	private Grid createGrid() {
		grid = new Grid<>();
		grid.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(this::viewDetails));
		grid.addThemeName("mobile");

		// Initialize data provider
		Collection<Client> clients = clientsService.getClients();
		dataProvider = new ListDataProvider<>(clients);
		grid.setDataProvider(dataProvider);

		grid.setId("clients");
		grid.setSizeFull();

		// "Mobile" column
		grid.addColumn(new ComponentRenderer<>(this::getMobileTemplate))
				.setVisible(false);

		// "Desktop" columns
		grid.addColumn(Client::getId)
				.setAutoWidth(true)
				.setFlexGrow(0)
				.setFrozen(true)
				.setHeader("ID")
				.setSortable(true);
		grid.addColumn(new ComponentRenderer<>(this::createNameInfo))
				.setHeader("Name")
				.setComparator((client1, client2) ->
					client1.getName().compareToIgnoreCase(client2.getName()))
				.setSortable(true)
				.setWidth("200px");
		grid.addColumn(new ComponentRenderer<>(this::createContactInfo))
				.setComparator(Client::getEmail)
				.setHeader("Contact Info")
				.setWidth("200px");
		grid.addColumn(new ComponentRenderer<>(this::createBalance))
				.setAutoWidth(true)
				.setComparator(Client::getBalance)
				.setFlexGrow(0)
				.setHeader("Balance ($)")
				.setTextAlign(ColumnTextAlign.END);
		grid.addColumn(new LocalDateRenderer<>(Client::getRegistered, "MMM dd, YYYY"))
				.setAutoWidth(true)
				.setComparator(Client::getRegistered)
				.setFlexGrow(0)
				.setHeader("Registered");

		return grid;
	}

	private ClientMobileTemplate getMobileTemplate(Client client) {
		return new ClientMobileTemplate(client);
	}

	private Component createNameInfo(Client client) {
		ListItem item = new ListItem(client.getName());
		item.setPadding(Vertical.XS);
		item.setPrefix(new Image(client.getLogoPath(), "Client avatar"));
		item.setSpacing(Right.M);
		return item;
	}

	private Component createContactInfo(Client client) {
		ListItem item = new ListItem(client.getEmail(), client.getPhone());
		item.setPadding(Vertical.XS);
		return item;
	}

	private Span createBalance(Client client) {
		Double balance = client.getBalance();
		Span amountLabel = new Span(UIUtils.formatAmount(balance));
		amountLabel.addClassName(LumoStyles.FontFamily.MONOSPACE);
		if (balance > 0) {
			UIUtils.setTextColor(TextColor.SUCCESS, amountLabel);
		} else {
			UIUtils.setTextColor(TextColor.ERROR, amountLabel);
		}
		return amountLabel;
	}

	private void viewDetails(Client client) {
		UI.getCurrent().navigate(ClientDetails.class, client.getId());
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		getUI().ifPresent(ui -> {
			Page page = ui.getPage();
			resizeListener = page.addBrowserWindowResizeListener(event -> updateVisibleColumns(event.getWidth()));
			page.retrieveExtendedClientDetails(details -> updateVisibleColumns(details.getBodyClientWidth()));
		});
	}

	@Override
	protected void onDetach(DetachEvent detachEvent) {
		resizeListener.remove();
		super.onDetach(detachEvent);
	}

	private void updateVisibleColumns(int width) {
		boolean mobile = width < MOBILE_BREAKPOINT;
		List<Grid.Column<Client>> columns = grid.getColumns();

		// "Mobile" column
		columns.get(0).setVisible(mobile);

		// "Desktop" columns
		for (int i = 1; i < columns.size(); i++) {
			columns.get(i).setVisible(!mobile);
		}
	}

	private void applyFilters() {
		dataProvider.clearFilters();

		// Apply ID filter
		if (idFilter.getValue() != null && !idFilter.getValue().isEmpty()) {
			try {
				Long id = Long.parseLong(idFilter.getValue());
				dataProvider.addFilter(client -> client.getId().equals(id));
			} catch (NumberFormatException e) {
				// Invalid ID format, ignore this filter
			}
		}

		// Apply name filter
		if (nameFilter.getValue() != null && !nameFilter.getValue().isEmpty()) {
			String nameValue = nameFilter.getValue().toLowerCase();
			dataProvider.addFilter(client -> 
				client.getName() != null && 
				client.getName().toLowerCase().contains(nameValue));
		}

		// Apply email filter
		if (emailFilter.getValue() != null && !emailFilter.getValue().isEmpty()) {
			String emailValue = emailFilter.getValue().toLowerCase();
			dataProvider.addFilter(client -> 
				client.getEmail() != null && 
				client.getEmail().toLowerCase().contains(emailValue));
		}

		// Apply phone filter
		if (phoneFilter.getValue() != null && !phoneFilter.getValue().isEmpty()) {
			String phoneValue = phoneFilter.getValue().toLowerCase();
			dataProvider.addFilter(client -> 
				client.getPhone() != null && 
				client.getPhone().toLowerCase().contains(phoneValue));
		}

		// Apply min balance filter
		if (minBalanceFilter.getValue() != null) {
			Double minValue = minBalanceFilter.getValue();
			dataProvider.addFilter(client -> 
				client.getBalance() != null && 
				client.getBalance() >= minValue);
		}

		// Apply max balance filter
		if (maxBalanceFilter.getValue() != null) {
			Double maxValue = maxBalanceFilter.getValue();
			dataProvider.addFilter(client -> 
				client.getBalance() != null && 
				client.getBalance() <= maxValue);
		}

		// Apply start date filter
		if (startDateFilter.getValue() != null) {
			LocalDate startDate = startDateFilter.getValue();
			dataProvider.addFilter(client -> 
				client.getRegistered() != null && 
				!client.getRegistered().isBefore(startDate));
		}

		// Apply end date filter
		if (endDateFilter.getValue() != null) {
			LocalDate endDate = endDateFilter.getValue();
			dataProvider.addFilter(client -> 
				client.getRegistered() != null && 
				!client.getRegistered().isAfter(endDate));
		}
	}

	private void clearFilters() {
		// Clear all filter fields
		idFilter.clear();
		nameFilter.clear();
		emailFilter.clear();
		phoneFilter.clear();
		minBalanceFilter.clear();
		maxBalanceFilter.clear();
		startDateFilter.clear();
		endDateFilter.clear();

		// Clear all filters from data provider
		dataProvider.clearFilters();
	}

	/**
	 * A layout for displaying Client info in a mobile friendly format.
	 */
	private class ClientMobileTemplate extends FlexBoxLayout {

		private Client client;

		public ClientMobileTemplate(Client client) {
			this.client = client;

			UIUtils.setLineHeight(LineHeight.M, this);
			UIUtils.setPointerEvents(PointerEvents.NONE, this);

			setPadding(Vertical.S);
			setSpacing(Right.L);

			Image avatar = getAvatar();
			FlexBoxLayout name = getName();
			Span email = getEmail();
			FlexBoxLayout balance = getBalance();

			FlexBoxLayout column = new FlexBoxLayout(name, email, balance);
			column.setFlexDirection(FlexDirection.COLUMN);
			column.setOverflow(Overflow.HIDDEN);

			add(avatar, column);
			setFlexGrow(1, column);
		}

		private Image getAvatar() {
			Image avatar = new Image(client.getLogoPath(), "Client avatar");
			setFlexShrink("0", avatar);
			avatar.setHeight(LumoStyles.IconSize.M);
			avatar.setWidth(LumoStyles.IconSize.M);
			return avatar;
		}

		private FlexBoxLayout getName() {
			Span name = new Span(client.getName());
			UIUtils.setFontSize(FontSize.M, name);
			UIUtils.setTextColor(TextColor.BODY, name);
			UIUtils.setOverflow(Overflow.HIDDEN, name);
			UIUtils.setTextOverflow(TextOverflow.ELLIPSIS, name);

			Badge id = new Badge(String.valueOf(client.getId()), BadgeColor.NORMAL, BadgeSize.S, BadgeShape.PILL);

			FlexBoxLayout wrapper = new FlexBoxLayout(name, id);
			wrapper.setAlignItems(Alignment.CENTER);
			wrapper.setFlexGrow(1, name);
			wrapper.setFlexShrink("0", id);
			wrapper.setSpacing(Right.M);
			return wrapper;
		}

		private Span getEmail() {
			Span email = new Span(client.getEmail());
			UIUtils.setFontSize(FontSize.S, email);
			UIUtils.setTextColor(TextColor.SECONDARY, email);
			email.getElement().getClassList().add(LumoStyles.Margin.Bottom.S);
			UIUtils.setOverflow(Overflow.HIDDEN, email);
			UIUtils.setTextOverflow(TextOverflow.ELLIPSIS, email);
			return email;
		}

		private FlexBoxLayout getBalance() {
			Span balance = createBalance(client);
			balance.setText("$" + balance.getText());
			Span registered = new Span(UIUtils.formatDate(client.getRegistered()));
			UIUtils.setFontSize(FontSize.XS, registered);
			UIUtils.setTextColor(TextColor.TERTIARY, registered);

			FlexBoxLayout wrapper = new FlexBoxLayout(balance, registered);
			wrapper.setAlignItems(Alignment.BASELINE);
			wrapper.setFlexGrow(1, balance);
			return wrapper;
		}
	}
}