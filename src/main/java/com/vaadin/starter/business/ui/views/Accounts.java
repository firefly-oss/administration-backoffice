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
import com.vaadin.starter.business.backend.BankAccount;
import com.vaadin.starter.business.backend.mapper.BankAccountMapper;
import com.vaadin.starter.business.backend.service.AccountsService;
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

@PageTitle("Accounts")
@Route(value = "accounts", layout = MainLayout.class)
public class Accounts extends ViewFrame {

	public static final int MOBILE_BREAKPOINT = 480;
	private Grid<BankAccount> grid;
	private Registration resizeListener;
	private ListDataProvider<BankAccount> dataProvider;

	// Filter form fields
	private TextField idFilter;
	private TextField ownerFilter;
	private TextField bankFilter;
	private TextField accountFilter;
	private NumberField minAvailabilityFilter;
	private NumberField maxAvailabilityFilter;
	private DatePicker startDateFilter;
	private DatePicker endDateFilter;

	private final AccountsService accountsService;

	private final BankAccountMapper bankAccountMapper;

	@Autowired
	public Accounts(AccountsService accountsService, BankAccountMapper bankAccountMapper) {
        this.accountsService = accountsService;
        this.bankAccountMapper = bankAccountMapper;
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

		ownerFilter = new TextField();
		ownerFilter.setValueChangeMode(ValueChangeMode.EAGER);
		ownerFilter.setClearButtonVisible(true);

		bankFilter = new TextField();
		bankFilter.setValueChangeMode(ValueChangeMode.EAGER);
		bankFilter.setClearButtonVisible(true);

		accountFilter = new TextField();
		accountFilter.setValueChangeMode(ValueChangeMode.EAGER);
		accountFilter.setClearButtonVisible(true);

		minAvailabilityFilter = new NumberField();
		minAvailabilityFilter.setClearButtonVisible(true);

		maxAvailabilityFilter = new NumberField();
		maxAvailabilityFilter.setClearButtonVisible(true);

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
		formLayout.addFormItem(ownerFilter, "Owner");
		formLayout.addFormItem(bankFilter, "Bank");
		formLayout.addFormItem(accountFilter, "Account");
		formLayout.addFormItem(minAvailabilityFilter, "Min Availability ($)");
		formLayout.addFormItem(maxAvailabilityFilter, "Max Availability ($)");
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
		Collection<BankAccount> accounts = accountsService.getBankAccounts();
		dataProvider = new ListDataProvider<>(accounts);
		grid.setDataProvider(dataProvider);

		grid.setId("accounts");
		grid.setSizeFull();

		// "Mobile" column
		grid.addColumn(new ComponentRenderer<>(this::getMobileTemplate))
				.setVisible(false);

		// "Desktop" columns
		grid.addColumn(BankAccount::getId)
				.setAutoWidth(true)
				.setFlexGrow(0)
				.setFrozen(true)
				.setHeader("ID")
				.setSortable(true);
		grid.addColumn(new ComponentRenderer<>(this::createOwnerInfo))
				.setHeader("Owner")
				.setComparator((account1, account2) ->
					account1.getOwner().compareToIgnoreCase(account2.getOwner()))
				.setSortable(true)
				.setWidth("200px");
		grid.addColumn(new ComponentRenderer<>(this::createBankInfo))
				.setComparator(BankAccount::getAccount)
				.setHeader("Bank Account")
				.setWidth("200px");
		grid.addColumn(new ComponentRenderer<>(this::createAvailability))
				.setAutoWidth(true)
				.setComparator(BankAccount::getAvailability)
				.setFlexGrow(0)
				.setHeader("Availability ($)")
				.setTextAlign(ColumnTextAlign.END);
		grid.addColumn(new LocalDateRenderer<>(BankAccount::getUpdated, "MMM dd, YYYY"))
				.setAutoWidth(true)
				.setComparator(BankAccount::getUpdated)
				.setFlexGrow(0)
				.setHeader("Updated");

		return grid;
	}

	private BankAccountMobileTemplate getMobileTemplate(BankAccount bankAccount) {
		return new BankAccountMobileTemplate(bankAccount);
	}

	private Component createOwnerInfo(BankAccount bankAccount) {
		ListItem item = new ListItem(bankAccount.getOwner());
		item.setPadding(Vertical.XS);
		item.setPrefix(new Image(bankAccount.getLogoPath(), "Company logo"));
		item.setSpacing(Right.M);
		return item;
	}

	private Component createBankInfo(BankAccount bankAccount) {
		ListItem item = new ListItem(bankAccount.getAccount(), bankAccount.getBank());
		item.setPadding(Vertical.XS);
		return item;
	}

	private Span createAvailability(BankAccount bankAccount) {
		Double availability = bankAccount.getAvailability();
		Span amountLabel = new Span(UIUtils.formatAmount(availability));
		amountLabel.addClassName(LumoStyles.FontFamily.MONOSPACE);
		if (availability > 0) {
			UIUtils.setTextColor(TextColor.SUCCESS, amountLabel);
		} else {
			UIUtils.setTextColor(TextColor.ERROR, amountLabel);
		}
		return amountLabel;
	}

