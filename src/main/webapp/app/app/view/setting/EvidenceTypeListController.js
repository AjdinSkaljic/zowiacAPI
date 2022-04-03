Ext.define('zowiac.view.settings.EvidenceTypeListController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.evidenceTypeList',


    onAdd: function (button) {
        Ext.log('onAdd');
        const eType = zowiac.model.EvidenceType.create({id: ' '});
        this.getView().store.add(eType);
    },


    onRemove: function () {
        Ext.log('onRemove');
        try {
            var eTypeModel = this.getView().getSelection()[0];
            if (eTypeModel != null)
                eTypeModel.erase();
        } catch (e) {
            //TODOO: Fehler ausgeben
        }

    },

    onEditItem: function (editor, e) {
        e.record.save();
        e.record.commit();
    }


});
