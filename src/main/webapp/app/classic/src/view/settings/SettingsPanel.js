Ext.define('zowiac.view.settings.SettingsPanel', {
    extend: 'Ext.panel.Panel',
    requires: [
        'zowiac.view.settings.AboutUsPanel'
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
