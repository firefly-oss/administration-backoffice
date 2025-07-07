package com.vaadin.starter.business.ui.views.clients;

import com.catalis.common.customer.sdk.model.LegalPersonDTO;
import com.catalis.common.customer.sdk.model.NaturalPersonDTO;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.starter.business.backend.sdks.services.CustomersService;
import com.vaadin.starter.business.backend.sdks.services.rest.customers.LegalPersonRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.customers.NaturalPersonRequest;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.ViewFrame;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@PageTitle("Clients")
@Route(value = "clients", layout = MainLayout.class)
public class Clients extends ViewFrame {

	public static final int MOBILE_BREAKPOINT = 480;
	private Grid<LegalPersonDTO> grid;
	private Registration resizeListener;
	private ListDataProvider<LegalPersonDTO> dataProvider;
	private UI ui;

	// Legal Person Filter form fields
	private TextField legalNameFilter;
	private TextField tradeNameFilter;
	private TextField registrationNumberFilter;
	private TextField taxIdFilter;
	private TextField businessActivityFilter;
	private NumberField numberOfEmployeesFilter;
	private TextField incorporationCountryFilter;
	private TextField phoneNumberFilter;
	private TextField emailAddressFilter;
	private TextField mainContactNameFilter;
	private DatePicker dateOfIncorporationFilter;
	private DatePicker dateCreatedFromFilter;
	private DatePicker dateCreatedToFilter;
	private DatePicker dateUpdatedFromFilter;
	private DatePicker dateUpdatedToFilter;

	// Natural Person Filter form fields
	private TextField firstNameFilter;
	private TextField middleNameFilter;
	private TextField firstSurnameFilter;
	private TextField secondSurnameFilter;
	private TextField taxIdentificationNumberFilter;
	private TextField nationalityFilter;
	private TextField countryOfResidenceFilter;
	private DatePicker dateOfBirthFilter;
	private TextField genderFilter;
	private TextField maritalStatusFilter;
	private DatePicker naturalPersonDateCreatedFromFilter;
	private DatePicker naturalPersonDateCreatedToFilter;
	private DatePicker naturalPersonDateUpdatedFromFilter;
	private DatePicker naturalPersonDateUpdatedToFilter;

	// Natural Person Grid
	private Grid<NaturalPersonDTO> naturalPersonGrid;
	private ListDataProvider<NaturalPersonDTO> naturalPersonDataProvider;

	private final CustomersService customersService;

	@Autowired
	public Clients(CustomersService customersService) {
		this.customersService = customersService;
		setViewContent(createContent());
	}

	private Component createContent() {
		FlexBoxLayout content = new FlexBoxLayout(createTabbedLayout());
		content.setBoxSizing(BoxSizing.BORDER_BOX);
		content.setHeightFull();
		content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
		content.setFlexDirection(FlexDirection.COLUMN);
		return content;
	}

	private Component createTabbedLayout() {
		// Create tabs
		Tab legalPersonsTab = new Tab("Legal persons");
		Tab naturalPersonsTab = new Tab("Natural persons");

		Tabs tabs = new Tabs(legalPersonsTab, naturalPersonsTab);
		tabs.setWidthFull();

		// Create tab content
		VerticalLayout legalPersonsContent = createLegalPersonsContent();
		VerticalLayout naturalPersonsContent = createNaturalPersonsContent();

		// Initially show legal persons content
		naturalPersonsContent.setVisible(false);

		// Create container for tab content
		VerticalLayout tabsContent = new VerticalLayout(legalPersonsContent, naturalPersonsContent);
		tabsContent.setPadding(false);
		tabsContent.setSpacing(false);
		tabsContent.setSizeFull();

		// Handle tab selection
		tabs.addSelectedChangeListener(event -> {
			legalPersonsContent.setVisible(event.getSelectedTab().equals(legalPersonsTab));
			naturalPersonsContent.setVisible(event.getSelectedTab().equals(naturalPersonsTab));
		});

		// Create layout with tabs and content
		VerticalLayout layout = new VerticalLayout(tabs, tabsContent);
		layout.setPadding(false);
		layout.setSpacing(false);
		layout.setSizeFull();

		return layout;
	}

	private VerticalLayout createLegalPersonsContent() {
		VerticalLayout content = new VerticalLayout(createLegalPersonsFilterForm(), createLegalPersonsGrid());
		content.setPadding(false);
		content.setSpacing(true);
		content.setSizeFull();
		return content;
	}

	private VerticalLayout createNaturalPersonsContent() {
		VerticalLayout content = new VerticalLayout(createNaturalPersonsFilterForm(), createNaturalPersonsGrid());
		content.setPadding(false);
		content.setSpacing(true);
		content.setSizeFull();
		return content;
	}

