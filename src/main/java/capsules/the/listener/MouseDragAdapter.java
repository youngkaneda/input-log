package capsules.the.listener;

import javax.swing.JFrame;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseDragAdapter extends MouseAdapter {

    private Point pressed;
    private final JFrame window;
    private boolean pressedHitBorder;

    public MouseDragAdapter(JFrame window) {
        super();
        this.window = window;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = e.getPoint();
        // this is not done in mousedragged because when the height of window changes, the pressed position will
        // not hit the new bottomborder position, so this is held here.
        pressedHitBorder = mouseHitBottomBorder(e.getLocationOnScreen());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (pressedHitBorder) {
            if (e.getPoint().y >= 38) {
                window.setSize(window.getWidth(), e.getPoint().y);
            }
            return;
        }
        moveWindowBy(new Point(
            (e.getX() - pressed.x),
            (e.getY() - pressed.y)
        ));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        boolean hit = mouseHitBottomBorder(e.getLocationOnScreen());
        if (hit) {
            window.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
        } else {
            window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    private boolean mouseHitBottomBorder(Point point) {
        boolean hitBottom = mouseHitLine(
            point,
            new Point((int) window.getBounds().getMinX(), (int) window.getBounds().getMaxY()),
            new Point((int) window.getBounds().getMaxX(), (int) window.getBounds().getMaxY()),
            5
        );
        return hitBottom;
    }

    private boolean mouseHitLine(Point point, Point start, Point end, int margin) {
        if (point.x > end.x + margin || point.x < start.x - margin) {
            return false;
        }
        if (point.y > end.y + margin || point.y < start.y - margin) {
            return false;
        }
        return true;
    }

    private void moveWindowBy(Point point) {
        window.setLocation(
            window.getLocationOnScreen().x + point.x,
            window.getLocationOnScreen().y + point.y
        );
    }
}