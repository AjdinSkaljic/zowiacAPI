Ext.define('zowiac.store.EvidenceType', {
    extend: 'Ext.data.JsonStore',

    alias: 'store.evidenceType',

    model: 'zowiac.model.EvidenceType',

    proxy: {
        type: 'ajax',
        url: urlPrefix + 'api/evidenceTypes',
        reader: {
            type: 'json'
            //rootProperty: 'images'
        }
    },

    autoLoad: true
});
