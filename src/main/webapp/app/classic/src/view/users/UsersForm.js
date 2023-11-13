Ext.define('zowiac.view.users.UsersForm', {
    extend: 'Ext.form.Panel',
    requires: [
        'zowiac.view.users.UsersFormController'
    ],
    xtype: 'usersForm',

    controller: 'usersForm',

    frame: true,
    bodyPadding: 10,

    defaultType: 'textfield',

    model: null,
    scrollable: 'y',

    layout: {
        type: 'vbox',
        pack: 'start',
        align: 'stretch'
    },

    initComponent: function () {

        this.items = [{
            allowBlank: false,
            fieldLabel: 'Email',
            name: 'username',
            vtype: 'email',
            maxLength: 124,
            labelWidth: 120,
            msgTarget: 'under'
        }, {
            allowBlank: false,
            fieldLabel: 'Name',
            labelWidth: 120,
            maxLength: 64,
            name: 'lastname'
        }, {
            allowBlank: false,
            fieldLabel: 'Vorname',
            labelWidth: 120,
            maxLength: 64,
            name: 'firstname'
        }, {
            allowBlank: false,
            xtype: 'combo',
            fieldLabel: 'Sprache',
            name: 'language',
            labelWidth: 120,
            value: 'de',
            store: [['de', 'deutsch'], ['en', 'englisch']],
            queryMode: 'local'
        }, {
            allowBlank: false,
            xtype: 'combo',
            fieldLabel: 'Rolle',
            name: 'userRole',
            labelWidth: 120,
            store: [['Admin', 'Admin'], ['User', 'Benutzer']],
            queryMode: 'local'
        }, {
            allowBlank: false,
            fieldLabel: 'Passwort',
            labelWidth: 120,
            name: 'userPass',
            maxLength: 16,
            minLength: 8,
            inputType: 'password',
        }];

        this.callParent();
    },

    listeners: {
        afterrender: function (component, eOpts) {
            Ext.log('afterrender');
            component.loadRecord(component.model);
        }
    },

    buttons: [{
        text: 'Speichern',
        handler: 'onSave'
    }, {
        text: 'Abbrechen',
        handler: 'onCancel'
    }]
})
;
