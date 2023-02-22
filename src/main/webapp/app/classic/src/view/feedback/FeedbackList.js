Ext.define('zowiac.view.feedback.FeedbackList', {
    extend: 'Ext.grid.Panel',
    xtype: 'feedbackList',

    requires: [
        'zowiac.store.Authority',
        'zowiac.view.authority.AuthorityForm',
        'zowiac.view.feedback.FeedbackListController'
    ],

    controller: 'feedbackList',

    title: 'Feedback',

    store: {
        type: 'feedback'
    },

    bbar: {
        xtype: 'pagingtoolbar',
        displayInfo: true,
        displayMsg: 'Kommentare {0} - {1} von {2}',
        emptyMsg: 'Keine Kommentare sind vorhanden'
    },

    tbar: [{
        xtype: 'button',
        text: 'Erledigt',
        iconCls: 'fa fa-check',
        handler: 'onDone'
    }, {
        xtype: 'button',
        text: 'Löschen',
        iconCls: 'fa fa-trash',
        handler: 'onDelete'
    }, {
        xtype: 'button',
        text: 'Anzeigen',
        iconCls: 'fa fa-eye',
        handler: 'onDisplay'
    }],

    columns: [{
        text: 'Kommentar',
        dataIndex: 'text',
        flex: 3,

        renderer: function (value, metaData, record, rowIdx, colIdx, store) {
            metaData.tdAttr = 'data-qtip="' + value + '"';
            return value;
        }
    }, {
        text: 'Status',
        dataIndex: 'status',
        flex: 1,
        renderer: function (val) {
            if (val === 'N')
                return 'neu';
            if (val === 'D')
                return 'erledigt';
            return '';
        }
    }, {
        text: 'Benutzer',
        dataIndex: 'userName',
        flex: 1
    }, {
        text: 'Datum',
        dataIndex: 'createDate',
        flex: 1,
        renderer: function (dt) {
            return Ext.Date.format(dt, 'd.m.Y');
        }
    }, {
        text: 'Uhrzeit',
        dataIndex: 'createTime',
        flex: 1,
        renderer: function (dt) {
            return Ext.Date.format(dt, 'H:i');
        }
    }]

});
