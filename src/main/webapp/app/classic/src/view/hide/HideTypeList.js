Ext.define('zowiac.view.hide.HideTypeList', {
    extend: 'Ext.grid.Panel',
    xtype: 'hideTypeList',

    requires: [
        'zowiac.store.HideType',
        'zowiac.view.hide.HideTypeForm',
        'zowiac.view.app.AppListController'
    ],

    controller: 'appList',

    title: 'Art der Jagdeinrichtung',

    store: {
        type: 'hideType'
    },

    bbar: {
        xtype: 'pagingtoolbar',
        displayInfo: true,
        displayMsg: 'Arten {0} - {1} of {2}',
        emptyMsg: 'Keine Arten vorhanden'
    },

    tbar: [{
        xtype: 'button',
        text: 'Anlegen',
        iconCls: 'fa fa-plus',
        handler: 'onCreate'
    }, {
        xtype: 'button',
        text: 'Ändern',
        iconCls: 'fa fa-pen',
        handler: 'onChange'
    }, {
        xtype: 'button',
        text: 'Löschen',
        iconCls: 'fa fa-trash',
        handler: 'onDelete'
    }],

    columns: [{
        text: 'ID',
        dataIndex: 'id',
        width: 80
    }, {
        text: 'Name',
        dataIndex: 'name',
        flex: 1
    }, {
        text: 'Beschreibung',
        dataIndex: 'description',
        flex: 1
    }],

    getFormXtype: function () {
        return 'hideTypeForm';
    },

    createNewModel: function () {
        return zowiac.model.HideType.create({id: 0});
    }

});
