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
        labelWidth: 180,
        width: '100%'
    },

    initComponent: function () {
        this.items = [{
            xtype: 'fieldcontainer',
            fieldLabel: 'Id/Datum',
            layout: 'hbox',
            items: [{
                name: 'id',
                xtype: 'textfield',
                minWidth: 50,
                maxWidth: 50,
                readOnly: true
            }, {
                xtype: 'splitter'
            }, {
                name: 'orderDate',
                xtype: 'datefield',
                minWidth: 100,
                maxWidth: 100,
                readOnly: true,
                format: 'd.m.Y'
            }]
        }, {
            xtype: 'fieldcontainer',
            fieldLabel: 'Name/Email',
            layout: 'hbox',
            items: [{
                xtype: 'textfield',
                name: 'fullname',
                readOnly: true,
                minWidth: 200
            }, {
                xtype: 'splitter'
            }, {
                xtype: 'textfield',
                name: 'email',
                readOnly: true,
                minWidth: 250
            }]
        }, {
            fieldLabel: 'Straße',
            name: 'street',
            xtype: 'textfield',
            minWidth: 400,
            maxWidth: 500,
            readOnly: true
        }, {
            xtype: 'fieldcontainer',
            fieldLabel: 'PLZ/Ort',
            layout: 'hbox',
            items: [{
                xtype: 'textfield',
                name: 'zip',
                maxWidth: 100,
                readOnly: true
            }, {
                xtype: 'splitter'
            }, {
                xtype: 'textfield',
                name: 'city',
                minWidth: 200,
                maxWidth: 350,
                readOnly: true
            }]
        }, {
            xtype: 'fieldcontainer',
            fieldLabel: '#Besucher/Poster/Vorträge',
            layout: 'hbox',
            items: [{
                xtype: 'textfield',
                minWidth: 50,
                maxWidth: 50,
                readOnly: true,
                name: 'countVisitors'
            }, {
                xtype: 'splitter'
            }, {
                xtype: 'textfield',
                minWidth: 50,
                maxWidth: 50,
                readOnly: true,
                name: 'countPosters'
            }, {
                xtype: 'splitter'
            }, {
                xtype: 'textfield',
                minWidth: 50,
                maxWidth: 50,
                readOnly: true,
                name: 'countSpeeches'
            }]
        }, {
            xtype: 'fieldcontainer',
            fieldLabel: 'Re.Nr./-datum/Betrag',
            layout: 'hbox',
            items: [{

                name: 'receiptId',
                xtype: 'textfield',
                minWidth: 100,
                maxWidth: 200,
                readOnly: true
            }, {
                xtype: 'splitter'
            }, {
                name: 'receiptDate',
                xtype: 'datefield',
                minWidth: 100,
                maxWidth: 200,
                readOnly: true,
                format: 'd.m.Y'
            }, {
                xtype: 'splitter'
            }, {
                name: 'receiptSum',
                xtype: 'textfield',
                minWidth: 100,
                maxWidth: 200,
                readOnly: true
            }]
        }, {
            xtype: 'tabpanel',
            width: '100%',
            items: [{
                title: 'Besucher',
                xtype: 'grid',
                store: this.model.visitors(),
                columns: [{
                    text: 'Name',
                    dataIndex: 'fullname',
                    flex: 1
                }, {
                    text: 'E-Mail',
                    dataIndex: 'email',
                    flex: 1
                }, {
                    text: 'Ticket',
                    dataIndex: 'discountTypeFormatted',
                    width: 100
                }, {
                    text: 'Preis in €',
                    dataIndex: 'price',
                    width: 100
                }]
            }, {
                title: 'Vorträge',
                xtype: 'grid',
                store: this.model.speeches(),
                columns: [{
                    text: 'Titel',
                    dataIndex: 'name',
                    flex: 1
                }, {
                    text: 'Thema',
                    dataIndex: 'topicFormatted',
                    flex: 1
                }, {
                    text: 'Abstract',
                    dataIndex: 'abstractNote',
                    flex: 1
                }, {
                    text: 'sonst. Hinweis',
                    dataIndex: 'note',
                    flex: 1
                }]
            }, {
                title: 'Poster',
                xtype: 'grid',
                store: this.model.posters(),
                columns: [{
                    text: 'Titel',
                    dataIndex: 'name',
                    flex: 1
                }, {
                    text: 'Thema',
                    dataIndex: 'topicFormatted',
                    flex: 1
                }, {
                    text: 'Abstract',
                    dataIndex: 'abstractNote',
                    flex: 1
                }, {
                    text: 'sonst. Hinweis',
                    dataIndex: 'note',
                    flex: 1
                }]
            }, {
                title: 'Log',
                xtype: 'grid',
                store: this.model.orderLogs(),
                columns: [{
                    text: 'Datum',
                    dataIndex: 'dateTime',
                    flex: 1,
                    renderer: function (dt) {
                        return Ext.Date.format(dt, 'd.m.Y H:i');
                    }
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
