
public class BodyMassIndex{
    double height;
    double weight;

    public BodyMassIndex( double height, double weight){
        this.height = height;
        this.weight = weight;
    }

    public double score(){
        double round = Math.pow(10,1);
        return (Math.round(((703 * weight)/ Math.pow(height,2))*round)/round);
    }

    public String category(double bmi){
        if(bmi < 18.5){
            return "Under Weight";
        }
        if(bmi >= 18.5 && bmi <= 24.9 ){
            return "Normal Weight";
        }
        if(bmi >= 25 && bmi <= 29.9 ){
            return "Overweight";
        }
        else
            return "Obese";
        }
}

