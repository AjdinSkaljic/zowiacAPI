Ext.define('zowiac.view.app.AppListController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.appList',


    onCreate: function (button) {
        Ext.log('onCreate');
        const model = this.getView().createNewModel();
        this.openFormWindow('Anlegen', model);
    },


    onChange: function () {
        Ext.log('onChange');
        try {
            let model = this.getView().getSelection()[0];
            if (model != null)
                this.openFormWindow("Ändern", model);
        } catch (e) {
            //TODOO: Fehler ausgeben
        }
    },

    openFormWindow: function (title, model) {
        Ext.log('openFormWindow');

        //TODOO: Uodate nach einfügen
        Ext.create('Ext.window.Window', {
            title: title,
            height: 600,
            width: 800,
            layout: 'fit',
            maximizable: true,
            maximized: false,
            items: {
                xtype: this.getView().getFormXtype(),
                model: model,
                listeners: {
                    scope: this,
                    'saved': this.reloadData
                }
            }
        }).show();
    },

    reloadData: function () {
        try {
            this.getView().getStore().reload();
        } catch (e) {
            alert(e);

        }
    },

    onDelete: function () {
        Ext.log('onDelete');

        let deleteItem = function (buttonId) {
            try {
                if (buttonId === 'yes') {
                    const model = this.getView().getSelection()[0];
                    if (model != null)
                        model.erase();
                }
            } catch (e) {
                //TODOO: Fehler ausgeben
            }
        }
        Ext.MessageBox.confirm(
            'Warnung', 'Wollen Sie den Eintrag löschen?', deleteItem, this);
    }
});
