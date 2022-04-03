/*
 * This file launches the application by asking Ext JS to create
 * and launch() the Application class.
 */
Ext.application({
    extend: 'zowiac.Application',

    name: 'zowiac',

    requires: [
        // This will automatically load all classes in the zowiac namespace
        // so that application classes do not need to require each other.
        'zowiac.*'
    ],

    // The name of the initial view to create.
    mainView: 'zowiac.view.main.Main'
});
