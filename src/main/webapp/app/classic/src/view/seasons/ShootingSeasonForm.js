Ext.define('zowiac.view.seasons.ShootingSeasonForm', {
    extend: 'Ext.form.Panel',
    xtype: 'shootingSeasonForm',

    requires: [
        'Ext.form.field.Date',
        'zowiac.commons.DataValues',
        'zowiac.view.app.AppFormController'
    ],

    controller: 'appForm',

    frame: true,

    bodyPadding: 10,

    defaultType: 'combobox',

    model: null,

    layout: {
        type: 'vbox',
        pack: 'start'
    },

    items: [{
        name: 'animalId',
        queryMode: 'local',
        displayField: 'name',
        valueField: 'id',
        store: Ext.create('zowiac.store.Animal'),
        allowBlank: false,
        fieldLabel: 'Tier',
        labelWidth: 120,
        width: '100%',
        msgTarget: 'under'
    }, {
        fieldLabel: 'Bundesland',
        name: 'state',
        labelWidth: 120,
        width: '100%',
        store: zowiac.commons.DataValues.states,
        msgTarget: 'under'
    }, {
        xtype: 'datefield',
        fieldLabel: 'Jagdperiode 1 von',
        name: 'dateFrom',
        allowBlank: true,
        format: 'd.m',
        labelWidth: 120,
        width: '100%',
        msgTarget: 'under'
    }, {
        xtype: 'datefield',
        fieldLabel: 'Jagdperiode 1 bis',
        name: 'dateTo',
        allowBlank: true,
        format: 'd.m',
        labelWidth: 120,
        width: '100%',
        msgTarget: 'under'
    }, {
        xtype: 'datefield',
        fieldLabel: 'Jagdperiode 2 von',
        name: 'dateFrom1',
        allowBlank: true,
        format: 'd.m',
        labelWidth: 120,
        width: '100%',
        msgTarget: 'under'
    }, {
        xtype: 'datefield',
        fieldLabel: 'Jagdperiode 2 bis',
        name: 'dateTo1',
        allowBlank: true,
        format: 'd.m',
        labelWidth: 120,
        width: '100%',
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
