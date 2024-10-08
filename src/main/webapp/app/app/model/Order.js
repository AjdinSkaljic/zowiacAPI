Ext.define('zowiac.model.Order', {
    extend: 'zowiac.model.Base',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'receiptId', type: 'int'},
        {name: 'countVisitors', type: 'int'},
        {name: 'countPosters', type: 'int'},
        {name: 'countSpeeches', type: 'int'},
        {name: 'fullname', type: 'string'},
        {name: 'email', type: 'string'},
        {name: 'adresse', type: 'string'},
        {name: 'canceled', type: 'boolean'},
        {name: 'settled', type: 'boolean'},
        {name: 'receiptSent', type: 'boolean'},
        {name: 'receiptCreated', type: 'boolean'},
        {name: 'userName', type: 'string'},
        {name: 'orderDate', type: 'date', dateFormat: 'd.m.Y'},
        {name: 'receiptDate', type: 'date', dateFormat: 'd.m.Y'}
    ],
    idProperty: 'id',

    hasMany: [
        {model: 'zowiac.model.OrderLog', name: 'orderLogs', associationKey: 'orderLogs'},
        {model: 'zowiac.model.OrderPosition', name: 'speeches', associationKey: 'speeches'},
        {model: 'zowiac.model.OrderPosition', name: 'posters', associationKey: 'posters'},
        {model: 'zowiac.model.OrderPosition', name: 'visitors', associationKey: 'visitors'},
    ],

    proxy: {
        type: 'rest',
        url: urlPrefix + 'api/orders',
        reader: {
            type: 'json'
        },
        writer: {
            writeAllFields: true
        }
    }
});
