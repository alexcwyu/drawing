package net.alexyu.drawing.model;

import java.util.Optional;

/**
 * Created by alex on 2/26/17.
 */
public class DrawingContext {

    private Canvas canvas;
    private State state;

    public DrawingContext() {
        state = State.Running;
    }

    public void updateCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Optional<Canvas> getCanvas() {
        return Optional.ofNullable(canvas);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum State {Running, Stop}
}
