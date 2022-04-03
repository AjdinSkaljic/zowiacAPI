Ext.define('zowiac.view.settins.SettingsPanel', {
    extend: 'Ext.panel.Panel',
    requires: [
        'zowiac.view.settins.AboutUsPanel'
    ],
    xtype: 'settingsPanel',

    layout: {
        type: 'accordion',
        titleCollapse: false,
        animate: true
    },

    defaults: {
        frame: true
        // bodyPadding: 15
    },

    items: [ {
        title: 'Über uns',
        xtype: 'aboutUsPanel'
    }]
});
