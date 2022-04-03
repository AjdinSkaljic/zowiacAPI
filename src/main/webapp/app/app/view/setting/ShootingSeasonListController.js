Ext.define('zowiac.view.settings.ShootingSeasonListController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.shootingSeasonList',


    onAdd: function (button) {
        Ext.log('onAdd');
        const shootingSeason = zowiac.model.ShootingSeason.create({id: 0});
        this.getView().store.add(shootingSeason);
    },


    onRemove: function () {
        Ext.log('onRemove');
        try {
            var shootingSeason = this.getView().getSelection()[0];
            if (shootingSeason != null)
                shootingSeason.erase();
        } catch (e) {
            //TODOO: Fehler ausgeben
        }

    },

    onEditItem: function (editor, e) {
        e.record.save();
        e.record.commit();
    }

});
