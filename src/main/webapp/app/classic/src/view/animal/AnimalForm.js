Ext.define('zowiac.view.animal.AnimalForm', {
    extend: 'Ext.form.Panel',
    xtype: 'animalForm',

    requires: [
        'zowiac.view.app.AppFormController'
    ],

    controller: 'appForm',

    frame: true,

    bodyPadding: 10,

    defaultType: 'textfield',

    model: null,

    layout: {
        type: 'vbox',
        pack: 'start'
    },

    items: [{
        allowBlank: false,
        fieldLabel: 'Name',
        name: 'name',
        labelWidth: 120,
        width: '100%',
        msgTarget: 'under'
    }, {
        allowBlank: false,
        fieldLabel: 'Name (englisch)',
        name: 'nameEn',
        labelWidth: 120,
        width: '100%',
        msgTarget: 'under'
    }, {
        allowBlank: false,
        fieldLabel: 'Wissenschaftlicher Name',
        name: 'scientificName',
        labelWidth: 120,
        width: '100%',
        msgTarget: 'under'
    }, {
        fieldLabel: 'Kategorie (englisch)',
        name: 'categoryEn',
        labelWidth: 120,
        width: '100%',
        msgTarget: 'under'
    }, {
        fieldLabel: 'Kategorie',
        name: 'category',
        labelWidth: 120,
        width: '100%',
        msgTarget: 'under'
    }, {
        xtype: 'combo',
        fieldLabel: 'Aktueller Bestand',
        store: zowiac.commons.DataValues.population,
        name: 'actualPopulation',
        labelWidth: 120,
        width: 350,
        msgTarget: 'under'
    }, {
        xtype: 'combo',
        fieldLabel: 'langfristiger Bestandstrend',
        store: zowiac.commons.DataValues.forecastPopulation,
        name: 'forecastPopulation',
        labelWidth: 120,
        width: 350,
        msgTarget: 'under'
    }, {
        fieldLabel: 'Erstbeschreiber',
        name: 'firstDescriber',
        labelWidth: 120,
        width: '100%',
        msgTarget: 'under'
    }, {
        fieldLabel: 'Beschreibung',
        labelAlign: 'top',
        xtype: 'textarea',
        height: 100,
        width: '100%',
        name: 'description'
    }, {
        xtype: 'combo',
        fieldLabel: 'Meldung erlauben',
        store: zowiac.commons.DataValues.yesNo,
        name: 'allowReport',
        labelWidth: 120,
        width: 250,
        msgTarget: 'under'
    }, {
        xtype: 'combo',
        fieldLabel: 'Meldungsart',
        store: zowiac.commons.DataValues.reportType,
        name: 'reportType',
        labelWidth: 120,
        width: 250,
        msgTarget: 'under'
    }, {
        fieldLabel: 'Unterart von',
        name: 'parentId',
        labelWidth: 120,
        width: 250,
        msgTarget: 'under'
    }],

    listeners: {
        afterrender: function (component, eOpts) {
            Ext.log('afterrender');
            component.loadRecord(component.model);
        }
    },

    buttons: [{
        text: 'Speichern',
        iconCls: 'fa fa-save',
        handler: 'onSave'
    }, {
        text: 'Abbrechen',
        iconCls: 'fa fa-times',
        handler: 'onCancel'
    }]
});
