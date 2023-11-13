Ext.define('zowiac.view.order.OrderListController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.orderList',


    onCreate: function (button) {
        Ext.log('onCreate');
        const orderModel = zowiac.model.Order.create({id: 0});
        this.openFormWindow('Ticket bearbeiten', orderModel);
    },


    onChange: function () {
        Ext.log('onChange');
        try {
            let orderModel = this.getView().getSelection()[0];
            if (orderModel != null)
                this.openFormWindow('Ticket bearbeiten', orderModel);
        } catch (e) {
            alert(e);
        }
    },


    openFormWindow: function (title, model) {
        Ext.log('openFormWindow');
        let win = Ext.create('Ext.window.Window', {
            title: title,
            height: 650,
            width: 800,
            layout: 'fit',
            maximizable: true,
            maximized: true,
            items: {
                xtype: 'orderForm',
                model: model,
                listeners: {
                    scope: this,
                    'reload': function () {
                        this.getView().getStore().reload();
                    }
                }
            }
        });
        win.show();
    },


    onDelete: function () {
        Ext.log('onDelete');
        let deleteAnimal = function (buttonId) {
            if (buttonId === 'yes') {
                try {
                    const model = this.getView().getSelection()[0];
                    if (model != null)
                        model.erase();
                } catch (e) {
                    //TODOO: Fehler ausgeben
                }
            }
        }

        Ext.MessageBox.confirm(
            'Warnung', 'Wollen Sie das Tier löschen?', deleteAnimal, this);
    },


    exportSpeech: function () {
        Ext.log('exportSpeech');
        window.open(urlPrefix + 'api/orders/export/S');
    },

    exportBesucher: function () {
        Ext.log('exportBesucher');
        window.open(urlPrefix + 'api/orders/export/V');
    },

    exportPoster: function () {
        Ext.log('exportBesucher');
        window.open(urlPrefix + 'api/orders/export/P');
    },


    sendMailFollowup: function () {
        Ext.log('sendMailFollowup');

        let items = this.getView().getSelection();
        if (items.length === 0) {
            Ext.MessageBox.alert('Warnung', 'Bitte wählen Sie ein Eintrag aus');
            return;
        }

        let sendFollowup = function () {
            try {
                let ids = [];
                let items = this.getView().getSelection();
                for (let i = 0; i < items.length; i++) {
                    ids.push(items[i].get('id'));
                }


                Ext.Ajax.request({
                    url: urlPrefix + 'api/orders/sendFollowup/' + ids.join(',') + '/',
                    method: 'GET',
                    scope: this,
                    success: function (response, opts) {
                        this.getView().getStore().reload();
                    },
                    failure: function (response, opts) {
                        console.log('server-side failure with status code ' + response.status);
                    }
                });

            } catch (e) {
            }
        }
        Ext.MessageBox.confirm(
            'Warnung', 'Wollen Sie die Followup-E-Mail senden?', sendFollowup, this);

    }


});
