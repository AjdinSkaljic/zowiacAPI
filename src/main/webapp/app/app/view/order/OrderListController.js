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
            //TODOO: Fehler ausgeben
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

    exportBesucher: function (){
        Ext.log('exportBesucher');
        window.open(urlPrefix + '/api/orders/export/V');
    },

    exportPoster: function (){
        Ext.log('exportBesucher');
        window.open(urlPrefix + '/api/orders/export/P');
    }
});
