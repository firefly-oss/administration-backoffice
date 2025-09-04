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
import com.vaadin.starter.business.backend.dto.systemconfig.MasterDataItemDTO;
import com.vaadin.starter.business.backend.service.SystemConfigService;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
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
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "master-data", layout = MainLayout.class)
@PageTitle("Master Data")
public class MasterData extends SplitViewFrame {

    private Grid<MasterDataItemDTO> grid;
    private ListDataProvider<MasterDataItemDTO> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;
    
    private final SystemConfigService systemConfigService;

    @Autowired
    public MasterData(SystemConfigService systemConfigService) {
        this.systemConfigService = systemConfigService;
        setViewContent(createContent());
        setViewDetails(createDetailsDrawer());
        setViewDetailsPosition(Position.BOTTOM);
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
        dataProvider = DataProvider.ofCollection(systemConfigService.getMasterDataItems());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(MasterDataItemDTO::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(MasterDataItemDTO::getName)
                .setAutoWidth(true)
                .setHeader("Data Entity")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(MasterDataItemDTO::getCategory)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Category")
                .setTextAlign(ColumnTextAlign.END)
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createLastModified))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Last Modified")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createStatus(MasterDataItemDTO masterDataItem) {
        boolean isActive = masterDataItem.isActive();
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

    private Component createLastModified(MasterDataItemDTO masterDataItem) {
        return new Span(UIUtils.formatDate(masterDataItem.getLastModified()));
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

    private void showDetails(MasterDataItemDTO masterDataItem) {
        detailsDrawerHeader.setTitle("Master Data: " + masterDataItem.getName());
        detailsDrawer.setContent(createDetails(masterDataItem));
        detailsDrawer.show();
    }

    private FormLayout createDetails(MasterDataItemDTO masterDataItem) {
        TextField entityName = new TextField();
        entityName.setValue(masterDataItem.getName());
        entityName.setWidthFull();

        TextArea description = new TextArea();
        description.setValue(masterDataItem.getDescription());
        description.setWidthFull();
        description.setMinHeight("100px");

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Under Review", "Deprecated");
        status.setValue(masterDataItem.getStatus());

        ComboBox<String> category = new ComboBox<>();
        String[] categories = {"Reference Data", "Business Data", "Configuration Data", "Transactional Data", "Metadata"};
        category.setItems(categories);
        category.setValue(masterDataItem.getCategory());
        category.setWidthFull();

        TextField dataType = new TextField();
        dataType.setValue(masterDataItem.getDataType());
        dataType.setWidthFull();

        TextField format = new TextField();
        format.setValue(masterDataItem.getFormat());
        format.setWidthFull();

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
        form.addFormItem(dataType, "Data Type");
        form.addFormItem(format, "Format");

        return form;
    }
}