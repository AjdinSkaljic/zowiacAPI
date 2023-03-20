Ext.define('zowiac.view.order.OrderList', {
    extend: 'Ext.grid.Panel',
    xtype: 'orderList',

    requires: [
        'Ext.grid.plugin.Exporter',
        'zowiac.store.Order',
        'zowiac.view.order.OrderForm',
        'zowiac.view.order.OrderListController'
    ],

    controller: 'orderList',

    title: 'Tickets',

    store: {
        type: 'order'
    },

    bbar: {
        xtype: 'pagingtoolbar',
        displayInfo: true,
        displayMsg: 'Tickets {0} - {1} von {2}',
        emptyMsg: 'Keine Tickets vorhanden'
    },

    tbar: [{
        xtype: 'button',
        text: 'Anzeigen',
        handler: 'onChange',
        iconCls: 'fa fa-eye',
    }, '-', {
        xtype: 'button',
        text: 'Liste der Besucher',
        iconCls: 'fa fa-file-excel',
        handler: 'exportBesucher'
    }, '-', {
        xtype: 'button',
        text: 'Liste der Poster',
        iconCls: 'fa fa-file-excel',
        handler: 'exportPoster'
    }],

    plugins: {
        gridexporter: true
    },

    loadMask: true,

    listeners: {
        documentsave: 'onDocumentSave',
        beforedocumentsave: 'onBeforeDocumentSave'
    },

    columns: [{
        text: 'ID',
        dataIndex: 'id',
        width: 80
    }, {text: 'E-Mail', dataIndex: 'email', flex: 1},
        {text: '#Besucher', dataIndex: 'countVisitors', flex: 1},
        {text: '#Poster', dataIndex: 'countPosters', flex: 1},
        {text: 'Rechnungsnummer', dataIndex: 'receiptId', flex: 1},
        {text: 'Rechnungsdatum', dataIndex: 'receiptDate', flex: 1},
        {text: 'Stadt', dataIndex: 'zip', flex: 1},
        {text: 'PLZ', dataIndex: 'city', flex: 1, hidden: true},
        {text: 'Strasse', dataIndex: 'street', flex: 1, hidden: true},
        {
            text: 'Rechnung erstellt',
            dataIndex: 'receiptCreated',
            renderer: function (v) {
                if (v)
                    return 'Ja';
                return 'Nein';
            },
            flex: 1
        }, {
            text: 'Rechnung gesendet.',
            dataIndex: 'receiptSent',
            renderer: function (v) {
                return v ? 'Ja' : 'Nein';
            },
            flex: 1
        }, {
            text: 'Rechnung gebucht',
            dataIndex: 'settled',
            renderer: function (v) {
                if (v)
                    return 'Ja';
                return 'Nein';
            },
            flex: 1
        }, {
            text: 'Storniert',
            dataIndex: 'canceled',
            renderer: function (v) {
                return v ? 'Ja' : 'Nein';
            },
            flex: 1
        }, {
            text: 'erstellt am',
            dataIndex: 'createDateTime',
            flex: 1,
            renderer: function (dt) {
                return Ext.Date.format(dt, '  d.m.Y H:i');
            }
        }
    ]
});
