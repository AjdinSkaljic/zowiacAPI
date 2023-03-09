Ext.define('zowiac.view.order.OrderForm', {
    extend: 'Ext.form.Panel',
    xtype: 'orderForm',

    requires: [
        'zowiac.view.order.OrderFormController'
    ],

    controller: 'orderForm',

    frame: true,

    bodyPadding: 10,

    defaultType: 'displayfield',

    model: null,

    layout: {
        type: 'vbox',
        pack: 'start'
    },

    defaults: {
        labelWidth: 120,
        width: '100%'
    },

    initComponent: function () {
        this.items = [{
            fieldLabel: 'Id',
            name: 'id'
        }, {
            xtype: 'fieldcontainer',
            fieldLabel: 'Name/Email',
            layout: 'hbox',
            items: [{
                xtype: 'displayfield',
                name: 'name'
            }, {
                xtype: 'splitter'
            }, {
                xtype: 'displayfield',
                name: 'email'
            }]
        }, {
            fieldLabel: 'Strasse',
            name: 'street'
        }, {
            xtype: 'fieldcontainer',
            fieldLabel: 'PLZ/Ort',
            layout: 'hbox',
            items: [{
                xtype: 'displayfield',
                name: 'zip'
            }, {
                xtype: 'splitter'
            }, {
                xtype: 'displayfield',
                name: 'city'
            }]
        }, {
            xtype: 'fieldcontainer',
            fieldLabel: '# Besucher/Poster',
            layout: 'hbox',
            items: [{
                xtype: 'displayfield',
                name: 'countVisitors'
            }, {
                xtype: 'splitter'
            }, {
                xtype: 'displayfield',
                name: 'countPosters'
            }]
        }, {
            fieldLabel: 'Rechnungsnummer',
            name: 'receiptId'
        }, {
            xtype: 'tabpanel',
            width: '100%',
            items: [{
                title: 'Besucher',
                xtype: 'grid',
                store: this.model.visitors(),
                columns: [{
                    text: 'Name',
                    dataIndex: 'name',
                    flex: 1
                },{
                    text: 'E-Mail',
                    dataIndex: 'email',
                    flex: 1
                },{
                    text: 'Nachlass',
                    dataIndex: 'discountType',
                    flex: 1
                }]
            }, {
                title: 'Poster',
                xtype: 'grid',
                store: this.model.posters(),
                columns: [{
                    text: 'Name',
                    dataIndex: 'name',
                    flex: 1
                }, {
                    text: 'Beschreibung',
                    dataIndex: 'description',
                    flex: 1
                }]
            }, {
                title: 'Log',
                xtype: 'grid',
                store: this.model.orderLogs(),
                columns: [{
                    text: 'Datum',
                    dataIndex: 'dateTime',
                    flex: 1
                }, {
                    text: 'Log',
                    dataIndex: 'message',
                    flex: 1
                }, {
                    text: 'Benutzer',
                    dataIndex: 'username',
                    flex: 1
                }]
            }]
        }];


        this.callParent();
    },


    listeners: {
        afterrender: function (component) {
            Ext.log('afterrender');
            component.loadRecord(component.model);
        }
    },

    buttons: [{
        text: 'Tickets stornieren',
        iconCls: 'fa fa-times',
        handler: 'onCancelOrder'
    }, {
        text: 'Rechnung erstellen',
        iconCls: 'fa fa-plus',
        handler: 'onCreateReceipt'
    }, {
        text: 'Rechnung senden',
        iconCls: 'fa fa-paper-plane',
        handler: 'onSendReceipt'
    }, {
        text: 'Rechnung buchen',
        iconCls: 'fa fa-money-bill',
        handler: 'onBookReceipt'
    }]
});
