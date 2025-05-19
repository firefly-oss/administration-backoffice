import { injectGlobalWebcomponentCss } from 'Frontend/generated/jar-resources/theme-util.js';

import { injectGlobalCss } from 'Frontend/generated/jar-resources/theme-util.js';

import { css, unsafeCSS, registerStyles } from '@vaadin/vaadin-themable-mixin';
import $cssFromFile_0 from 'Frontend/styles/components/charts.css?inline';
import $cssFromFile_1 from 'Frontend/styles/components/floating-action-button.css?inline';
import $cssFromFile_2 from 'Frontend/styles/components/grid.css?inline';
import $cssFromFile_3 from 'Frontend/styles/lumo/border-radius.css?inline';
import $cssFromFile_4 from 'Frontend/styles/lumo/icon-size.css?inline';
import $cssFromFile_5 from 'Frontend/styles/lumo/margin.css?inline';
import $cssFromFile_6 from 'Frontend/styles/lumo/padding.css?inline';
import $cssFromFile_7 from 'Frontend/styles/lumo/shadow.css?inline';
import $cssFromFile_8 from 'Frontend/styles/lumo/spacing.css?inline';
import $cssFromFile_9 from 'Frontend/styles/lumo/typography.css?inline';
import $cssFromFile_10 from 'Frontend/styles/misc/box-shadow-borders.css?inline';
import $cssFromFile_11 from 'Frontend/styles/styles.css?inline';
import $cssFromFile_12 from 'Frontend/styles/components/list-item.css?inline';
import $cssFromFile_13 from 'Frontend/styles/components/details-drawer.css?inline';
import $cssFromFile_14 from 'Frontend/styles/components/app-bar.css?inline';
import $cssFromFile_15 from 'Frontend/styles/components/tab-bar.css?inline';
import $cssFromFile_16 from 'Frontend/styles/components/account-switcher.css?inline';
import $cssFromFile_17 from 'Frontend/styles/components/brand-expression.css?inline';
import $cssFromFile_18 from 'Frontend/styles/components/navi-drawer.css?inline';
import $cssFromFile_19 from 'Frontend/styles/components/navi-item.css?inline';
import $cssFromFile_20 from 'Frontend/styles/components/navi-menu.css?inline';
import $cssFromFile_21 from 'Frontend/styles/components/view-frame.css?inline';
import $cssFromFile_22 from 'Frontend/styles/views/statistics.css?inline';
import '@vaadin/field-highlighter/src/vaadin-field-highlighter.js';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/accordion/src/vaadin-accordion.js';
import '@vaadin/details/src/vaadin-details.js';
import '@vaadin/accordion/src/vaadin-accordion-panel.js';
import '@vaadin/app-layout/src/vaadin-app-layout.js';
import '@vaadin/button/src/vaadin-button.js';
import '@vaadin/app-layout/src/vaadin-drawer-toggle.js';
import '@vaadin/avatar/src/vaadin-avatar.js';
import '@vaadin/avatar-group/src/vaadin-avatar-group.js';
import '@vaadin/board/src/vaadin-board.js';
import '@vaadin/board/src/vaadin-board-row.js';
import '@vaadin/charts/src/vaadin-chart.js';
import '@vaadin/checkbox/src/vaadin-checkbox.js';
import '@vaadin/checkbox-group/src/vaadin-checkbox-group.js';
import '@vaadin/combo-box/src/vaadin-combo-box.js';
import 'Frontend/generated/jar-resources/flow-component-renderer.js';
import 'Frontend/generated/jar-resources/comboBoxConnector.js';
import '@vaadin/multi-select-combo-box/src/vaadin-multi-select-combo-box.js';
import '@vaadin/confirm-dialog/src/vaadin-confirm-dialog.js';
import '@vaadin/context-menu/src/vaadin-context-menu.js';
import 'Frontend/generated/jar-resources/contextMenuConnector.js';
import 'Frontend/generated/jar-resources/contextMenuTargetConnector.js';
import '@vaadin/cookie-consent/src/vaadin-cookie-consent.js';
import 'Frontend/generated/jar-resources/cookieConsentConnector.js';
import '@vaadin/crud/src/vaadin-crud.js';
import '@vaadin/crud/src/vaadin-crud-edit-column.js';
import '@vaadin/grid/src/vaadin-grid.js';
import '@vaadin/grid/src/vaadin-grid-column.js';
import '@vaadin/grid/src/vaadin-grid-sorter.js';
import 'Frontend/generated/jar-resources/gridConnector.ts';
import '@vaadin/tooltip/src/vaadin-tooltip.js';
import '@vaadin/custom-field/src/vaadin-custom-field.js';
import '@vaadin/dashboard/src/vaadin-dashboard-section.js';
import '@vaadin/dashboard/src/vaadin-dashboard-widget.js';
import '@vaadin/date-picker/src/vaadin-date-picker.js';
import 'Frontend/generated/jar-resources/datepickerConnector.js';
import '@vaadin/date-time-picker/src/vaadin-date-time-picker.js';
import '@vaadin/time-picker/src/vaadin-time-picker.js';
import 'Frontend/generated/jar-resources/vaadin-time-picker/timepickerConnector.js';
import '@vaadin/dialog/src/vaadin-dialog.js';
import 'Frontend/generated/jar-resources/dndConnector.js';
import '@vaadin/form-layout/src/vaadin-form-layout.js';
import '@vaadin/form-layout/src/vaadin-form-item.js';
import '@vaadin/grid/src/vaadin-grid-column-group.js';
import 'Frontend/generated/jar-resources/vaadin-grid-flow-selection-column.js';
import '@vaadin/grid-pro/src/vaadin-grid-pro.js';
import '@vaadin/grid-pro/src/vaadin-grid-pro-edit-column.js';
import 'Frontend/generated/jar-resources/gridProConnector.js';
import '@vaadin/icon/src/vaadin-icon.js';
import '@vaadin/icons/vaadin-iconset.js';
import '@vaadin/list-box/src/vaadin-list-box.js';
import '@vaadin/item/src/vaadin-item.js';
import '@vaadin/login/src/vaadin-login-form.js';
import '@vaadin/login/src/vaadin-login-overlay.js';
import '@vaadin/map/src/vaadin-map.js';
import 'Frontend/generated/jar-resources/vaadin-map/mapConnector.js';
import 'Frontend/generated/jar-resources/menubarConnector.js';
import '@vaadin/menu-bar/src/vaadin-menu-bar.js';
import '@vaadin/message-input/src/vaadin-message-input.js';
import 'Frontend/generated/jar-resources/messageListConnector.js';
import '@vaadin/message-list/src/vaadin-message-list.js';
import '@vaadin/notification/src/vaadin-notification.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/scroller/src/vaadin-scroller.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/popover/src/vaadin-popover.js';
import 'Frontend/generated/jar-resources/vaadin-popover/popover.ts';
import '@vaadin/progress-bar/src/vaadin-progress-bar.js';
import '@vaadin/radio-group/src/vaadin-radio-button.js';
import '@vaadin/radio-group/src/vaadin-radio-group.js';
import 'Frontend/generated/jar-resources/ReactRouterOutletElement.tsx';
import '@vaadin/rich-text-editor/src/vaadin-rich-text-editor.js';
import '@vaadin/select/src/vaadin-select.js';
import 'Frontend/generated/jar-resources/selectConnector.js';
import 'Frontend/generated/jar-resources/tooltip.ts';
import 'Frontend/generated/jar-resources/disableOnClickFunctions.js';
import '@vaadin/side-nav/src/vaadin-side-nav.js';
import '@vaadin/side-nav/src/vaadin-side-nav-item.js';
import '@vaadin/split-layout/src/vaadin-split-layout.js';
import '@vaadin/tabs/src/vaadin-tab.js';
import '@vaadin/tabsheet/src/vaadin-tabsheet.js';
import '@vaadin/tabs/src/vaadin-tabs.js';
import 'Frontend/generated/jar-resources/vaadin-big-decimal-field.js';
import '@vaadin/email-field/src/vaadin-email-field.js';
import '@vaadin/integer-field/src/vaadin-integer-field.js';
import '@vaadin/number-field/src/vaadin-number-field.js';
import '@vaadin/password-field/src/vaadin-password-field.js';
import '@vaadin/text-area/src/vaadin-text-area.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import 'Frontend/generated/jar-resources/lit-renderer.ts';
import '@vaadin/grid/src/vaadin-grid-tree-toggle.js';
import '@vaadin/upload/src/vaadin-upload.js';
import '@vaadin/virtual-list/src/vaadin-virtual-list.js';
import 'Frontend/generated/jar-resources/virtualListConnector.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';
import '@vaadin/vaadin-lumo-styles/badge';
import 'Frontend/swipe-away.js';
const $css_0 = typeof $cssFromFile_0  === 'string' ? unsafeCSS($cssFromFile_0) : $cssFromFile_0;
registerStyles('vaadin-chart', $css_0, {moduleId: 'flow_css_mod_0'});
const $css_1 = typeof $cssFromFile_1  === 'string' ? unsafeCSS($cssFromFile_1) : $cssFromFile_1;
registerStyles('vaadin-button', $css_1, {moduleId: 'flow_css_mod_1'});
const $css_2 = typeof $cssFromFile_2  === 'string' ? unsafeCSS($cssFromFile_2) : $cssFromFile_2;
registerStyles('vaadin-grid', $css_2, {moduleId: 'flow_css_mod_2'});

