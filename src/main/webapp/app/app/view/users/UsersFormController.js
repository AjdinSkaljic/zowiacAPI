Ext.define('zowiac.view.users.UsersFormController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.usersForm',


    onSave: function (button) {
        Ext.log('onSave');
        this.getView().updateRecord();

        this.getView().getRecord().save({
                scope: this,
                callback: function (record, operation, success) {
                    this.onCancel();
                }
            }
        );
    },

    onCancel: function (button) {
        Ext.log('onCancel');
        this.getView().up('window').close();
    },




});
