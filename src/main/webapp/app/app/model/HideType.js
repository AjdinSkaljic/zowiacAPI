Ext.define('zowiac.model.HideType', {
    extend: 'zowiac.model.Base',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'name', type: 'string'},
        {name: 'description', type: 'string'}
    ],
    idProperty: 'id',

    proxy: {
        type: 'rest',
        url: urlPrefix + 'api/hideTypes',
        reader: {
            type: 'json'
        },
        writer: {
            writeAllFields: true
        }
    }

});


