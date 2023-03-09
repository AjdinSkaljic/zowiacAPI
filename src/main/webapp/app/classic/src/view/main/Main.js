/**
 * This class is the main view for the application. It is specified in app.js as the
 * "mainView" property. That setting automatically applies the "viewport"
 * plugin causing this view to become the body element (i.e., the viewport).
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('zowiac.view.main.Main', {
    extend: 'Ext.tab.Panel',
    xtype: 'app-main',

    requires: [
        'Ext.plugin.Viewport',
        'Ext.window.MessageBox',
        'Ext.form.field.*',
        'zowiac.model.*',
        'zowiac.view.main.MainController',
        'zowiac.view.main.MainModel',
        'zowiac.view.report.ReportList',
        'zowiac.view.animal.AnimalList',
        'zowiac.view.seasons.ShootingSeasonList',
        'zowiac.view.settings.SettingsPanel',
        'zowiac.view.hide.HideTypeList',
        'zowiac.view.authority.AuthorityList',
        'zowiac.view.users.UsersList',
        'zowiac.view.feedback.FeedbackList',
        'zowiac.view.evidenceType.EvidenceTypeList',
        'zowiac.view.order.OrderList'    ],

    controller: 'main',

    viewModel: 'main',

    ui: 'navigation',

    tabBarHeaderPosition: 1,
    titleRotation: 0,
    tabRotation: 0,

    header: {
        layout: {
            align: 'stretchmax'
        },
        title: {
            bind: {
                text: '{name}'
            },
            flex: 0
        },
        iconCls: 'fa-th-list'
    },

    tabBar: {
        flex: 1,
        layout: {
            align: 'stretch',
            overflowHandler: 'none'
        }
    },

    responsiveConfig: {
        tall: {
            headerPosition: 'top'
        },
        wide: {
            headerPosition: 'left'
        }
    },

    defaults: {
        bodyPadding: 20,
        tabConfig: {
            responsiveConfig: {
                wide: {
                    iconAlign: 'left',
                    textAlign: 'left'
                },
                tall: {
                    iconAlign: 'top',
                    textAlign: 'center',
                    width: 120
                }
            }
        }
    },

    items: [{
        title: 'Meldungen',
        iconCls: 'fa-home',
        layout: {
            type: 'vbox',
            pack: 'start',
            align: 'stretch'

        },
        items: [{
            xtype: 'reportList',
            flex: 1
        }]
    }, {
        title: 'Tickets',
        iconCls: 'fa-ticket-alt',
        layout: {
            type: 'vbox',
            pack: 'start',
            align: 'stretch'

        },
        items: [{
            xtype: 'orderList',
            flex: 1
        }]
    },{
        title: 'Tierart',
        iconCls: 'fa-paw',
        layout: {
            type: 'vbox',
            pack: 'start',
            align: 'stretch'

        },
        items: [{
            xtype: 'animalList',
            flex: 1
        }]
    }, {

        title: 'Benutzer',
        iconCls: 'fa-users',
        layout: {
            type: 'vbox',
            pack: 'start',
            align: 'stretch'

        },
        items: [{
            xtype: 'usersList',
            flex: 1
        }]

    }, {
        title: 'Zuständige Behörden',
        iconCls: 'fa-building',
        layout: {
            type: 'vbox',
            pack: 'start',
            align: 'stretch'

        },
        items: [{
            xtype: 'authorityList',
            flex: 1
        }]
    }, {
        title: 'Jagdzeiten',
        iconCls: 'fa-calendar-alt',
        layout: {
            type: 'vbox',
            pack: 'start',
            align: 'stretch'

        },
        items: [{
            xtype: 'shootingSeasonList',
            flex: 1
        }]
    }, {
        title: 'Art der Sichtungen',
        iconCls: 'fa-binoculars',
        layout: {
            type: 'vbox',
            pack: 'start',
            align: 'stretch'

        },
        items: [{
            xtype: 'evidenceTypeList',
            flex: 1
        }]
    }, {
        title: 'Art der Jagdeinrichtung',
        iconCls: 'fa-bookmark',
        layout: {
            type: 'vbox',
            pack: 'start',
            align: 'stretch'

        },
        items: [{
            xtype: 'hideTypeList',
            flex: 1
        }]
    }, {
        title: 'Feedback',
        iconCls: 'fa-comments',
        layout: {
            type: 'vbox',
            pack: 'start',
            align: 'stretch'

        },
        items: [{
            xtype: 'feedbackList',
            flex: 1
        }]
    }, {
        title: 'Einstellungen',
        iconCls: 'fa-cog',
        layout: {
            type: 'vbox',
            pack: 'start',
            align: 'stretch'

        },
        items: [{
            xtype: 'settingsPanel',
            flex: 1
        }]
    }, {
        title: 'Abmelden',
        iconCls: 'fa-sign-out-alt',
        logoff: true,
        layout: {
            type: 'vbox',
            pack: 'start',
            align: 'stretch'

        },
        items: [{
            html: 'Abmelden. Bitte warten!'
        }]
    }
    ],

    listeners: {
        'tabchange': function (tabPanel, newCard, oldCard, eOpts) {
            if (newCard.logoff)
                window.location.href = urlPrefix + 'logoff.jsp'
        }
    },
});
