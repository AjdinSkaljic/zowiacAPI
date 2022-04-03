Ext.define('zowiac.store.HideType', {
    extend: 'Ext.data.JsonStore',

    alias: 'store.hideType',

    model: 'zowiac.model.HideType',

    proxy: {
        type: 'ajax',
        url: urlPrefix + 'api/hideTypes',
        reader: {
            type: 'json'
        }
    },

    autoLoad: true
});
