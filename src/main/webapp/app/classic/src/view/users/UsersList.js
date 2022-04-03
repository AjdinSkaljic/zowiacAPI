Ext.define('zowiac.view.users.UsersList', {
    extend: 'Ext.grid.Panel',
    xtype: 'usersList',

    requires: [
        'zowiac.store.User',
        'zowiac.view.users.UsersForm',
        'zowiac.view.users.UsersListController'
    ],

    controller: 'usersList',

    title: 'Benutzer',

    store: {
        type: 'user'
    },

    tbar: [{
        xtype: 'button',
        text: 'Anlegen',
        iconCls: 'fa fa-plus',
        handler: 'onCreate'
    },
        //TODOO: Löschen des Benutzers
        /* '-', {
        xtype: 'button',
        text: 'Löschen',
        iconCls: 'fa fa-trash',
        handler: 'onDelete'
    }, */'-', {
            xtype: 'button',
            text: 'Sperren',
            iconCls: 'fa fa-lock',
            handler: 'onLockUser'
        }, '-', {
            xtype: 'button',
            text: 'Entsperren',
            iconCls: 'fa fa-lock',
            handler: 'onUnlockUser'
        }],

    bbar: {
        xtype: 'pagingtoolbar',
        displayInfo: true,
        displayMsg: 'Benutzer {0} - {1} von {2}',
        emptyMsg: 'Keine Benutzer sind gepflegt'
    },

    columns: [{
        text: 'Benutzername',
        dataIndex: 'username',
        flex: 1
    }, {
        text: 'Vorname',
        dataIndex: 'firstname',
        flex: 1
    }, {
        text: 'Nachname',
        dataIndex: 'lastname',
        flex: 1
    }, {
        text: 'Rolle(n)',
        dataIndex: 'userRolesNames',
        flex: 1
    }, {
        text: 'Jäger',
        dataIndex: 'hunter',
        width: 100
    }, {
        text: 'Tutorial gesehen',
        dataIndex: 'skipTutorial',
        width: 100
    }],


    getFormXtype: function () {
        return 'usersForm';
    },

    createNewModel: function () {
        return zowiac.model.User.create({username: 'example@email.de'});
    }

});
