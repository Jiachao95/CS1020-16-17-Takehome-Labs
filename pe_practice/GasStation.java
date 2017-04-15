/**
 * Name           : Lin Jiachao 
 * Matric No.     : A015568R
 * PLab Acct.     :
 */

import java.util.*;

public class GasStation {
    private ArrayList<City> cityList = new ArrayList<City>();

    public void run() {
        // treat this as your "main" method

        Scanner sc = new Scanner(System.in);

        int numCities = sc.nextInt();

        for (int i=0; i<numCities; i++){//populates cities
            cityList.add( new City(sc.nextInt(), sc.nextInt()) );
        }

        int distToSG = sc.nextInt();
        cityList.add( new City(distToSG, 0) );      //include the last stretch as part of the journey

        for (City city: cityList){  //checks if can reach or not
            if (city.getDist() >200){
                System.out.println("can meh?");
                return;
            }
        }


        System.out.println( evalBestPrice( 0, 200 - cityList.get(0).getDist(), 0) );

    }

    public static void main(String[] args) {
        GasStation myGasStation = new GasStation();
        myGasStation.run();
    }

    //returns the lowest money spent
    private int evalBestPrice(int cityIndex, int currFuel, int moneySpent){
        //        System.out.println("money spent is " + moneySpent);

        if (cityIndex == cityList.size()-1){        //left the last stretch
            if (currFuel >= cityList.get(cityIndex).getDist() ) //if no need pump
                return moneySpent;  
            else
                return moneySpent + cityList.get(cityIndex).getUnitPrice()*(200-currFuel);   //last top up

        }else if ( currFuel < cityList.get(cityIndex+1).getDist()){    //not possible to make it to next city
            return evalBestPrice( cityIndex+1, 200 - cityList.get(cityIndex+1).getDist(), moneySpent + cityList.get(cityIndex).getUnitPrice()*(200-currFuel) );

        }else{

            int pump = evalBestPrice( cityIndex+1, 200 - cityList.get(cityIndex+1).getDist(), moneySpent + cityList.get(cityIndex).getUnitPrice()*(200-currFuel) );

            int dontPump = evalBestPrice( cityIndex+1, currFuel - cityList.get(cityIndex+1).getDist(), moneySpent );

            return Math.min(pump, dontPump);        //returns the lower of the 2 costs
        }



        /*
           if (cityIndex==cityList.size()-1 ){       //base case: last city alr or no cities exist

           if ( 200< distToSG )//cannot reach
           return -1;
           else if (currFuel >= distToSG )
           return moneySpent;  
           else
           return moneySpent + cityList.get(cityIndex).getUnitPrice()*(200-currFuel);   //last top up

           }else if ( 200 < cityList.get(cityIndex+1).getDist())    //not possible to make it to next city
           return -1;

           else{   //can reach next city

           int pump = evalBestPrice(cityIndex+1, 200-cityList.get(cityIndex+1).getDist(), moneySpent + cityList.get(cityIndex).getUnitPrice()*(200-currFuel), distToSG);
           int dontPump = evalBestPrice(cityIndex+1, currFuel-cityList.get(cityIndex+1).getDist(), moneySpent, distToSG);

           if (pump == -1 && dontPump == -1)//both dead end
           return -1;
           else if (pump==-1)  //pump deadend
           return dontPump;
           else if (dontPump==-1)  //dontPump deadend
           return pump;
           else
           return Math.min(pump, dontPump);

           }

         */
    }

}


class City{

    private int distance; 
    private int unitPrice;

    public City(int distance, int unitPrice){
        this.distance = distance;
        this.unitPrice = unitPrice;
    }

    public int getDist(){
        return distance;
    }

    public int getUnitPrice(){
        return unitPrice;
    }



}
