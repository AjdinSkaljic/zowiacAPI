Ext.define('zowiac.view.report.ReportList', {
    extend: 'Ext.grid.Panel',
    xtype: 'reportList',

    requires: [
        'Ext.grid.plugin.Exporter',
        'zowiac.store.Report',
        'zowiac.view.report.ReportListController'
    ],

    controller: 'reportList',

    title: 'Meldungen',

    store: {
        type: 'report'
    },

    bbar: {
        xtype: 'pagingtoolbar',
        displayInfo: true,
        displayMsg: 'Meldungen {0} - {1} of {2}',
        emptyMsg: 'Keine Meldungen vorhanden'
    },

    tbar: [{
        xtype: 'button',
        text: 'Bild anzeigen',
        handler: 'onShowPicture',
        iconCls: 'fa fa-image',
    }, '-', {
        xtype: 'button',
        text: 'Exportieren',
        iconCls: 'fa fa-file-excel',
        cfg: {
            type: 'excel07',
            ext: 'xlsx'
        },
        handler: 'exportTo'
    }, '-', {
        xtype: 'button',
        text: 'Löschen',
        iconCls: 'fa fa-trash',
        handler: 'onDeleteReport'
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
    }, {text: 'Tier', dataIndex: 'animalName', flex: 1},
        {text: 'Unterart', dataIndex: 'subAnimalName', flex: 1},
        {text: 'Art der Sichtung', dataIndex: 'evidenceTypeName', flex: 1},
        {text: 'Anzahl', dataIndex: 'count', flex: 1},
        {
            text: 'Jagdstrecke',
            dataIndex: 'hunting',
            flex: 1,
            renderer: function (val) {
                if (val === 'J')
                    return 'Ja';
                return 'Nein';
            }
        }, {
            text: 'Datum', dataIndex: 'date',
            renderer: function (dt) {
                return Ext.Date.format(dt, 'd.m.Y');
            },
            flex: 1
        }, {
            text: 'Uhrzeit', dataIndex: 'time',
            renderer: function (dt) {
                return Ext.Date.format(dt, 'H:i');
            },
            flex: 1

        },


        {text: 'Benutzername', dataIndex: 'userName', flex: 1},
        {text: 'Benutzer', dataIndex: 'user', flex: 1, hidden: true},
        {text: 'Standort', dataIndex: 'adressLine', flex: 1},
        {text: 'Bundesland', dataIndex: 'state', flex: 1},
        {text: 'PLZ', dataIndex: 'postalCode', flex: 1},
        {text: 'Latitude', dataIndex: 'latitude', flex: 1},
        {text: 'Longitude', dataIndex: 'longitude', flex: 1},
        {text: 'Jagdradius in km', dataIndex: 'huntingRadius', flex: 1, hidden: true},
        {text: 'Land', dataIndex: 'country', flex: 1},
        {text: 'Code', dataIndex: 'countryCode', flex: 1},
        {text: 'Strasse', dataIndex: 'street', flex: 1},
        {text: 'Hausnr.', dataIndex: 'houseNumber', flex: 1},
        {
            text: 'Bildursprung',
            dataIndex: 'pictureType',
            flex: 1,
            renderer: function (val) {
                if (val === 'C')
                    return 'Kamera';
                if (val === 'D')
                    return 'Album';
                return '';
            }
        }, {
            text: 'Bilddatum/-uhrzeit',
            dataIndex: 'pictureDateTime',
            flex: 1,
            renderer: function (dt) {
                return Ext.Date.format(dt, '  d.m.Y H:i');
            }
        }
    ]
});