	private void viewDetails(BankAccount bankAccount) {
		UI.getCurrent().navigate(AccountDetails.class, bankAccount.getId());
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
		List<Grid.Column<BankAccount>> columns = grid.getColumns();

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
				dataProvider.addFilter(account -> account.getId().equals(id));
			} catch (NumberFormatException e) {
				// Invalid ID format, ignore this filter
			}
		}

		// Apply owner filter
		if (ownerFilter.getValue() != null && !ownerFilter.getValue().isEmpty()) {
			String ownerValue = ownerFilter.getValue().toLowerCase();
			dataProvider.addFilter(account -> 
				account.getOwner() != null && 
				account.getOwner().toLowerCase().contains(ownerValue));
		}

		// Apply bank filter
		if (bankFilter.getValue() != null && !bankFilter.getValue().isEmpty()) {
			String bankValue = bankFilter.getValue().toLowerCase();
			dataProvider.addFilter(account -> 
				account.getBank() != null && 
				account.getBank().toLowerCase().contains(bankValue));
		}

		// Apply account filter
		if (accountFilter.getValue() != null && !accountFilter.getValue().isEmpty()) {
			String accountValue = accountFilter.getValue().toLowerCase();
			dataProvider.addFilter(account -> 
				account.getAccount() != null && 
				account.getAccount().toLowerCase().contains(accountValue));
		}

		// Apply min availability filter
		if (minAvailabilityFilter.getValue() != null) {
			Double minValue = minAvailabilityFilter.getValue();
			dataProvider.addFilter(account -> 
				account.getAvailability() != null && 
				account.getAvailability() >= minValue);
		}

		// Apply max availability filter
		if (maxAvailabilityFilter.getValue() != null) {
			Double maxValue = maxAvailabilityFilter.getValue();
			dataProvider.addFilter(account -> 
				account.getAvailability() != null && 
				account.getAvailability() <= maxValue);
		}

		// Apply start date filter
		if (startDateFilter.getValue() != null) {
			LocalDate startDate = startDateFilter.getValue();
			dataProvider.addFilter(account -> 
				account.getUpdated() != null && 
				!account.getUpdated().isBefore(startDate));
		}

		// Apply end date filter
		if (endDateFilter.getValue() != null) {
			LocalDate endDate = endDateFilter.getValue();
			dataProvider.addFilter(account -> 
				account.getUpdated() != null && 
				!account.getUpdated().isAfter(endDate));
		}
	}

	private void clearFilters() {
		// Clear all filter fields
		idFilter.clear();
		ownerFilter.clear();
		bankFilter.clear();
		accountFilter.clear();
		minAvailabilityFilter.clear();
		maxAvailabilityFilter.clear();
		startDateFilter.clear();
		endDateFilter.clear();

		// Clear all filters from data provider
		dataProvider.clearFilters();
	}

	/**
	 * A layout for displaying BankAccount info in a mobile friendly format.
	 */
	private class BankAccountMobileTemplate extends FlexBoxLayout {

		private BankAccount bankAccount;

		public BankAccountMobileTemplate(BankAccount bankAccount) {
			this.bankAccount = bankAccount;

			UIUtils.setLineHeight(LineHeight.M, this);
			UIUtils.setPointerEvents(PointerEvents.NONE, this);

			setPadding(Vertical.S);
			setSpacing(Right.L);

			Image logo = getLogo();
			FlexBoxLayout owner = getOwner();
			Span account = getAccount();
			FlexBoxLayout availability = getAvailability();

			FlexBoxLayout column = new FlexBoxLayout(owner, account, availability);
			column.setFlexDirection(FlexDirection.COLUMN);
			column.setOverflow(Overflow.HIDDEN);

			add(logo, column);
			setFlexGrow(1, column);
		}

		private Image getLogo() {
			Image logo = new Image(bankAccount.getLogoPath(), "Company logo");
			setFlexShrink("0", logo);
			logo.setHeight(LumoStyles.IconSize.M);
			logo.setWidth(LumoStyles.IconSize.M);
			return logo;
		}

		private FlexBoxLayout getOwner() {
			Span owner = new Span(bankAccount.getOwner());
			UIUtils.setFontSize(FontSize.M, owner);
			UIUtils.setTextColor(TextColor.BODY, owner);
			UIUtils.setOverflow(Overflow.HIDDEN, owner);
			UIUtils.setTextOverflow(TextOverflow.ELLIPSIS, owner);

			Badge id = new Badge(String.valueOf(bankAccount.getId()), BadgeColor.NORMAL, BadgeSize.S, BadgeShape.PILL);

			FlexBoxLayout wrapper = new FlexBoxLayout(owner, id);
			wrapper.setAlignItems(Alignment.CENTER);
			wrapper.setFlexGrow(1, owner);
			wrapper.setFlexShrink("0", id);
			wrapper.setSpacing(Right.M);
			return wrapper;
		}

		private Span getAccount() {
			Span account = new Span(bankAccount.getAccount());
			UIUtils.setFontSize(FontSize.S, account);
			UIUtils.setTextColor(TextColor.SECONDARY, account);
			account.getElement().getClassList().add(LumoStyles.Margin.Bottom.S);
			UIUtils.setOverflow(Overflow.HIDDEN, account);
			UIUtils.setTextOverflow(TextOverflow.ELLIPSIS, account);
			return account;
		}

		private FlexBoxLayout getAvailability() {
			Span availability = createAvailability(bankAccount);
			availability.setText("$" + availability.getText());
			Span updated = new Span(UIUtils.formatDate(bankAccount.getUpdated()));
			UIUtils.setFontSize(FontSize.XS, updated);
			UIUtils.setTextColor(TextColor.TERTIARY, updated);

			FlexBoxLayout wrapper = new FlexBoxLayout(availability, updated);
			wrapper.setAlignItems(Alignment.BASELINE);
			wrapper.setFlexGrow(1, availability);
			return wrapper;
		}
	}
}
