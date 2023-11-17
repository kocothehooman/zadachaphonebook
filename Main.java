import java.io.*;
import java.util.*;

public class Main{
    static Scanner keeb = new Scanner(System.in);
    static Scanner keeb3 = new Scanner(System.in);
    public static void menu(){
        System.out.println("-------MENU-------");
        System.out.println("1. Add element");
        System.out.println("2. Remove element");
        System.out.println("3. Find phone by name");
        System.out.println("4. Print sorted by name");
        System.out.println("5. Exit");

    }
    public static void add(ArrayList<PhoneEntry> arr){
        System.out.println("New name entry?");
        String nname = keeb.nextLine();
        String nphone;
        do {
            System.out.println("New phone entry?");
            nphone = keeb.nextLine();
            if(!nphone.matches("(\\+359|0|00359)8[7-9][2-9]\\d\\d\\d\\d\\d\\d")){
                System.out.println("Greshna forma na telefona");
            }
        }while(!nphone.matches("(\\+359|0|00359)8[7-9][2-9]\\d\\d\\d\\d\\d\\d"));
        arr.add(new PhoneEntry(nname,nphone));
        }
        public static void remove(ArrayList<PhoneEntry> arr){
        System.out.println("Koe ime da mahna?");
        String ndel = keeb.nextLine();
        for(int i = 0;i<arr.size();i++){
            if(ndel.matches(arr.get(i).name)){
                arr.remove(i);
            }
        }

    }
    public static void find(ArrayList<PhoneEntry> arr){
        System.out.println("Which name's phone should i find?");
        String phon = null;
        String search = keeb.nextLine();
        for(int i = 0;i<arr.size();i++){
            if(search.matches(arr.get(i).name)){
                phon = arr.get(i).phone;
            }
        }
        if(phon != null) {
            System.out.println(search + "'s phone is " + phon);
        }
    }
    public static void printSort(ArrayList<PhoneEntry> arr){
        int n = arr.size();
        int min;
        for(int i = 0;i<n;i++){
            min = i;
            for(int j = i+1;j<n;j++){
                if(arr.get(i).name.compareToIgnoreCase(arr.get(min).name) < 0){
                    min = i;
                }
            }
            PhoneEntry temp = arr.get(i);
            arr.set(i,arr.get(min));
            arr.set(min,temp);
        }
        for(PhoneEntry i : arr){
            System.out.println(i.name + " " + i.phone);
        }
    }
    public static void main(String[] args){
            File fille = new File("nomera.txt");
            Scanner fileread;
        try {
            fileread = new Scanner(fille);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<PhoneEntry> phones = new ArrayList<>();
        while (fileread.hasNextLine()){
            String tempname = fileread.nextLine();
            String[] split = tempname.split("-");
            split[1] = split[1].replaceAll(" ","");

            boolean test = split[1].matches("(\\+359|0|00359)8[7-9][2-9]\\d\\d\\d\\d\\d\\d");
                if(test) {
                    split[1] = split[1].replaceAll("\\+359","08");
                    split[1] = split[1].replaceAll("00359","08");
                    phones.add(new PhoneEntry(split[0],split[1]));
                }
        }
        int choice;
        do {
            menu();
            choice = keeb3.nextInt();
            switch(choice){
                case 1:
                add(phones);
                break;
                case 2:
                remove(phones);
                break;
                case 3:
                find(phones);
                break;
                case 4:
                printSort(phones);
                break;
                default:
                    System.out.println("Priqten den!");
                    choice = -1;
            }
            }while(choice != -1);
        /*for(int i = 0; i < phones.size();i++) {
            System.out.println(phones.get(i).name + " " + phones.get(i).phone);
        }

        //add element
        //remove element by name
        //find phone for name
        //print sorted by name */
    }
}