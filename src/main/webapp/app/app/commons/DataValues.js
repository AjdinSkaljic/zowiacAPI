Ext.define('zowiac.commons.DataValues', {
    statics: {
        states: [
            ['BU', 'bundesweit'],
            ['BW', 'Baden-Württemberg'],
            ['BY', 'Bayern'],
            ['BE', 'Berlin'],
            ['BB', 'Brandenburg'],
            ['HB', 'Bremen'],
            ['HH', 'Hamburg'],
            ['HE', 'Hessen'],
            ['MV', 'Mecklenburg-Vorpommern'],
            ['NI', 'Niedersachsen'],
            ['NW', 'Nordrhein-Westfalen'],
            ['RP', 'Rheinland-Pfalz'],
            ['SL', 'Saarland'],
            ['SN', 'Sachsen'],
            ['ST', 'Sachsen-Anhalt'],
            ['SH', 'Schleswig-Holstein'],
            ['TH', 'Thüringen']
        ],

        population: [
            ['30', 'sehr häufig'],
            ['25', 'häufig'],
            ['20', 'mäßig häufig'],
            ['15', 'selten'],
            ['10', 'sehr selten'],
            ['5', 'extrem selten'],
            ['0', 'ausgestorben oder verschollen']
        ],

        forecastPopulation: [
            ['30', 'Rückgang, im Ausmaß unbekannt'],
            ['25', 'sehr starker Rückgang'],
            ['20', 'starker Rückgang'],
            ['15', 'mäßiger Rückgang'],
            ['10', 'stabil'],
            ['5', 'deutliche Zunahme'],
            ['0', 'Daten ungenügend']
        ],
        reportType: [
            ['J', 'ZOWIAC'],
            ['H', 'Jagd'],
            ['O', 'Beobachter']],

        yesNo: [['J', 'Ja'], ['N', 'Nein']],

        getValueByKey(list, key) {
            try {
                for (let i = 0; i < list.length; i++)
                    if (list[i][0] === key)
                        return list[i][1];
            } catch (e) {
            }
            return '';

        }
    }
});
