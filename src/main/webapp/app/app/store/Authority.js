Ext.define('zowiac.store.Authority', {
    extend: 'Ext.data.JsonStore',

    alias: 'store.authority',

    model: 'zowiac.model.Authority',

    proxy: {
        type: 'ajax',
        url: urlPrefix + 'api/authorities',
        reader: {
            type: 'json'
        }
    },
    autoLoad: true
});
