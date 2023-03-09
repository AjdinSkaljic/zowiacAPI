Ext.define('zowiac.view.settings.AboutUsPanel', {
    extend: 'Ext.form.Panel',
    requires: [
        'zowiac.view.settings.AboutUsPanelController'
    ],
    xtype: 'aboutUsPanel',

    controller: 'aboutUsPanel',

    layout: 'fit',

    tbar: [{
        iconCls: 'fa fa-save',
        text: 'Speichern',
        handler: 'onSave'
    }],

    items: [{
        xtype: 'htmleditor',
        name: 'text'
    }],

    listeners: {
        afterrender: function (component, eOpts) {
            Ext.log('afterrender');
            const model = zowiac.model.Text.load('aboutus', {
                scope: component,
                callback: function (record, operation, success) {
                    this.loadRecord(record);
                }
            });
        }
    }


});
