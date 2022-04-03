Ext.define('zowiac.model.Text', {
    extend: 'zowiac.model.Base',
    fields: [
        {name: 'id', type: 'string'},
        {name: 'text', type: 'string'}
    ],
    idProperty: 'id',

    proxy: {
        type: 'ajax',
        url: urlPrefix + 'api/text',
        reader: {
            type: 'json'
            //rootProperty: 'images'
        }
    }
});
