import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

        while (moreInput()) {
            double height = getUserHeight();
            double weight = getUserWeight();

            BodyMassIndex bmi = new BodyMassIndex(height, weight);
            bmiData.add(bmi);

            displayBmiInfo(bmi);
        }

        displayBmiStatistics(bmiData);
    }

    private static boolean moreInput(){
        boolean YorN = true;
        String reply = "n";

        while(YorN) {
            System.out.println("Enter 'Y' or 'N'");
            reply = in.nextLine();
            if(reply.equalsIgnoreCase("y") || reply.equalsIgnoreCase("n")){
                YorN = false;
            }
        }
        return reply.equalsIgnoreCase("Y");
    }

    private static double getUserHeight(){
        double height = 0;

        boolean negative = true;
        while(negative) {
            System.out.println("Enter height (only positives value)");
            height = in.nextDouble();
            in.nextLine();
            if(height > 0){
                negative = false;
            }
        }
        return height;
    }

    private static double getUserWeight(){
        double weight = 0;

        boolean negative = true;
        while(negative) {
            System.out.println("Enter weight (only positives value)");
            weight = in.nextDouble();
            in.nextLine();
            if(weight > 0){
                negative = false;
            }
        }
        return weight;
    }

    private static void displayBmiInfo(BodyMassIndex bmi){
        double score = bmi.score();
        System.out.println("BMI is "+ score);
        System.out.println("BMI category is "+ bmi.category(score));
    }

    private static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData){
        int size = bmiData.size();
        double sum = 0;

        for(int i = 0; i < size; i++){
            BodyMassIndex Bmi = bmiData.get(i);
            sum += Bmi.score();
        }
        double round = Math.pow(10,1);
        System.out.println("Average BMI " + Math.round((sum/size)*round)/round);
    }
}
