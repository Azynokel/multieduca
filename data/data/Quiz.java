/*
* Autoren: Niklas, Felix, Till, Alexander H.
* Thema: Datentyp zur Repraesentation von Quizzen
* Erstellungsdatum: 09.02.2023
* Letzte Aenderung: 06.03.2023 
* Change-log:
*  09.02: Importerung aller wesemtlicher Klassen
*         Implementierung der Funktion 'save' 
*         Implementierung der Konstruktoren 
*         Implementierung der Funktion 'genPunkte'
*           - Niklas, Felix, Till, Alexander H.
*  14.02: Implementierung der Funktionen 'addFrage' und 'getFrage'
*           - Niklas, Felix
*  16.02: Implementierung der Funktion 'getLength'
*           - Niklas
*  06.03: Ueberarbeitung der Funktion 'genPunkte' 
*         Beruecksichtigt nun die Mehrfachauswahl von Antworten
*           - Felix
*/

package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

// import von den Datentypen JSONArray und JSONObjact zur Repraesentation der im Quiz gespeicherten Daten im JSON-Format.
import org.json.JSONArray;
import org.json.JSONObject;

public class Quiz {
    // Parameter zum Speichern aller im Quiz enthaltenen Fragen als JSONArray (Ein Quizz besteht aus fragen)
    private JSONArray fragen;

    // Konstruktor zum erstellen eines leeren Quiz 
    public Quiz() {
        fragen = new JSONArray();
    }

    // Konstruktor zum Erstellen eines Quiz' aus einem vorhandenen JSON-Dokument
    public Quiz(File quizDatei) {
        try {
            // Wenn Vorhanden, wird aus dem eingegebenen Dokument der Inhalt ausgelesen und im String JSONtext gespeichert
            String JSONtext = new String(Files.readAllBytes(Paths.get(quizDatei.getPath())), StandardCharsets.UTF_8);
            // Aus dem String JSONtext wird ein JSONArray generiert, der im Parameter fragen gespeichert wird
            fragen = new JSONArray(JSONtext);
        } catch (IOException e) {
            // Fehlermanagement
            System.err.println("Fehler beim Laden der Quizdatei");
            e.printStackTrace();
        }
    }

    public void addFrage(String text, String[] antworten, int[] loesungen, int zeit) {
        JSONObject frage = new JSONObject();
        frage.put("text", text);
        frage.put("antworten", new JSONArray(antworten));
        frage.put("loesungen", new JSONArray(loesungen));
        frage.put("zeit", zeit);
        fragen.put(frage);
    }

    public void save(File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fragen.write(fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getFrage(int index) {
        return (JSONObject) fragen.get(index);
    }

    public int getLength() {
        return fragen.length();
    }

    //funktion zur Punktberechnung
    //Erstellt von Felix 06-03
    public static int genPunkte(JSONObject frage, int[] eingaben, double antwortZeit) {
        double output = 0;
        int maxZeit = frage.getInt("zeit");
        int nAntworten = frage.getJSONArray("antworten").length();
        JSONArray loesungen = frage.getJSONArray("loesungen");
        double punkteProRichtigeAntwort = (100 - (Math.pow(antwortZeit, 2) / Math.pow(maxZeit, 2) * 50)) / nAntworten;
        for (int i = 0; i < nAntworten; i++) {

            boolean istRichtig = false;
            for (Object loesung : loesungen) {
                if (i == (int) loesung) {
                    istRichtig = true;
                }
            }

            boolean wurdeAusgewaehlt = false;
            for (int j = 0; j < eingaben.length; j++) {
                if (i == eingaben[j]) {
                    wurdeAusgewaehlt = true;
                }
            }

            if ((istRichtig & wurdeAusgewaehlt) || (!istRichtig & !wurdeAusgewaehlt)) {
                output += punkteProRichtigeAntwort;
            }
        }
        return (int) output;
    }

}
