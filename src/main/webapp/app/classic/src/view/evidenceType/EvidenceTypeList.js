Ext.define('zowiac.view.evidenceType.EvidenceTypeList', {
    extend: 'Ext.grid.Panel',
    xtype: 'evidenceTypeList',

    requires: [
        'zowiac.store.EvidenceType',
        'zowiac.view.evidenceType.EvidenceTypeForm',
        'zowiac.view.app.AppListController'
    ],

    controller: 'appList',

    title: 'Art der Sichtung',

    store: {
        type: 'evidenceType'
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

    bbar: {
        xtype: 'pagingtoolbar',
        displayInfo: true,
        displayMsg: 'Art der Sichtungen {0} - {1} von {2}',
        emptyMsg: 'Keine Arten sind gepflegt'
    },

    columns: [{
        text: 'ID',
        dataIndex: 'id',
        width: 100
    }, {
        text: 'Art',
        dataIndex: 'name',
        flex: 1
    }, {
        text: 'Standard',
        dataIndex: 'preselect',
        width: 120,
        renderer: function (val) {
            if (val === 'J')
                return 'Ja';
            return '';
        }
    }, {
        text: 'Jagd',
        dataIndex: 'hunting',
        width: 120,
        renderer: function (val) {
            if (val === 'J')
                return 'Ja';
            return '';
        }
    },{
        text: 'Ämter anzeigen',
        tooltip: 'Bei der Auswahl dieser Art werden die Ämter automatisch angezeigt',
        dataIndex: 'displayAuthorities',
        width: 120,
        renderer: function (val) {
            if (val === 'J')
                return 'Ja';
            return '';
        }
    }],


    getFormXtype: function () {
        return 'evidenceTypeForm';
    },

    createNewModel: function () {
        return zowiac.model.EvidenceType.create({id: 0});
    }

});
