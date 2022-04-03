Ext.define('zowiac.view.feedback.FeedbackListController', {
    extend: 'zowiac.view.app.AppListController',

    alias: 'controller.feedbackList',


    onDone: function () {
        Ext.log('onDone');

        let itemDone = function () {
            try {
                let user = this.getView().getSelection()[0];
                if (user != null) {
                    Ext.Ajax.request({
                        url: urlPrefix + 'api/feedbackDone/' + user.get('id'),
                        method: 'POST',
                        scope: this,
                        success: function (response, opts) {
                            this.getView().getStore().reload();
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
            'Warnung', 'Eintrag als erledigt kennzeichnen?', itemDone, this);
    },
});
