Ext.define('zowiac.view.evidenceType.EvidenceTypeForm', {
    extend: 'Ext.form.Panel',
    xtype: 'evidenceTypeForm',

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
        fieldLabel: 'Art',
        name: 'name',
        labelWidth: 120,
        width: '100%',
        allowBlank: false,
        msgTarget: 'under'
    }, {
        fieldLabel: 'Standard',
        name: 'preselect',
        xtype: 'combobox',
        labelWidth: 120,
        store: [['J', 'Ja'], ['N', 'Nein']]
    }, {
        fieldLabel: 'Jagd',
        name: 'hunting',
        xtype: 'combobox',
        labelWidth: 120,
        store: [['J', 'Ja'], ['N', 'Nein']]
    }, {
        fieldLabel: 'Ämter anzeigen',
        name: 'displayAuthorities',
        xtype: 'combobox',
        labelWidth: 120,
        store: [['J', 'Ja'], ['N', 'Nein']]
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
