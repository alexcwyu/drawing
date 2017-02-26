package net.alexyu.drawing;

import net.alexyu.drawing.model.Canvas;
import net.alexyu.drawing.model.DrawingContext;

import java.util.Optional;
import java.util.Scanner;

/**
 * Created by alex on 2/25/17.
 */
public class DrawingApp {

    private final Scanner in = new Scanner(System.in);

    private DrawingContext context;
    private CommandExecutor executor;

    public DrawingApp() {
        context = new DrawingContext();
        executor = new CommandExecutor(context);
    }

    private String getCommand() {
        System.out.print("enter command: ");
        return in.nextLine();
    }

    private void printResult() {
        Optional<Canvas> canvas = context.getCanvas();
        if (canvas.isPresent()) {
            System.out.println(canvas.get());
        }
    }

    private void run() {
        while (context.getState() == DrawingContext.State.Running) {
            String command = getCommand();
            executor.execute(command, context);

            printResult();
        }
    }


    public static void main(String[] args) throws Exception {

        DrawingApp app = new DrawingApp();
        app.run();

    }
}
