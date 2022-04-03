Ext.define('zowiac.view.settings.AboutUsPanelController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.aboutUsPanel',


    onSave: function (button) {
        Ext.log('onSave');
        this.getView().updateRecord();
        this.getView().getRecord().save({
                scope: this,
                callback: function (record, operation, success) {
                    Ext.toast('Daten gespeichert..');
                }
            }
        );
    }


});
