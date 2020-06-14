package capsules.the.listener;

import javax.swing.JFrame;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseDragAdapter extends MouseAdapter {

    private Point pressed;
    private JFrame window;

    public MouseDragAdapter(JFrame window) {
        super();
        this.window = window;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        moveWindowBy(new Point(
            (e.getX() - pressed.x),
            (e.getY() - pressed.y)
        ));
    }

    private void moveWindowBy(Point point) {
        window.setLocation(
            window.getLocationOnScreen().x + point.x,
            window.getLocationOnScreen().y + point.y
        );
    }
}
