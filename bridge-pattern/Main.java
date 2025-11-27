


public class Main {

    public static void main(String[] args) {
        Renderer vectorRenderer = new VectorRenderer();
        Shape circle1 = new Circle(vectorRenderer, 5, 10, 15);
        circle1.draw();

        Renderer rasterRenderer = new RasterRenderer();
        Shape circle2 = new Circle(rasterRenderer, 20, 30, 25);
        circle2.draw();
    }
}



//creating Renderer interface
interface Renderer {
    void renderCircle(float x, float y, float radius);
}

class VectorRenderer implements Renderer {
    @Override
    public void renderCircle(float x, float y, float radius) {
        System.out.println("Drawing a circle of radius " + radius + " at (" + x + ", " + y + ") using Vector Renderer");
    }
}

class RasterRenderer implements Renderer {
    @Override
    public void renderCircle(float x, float y, float radius) {
        System.out.println("Drawing pixels for a circle of radius " + radius + " at (" + x + ", " + y + ") using Raster Renderer");
    }
}

abstract class Shape {
   protected Renderer renderer;
    public Shape(Renderer renderer) {
         this.renderer = renderer;
    }
    public abstract void draw();
    
}
class Circle extends Shape {
    private float x, y, radius;

    public Circle(Renderer renderer, float x, float y, float radius) {
        super(renderer);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        renderer.renderCircle(x, y, radius);
    }
}