	private Grid<NaturalPersonDTO> createNaturalPersonsGrid() {
		naturalPersonGrid = new Grid<>();
		naturalPersonGrid.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(this::viewNaturalPersonDetails));
		naturalPersonGrid.addThemeName("mobile");

		naturalPersonGrid.setId("naturalPersons");
		naturalPersonGrid.setSizeFull();

		// Configure grid columns
		naturalPersonGrid.addColumn(NaturalPersonDTO::getNaturalPersonId)
				.setAutoWidth(true)
				.setFlexGrow(0)
				.setFrozen(true)
				.setHeader("ID")
				.setSortable(true);
		naturalPersonGrid.addColumn(NaturalPersonDTO::getFirstName)
				.setAutoWidth(true)
				.setHeader("First Name")
				.setSortable(true);
		naturalPersonGrid.addColumn(NaturalPersonDTO::getMiddleName)
				.setAutoWidth(true)
				.setHeader("Middle Name")
				.setSortable(true);
		naturalPersonGrid.addColumn(NaturalPersonDTO::getFirstSurname)
				.setAutoWidth(true)
				.setHeader("First Surname")
				.setSortable(true);
		naturalPersonGrid.addColumn(NaturalPersonDTO::getSecondSurname)
				.setAutoWidth(true)
				.setHeader("Second Surname")
				.setSortable(true);
		naturalPersonGrid.addColumn(NaturalPersonDTO::getTaxIdentificationNumber)
				.setAutoWidth(true)
				.setHeader("Tax ID")
				.setSortable(true);
		naturalPersonGrid.addColumn(NaturalPersonDTO::getNationalityDescription)
				.setAutoWidth(true)
				.setHeader("Nationality")
				.setSortable(true);
		naturalPersonGrid.addColumn(NaturalPersonDTO::getCountryOfResidenceDescription)
				.setAutoWidth(true)
				.setHeader("Country of Residence")
				.setSortable(true);
		naturalPersonGrid.addColumn(new LocalDateRenderer<>(NaturalPersonDTO::getDateOfBirth, "MMM dd, YYYY"))
				.setAutoWidth(true)
				.setComparator(NaturalPersonDTO::getDateOfBirth)
				.setFlexGrow(0)
				.setHeader("Date of Birth");
		naturalPersonGrid.addColumn(person -> person.getGender() != null ? person.getGender() : "")
				.setAutoWidth(true)
				.setHeader("Gender")
				.setSortable(true);
		naturalPersonGrid.addColumn(person -> person.getMaritalStatus() != null ? person.getMaritalStatus() : "")
				.setAutoWidth(true)
				.setHeader("Marital Status")
				.setSortable(true);

		// Initialize with empty data provider
		naturalPersonDataProvider = new ListDataProvider<>(List.of());
		naturalPersonGrid.setDataProvider(naturalPersonDataProvider);

