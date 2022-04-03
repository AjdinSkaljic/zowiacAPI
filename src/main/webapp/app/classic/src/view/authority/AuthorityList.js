Ext.define('zowiac.view.authority.AuthorityList', {
    extend: 'Ext.grid.Panel',
    xtype: 'authorityList',

    requires: [
        'zowiac.store.Authority',
        'zowiac.view.authority.AuthorityForm',
        'zowiac.view.authority.AuthorityListController'
    ],

    controller: 'authorityList',

    title: 'Zuständige Behörden',

    store: {
        type: 'authority'
    },

    bbar: {
        xtype: 'pagingtoolbar',
        displayInfo: true,
        displayMsg: 'Behörden {0} - {1} von {2}',
        emptyMsg: 'Keine Behörden sind gepflegt'
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
        text: 'Name',
        dataIndex: 'name',
        flex: 2
    }, {
        text: 'Stadt',
        dataIndex: 'city',
        flex: 1
    }, {
        text: 'PLZ',
        dataIndex: 'postalCode',
        flex: 1
    }, {
        text: 'Telefon',
        dataIndex: 'phone',
        flex: 1
    }, {
        text: 'Telefon erlaubt?',
        dataIndex: 'phonePermitted',
        tooltip: 'Telefon darf in der App angezeigt werden',
        width: 100,
        renderer: function (val) {
            if (val === 'J')
                return 'Ja';
            return 'Nein';
        }
    }, {
        text: 'E-Mail',
        dataIndex: 'mail',
        flex: 1
    }, {
        text: 'Web',
        dataIndex: 'web',
        flex: 1
    }]

});
