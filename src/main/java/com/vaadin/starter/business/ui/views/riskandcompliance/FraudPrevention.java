package com.vaadin.starter.business.ui.views.riskandcompliance;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.dummy.Person;
import com.vaadin.starter.business.backend.service.RiskAndComplianceService;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.components.Initials;
import com.vaadin.starter.business.ui.components.ListItem;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawer;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawerFooter;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawerHeader;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Right;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.layout.size.Vertical;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.SplitViewFrame;

@Route(value = "fraud-prevention", layout = MainLayout.class)
@PageTitle("Fraud Prevention")
public class FraudPrevention extends SplitViewFrame {

    private Grid<Person> grid;
    private ListDataProvider<Person> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    private final RiskAndComplianceService riskAndComplianceService;

    public FraudPrevention(RiskAndComplianceService riskAndComplianceService) {
        this.riskAndComplianceService = riskAndComplianceService;
        setViewContent(createContent());
        setViewDetails(createDetailsDrawer());
        setViewDetailsPosition(Position.BOTTOM);

        filter();
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(createGrid());
        content.setBoxSizing(BoxSizing.BORDER_BOX);
        content.setHeightFull();
        content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
        return content;
    }

    private Grid createGrid() {
        grid = new Grid<>();
        grid.addSelectionListener(event -> event.getFirstSelectedItem()
                .ifPresent(this::showDetails));
        dataProvider = DataProvider.ofCollection(riskAndComplianceService.getFraudAlerts());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(Person::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createAlertInfo))
                .setAutoWidth(true)
                .setHeader("Alert");
        grid.addColumn(new ComponentRenderer<>(this::createSeverity))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Severity")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createDate))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Detection Date")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createAlertInfo(Person person) {
        ListItem item = new ListItem(
                new Initials(person.getInitials()), 
                "Fraud Alert #" + person.getId(),
                "Suspicious activity detected for " + person.getEmail());
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }

    private Component createSeverity(Person person) {
        int severity = person.getRandomInteger() % 3;
        String text;
        String color;

        switch (severity) {
            case 0:
                text = "Low";
                color = "var(--lumo-success-color)";
                break;
            case 1:
                text = "Medium";
                color = "var(--lumo-primary-color)";
                break;
            default:
                text = "High";
                color = "var(--lumo-error-color)";
                break;
        }

        Span span = new Span(text);
        span.getElement().getStyle().set("color", color);
        span.getElement().getStyle().set("font-weight", "bold");

        return span;
    }

    private Component createStatus(Person person) {
        boolean isResolved = person.getRandomBoolean();
        Icon icon;
        String text;

        if (isResolved) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
            text = "Resolved";
        } else {
            icon = UIUtils.createSecondaryIcon(VaadinIcon.EXCLAMATION_CIRCLE);
            text = "Open";
        }

        Span span = new Span(icon, new Span(" " + text));
        return span;
    }

    private Component createDate(Person person) {
        return new Span(UIUtils.formatDate(person.getLastModified()));
    }

    private DetailsDrawer createDetailsDrawer() {
        detailsDrawer = new DetailsDrawer(DetailsDrawer.Position.BOTTOM);

        // Header
        detailsDrawerHeader = new DetailsDrawerHeader("");
        detailsDrawerHeader.addCloseListener(buttonClickEvent -> detailsDrawer.hide());
        detailsDrawer.setHeader(detailsDrawerHeader);

        // Footer
        DetailsDrawerFooter footer = new DetailsDrawerFooter();
        footer.addSaveListener(e -> {
            detailsDrawer.hide();
            UIUtils.showNotification("Fraud alert updated.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(Person person) {
        detailsDrawerHeader.setTitle("Fraud Alert #" + person.getId());
        detailsDrawer.setContent(createDetails(person));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Person person) {
        TextField alertId = new TextField();
        alertId.setValue("#" + person.getId());
        alertId.setReadOnly(true);
        alertId.setWidthFull();

        TextField account = new TextField();
        account.setValue(person.getEmail());
        account.setWidthFull();

        ComboBox<String> severity = new ComboBox<>();
        severity.setItems("Low", "Medium", "High");
        severity.setValue(person.getRandomInteger() % 3 == 0 ? "Low" : 
                         (person.getRandomInteger() % 3 == 1 ? "Medium" : "High"));
        severity.setWidthFull();

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Open", "Under Investigation", "Resolved");
        status.setValue(person.getRandomBoolean() ? "Resolved" : "Open");

        ComboBox<String> fraudType = new ComboBox<>();
        fraudType.setItems("Identity Theft", "Account Takeover", "Transaction Fraud", 
                          "Application Fraud", "Insider Fraud");
        fraudType.setValue("Transaction Fraud");
        fraudType.setWidthFull();

        TextArea description = new TextArea();
        description.setValue("Suspicious activity detected on " + 
                            UIUtils.formatDate(person.getLastModified()) + 
                            " involving unusual transaction patterns.");
        description.setWidthFull();
        description.setMinHeight("100px");

        // Form layout
        FormLayout form = new FormLayout();
        form.addClassNames(LumoStyles.Padding.Bottom.L,
                LumoStyles.Padding.Horizontal.L, LumoStyles.Padding.Top.S);
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("1024px", 3,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP));
        form.addFormItem(alertId, "Alert ID");
        form.addFormItem(account, "Account");
        form.addFormItem(severity, "Severity");
        form.addFormItem(status, "Status");
        form.addFormItem(fraudType, "Fraud Type");
        form.addFormItem(description, "Description");

        return form;
    }

    private void filter() {
        // The service already filters for MANAGER role
    }
}
