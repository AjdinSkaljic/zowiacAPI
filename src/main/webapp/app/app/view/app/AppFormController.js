Ext.define('zowiac.view.app.AppFormController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.appForm',


    onSave: function (button) {
        Ext.log('onSave');
        this.getView().updateRecord();

        this.getView().getRecord().save({
                scope: this,
                callback: function (record, operation, success) {
                    this.getView().fireEvent('saved');
                    this.onCancel();
                }
            }
        );
    },

    onCancel: function (button) {
        Ext.log('onCancel');
        this.getView().up('window').close();
    }
});
