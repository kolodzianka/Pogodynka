package pl.kolodzanka.meteo;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class Meteo {



    public static void main(String[] args) throws IOException, URISyntaxException {

        ManySources sources = new ManySources();


        List list = sources.makeUriList();
        List list2 = sources.convertUriListToResponse(list);

        List temp = getDaneFromDifferentStations(list2, DANE.TEMPERATURA);
        System.out.println(temp);
        System.out.println(averageData(temp));

        List wilgotnosc = getDaneFromDifferentStations(list2, DANE.WILGOTNOŚĆ);
        System.out.println(wilgotnosc);
        System.out.println(averageData(wilgotnosc));

        List wiatr = getDaneFromDifferentStations(list2, DANE.WIATR);
        System.out.println(wiatr);
        System.out.println(averageData(wiatr));

        List opady = getDaneFromDifferentStations(list2, DANE.OPADY);
        System.out.println(opady);
        System.out.println(averageData(opady));
    }

    public static String getDane (DANE dane, String source){
        if( dane == null || StringUtils.isBlank(dane.tekstIn) || StringUtils.isBlank(dane.tekstOut)
                || StringUtils.isBlank(source)){
            throw new IllegalArgumentException("Illegal data!");
        }
        return StringUtils.substringBetween(source,dane.tekstIn, dane.tekstOut);
    }

    public static List getDaneFromDifferentStations (List <String> urlList, DANE dane) {

        List<String> daneFromDifferentStations = urlList.stream()
                .map((u) -> getDane(dane,u ))
                .collect(Collectors.toList());

        return daneFromDifferentStations;
    }

    public static Double averageData (List<String> list){

        
        Double result= 0.0;

        for (int i = 0; i< list.size() ;i++){
            result = result + Double.parseDouble(Optional.ofNullable(list.get(i)).orElse("0.0"));
        }
        result = result/list.size();

        return  result;
        }
        else {
            System.out.println("is not digit!");
            return 0.0;
        }
    }

}
