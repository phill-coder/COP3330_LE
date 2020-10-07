public class Pyramid extends Shape3D{
    private double x;
    private double y;
    private double z;
    public Pyramid(double x,double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public double getArea() {
        return (x * y) + (x * Math.sqrt(Math.pow(y / 2, 2) +
                Math.pow(z, 2))) + (y * Math.sqrt(Math.pow(x / 2, 2) + Math.pow(z, 2)));
    }

    @Override
    public String getName() {
        return "pyramid";
    }

    @Override
    public double getVolume() {
        return ((x*y)*z)/3;
    }
}
