public class Triangle extends Shape2D {
    private double x;
    private double y;

    public Triangle(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String getName(){
        return "triangle";
    }

    @Override
    public double getArea() {
        return (x*y)/2;
    }
}
