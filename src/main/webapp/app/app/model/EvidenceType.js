Ext.define('zowiac.model.EvidenceType', {
    extend: 'zowiac.model.Base',
    fields: [
        {name: 'id', type: 'string'},
        {name: 'name', type: 'string'},
        {name: 'hunting', type: 'string'},
        {name: 'preselect', type: 'string'},
        {name: 'displayAuthorities', type: 'string'}
    ],
    idProperty: 'id',

    proxy: {
        type: 'ajax',
        url: urlPrefix + 'api/evidenceTypes',
        reader: {
            type: 'json'
        },
        writer: {
            writeAllFields: true
        }
    }

});


