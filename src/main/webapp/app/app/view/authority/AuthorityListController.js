Ext.define('zowiac.view.authority.AuthorityListController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.authorityList',


    onCreate: function (button) {
        Ext.log('onCreate');
        const model = zowiac.model.Authority.create({id: 0});
        this.openFormWindow('Behörde  anlegen', model);
    },


    onChange: function () {
        Ext.log('onChange');
        try {
            var model = this.getView().getSelection()[0];
            if (model != null)
                this.openFormWindow("Behörde ändern", model);
        } catch (e) {
            //TODOO: Fehler ausgeben
        }
    },

    openFormWindow: function (title, model) {
        Ext.log('openFormWindow');

        Ext.create('Ext.window.Window', {
            title: title,
            height: 600,
            width: 800,
            layout: 'fit',
            maximizable: true,
            maximized: true,
            items: {
                xtype: 'authorityForm',
                model: model
            }
        }).show();
    },

    onDelete: function () {
        Ext.log('onDelete');
        try {
            const model = this.getView().getSelection()[0];
            if (model != null)
                model.erase();
        } catch (e) {
            //TODOO: Fehler ausgeben
        }
    }
});
