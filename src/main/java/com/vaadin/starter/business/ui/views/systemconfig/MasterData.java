package com.vaadin.starter.business.ui.views.systemconfig;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
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

@Route(value = "master-data", layout = MainLayout.class)
@PageTitle("Master Data")
public class MasterData extends SplitViewFrame {

    private Grid<Person> grid;
    private ListDataProvider<Person> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    public MasterData() {
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
        grid.addColumn(new ComponentRenderer<>(this::createDataInfo))
                .setAutoWidth(true)
                .setHeader("Data Entity");
        grid.addColumn(new ComponentRenderer<>(this::createStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createCategory))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Category")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createLastModified))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Last Modified")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createDataInfo(Person person) {
        String[] entities = {
            "Country Codes", 
            "Currency Codes", 
            "Product Categories", 
            "Customer Types", 
            "Transaction Types", 
            "Account Types"
        };
        
        String entity = entities[(int)(Math.abs(person.getId()) % entities.length)];
        
        ListItem item = new ListItem(
                new Initials(person.getInitials()), 
                entity,
                "Master data for " + person.getEmail());
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }

    private Component createStatus(Person person) {
        boolean isActive = person.getRandomBoolean();
        Icon icon;
        String text;

        if (isActive) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
            text = "Active";
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOCK);
            text = "Under Review";
        }

        Span span = new Span(icon, new Span(" " + text));
        return span;
    }

    private Component createCategory(Person person) {
        String[] categories = {
            "Reference Data", 
            "Business Data", 
            "Configuration Data", 
            "Transactional Data", 
            "Metadata"
        };
        
        String category = categories[(int)(Math.abs(person.getId()) % categories.length)];
        return new Span(category);
    }

    private Component createLastModified(Person person) {
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
            UIUtils.showNotification("Master data updated.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(Person person) {
        String[] entities = {
            "Country Codes", 
            "Currency Codes", 
            "Product Categories", 
            "Customer Types", 
            "Transaction Types", 
            "Account Types"
        };
        
        String entity = entities[(int)(Math.abs(person.getId()) % entities.length)];
        
        detailsDrawerHeader.setTitle("Master Data: " + entity);
        detailsDrawer.setContent(createDetails(person, entity));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Person person, String entity) {
        TextField entityName = new TextField();
        entityName.setValue(entity);
        entityName.setWidthFull();

        TextArea description = new TextArea();
        description.setValue("Reference data used across the system for standardization and consistency.");
        description.setWidthFull();
        description.setMinHeight("100px");

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Under Review", "Deprecated");
        status.setValue(person.getRandomBoolean() ? "Active" : "Under Review");

        ComboBox<String> category = new ComboBox<>();
        String[] categories = {"Reference Data", "Business Data", "Configuration Data", "Transactional Data", "Metadata"};
        category.setItems(categories);
        category.setValue(categories[(int)(Math.abs(person.getId()) % categories.length)]);
        category.setWidthFull();

        TextField itemCount = new TextField();
        itemCount.setValue(Integer.toString(10 + (int)(Math.abs(person.getId()) % 190)));
        itemCount.setWidthFull();

        ComboBox<String> dataOwner = new ComboBox<>();
        dataOwner.setItems("System Administrator", "Data Steward", "Business Analyst", "Product Manager", "IT Department");
        dataOwner.setValue("Data Steward");
        dataOwner.setWidthFull();

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
        form.addFormItem(entityName, "Entity Name");
        form.addFormItem(description, "Description");
        form.addFormItem(status, "Status");
        form.addFormItem(category, "Category");
        form.addFormItem(itemCount, "Number of Items");
        form.addFormItem(dataOwner, "Data Owner");

        return form;
    }

    private void filter() {
        // We're using the same data source but could filter differently if needed
        dataProvider.setFilterByValue(Person::getRole, Person.Role.MANAGER);
    }
}