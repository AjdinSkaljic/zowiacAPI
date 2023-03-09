Ext.define('zowiac.model.OrderPosition', {
    extend: 'zowiac.model.Base',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'type', type: 'string'},
        {name: 'name', type: 'string'},
        {name: 'email', type: 'string'},
        {name: 'description', type: 'string'},
        {name: 'discountType', type: 'string'}
    ],
    idProperty: 'id'
});
