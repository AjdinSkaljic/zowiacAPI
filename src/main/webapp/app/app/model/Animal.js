Ext.define('zowiac.model.Animal', {
    extend: 'zowiac.model.Base',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'name', type: 'string'},
        {name: 'scientificName', type: 'string'},
        {name: 'category', type: 'string'},
        {name: 'description', type: 'string'},
        {name: 'allowReport', type: 'string'},
        {name: 'parentName', type: 'string'},
        {name: 'reportType', type: 'string'},
        {name: 'firstDescriber', type: 'string'},
        {name: 'actualPopulation', type: 'string'},
        {name: 'forecastPopulation', type: 'string'}
    ],

    idProperty: 'id',

    proxy: {
        type: 'rest',
        url: urlPrefix + 'api/animals',
        reader: {
            type: 'json'
        },
        writer: {
            writeAllFields: true
        }
    }
});
