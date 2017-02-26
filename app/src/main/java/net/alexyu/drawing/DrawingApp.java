package net.alexyu.drawing;

import net.alexyu.drawing.model.Canvas;
import net.alexyu.drawing.model.DrawingContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.Scanner;

/**
 * Created by alex on 2/25/17.
 */
public class DrawingApp {

    private final Scanner in = new Scanner(System.in, Charset.defaultCharset().name());

    private DrawingContext context;
    private CommandExecutor executor;

    public DrawingApp() {
        context = new DrawingContext();
        executor = new CommandExecutor(context);
    }

    public static void main(String[] args) throws Exception {

        DrawingApp app = new DrawingApp();
        app.run();

    }

    private String getCommand() {
        System.out.print("enter command: ");
        return in.nextLine();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.print("enter command: ");
//        try {
//            return reader.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";
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
            executor.execute(command);

            printResult();
        }
    }
}
