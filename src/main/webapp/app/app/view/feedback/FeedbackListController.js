Ext.define('zowiac.view.feedback.FeedbackListController', {
    extend: 'zowiac.view.app.AppListController',

    alias: 'controller.feedbackList',


    onDone: function () {
        Ext.log('onDone');

        let itemDone = function (buttonId) {
            try {
                if (buttonId === 'yes') {
                    let item = this.getView().getSelection()[0];
                    if (item != null) {
                        Ext.Ajax.request({
                            url: urlPrefix + 'api/feedbackDone/' + item.get('id'),
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
                }
            } catch (e) {
                //TODOO: Fehler ausgeben
            }
        }
        Ext.MessageBox.confirm(
            'Warnung', 'Eintrag als erledigt kennzeichnen?', itemDone, this);
    },

    onDisplay: function () {

        try {
            let feedback = this.getView().getSelection()[0];
            if (feedback != null) {
                Ext.MessageBox.alert(
                    'Feedback', feedback.get('text'));
            }
        } catch (e) {
            //TODOO: Fehler ausgeben
        }

    }

});
