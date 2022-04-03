Ext.define('zowiac.store.ShootingSeason', {
    extend: 'Ext.data.JsonStore',

    alias: 'store.shootingSeason',

    model: 'zowiac.model.ShootingSeason',

    proxy: {
        type: 'ajax',
        url: urlPrefix + 'api/shootingSeasons',
        reader: {
            type: 'json'
            //rootProperty: 'images'
        }
    },

    autoLoad: true
});
