Ext.define('zowiac.view.hide.HideTypeForm', {
    extend: 'Ext.form.Panel',
    xtype: 'hideTypeForm',

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
        fieldLabel: 'Name',
        name: 'name',
        labelWidth: 120,
        width: '100%',
        allowBlank: false,
        msgTarget: 'under'
    }, {
        fieldLabel: 'Name (englisch)',
        name: 'nameEn',
        labelWidth: 120,
        width: '100%',
        allowBlank: false,
        msgTarget: 'under'
    }, {
        fieldLabel: 'Beschreibung',
        name: 'description',
        labelWidth: 120,
        width: '100%',
        allowBlank: true,
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
