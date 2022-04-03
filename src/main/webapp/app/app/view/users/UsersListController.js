Ext.define('zowiac.view.users.UsersListController', {
    extend: 'zowiac.view.app.AppListController',

    alias: 'controller.usersList',

    onLockUser: function () {
        Ext.log('onLockUser');

        let lockUser = function () {
            try {
                let user = this.getView().getSelection()[0];
                if (user != null) {
                    Ext.Ajax.request({
                        url: urlPrefix + 'api/userLock/' + user.get('username') + '/',
                        method: 'POST',
                        success: function (response, opts) {
                        },
                        failure: function (response, opts) {
                            console.log('server-side failure with status code ' + response.status);
                        }
                    });
                }
            } catch (e) {
                //TODOO: Fehler ausgeben
            }
        }
        Ext.MessageBox.confirm(
            'Warnung', 'Wollen Sie den User sperren?', lockUser, this);
    },

    onUnlockUser: function () {
        Ext.log('onUnlockUser');

        let unlockUser = function () {
            try {
                let user = this.getView().getSelection()[0];
                if (user != null) {
                    Ext.Ajax.request({
                        url: urlPrefix + 'api/userUnlock/' + user.get('username') + '/',
                        method: 'POST',
                        success: function (response, opts) {
                        },
                        failure: function (response, opts) {
                            console.log('server-side failure with status code ' + response.status);
                        }
                    });
                }
            } catch (e) {
                //TODOO: Fehler ausgeben
            }
        }
        Ext.MessageBox.confirm(
            'Warnung', 'Wollen Sie den User entsperren?', unlockUser, this);
    }


});
