Ext.define('zowiac.model.Feedback', {
    extend: 'zowiac.model.Base',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'text', type: 'string'},
        {name: 'userName', type: 'string'},
        {name: 'status', type: 'string'},
        {name: 'createDate', type: 'date', dateFormat: 'd.m.Y'},
        {name: 'createTime', type: 'date', dateFormat: 'H:i:s'}
    ],
    idProperty: 'id',
    proxy: {
        type: 'rest',
        url: urlPrefix + 'api/feedback',
        reader: {
            type: 'json'
        },
        writer: {
            writeAllFields: true
        }
    }
});
