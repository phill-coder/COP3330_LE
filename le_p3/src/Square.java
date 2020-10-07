public class Square extends Shape2D {
    private double area;

    public Square(double area){
        this.area = area;
    }

    @Override
    public String getName(){
        return "square";
    }
    @Override
    public double getArea() {
        return area*area;
    }

}
