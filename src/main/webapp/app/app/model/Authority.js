Ext.define('zowiac.model.Authority', {
    extend: 'zowiac.model.Base',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'name', type: 'string'},
        {name: 'city', type: 'string'},
        {name: 'postalCode', type: 'string'},
        {name: 'phone', type: 'string'},
        {name: 'mail', type: 'string'},
        {name: 'web', type: 'string'},
        {name: 'phonePermitted', type: 'string'},

    ],

    idProperty: 'id',

    proxy: {
        type: 'rest',
        url: urlPrefix + 'api/authorities',
        reader: {
            type: 'json'
        },
        writer: {
            writeAllFields: true
        }
    }
});
