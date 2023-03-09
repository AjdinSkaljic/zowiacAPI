Ext.define('zowiac.view.order.OrderFormController', {
    extend: 'zowiac.view.app.AppFormController',

    alias: 'controller.orderForm',

    onCancelOrder: function () {
        Ext.log('onCancelOrder');
        let cancelOrder = function () {
            try {
                let orderModel = this.getView().model;
                if (orderModel != null) {
                    Ext.Ajax.request({
                        url: urlPrefix + 'api/orders/cancel/' + orderModel.get('id') + '/',
                        method: 'GET',
                        scope: this,
                        success: function (response, opts) {
                            this.getView().fireEvent('reload');
                            this.onCancel();
                        },
                        failure: function (response, opts) {
                            console.log('server-side failure with status code ' + response.status);
                        }
                    });
                }
            } catch (e) {
            }
        }
        Ext.MessageBox.confirm(
            'Warnung', 'Wollen Sie den Auftrag stornieren?', cancelOrder, this);

    },

    onCreateReceipt: function () {
        Ext.log('onCreateReceipt');
        let createReceipt = function () {
            try {
                let orderModel = this.getView().model;
                if (orderModel != null) {
                    Ext.Ajax.request({
                        url: urlPrefix + 'api/orders/createReceipt/' + orderModel.get('id') + '/',
                        method: 'GET',
                        scope: this,
                        success: function (response, opts) {
                            this.getView().fireEvent('reload');
                            this.onCancel();
                        },
                        failure: function (response, opts) {
                            console.log('server-side failure with status code ' + response.status);
                        }
                    });
                }
            } catch (e) {
            }
        }
        Ext.MessageBox.confirm(
            'Warnung', 'Wollen Sie die Rechnung erstellen?', createReceipt, this);
    },

    onSendReceipt: function () {
        Ext.log('onSendReceipt');
        let sendReceipt = function () {
            try {
                let orderModel = this.getView().model;
                if (orderModel != null) {
                    Ext.Ajax.request({
                        url: urlPrefix + 'api/orders/sendReceipt/' + orderModel.get('id') + '/',
                        method: 'GET',
                        scope: this,
                        success: function (response, opts) {
                            this.getView().fireEvent('reload');
                            this.onCancel();
                        },
                        failure: function (response, opts) {
                            console.log('server-side failure with status code ' + response.status);
                        }
                    });
                }
            } catch (e) {
            }
        }
        Ext.MessageBox.confirm(
            'Warnung', 'Wollen Sie die Rechnung versenden?', sendReceipt, this);
    },

    onBookReceipt: function () {
        Ext.log('onBookReceipt');
        let bookReceipt = function () {
            try {
                let orderModel = this.getView().model;
                if (orderModel != null) {
                    Ext.Ajax.request({
                        url: urlPrefix + 'api/orders/bookReceipt/' + orderModel.get('id') + '/',
                        method: 'GET',
                        scope: this,
                        success: function (response, opts) {
                            this.getView().fireEvent('reload');
                            this.onCancel();
                        },
                        failure: function (response, opts) {
                            console.log('server-side failure with status code ' + response.status);
                        }
                    });
                }
            } catch (e) {
            }
        }
        Ext.MessageBox.confirm(
            'Warnung', 'Wollen Sie die Rechnung buchen?', bookReceipt, this);
    }


});
