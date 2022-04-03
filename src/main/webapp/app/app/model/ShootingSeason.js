Ext.define('zowiac.model.ShootingSeason', {
    extend: 'zowiac.model.Base',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'animalId', type: 'int'},
        {name: 'animalName', type: 'string'},
        {name: 'dateFrom', type: 'date', dateFormat: 'Y-m-d'},
        {name: 'dateTo', type: 'date', dateFormat: 'Y-m-d'},
        {name: 'dateFrom1', type: 'date', dateFormat: 'Y-m-d'},
        {name: 'dateTo1', type: 'date', dateFormat: 'Y-m-d'},
        {name: 'state', type: 'string'}
    ],
    idProperty: 'id',

    proxy: {
        type: 'rest',
        url: urlPrefix + 'api/shootingSeasons',
        reader: {
            type: 'json'
        },
        writer: {
            writeAllFields: true
        }
    }
});
