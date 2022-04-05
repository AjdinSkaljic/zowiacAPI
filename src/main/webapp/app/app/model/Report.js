Ext.define('zowiac.model.Report', {
    extend: 'zowiac.model.Base',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'refId', type: 'string'},
        {name: 'animalId', type: 'int'},
        {name: 'animalName', type: 'string'},
        {name: 'subAnimalId', type: 'int'},
        {name: 'subAnimalName', type: 'string'},
        {name: 'evidenceType', type: 'string'},
        {name: 'evidenceTypeName', type: 'string'},
        {name: 'count', type: 'number'},
        {name: 'hunting', type: 'string'},
        {name: 'huntingRadius', type: 'number'},
        {name: 'date', type: 'date', dateFormat: 'd.m.Y'},
        {name: 'time', type: 'date', dateFormat: 'H:i'},
        {name: 'user', type: 'string'},
        {name: 'userName', type: 'string'},
        {name: 'latitude', type: 'number'},
        {name: 'longitude', type: 'number'},
        {name: 'adressLine', type: 'string'},
        {name: 'country', type: 'string'},
        {name: 'countryCode', type: 'string'},
        {name: 'state', type: 'string'},
        {name: 'street', type: 'string'},
        {name: 'houseNumber', type: 'string'},
        {name: 'pictureType', type: 'string'},
        {name: 'pictureDateTime', type: 'date', dateFormat: 'd.m.Y H:i'}

    ],
    idProperty: 'id',

    proxy: {
        type: 'rest',
        url: urlPrefix + 'api/reports',
        reader: {
            type: 'json'
            //rootProperty: 'images'
        },
        writer: {
            writeAllFields: true
        }
    }
});
