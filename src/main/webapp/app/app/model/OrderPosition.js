Ext.define('zowiac.model.OrderPosition', {
    extend: 'zowiac.model.Base',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'type', type: 'string'},
        {name: 'fullname', type: 'string'},
        {name: 'firstname', type: 'string'},
        {name: 'lastname', type: 'string'},
        {name: 'email', type: 'string'},
        {name: 'abstractNote', type: 'string'},
        {name: 'note', type: 'string'},
        {name: 'discountType', type: 'string'},
        {name: 'discountTypeFormatted', type: 'string'},
        {name: 'preis', type: 'number'}
    ],
    idProperty: 'id'
});
