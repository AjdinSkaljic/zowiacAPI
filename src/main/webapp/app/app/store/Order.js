Ext.define('zowiac.store.Order', {
    extend: 'Ext.data.JsonStore',

    alias: 'store.order',

    model: 'zowiac.model.Order',

    proxy: {
        type: 'ajax',
        url: urlPrefix + 'api/orders',
        reader: {
            type: 'json'
        }
    },

    autoLoad: true
});
