Ext.define('zowiac.store.Report', {
    extend: 'Ext.data.JsonStore',

    alias: 'store.report',

    model: 'zowiac.model.Report',

    proxy: {
        type: 'ajax',
        url: urlPrefix + 'api/reports',
        reader: {
            type: 'json'
            //rootProperty: 'images'
        }
    },

    autoLoad: true
});
