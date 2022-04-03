Ext.define('zowiac.store.User', {
    extend: 'Ext.data.JsonStore',

    alias: 'store.user',

    model: 'zowiac.model.User',

    proxy: {
        type: 'ajax',
        url: urlPrefix + 'api/users',
        reader: {
            type: 'json'
        },
        writer: {
            writeAllFields: true
        }
    },
    autoLoad: true
});
