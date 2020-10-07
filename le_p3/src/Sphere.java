public class Sphere extends Shape3D{
    private double r;
    public Sphere(double r){
        this.r = r;

    }

    @Override
    public double getArea() {
        return 4*Math.PI*Math.pow(r,2);
    }

    @Override
    public String getName() {
        return "sphere";
    }

    @Override
    public double getVolume() {
        return (4*Math.PI*Math.pow(r,3))/3;
    }
}
