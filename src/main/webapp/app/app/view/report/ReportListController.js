Ext.define('zowiac.view.report.ReportListController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.reportList',


    onShowPicture: function (button) {
        Ext.log('oShowPicture');
        try {
            let report = this.getView().getSelection()[0];
            if (report != null)
                window.open("../api/files/" + report.get('refId'));
        } catch (e) {
            //TODOO: Fehler ausgeben
        }
    },

    exportTo: function (btn) {
        Ext.log('exportTo');
        let cfg = Ext.merge({
            title: 'Meldungen',
            fileName: 'Meldungen' + '.' + (btn.cfg.ext || btn.cfg.type)
        }, btn.cfg);

        this.getView().saveDocumentAs(cfg);
    },

    onBeforeDocumentSave: function (view) {
        view.mask('Dokument wird für den Export vorbereitet. Bitte warten ...');
    },

    onDocumentSave: function (view) {
        view.unmask();
        Ext.log('Export abgeschlossen');
    },

    onDeleteReport: function () {
        Ext.log('onDelete');

        let deleteReport = function () {
            try {
                let report = this.getView().getSelection()[0];
                if (report != null)
                    report.erase();
            } catch (e) {
                //TODOO: Fehler ausgeben
            }
        }
        Ext.MessageBox.confirm(
            'Warnung', 'Wollen Sie den Eintrag löschen?', deleteReport, this);
    }

});
