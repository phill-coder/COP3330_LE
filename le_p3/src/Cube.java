public class Cube extends Shape3D {
    private double area;

    public Cube(double area){
        this.area = area;
    }

    @Override
    public double getArea() {
        return 6*Math.pow(area,2) ;
    }

    @Override
    public String getName() {
        return "cube";
    }

    @Override
    public double getVolume() {
        return Math.pow(area,3);
    }
}
