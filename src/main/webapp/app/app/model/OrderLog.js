Ext.define('zowiac.model.OrderLog', {
    extend: 'zowiac.model.Base',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'username', type: 'string'},
        {name: 'message', type: 'string'},
        {name: 'dateTime', type: 'date', dateFormat: 'd.m.Y H:i'}
    ],
    idProperty: 'id'
});
