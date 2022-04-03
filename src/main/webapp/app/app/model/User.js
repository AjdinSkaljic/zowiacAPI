Ext.define('zowiac.model.User', {
    extend: 'zowiac.model.Base',
    fields: [
        {name: 'username', type: 'string'},
        {name: 'firstname', type: 'string'},
        {name: 'lastname', type: 'string'},
        {name: 'userRole', type: 'string'},
        {name: 'userPass', type: 'string'},
        {name: 'userRolesNames', type: 'string'},
        {name: 'hunter', type: 'string'},
        {name: 'skipTutorial', type: 'string'},
    ],

    idProperty: 'username',

    proxy: {
        type: 'rest',
        url: urlPrefix + 'api/users',
        reader: {
            type: 'json'
            //rootProperty: 'images'
        },
        writer: {
            writeAllFields: true
        }
    }
});
