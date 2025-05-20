package com.vaadin.starter.business.ui.views.riskandcompliance;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.backend.DummyData;
import com.vaadin.starter.business.backend.Person;
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

import java.time.LocalDate;

@Route(value = "regulatory-compliance", layout = MainLayout.class)
@PageTitle("Regulatory Compliance")
public class RegulatoryCompliance extends SplitViewFrame {

    private Grid<Person> grid;
    private ListDataProvider<Person> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    public RegulatoryCompliance() {
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
        dataProvider = DataProvider.ofCollection(DummyData.getPersons());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(Person::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createComplianceInfo))
                .setAutoWidth(true)
                .setHeader("Regulation");
        grid.addColumn(new ComponentRenderer<>(this::createComplianceStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createDeadline))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Deadline")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createResponsible))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Responsible")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createComplianceInfo(Person person) {
        String[] regulations = {
            "GDPR Compliance", 
            "Anti-Money Laundering", 
            "Know Your Customer", 
            "PCI DSS", 
            "Basel III", 
            "Sarbanes-Oxley"
        };

        String regulation = regulations[(int)(Math.abs(person.getId()) % regulations.length)];

        ListItem item = new ListItem(
                new Initials(person.getInitials()), 
                regulation,
                "Regulatory framework for financial institutions");
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }

    private Component createComplianceStatus(Person person) {
        int status = person.getRandomInteger() % 4;
        String text;
        String color;
        Icon icon;

        switch (status) {
            case 0:
                text = "Compliant";
                color = "var(--lumo-success-color)";
                icon = UIUtils.createSuccessIcon(VaadinIcon.CHECK);
                break;
            case 1:
                text = "In Progress";
                color = "var(--lumo-primary-color)";
                icon = UIUtils.createPrimaryIcon(VaadinIcon.HOURGLASS);
                break;
            case 2:
                text = "Review Needed";
                color = "var(--lumo-contrast-color)";
                icon = UIUtils.createSecondaryIcon(VaadinIcon.SEARCH);
                break;
            default:
                text = "Non-Compliant";
                color = "var(--lumo-error-color)";
                icon = UIUtils.createErrorIcon(VaadinIcon.WARNING);
                break;
        }

        Span span = new Span(icon, new Span(" " + text));
        span.getElement().getStyle().set("color", color);

        return span;
    }

    private Component createDeadline(Person person) {
        // Create a deadline between 0 and 90 days from now
        LocalDate deadline = LocalDate.now().plusDays(person.getRandomInteger() % 90);
        return new Span(UIUtils.formatDate(deadline));
    }

    private Component createResponsible(Person person) {
        return new Span(person.getName());
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
            UIUtils.showNotification("Compliance record updated.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(Person person) {
        String[] regulations = {
            "GDPR Compliance", 
            "Anti-Money Laundering", 
            "Know Your Customer", 
            "PCI DSS", 
            "Basel III", 
            "Sarbanes-Oxley"
        };

        String regulation = regulations[(int)(Math.abs(person.getId()) % regulations.length)];

        detailsDrawerHeader.setTitle(regulation);
        detailsDrawer.setContent(createDetails(person, regulation));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Person person, String regulation) {
        TextField regulationName = new TextField();
        regulationName.setValue(regulation);
        regulationName.setWidthFull();

        TextArea description = new TextArea();
        description.setValue("Regulatory framework requiring compliance with specific financial and data protection standards.");
        description.setWidthFull();
        description.setMinHeight("100px");

        ComboBox<String> status = new ComboBox<>();
        status.setItems("Compliant", "In Progress", "Review Needed", "Non-Compliant");
        status.setValue(person.getRandomInteger() % 4 == 0 ? "Compliant" : 
                       (person.getRandomInteger() % 4 == 1 ? "In Progress" : 
                       (person.getRandomInteger() % 4 == 2 ? "Review Needed" : "Non-Compliant")));
        status.setWidthFull();

        DatePicker deadline = new DatePicker();
        deadline.setValue(LocalDate.now().plusDays(person.getRandomInteger() % 90));
        deadline.setWidthFull();

        TextField responsible = new TextField();
        responsible.setValue(person.getName());
        responsible.setWidthFull();

        ComboBox<String> riskLevel = new ComboBox<>();
        riskLevel.setItems("Low", "Medium", "High", "Critical");
        riskLevel.setValue(person.getRandomInteger() % 4 == 0 ? "Low" : 
                          (person.getRandomInteger() % 4 == 1 ? "Medium" : 
                          (person.getRandomInteger() % 4 == 2 ? "High" : "Critical")));
        riskLevel.setWidthFull();

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
        form.addFormItem(regulationName, "Regulation");
        form.addFormItem(description, "Description");
        form.addFormItem(status, "Compliance Status");
        form.addFormItem(deadline, "Deadline");
        form.addFormItem(responsible, "Responsible Person");
        form.addFormItem(riskLevel, "Risk Level");

        return form;
    }

    private void filter() {
        // We're using the same data source but could filter differently if needed
        dataProvider.setFilterByValue(Person::getRole, Person.Role.MANAGER);
    }
}
