package ShapesDecoratorExemple;

import java.io.Reader;

/**
 * Created by Yosef.Yehoshua on 28/07/2016.
 */
public abstract class ShapeDecorator implements Shape {

    Shape DecoratedShape;
    public ShapeDecorator(Shape DecoratedShape){
        this.DecoratedShape = DecoratedShape;
    }
    @Override
    public void draw() {
        DecoratedShape.draw();
    }
}