		return naturalPersonGrid;
	}

	private void viewNaturalPersonDetails(NaturalPersonDTO naturalPerson) {
		// For now, just log the details since we don't have a NaturalPersonDetails view
		System.out.println("[DEBUG_LOG] Viewing details for natural person: " + naturalPerson.getFirstName() + " " + naturalPerson.getFirstSurname());
		// In a real implementation, you would navigate to a details view
		// UI.getCurrent().navigate(NaturalPersonDetails.class, naturalPerson.getNaturalPersonId());
	}

	private Component createNaturalPersonsFilterForm() {
		// Initialize filter fields
		firstNameFilter = new TextField();
		firstNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
		firstNameFilter.setClearButtonVisible(true);

		middleNameFilter = new TextField();
		middleNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
		middleNameFilter.setClearButtonVisible(true);

		firstSurnameFilter = new TextField();
		firstSurnameFilter.setValueChangeMode(ValueChangeMode.EAGER);
		firstSurnameFilter.setClearButtonVisible(true);

		secondSurnameFilter = new TextField();
		secondSurnameFilter.setValueChangeMode(ValueChangeMode.EAGER);
		secondSurnameFilter.setClearButtonVisible(true);

		taxIdentificationNumberFilter = new TextField();
		taxIdentificationNumberFilter.setValueChangeMode(ValueChangeMode.EAGER);
		taxIdentificationNumberFilter.setClearButtonVisible(true);

		nationalityFilter = new TextField();
		nationalityFilter.setValueChangeMode(ValueChangeMode.EAGER);
		nationalityFilter.setClearButtonVisible(true);

		countryOfResidenceFilter = new TextField();
		countryOfResidenceFilter.setValueChangeMode(ValueChangeMode.EAGER);
		countryOfResidenceFilter.setClearButtonVisible(true);

		dateOfBirthFilter = new DatePicker();
		dateOfBirthFilter.setClearButtonVisible(true);

		genderFilter = new TextField();
		genderFilter.setValueChangeMode(ValueChangeMode.EAGER);
		genderFilter.setClearButtonVisible(true);

		maritalStatusFilter = new TextField();
		maritalStatusFilter.setValueChangeMode(ValueChangeMode.EAGER);
		maritalStatusFilter.setClearButtonVisible(true);

		naturalPersonDateCreatedFromFilter = new DatePicker();
		naturalPersonDateCreatedFromFilter.setClearButtonVisible(true);

		naturalPersonDateCreatedToFilter = new DatePicker();
		naturalPersonDateCreatedToFilter.setClearButtonVisible(true);

		naturalPersonDateUpdatedFromFilter = new DatePicker();
		naturalPersonDateUpdatedFromFilter.setClearButtonVisible(true);

		naturalPersonDateUpdatedToFilter = new DatePicker();
		naturalPersonDateUpdatedToFilter.setClearButtonVisible(true);

		// Create buttons
		Button searchButton = UIUtils.createPrimaryButton("Search");
		searchButton.addClickListener(e -> applyNaturalPersonFilters());

		Button clearButton = UIUtils.createTertiaryButton("Clear");
		clearButton.addClickListener(e -> clearNaturalPersonFilters());

		Button createCustomerButton = UIUtils.createSuccessButton("Create Natural Person");
		createCustomerButton.addClickListener(e -> openCreateCustomerDialog());

		// Create a wrapper for search and clear buttons (right side)
		HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
		rightButtons.setSpacing(true);

		// Create button layout with Create Customer on left and search/clear on right
		HorizontalLayout buttonLayout = new HorizontalLayout(createCustomerButton, rightButtons);
		buttonLayout.setWidthFull();
		buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

		// Create form layout
		FormLayout formLayout = new FormLayout();
		formLayout.addFormItem(firstNameFilter, "First Name");
		formLayout.addFormItem(middleNameFilter, "Middle Name");
		formLayout.addFormItem(firstSurnameFilter, "First Surname");
		formLayout.addFormItem(secondSurnameFilter, "Second Surname");
		formLayout.addFormItem(taxIdentificationNumberFilter, "Tax ID");
		formLayout.addFormItem(nationalityFilter, "Nationality");
		formLayout.addFormItem(countryOfResidenceFilter, "Country of Residence");
		formLayout.addFormItem(dateOfBirthFilter, "Date of Birth");
		formLayout.addFormItem(genderFilter, "Gender");
		formLayout.addFormItem(maritalStatusFilter, "Marital Status");
		formLayout.addFormItem(naturalPersonDateCreatedFromFilter, "Created From");
		formLayout.addFormItem(naturalPersonDateCreatedToFilter, "Created To");
		formLayout.addFormItem(naturalPersonDateUpdatedFromFilter, "Updated From");
		formLayout.addFormItem(naturalPersonDateUpdatedToFilter, "Updated To");

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

	private Component createLegalPersonsFilterForm() {
		// Initialize filter fields
		legalNameFilter = new TextField();
		legalNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
		legalNameFilter.setClearButtonVisible(true);

		tradeNameFilter = new TextField();
		tradeNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
		tradeNameFilter.setClearButtonVisible(true);

		registrationNumberFilter = new TextField();
		registrationNumberFilter.setValueChangeMode(ValueChangeMode.EAGER);
		registrationNumberFilter.setClearButtonVisible(true);

		taxIdFilter = new TextField();
		taxIdFilter.setValueChangeMode(ValueChangeMode.EAGER);
		taxIdFilter.setClearButtonVisible(true);

		businessActivityFilter = new TextField();
		businessActivityFilter.setValueChangeMode(ValueChangeMode.EAGER);
		businessActivityFilter.setClearButtonVisible(true);

		numberOfEmployeesFilter = new NumberField();
		numberOfEmployeesFilter.setClearButtonVisible(true);

		incorporationCountryFilter = new TextField();
		incorporationCountryFilter.setValueChangeMode(ValueChangeMode.EAGER);
		incorporationCountryFilter.setClearButtonVisible(true);

		phoneNumberFilter = new TextField();
		phoneNumberFilter.setValueChangeMode(ValueChangeMode.EAGER);
		phoneNumberFilter.setClearButtonVisible(true);

		emailAddressFilter = new TextField();
		emailAddressFilter.setValueChangeMode(ValueChangeMode.EAGER);
		emailAddressFilter.setClearButtonVisible(true);

		mainContactNameFilter = new TextField();
		mainContactNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
		mainContactNameFilter.setClearButtonVisible(true);

		dateOfIncorporationFilter = new DatePicker();
		dateOfIncorporationFilter.setClearButtonVisible(true);

		dateCreatedFromFilter = new DatePicker();
		dateCreatedFromFilter.setClearButtonVisible(true);

		dateCreatedToFilter = new DatePicker();
		dateCreatedToFilter.setClearButtonVisible(true);

		dateUpdatedFromFilter = new DatePicker();
		dateUpdatedFromFilter.setClearButtonVisible(true);

		dateUpdatedToFilter = new DatePicker();
		dateUpdatedToFilter.setClearButtonVisible(true);

		// Create buttons
		Button searchButton = UIUtils.createPrimaryButton("Search");
		searchButton.addClickListener(e -> applyFilters());

		Button clearButton = UIUtils.createTertiaryButton("Clear");
		clearButton.addClickListener(e -> clearFilters());

		Button createCustomerButton = UIUtils.createSuccessButton("Create Legal Person");
		createCustomerButton.addClickListener(e -> openCreateCustomerDialog());

		// Create a wrapper for search and clear buttons (right side)
		HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
		rightButtons.setSpacing(true);

		// Create button layout with Create Customer on left and search/clear on right
		HorizontalLayout buttonLayout = new HorizontalLayout(createCustomerButton, rightButtons);
		buttonLayout.setWidthFull();
		buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

		// Create form layout
		FormLayout formLayout = new FormLayout();
		formLayout.addFormItem(legalNameFilter, "Legal Name");
		formLayout.addFormItem(tradeNameFilter, "Trade Name");
		formLayout.addFormItem(registrationNumberFilter, "Registration Number");
		formLayout.addFormItem(taxIdFilter, "Tax ID");
		formLayout.addFormItem(businessActivityFilter, "Business Activity");
		formLayout.addFormItem(numberOfEmployeesFilter, "Number of Employees");
		formLayout.addFormItem(incorporationCountryFilter, "Country");
		formLayout.addFormItem(phoneNumberFilter, "Phone Number");
		formLayout.addFormItem(emailAddressFilter, "Email");
		formLayout.addFormItem(mainContactNameFilter, "Contact Name");
		formLayout.addFormItem(dateOfIncorporationFilter, "Incorporation Date");
		formLayout.addFormItem(dateCreatedFromFilter, "Created From");
		formLayout.addFormItem(dateCreatedToFilter, "Created To");
		formLayout.addFormItem(dateUpdatedFromFilter, "Updated From");
		formLayout.addFormItem(dateUpdatedToFilter, "Updated To");

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

	private Grid<LegalPersonDTO> createLegalPersonsGrid() {
		grid = new Grid<>();
		grid.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(this::viewDetails));
		grid.addThemeName("mobile");

		grid.setId("legalPersons");
		grid.setSizeFull();

		// Configure grid columns
		grid.addColumn(LegalPersonDTO::getLegalPersonId)
				.setAutoWidth(true)
				.setFlexGrow(0)
				.setFrozen(true)
				.setHeader("ID")
				.setSortable(true);
		grid.addColumn(LegalPersonDTO::getLegalName)
				.setAutoWidth(true)
				.setHeader("Legal Name")
				.setSortable(true);
		grid.addColumn(LegalPersonDTO::getTradeName)
				.setAutoWidth(true)
				.setHeader("Trade Name")
				.setSortable(true);
		grid.addColumn(LegalPersonDTO::getRegistrationNumber)
				.setAutoWidth(true)
				.setHeader("Registration Number")
				.setSortable(true);
		grid.addColumn(LegalPersonDTO::getTaxIdentificationNumber)
				.setAutoWidth(true)
				.setHeader("Tax ID")
				.setSortable(true);
		grid.addColumn(LegalPersonDTO::getBusinessActivity)
				.setAutoWidth(true)
				.setHeader("Business Activity")
				.setSortable(true);
		grid.addColumn(LegalPersonDTO::getIncorporationCountry)
				.setAutoWidth(true)
				.setHeader("Country")
				.setSortable(true);
		grid.addColumn(LegalPersonDTO::getPhoneNumber)
				.setAutoWidth(true)
				.setHeader("Phone")
				.setSortable(true);
		grid.addColumn(LegalPersonDTO::getEmailAddress)
				.setAutoWidth(true)
				.setHeader("Email")
				.setSortable(true);
		grid.addColumn(LegalPersonDTO::getMainContactName)
				.setAutoWidth(true)
				.setHeader("Contact Name")
				.setSortable(true);
		grid.addColumn(new LocalDateRenderer<>(LegalPersonDTO::getDateOfIncorporation, "MMM dd, YYYY"))
				.setAutoWidth(true)
				.setComparator(LegalPersonDTO::getDateOfIncorporation)
				.setFlexGrow(0)
				.setHeader("Incorporation Date");

		// Initialize with empty data provider
		dataProvider = new ListDataProvider<>(List.of());
		grid.setDataProvider(dataProvider);

		return grid;
	}

	private void openCreateCustomerDialog() {
		// This would be implemented to open a dialog for creating a new customer
		// For now, just log a message
		System.out.println("[DEBUG_LOG] Create Customer dialog would open here");
	}

	private void loadLegalPersonData() {
		LegalPersonRequest filterRequest = new LegalPersonRequest();
		filterRequest.setPaginationPageNumber(0);
		filterRequest.setPaginationPageSize(10);
		filterRequest.setPaginationSortBy("legal_person_id");
		filterRequest.setPaginationSortDirection("DESC");

		System.out.println("[DEBUG_LOG] Starting to load legal person data");

		customersService.filterLegalPerson(filterRequest)
				.subscribe(response -> {
					System.out.println("[DEBUG_LOG] Response status: " + response.getStatusCode());
					if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
						// Get the content from the response and create a new list using reactive programming
						List<LegalPersonDTO> legalPersons = response.getBody().getContent();

						System.out.println("[DEBUG_LOG] Received legal persons: " + Objects.requireNonNull(legalPersons).size());

						if (!legalPersons.isEmpty()) {
							System.out.println("[DEBUG_LOG] First legal person ID: " + legalPersons.getFirst().getLegalPersonId());
						}

                        // Use UI.access() to safely update the UI from the async callback
                        ui.access(() -> {
                            try {
                                dataProvider = new ListDataProvider<>(legalPersons);
                                grid.setDataProvider(dataProvider);
                                System.out.println("[DEBUG_LOG] Grid data provider updated successfully with " + legalPersons.size() + " items");
                                ui.push(); // Force UI update to the client
                            } catch (Exception e) {
                                System.out.println("[DEBUG_LOG] Error updating grid: " + e.getMessage());
                            }
                        });
                    } else {
						System.out.println("[DEBUG_LOG] Response unsuccessful or body is null");
					}
				}, error -> {
					System.out.println("[DEBUG_LOG] Error in filterLegalPerson: " + error.getMessage());

					// Handle error in UI thread
					ui.access(() -> {
						// You could show a notification here
						System.out.println("[DEBUG_LOG] Error handled in UI thread");
					});
				});
	}

	private void viewDetails(LegalPersonDTO legalPerson) {
		// For now, just log the details since we don't have a LegalPersonDetails view
		System.out.println("[DEBUG_LOG] Viewing details for legal person: " + legalPerson.getLegalName());
		// In a real implementation, you would navigate to a details view
		// UI.getCurrent().navigate(LegalPersonDetails.class, legalPerson.getLegalPersonId());
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		getUI().ifPresent(currentUI -> {
			this.ui = currentUI;
			Page page = currentUI.getPage();
			resizeListener = page.addBrowserWindowResizeListener(event -> updateVisibleColumns(event.getWidth()));
			page.retrieveExtendedClientDetails(details -> updateVisibleColumns(details.getBodyClientWidth()));

			// Load data after UI is completely initialized
			System.out.println("[DEBUG_LOG] UI attached, loading legal person data");
			loadLegalPersonData();
			loadNaturalPersonData();
		});
	}

	private void loadNaturalPersonData() {
		NaturalPersonRequest filterRequest = new NaturalPersonRequest();
		filterRequest.setPaginationPageNumber(0);
		filterRequest.setPaginationPageSize(10);
		filterRequest.setPaginationSortBy("natural_person_id");
		filterRequest.setPaginationSortDirection("DESC");

		System.out.println("[DEBUG_LOG] Starting to load natural person data");

		customersService.filterNaturalPerson(filterRequest)
				.subscribe(response -> {
					System.out.println("[DEBUG_LOG] Response status: " + response.getStatusCode());
					if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
						// Get the content from the response and create a new list using reactive programming
						List<NaturalPersonDTO> naturalPersons = response.getBody().getContent();

						System.out.println("[DEBUG_LOG] Received natural persons: " + Objects.requireNonNull(naturalPersons).size());

						if (!naturalPersons.isEmpty()) {
							System.out.println("[DEBUG_LOG] First natural person ID: " + naturalPersons.getFirst().getNaturalPersonId());
						}

						// Use UI.access() to safely update the UI from the async callback
						ui.access(() -> {
							try {
								naturalPersonDataProvider = new ListDataProvider<>(naturalPersons);
								naturalPersonGrid.setDataProvider(naturalPersonDataProvider);
								System.out.println("[DEBUG_LOG] Grid data provider updated successfully with " + naturalPersons.size() + " items");
								ui.push(); // Force UI update to the client
							} catch (Exception e) {
								System.out.println("[DEBUG_LOG] Error updating grid: " + e.getMessage());
							}
						});
					} else {
						System.out.println("[DEBUG_LOG] Response unsuccessful or body is null");
					}
				}, error -> {
					System.out.println("[DEBUG_LOG] Error in filterNaturalPerson: " + error.getMessage());

					// Handle error in UI thread
					ui.access(() -> {
						// You could show a notification here
						System.out.println("[DEBUG_LOG] Error handled in UI thread");
					});
				});
	}

	@Override
	protected void onDetach(DetachEvent detachEvent) {
		if (resizeListener != null) {
			resizeListener.remove();
		}
		super.onDetach(detachEvent);
	}

	private void updateVisibleColumns(int width) {
		boolean mobile = width < MOBILE_BREAKPOINT;
		List<Grid.Column<LegalPersonDTO>> columns = grid.getColumns();

		// "Desktop" columns
		for (int i = 0; i < columns.size(); i++) {
			columns.get(i).setVisible(!mobile);
		}
	}

	private void applyFilters() {
		if (dataProvider == null) {
			System.out.println("[DEBUG_LOG] DataProvider is null, cannot apply filters");
			return;
		}

		dataProvider.clearFilters();

		// Apply legal name filter
		if (legalNameFilter.getValue() != null && !legalNameFilter.getValue().isEmpty()) {
			String legalNameValue = legalNameFilter.getValue().toLowerCase();
			dataProvider.addFilter(legalPerson ->
					legalPerson.getLegalName() != null &&
							legalPerson.getLegalName().toLowerCase().contains(legalNameValue));
		}

		// Apply trade name filter
		if (tradeNameFilter.getValue() != null && !tradeNameFilter.getValue().isEmpty()) {
			String tradeNameValue = tradeNameFilter.getValue().toLowerCase();
			dataProvider.addFilter(legalPerson ->
					legalPerson.getTradeName() != null &&
							legalPerson.getTradeName().toLowerCase().contains(tradeNameValue));
		}

		// Apply registration number filter
		if (registrationNumberFilter.getValue() != null && !registrationNumberFilter.getValue().isEmpty()) {
			String registrationNumberValue = registrationNumberFilter.getValue().toLowerCase();
			dataProvider.addFilter(legalPerson ->
					legalPerson.getRegistrationNumber() != null &&
							legalPerson.getRegistrationNumber().toLowerCase().contains(registrationNumberValue));
		}

		// Apply tax ID filter
		if (taxIdFilter.getValue() != null && !taxIdFilter.getValue().isEmpty()) {
			String taxIdValue = taxIdFilter.getValue().toLowerCase();
			dataProvider.addFilter(legalPerson ->
					legalPerson.getTaxIdentificationNumber() != null &&
							legalPerson.getTaxIdentificationNumber().toLowerCase().contains(taxIdValue));
		}

		// Apply business activity filter
		if (businessActivityFilter.getValue() != null && !businessActivityFilter.getValue().isEmpty()) {
			String businessActivityValue = businessActivityFilter.getValue().toLowerCase();
			dataProvider.addFilter(legalPerson ->
					legalPerson.getBusinessActivity() != null &&
							legalPerson.getBusinessActivity().toLowerCase().contains(businessActivityValue));
		}

		// Apply number of employees filter
		if (numberOfEmployeesFilter.getValue() != null) {
			Double employeesValue = numberOfEmployeesFilter.getValue();
			dataProvider.addFilter(legalPerson ->
					legalPerson.getNumberOfEmployees() != null &&
							legalPerson.getNumberOfEmployees() >= employeesValue.longValue());
		}

		// Apply incorporation country filter
		if (incorporationCountryFilter.getValue() != null && !incorporationCountryFilter.getValue().isEmpty()) {
			String countryValue = incorporationCountryFilter.getValue().toLowerCase();
			dataProvider.addFilter(legalPerson ->
					legalPerson.getIncorporationCountry() != null &&
							legalPerson.getIncorporationCountry().toLowerCase().contains(countryValue));
		}

		// Apply phone number filter
		if (phoneNumberFilter.getValue() != null && !phoneNumberFilter.getValue().isEmpty()) {
			String phoneValue = phoneNumberFilter.getValue().toLowerCase();
			dataProvider.addFilter(legalPerson ->
					legalPerson.getPhoneNumber() != null &&
							legalPerson.getPhoneNumber().toLowerCase().contains(phoneValue));
		}

		// Apply email address filter
		if (emailAddressFilter.getValue() != null && !emailAddressFilter.getValue().isEmpty()) {
			String emailValue = emailAddressFilter.getValue().toLowerCase();
			dataProvider.addFilter(legalPerson ->
					legalPerson.getEmailAddress() != null &&
							legalPerson.getEmailAddress().toLowerCase().contains(emailValue));
		}

		// Apply main contact name filter
		if (mainContactNameFilter.getValue() != null && !mainContactNameFilter.getValue().isEmpty()) {
			String contactNameValue = mainContactNameFilter.getValue().toLowerCase();
			dataProvider.addFilter(legalPerson ->
					legalPerson.getMainContactName() != null &&
							legalPerson.getMainContactName().toLowerCase().contains(contactNameValue));
		}

		// Apply date of incorporation filter
		if (dateOfIncorporationFilter.getValue() != null) {
			LocalDate incorporationDate = dateOfIncorporationFilter.getValue();
			dataProvider.addFilter(legalPerson ->
					legalPerson.getDateOfIncorporation() != null &&
							legalPerson.getDateOfIncorporation().equals(incorporationDate));
		}

		// Apply date created from filter
		if (dateCreatedFromFilter.getValue() != null) {
			LocalDate fromDate = dateCreatedFromFilter.getValue();
			dataProvider.addFilter(legalPerson ->
					legalPerson.getDateCreated() != null &&
							!legalPerson.getDateCreated().toLocalDate().isBefore(fromDate));
		}

		// Apply date created to filter
		if (dateCreatedToFilter.getValue() != null) {
			LocalDate toDate = dateCreatedToFilter.getValue();
			dataProvider.addFilter(legalPerson ->
					legalPerson.getDateCreated() != null &&
							!legalPerson.getDateCreated().toLocalDate().isAfter(toDate));
		}

		// Apply date updated from filter
		if (dateUpdatedFromFilter.getValue() != null) {
			LocalDate fromDate = dateUpdatedFromFilter.getValue();
			dataProvider.addFilter(legalPerson ->
					legalPerson.getDateUpdated() != null &&
							!legalPerson.getDateUpdated().toLocalDate().isBefore(fromDate));
		}

		// Apply date updated to filter
		if (dateUpdatedToFilter.getValue() != null) {
			LocalDate toDate = dateUpdatedToFilter.getValue();
			dataProvider.addFilter(legalPerson ->
					legalPerson.getDateUpdated() != null &&
							!legalPerson.getDateUpdated().toLocalDate().isAfter(toDate));
		}

		System.out.println("[DEBUG_LOG] Filters applied successfully");
	}

	private void clearFilters() {
		// Clear all filter fields
		legalNameFilter.clear();
		tradeNameFilter.clear();
		registrationNumberFilter.clear();
		taxIdFilter.clear();
		businessActivityFilter.clear();
		numberOfEmployeesFilter.clear();
		incorporationCountryFilter.clear();
		phoneNumberFilter.clear();
		emailAddressFilter.clear();
		mainContactNameFilter.clear();
		dateOfIncorporationFilter.clear();
		dateCreatedFromFilter.clear();
		dateCreatedToFilter.clear();
		dateUpdatedFromFilter.clear();
		dateUpdatedToFilter.clear();

		// Clear all filters from data provider
		if (dataProvider != null) {
			dataProvider.clearFilters();
			System.out.println("[DEBUG_LOG] All filters cleared");
		}
	}

	private void applyNaturalPersonFilters() {
		if (naturalPersonDataProvider == null) {
			System.out.println("[DEBUG_LOG] NaturalPersonDataProvider is null, cannot apply filters");
			return;
		}

		naturalPersonDataProvider.clearFilters();

		// Apply first name filter
		if (firstNameFilter.getValue() != null && !firstNameFilter.getValue().isEmpty()) {
			String firstNameValue = firstNameFilter.getValue().toLowerCase();
			naturalPersonDataProvider.addFilter(naturalPerson ->
					naturalPerson.getFirstName() != null &&
							naturalPerson.getFirstName().toLowerCase().contains(firstNameValue));
		}

		// Apply middle name filter
		if (middleNameFilter.getValue() != null && !middleNameFilter.getValue().isEmpty()) {
			String middleNameValue = middleNameFilter.getValue().toLowerCase();
			naturalPersonDataProvider.addFilter(naturalPerson ->
					naturalPerson.getMiddleName() != null &&
							naturalPerson.getMiddleName().toLowerCase().contains(middleNameValue));
		}

		// Apply first surname filter
		if (firstSurnameFilter.getValue() != null && !firstSurnameFilter.getValue().isEmpty()) {
			String firstSurnameValue = firstSurnameFilter.getValue().toLowerCase();
			naturalPersonDataProvider.addFilter(naturalPerson ->
					naturalPerson.getFirstSurname() != null &&
							naturalPerson.getFirstSurname().toLowerCase().contains(firstSurnameValue));
		}

		// Apply second surname filter
		if (secondSurnameFilter.getValue() != null && !secondSurnameFilter.getValue().isEmpty()) {
			String secondSurnameValue = secondSurnameFilter.getValue().toLowerCase();
			naturalPersonDataProvider.addFilter(naturalPerson ->
					naturalPerson.getSecondSurname() != null &&
							naturalPerson.getSecondSurname().toLowerCase().contains(secondSurnameValue));
		}

		// Apply tax identification number filter
		if (taxIdentificationNumberFilter.getValue() != null && !taxIdentificationNumberFilter.getValue().isEmpty()) {
			String taxIdValue = taxIdentificationNumberFilter.getValue().toLowerCase();
			naturalPersonDataProvider.addFilter(naturalPerson ->
					naturalPerson.getTaxIdentificationNumber() != null &&
							naturalPerson.getTaxIdentificationNumber().toLowerCase().contains(taxIdValue));
		}

		// Apply nationality filter
		if (nationalityFilter.getValue() != null && !nationalityFilter.getValue().isEmpty()) {
			String nationalityValue = nationalityFilter.getValue().toLowerCase();
			naturalPersonDataProvider.addFilter(naturalPerson ->
					naturalPerson.getNationalityDescription() != null &&
							naturalPerson.getNationalityDescription().toLowerCase().contains(nationalityValue));
		}

		// Apply country of residence filter
		if (countryOfResidenceFilter.getValue() != null && !countryOfResidenceFilter.getValue().isEmpty()) {
			String countryValue = countryOfResidenceFilter.getValue().toLowerCase();
			naturalPersonDataProvider.addFilter(naturalPerson ->
					naturalPerson.getCountryOfResidenceDescription() != null &&
							naturalPerson.getCountryOfResidenceDescription().toLowerCase().contains(countryValue));
		}

		// Apply date of birth filter
		if (dateOfBirthFilter.getValue() != null) {
			LocalDate birthDate = dateOfBirthFilter.getValue();
			naturalPersonDataProvider.addFilter(naturalPerson ->
					naturalPerson.getDateOfBirth() != null &&
							naturalPerson.getDateOfBirth().equals(birthDate));
		}

		// Apply gender filter
		if (genderFilter.getValue() != null && !genderFilter.getValue().isEmpty()) {
			String genderValue = genderFilter.getValue().toLowerCase();
			naturalPersonDataProvider.addFilter(naturalPerson ->
					naturalPerson.getGender() != null &&
							naturalPerson.getGender().toString().toLowerCase().contains(genderValue));
		}

		// Apply marital status filter
		if (maritalStatusFilter.getValue() != null && !maritalStatusFilter.getValue().isEmpty()) {
			String maritalStatusValue = maritalStatusFilter.getValue().toLowerCase();
			naturalPersonDataProvider.addFilter(naturalPerson ->
					naturalPerson.getMaritalStatus() != null &&
							naturalPerson.getMaritalStatus().toString().toLowerCase().contains(maritalStatusValue));
		}

		// Apply date created from filter
		if (naturalPersonDateCreatedFromFilter.getValue() != null) {
			LocalDate fromDate = naturalPersonDateCreatedFromFilter.getValue();
			naturalPersonDataProvider.addFilter(naturalPerson ->
					naturalPerson.getDateCreated() != null &&
							!naturalPerson.getDateCreated().toLocalDate().isBefore(fromDate));
		}

		// Apply date created to filter
		if (naturalPersonDateCreatedToFilter.getValue() != null) {
			LocalDate toDate = naturalPersonDateCreatedToFilter.getValue();
			naturalPersonDataProvider.addFilter(naturalPerson ->
					naturalPerson.getDateCreated() != null &&
							!naturalPerson.getDateCreated().toLocalDate().isAfter(toDate));
		}

		// Apply date updated from filter
		if (naturalPersonDateUpdatedFromFilter.getValue() != null) {
			LocalDate fromDate = naturalPersonDateUpdatedFromFilter.getValue();
			naturalPersonDataProvider.addFilter(naturalPerson ->
					naturalPerson.getDateUpdated() != null &&
							!naturalPerson.getDateUpdated().toLocalDate().isBefore(fromDate));
		}

		// Apply date updated to filter
		if (naturalPersonDateUpdatedToFilter.getValue() != null) {
			LocalDate toDate = naturalPersonDateUpdatedToFilter.getValue();
			naturalPersonDataProvider.addFilter(naturalPerson ->
					naturalPerson.getDateUpdated() != null &&
							!naturalPerson.getDateUpdated().toLocalDate().isAfter(toDate));
		}

		System.out.println("[DEBUG_LOG] Natural person filters applied successfully");
	}

	private void clearNaturalPersonFilters() {
		// Clear all filter fields
		firstNameFilter.clear();
		middleNameFilter.clear();
		firstSurnameFilter.clear();
		secondSurnameFilter.clear();
		taxIdentificationNumberFilter.clear();
		nationalityFilter.clear();
		countryOfResidenceFilter.clear();
		dateOfBirthFilter.clear();
		genderFilter.clear();
		maritalStatusFilter.clear();
		naturalPersonDateCreatedFromFilter.clear();
		naturalPersonDateCreatedToFilter.clear();
		naturalPersonDateUpdatedFromFilter.clear();
		naturalPersonDateUpdatedToFilter.clear();

		// Clear all filters from data provider
		if (naturalPersonDataProvider != null) {
			naturalPersonDataProvider.clearFilters();
			System.out.println("[DEBUG_LOG] All natural person filters cleared");
		}
	}
}
