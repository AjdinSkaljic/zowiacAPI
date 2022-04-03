Ext.define('zowiac.view.animal.AnimalListController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.animalList',


    onCreate: function (button) {
        Ext.log('onCreate');
        const animalModel = zowiac.model.Animal.create({id: 0});
        this.openFormWindow('Tier anlegen', animalModel);
    },


    onChange: function () {
        Ext.log('onChange');
        try {
            let animalModel = this.getView().getSelection()[0];
            if (animalModel != null)
                this.openFormWindow('Tier ändern', animalModel);
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
            maximized: false,
            items: {
                xtype: 'animalForm',
                model: model,
                listeners: {
                    scope: this,
                    'saved': function () {
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
    }
});
