package com.vaadin.starter.business.ui.views.channelsandservices;

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

@Route(value = "channel-integration", layout = MainLayout.class)
@PageTitle("Channel Integration")
public class ChannelIntegration extends SplitViewFrame {

    private Grid<Person> grid;
    private ListDataProvider<Person> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    public ChannelIntegration() {
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
        grid.addColumn(new ComponentRenderer<>(this::createChannelInfo))
                .setAutoWidth(true)
                .setHeader("Channel");
        grid.addColumn(new ComponentRenderer<>(this::createStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createIntegrationType))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Integration Type")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createLastUpdated))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Last Updated")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createChannelInfo(Person person) {
        String[] channels = {
            "Mobile Banking App", 
            "Web Portal", 
            "ATM Network", 
            "Branch Network", 
            "Call Center", 
            "Partner API"
        };
        
        String channel = channels[(int)(Math.abs(person.getId()) % channels.length)];
        
        ListItem item = new ListItem(
                new Initials(person.getInitials()), 
                channel,
                "Integration for " + person.getEmail());
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }

    private Component createStatus(Person person) {
        Icon icon;
        if (person.getRandomBoolean()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createIntegrationType(Person person) {
        String[] types = {
            "API", 
            "Webhook", 
            "File Transfer", 
            "Direct Database", 
            "Message Queue"
        };
        
        String type = types[(int)(Math.abs(person.getId()) % types.length)];
        return new Span(type);
    }

    private Component createLastUpdated(Person person) {
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
            UIUtils.showNotification("Channel integration updated.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(Person person) {
        String[] channels = {
            "Mobile Banking App", 
            "Web Portal", 
            "ATM Network", 
            "Branch Network", 
            "Call Center", 
            "Partner API"
        };
        
        String channel = channels[(int)(Math.abs(person.getId()) % channels.length)];
        
        detailsDrawerHeader.setTitle("Channel: " + channel);
        detailsDrawer.setContent(createDetails(person, channel));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Person person, String channel) {
        TextField channelName = new TextField();
        channelName.setValue(channel);
        channelName.setWidthFull();

        TextArea description = new TextArea();
        description.setValue("Integration configuration for " + channel + " with backend systems.");
        description.setWidthFull();
        description.setMinHeight("100px");

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Inactive", "Maintenance");
        status.setValue(person.getRandomBoolean() ? "Active" : "Inactive");

        ComboBox<String> integrationType = new ComboBox<>();
        String[] types = {"API", "Webhook", "File Transfer", "Direct Database", "Message Queue"};
        integrationType.setItems(types);
        integrationType.setValue(types[(int)(Math.abs(person.getId()) % types.length)]);
        integrationType.setWidthFull();

        TextField endpoint = new TextField();
        endpoint.setValue("https://api.example.com/v1/" + channel.toLowerCase().replace(" ", "-"));
        endpoint.setWidthFull();

        ComboBox<String> securityLevel = new ComboBox<>();
        securityLevel.setItems("Basic", "OAuth 2.0", "API Key", "JWT", "mTLS");
        securityLevel.setValue("OAuth 2.0");
        securityLevel.setWidthFull();

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
        form.addFormItem(channelName, "Channel Name");
        form.addFormItem(description, "Description");
        form.addFormItem(status, "Status");
        form.addFormItem(integrationType, "Integration Type");
        form.addFormItem(endpoint, "Endpoint URL");
        form.addFormItem(securityLevel, "Security Level");

        return form;
    }

    private void filter() {
        // We're using the same data source but could filter differently if needed
        dataProvider.setFilterByValue(Person::getRole, Person.Role.MANAGER);
    }
}