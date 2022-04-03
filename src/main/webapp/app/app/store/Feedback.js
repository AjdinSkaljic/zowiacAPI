Ext.define('zowiac.store.Feedback', {
    extend: 'Ext.data.JsonStore',

    alias: 'store.feedback',

    model: 'zowiac.model.Feedback',

    proxy: {
        type: 'ajax',
        url: urlPrefix + 'api/feedback',
        reader: {
            type: 'json'
        }
    },
    autoLoad: true
});
