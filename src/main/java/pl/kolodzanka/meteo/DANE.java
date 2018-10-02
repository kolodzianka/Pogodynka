package pl.kolodzanka.meteo;

public enum DANE {


    TEMPERATURA ("Temperatura powietrza: <b>","&deg;C</b><br>"),
    WILGOTNOŚĆ("Wilgotność: <b>", " %</b><br>"),
    OPADY("Rodzaj opadu: <b>", "&oacute;w</b><br>"),
    WIATR("Prędkość wiatru: <b>", "m/s</b><br>");

    String tekstIn;
    String tekstOut;

    DANE(String tekstIn, String tekstOut){
        this.tekstIn = tekstIn;
        this.tekstOut = tekstOut;
    }


}

