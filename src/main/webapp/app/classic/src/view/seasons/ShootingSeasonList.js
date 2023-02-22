Ext.define('zowiac.view.seasons.ShootingSeasonList', {
    extend: 'Ext.grid.Panel',
    xtype: 'shootingSeasonList',

    requires: [
        'zowiac.commons.DataValues',
        'zowiac.store.ShootingSeason',
        'zowiac.view.seasons.ShootingSeasonForm',
        'zowiac.view.app.AppListController'

    ],
    controller: 'appList',

    title: 'Jagdzeiten',

    store: {
        type: 'shootingSeason'
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
        displayMsg: 'Jagdzeiten {0} - {1} von {2}',
        emptyMsg: 'Keine Jagdzeiten sind gepflegt'
    },

    columns: [{
        text: 'Tier',
        dataIndex: 'animalId',
        flex: 1,
        renderer: function (val, rec) {
            try {
                return rec.record.get('animalName');
            } catch (e) {
                return val;
            }
        }
    }, {
        text: 'Bundesland',
        dataIndex: 'state',
        flex: 1,
        renderer: function (st) {
            for (let i = 0; i < zowiac.commons.DataValues.states.length; i++)
                if (zowiac.commons.DataValues.states[i][0] === st)
                    return zowiac.commons.DataValues.states[i][1];
            return st;
        }
    }, {
        text: 'Jagdperiode 1 von',
        dataIndex: 'dateFrom',
        flex: 1,
        renderer: function (dt) {
            return Ext.Date.format(dt, 'd.m');
        }
    }, {
        text: 'Jagdperiode 1 bis',
        dataIndex: 'dateTo',
        flex: 1,
        renderer: function (dt) {
            return Ext.Date.format(dt, 'd.m');
        }
    }, {
        text: 'Jagdperiode 2 von',
        dataIndex: 'dateFrom1',
        flex: 1,
        renderer: function (dt) {
            return Ext.Date.format(dt, 'd.m');
        }
    }, {
        text: 'Jagdperiode 2 bis',
        dataIndex: 'dateTo1',
        flex: 1,
        renderer: function (dt) {
            return Ext.Date.format(dt, 'd.m');
        }
    }],

    getFormXtype: function () {
        return 'shootingSeasonForm';
    },

    createNewModel: function () {
        return zowiac.model.ShootingSeason.create({id: 0});
    }
});
