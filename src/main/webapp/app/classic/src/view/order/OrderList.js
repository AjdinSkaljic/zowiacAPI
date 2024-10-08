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

    multiSelect: true,

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
    }, '-', {
        xtype: 'button',
        text: 'Liste der Vorträge',
        iconCls: 'fa fa-file-excel',
        handler: 'exportSpeech'
    }, '-', {
        xtype: 'button',
        text: 'E-Mail Followup',
        iconCls: 'fa fa-plane',
        handler: 'sendMailFollowup'
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
    }, {
        text: 'Bestelldatum', dataIndex: 'orderDate', width: 100,
        renderer: function (dt) {
            return Ext.Date.format(dt, 'd.m.Y');
        }
    },
        {text: 'name', dataIndex: 'fullname', flex: 1},
        {text: 'E-Mail', dataIndex: 'email', flex: 1},
        {text: '#Besucher', dataIndex: 'countVisitors', width: 100},
        {text: '#Poster', dataIndex: 'countPosters', width: 100},
        {text: '#Vorträge', dataIndex: 'countSpeeches', width: 100},

        {
            text: 'Re.Nr.', dataIndex: 'receiptId', width: 100,
            renderer: function (dt) {
                if (dt === 0)
                    return '';
                return dt;
            }
        },
        {
            text: 'Re.Datum', dataIndex: 'receiptDate', width: 100,
            renderer: function (dt) {
                return Ext.Date.format(dt, 'd.m.Y');
            }
        },
        {text: 'Re.Betrag €', dataIndex: 'receiptSum', width: 100},
        {text: 'Stadt', dataIndex: 'zip', flex: 1},
        {text: 'PLZ', dataIndex: 'city', flex: 1, hidden: true},
        {text: 'Straße', dataIndex: 'street', flex: 1, hidden: true},
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
            text: 'Followup',
            dataIndex: 'followup',
            renderer: function (v) {
                return v ? 'Ja' : 'Nein';
            },
            flex: 1
        }

    ]
});
