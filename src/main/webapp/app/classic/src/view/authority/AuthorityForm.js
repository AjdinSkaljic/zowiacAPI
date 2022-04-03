Ext.define('zowiac.view.authority.AuthorityForm', {
    extend: 'Ext.form.Panel',
    xtype: 'authorityForm',

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
        fieldLabel: 'Stadt',
        labelWidth: 120,
        width: '100%',
        name: 'city',
        maxLength: 128,
        allowBlank: false,
        msgTarget: 'under'
    }, {
        fieldLabel: 'PLZ',
        labelWidth: 120,
        width: '100%',
        name: 'postalCode',
        maxLength: 6,
        allowBlank: false,
        msgTarget: 'under'
    }, {
        allowBlank: false,
        fieldLabel: 'Telefon',
        labelWidth: 120,
        width: '100%',
        name: 'phone',
        maxLength: 32,
        msgTarget: 'under'
    }, {
        fieldLabel: 'Telefon erlaubt?',
        xtype: 'combo',
        store: [['J', 'Ja'], ['N', 'Nein']],
        name: 'phonePermitted',
        labelWidth: 120,
        width: 250,
        msgTarget: 'under'
    }, {
        allowBlank: false,
        fieldLabel: 'E-Mail',
        labelWidth: 120,
        width: '100%',
        name: 'mail',
        vtype: 'email',
        maxLength: 128,
        msgTarget: 'under'
    }, {
        allowBlank: false,
        fieldLabel: 'Web',
        labelWidth: 120,
        width: '100%',
        name: 'web',
        vtype: 'url',
        maxLength: 128,
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
