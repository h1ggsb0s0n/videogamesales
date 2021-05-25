
## Start
1) MySQL Datenbank aufsetzen
2) csv Einlesen (Siehe unten)
3) application.properties file mit eigener Datenbank Info Anpassen (target -> classes)
4) Datenbank verbindung erstellen


## Guide
ATL 02 -> Main View + SearchForm
Die Applikation wird durch die Default Route localhost:8080 gestartet.
Beinhaltet: Search By Name -> Gesucht wird per Typen, Range Jahr von Bis mit Button. Und Top 5 Genres. Die Genres werden dynamisch je nach Daten geladen.
Die Filter funktionieren ausschliesslich einzeln. Alle anderen Search Felder werden zurückgesetzt.


## Reflektion

Die Suchfunktionen Funktionieren. Jedoch ist die Applikation nicht perormant. Es werden noch zu viele Datenbankabfragen durchgeführt. Mit ein wenig mehr Zeit würde ich einmal am Anfang alle Daten laden. Anschliessend die Suchfunktionen ausschliesslich mit Stream Funktionen durchführen. So könnten die Filter auch gleichzeitig verwendet werden. (Durch pipen)

## Datenbank Datei:
Die CSV Datei kann hier geladen werden (https://www.kaggle.com/gregorut/videogamesales?select=vgsales.csv).
