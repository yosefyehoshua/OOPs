package ShapesDecoratorExemple;

/**
 * Created by Yosef.Yehoshua on 28/07/2016.
 */
public class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(Shape DecoratedShape) {
        super(DecoratedShape);
    }
    @Override
    public void draw(){
        DecoratedShape.draw();
        drawRedBorder(DecoratedShape);
    }

    private void drawRedBorder(Shape decoratedShape) {
        System.out.println("red boarder");
    }
}
