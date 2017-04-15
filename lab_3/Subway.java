/**
 * Name			: Jiachao
 * Matric. No	: A155568R
 * PLab Acct.	:
 */

import java.util.*;

public class Subway {

    private static LinkedList<Station>  network= new LinkedList<Station>();

    public Subway() {
        //constructor
    }

    public void run() {
        //implement your "main" method here
        Scanner sc = new Scanner(System.in);

        int numStations = sc.nextInt();

        while(numStations-- > 0)
            network.addLast( new Station( sc.next() ));

        int numQueries = sc.nextInt();

        while(numQueries-- >0){
            switch (sc.next()){
                case "TIME":
                    time(sc);
                    break;
                case "BUILD":
                    build(sc);
                    break;
                case "PATH":
                    path(sc);
                    break;
                case "PRINT":
                    print(sc);
                    break;
                default:
                    break;
            }
        }

    }   

    public static void main(String[] args) {
        Subway newSubwayNetwork = new Subway();
        newSubwayNetwork.run();
    }

    //returns the station
    public Station getStation(String stationName){
        for (Station station : network){
            if ( station.getName().equals(stationName) )
                return station;
        }
        return null;
    }

    //subway queries
	
	//precon: station names must be valid
    public void time(Scanner sc){	
        String startName = sc.next();
        String endName = sc.next();

        Station start = getStation(startName);
        Station end = getStation(endName);

        int startIndex = network.indexOf(start);
        int endIndex = network.indexOf(end);
        int difference = Math.abs(endIndex-startIndex);
        //time taken for forward direction
        int tFor = ( ( difference-1)*3 ) +2;

        //time taken for reverse direction
        int tRev = ( (network.size()- difference-1 )*3) +2;

        if(startName.equals(endName)){
            System.out.println(0);
        }else if (tFor<=tRev){
            System.out.println(tFor);
        }else{ 
            System.out.println(tRev);
        }

    }
	//precon: the existing station must ve valid and the new station name must be unique
    public void build(Scanner sc){
        Station preStation = getStation(sc.next());
        String newStationName = sc.next();
        int preIndex = network.indexOf(preStation);

        network.add(preIndex+1, new Station(newStationName) );
        System.out.println("station " + newStationName + " has been built");
    }
	
	//precon:station names bust be valid
    public void path(Scanner sc){
        String startName = sc.next();
        String endName = sc.next();

        Station start = getStation(startName);
        Station end = getStation(endName);

        int startIndex = network.indexOf(start);
        int endIndex = network.indexOf(end);
        int difference = (endIndex-startIndex);
        //time taken for forward direction
        int tFor = ( ( Math.abs(difference)-1)*3 ) +2;

        //time taken for reverse direction
        int tRev = ( (network.size()- Math.abs(difference)- 1 )*3) +2;
        
        //DEBUGGING System.out.println("start index = "+ startIndex + " endIndex = "+ endIndex+ " difference = "+ difference +" tFor = "+ tFor+" tRev = "+ tRev );
        //prints the shortest path
        if(startName.equals(endName)){
            System.out.println(startName);//the no travel case
        }
		//(forward is faster or same speed && subway is moving in the forward direction) 
		//or (reverse is faster or same speed && subway is moving in the opposite direction)
        else if((tFor<=tRev && difference>0)||(tRev<=tFor && difference<0)){
            while(endIndex!=startIndex){//print forward
                System.out.print(network.get(startIndex).getName() + " ");
                startIndex = (startIndex +1)%network.size();
            }
            System.out.println(endName);
        }
        else { //reverse is faster
            while(startIndex!=endIndex){//print reverse
                System.out.print(network.get(startIndex).getName() + " ");
                if (startIndex == 0)
                    startIndex = network.size()-1;
                else
                    startIndex--;
            }
            System.out.println(endName);
        }


    }
	
	//precon: station names must be valid
    public void print(Scanner sc){
        String stationName = sc.next();

        Station station = getStation(stationName);
        int startIndex = network.indexOf(station);
        int endIndex= startIndex-1;
        
        if (startIndex==0)
            endIndex= network.size()-1;

        if (network.size()==1){
            System.out.println(stationName);
            return; //then print once and end
        }

        while (startIndex!=endIndex){
            System.out.print(network.get(startIndex).getName() + " ");
            startIndex= (startIndex+1)% network.size();//updates makes it a cirlular linked list
        }
        System.out.println(network.get(endIndex).getName());
    }

}

class Station{

    private String name;

    public Station(String name){
        this.name= name;
    }

    public String getName(){
        return name;
    }
}
