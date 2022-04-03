Ext.define('zowiac.store.Animal', {
    extend: 'Ext.data.JsonStore',

    alias: 'store.animal',

    model: 'zowiac.model.Animal',

    proxy: {
        type: 'ajax',
        url: urlPrefix + 'api/animals',
        reader: {
            type: 'json'
            //rootProperty: 'images'
        }
    },

    autoLoad: true
});
