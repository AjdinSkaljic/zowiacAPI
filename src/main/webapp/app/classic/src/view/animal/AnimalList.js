Ext.define('zowiac.view.animal.AnimalList', {
    extend: 'Ext.grid.Panel',
    xtype: 'animalList',

    requires: [
        'zowiac.store.Animal',
        'zowiac.view.animal.AnimalForm',
        'zowiac.view.animal.AnimalListController'
    ],

    controller: 'animalList',

    title: 'Tiere',

    store: {
        type: 'animal'
    },

    bbar: {
        xtype: 'pagingtoolbar',
        displayInfo: true,
        displayMsg: 'Tiere {0} - {1} of {2}',
        emptyMsg: "Keine Tiere vorhanden"
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
        width: 40
    },{
        text: 'Name',
        dataIndex: 'name',
        flex: 1
    }, {
        text: 'Wissenschaftlicher  Name',
        dataIndex: 'scientificName',
        flex: 1
    }, {
        text: 'Beschreibung',
        dataIndex: 'description',
        hidden: true,
        flex: 1
    }, {
        text: 'Kategorie',
        dataIndex: 'category',
        flex: 1
    }, {
        text: 'Unterart von',
        dataIndex: 'parentName',
        flex: 1
    },{
        text: 'Meldung erlaubt',
        dataIndex: 'allowReport',
        width: 150,
        renderer: function (val) {
            return  zowiac.commons.DataValues.getValueByKey(zowiac.commons.DataValues.yesNo, val);
        }
    }, {
        text: 'Meldungsart',
        dataIndex: 'reportType',
        width: 150,
        renderer: function (val) {
            return  zowiac.commons.DataValues.getValueByKey(zowiac.commons.DataValues.reportType, val);
        }
    }, {
        text: 'Aktueller Bestand',
        dataIndex: 'actualPopulation',
        width: 150,
        renderer: function (val) {
            return  zowiac.commons.DataValues.getValueByKey(zowiac.commons.DataValues.population, val);
        }
    }, {
        text: 'langfristiger Bestandstrend',
        dataIndex: 'forecastPopulation',
        width: 150,
        renderer: function (val) {
            return  zowiac.commons.DataValues.getValueByKey(zowiac.commons.DataValues.forecastPopulation, val);
        }
    }, {
        text: 'Erstbeschreiber',
        dataIndex: 'firstDescriber',
        width: 150,
        hidden:true
    }]
});
