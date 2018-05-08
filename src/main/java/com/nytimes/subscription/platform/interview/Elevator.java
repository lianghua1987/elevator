package com.nytimes.subscription.platform.interview;

import java.io.File;
import java.util.Scanner;

public class Elevator {


    public static void main(String[] args) {

        while (true) {
            System.out.println("Please select model:\na: ModelA\nb: ModelB\nd:Default test file runs both ModelA and ModelB\nq: Exit");
            Scanner sc = new Scanner(System.in);

            switch (sc.next().toLowerCase()) {
                case "a":
                    executeModel(new ModelA());
                    break;
                case "b":
                    executeModel(new ModelB());
                    break;
                case "d":
                    new ModelA().operate(new File(Elevator.class.getClassLoader().getResource("input.txt").getFile()));
                    new ModelB().operate(new File(Elevator.class.getClassLoader().getResource("input.txt").getFile()));
                    break;
                case "q":
                    System.exit(1);
                default:
            }
        }

    }


    public static void executeModel(Model model) {
        System.out.println("Please type file absolute path:");
        while (true) {
            Scanner sc = new Scanner(System.in);
            File file = new File(sc.next());
            if (!file.exists()) {
                System.out.println("Please validate your file path and try again:");
                continue;
            }

            model.operate(file);
            return;

        }

    }


}