injectGlobalCss($cssFromFile_3.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_3.toString());

injectGlobalCss($cssFromFile_4.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_4.toString());

injectGlobalCss($cssFromFile_5.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_5.toString());

injectGlobalCss($cssFromFile_6.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_6.toString());

injectGlobalCss($cssFromFile_7.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_7.toString());

injectGlobalCss($cssFromFile_8.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_8.toString());

injectGlobalCss($cssFromFile_9.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_9.toString());

injectGlobalCss($cssFromFile_10.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_10.toString());
function addCssBlock(block) {
 const tpl = document.createElement('template');
 tpl.innerHTML = block;
 document.head.appendChild(tpl.content);
}
const $css_11 = typeof $cssFromFile_11  === 'string' ? unsafeCSS($cssFromFile_11) : $cssFromFile_11;
addCssBlock(`<style include="lumo-badge">${$css_11}</style>`);

injectGlobalCss($cssFromFile_12.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_12.toString());

injectGlobalCss($cssFromFile_13.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_13.toString());

injectGlobalCss($cssFromFile_14.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_14.toString());

injectGlobalCss($cssFromFile_15.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_15.toString());

injectGlobalCss($cssFromFile_16.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_16.toString());

injectGlobalCss($cssFromFile_17.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_17.toString());

injectGlobalCss($cssFromFile_18.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_18.toString());

injectGlobalCss($cssFromFile_19.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_19.toString());

injectGlobalCss($cssFromFile_20.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_20.toString());

injectGlobalCss($cssFromFile_21.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_21.toString());

injectGlobalCss($cssFromFile_22.toString(), 'CSSImport end', document);
injectGlobalWebcomponentCss($cssFromFile_22.toString());
const loadOnDemand = (key) => { return Promise.resolve(0); }
window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;
window.Vaadin.Flow.resetFocus = () => {
 let ae=document.activeElement;
 while(ae&&ae.shadowRoot) ae = ae.shadowRoot.activeElement;
 return !ae || ae.blur() || ae.focus() || true;
}