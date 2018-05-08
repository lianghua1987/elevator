package com.nytimes.subscription.platform.interview;

import com.nytimes.subscription.platform.interview.pojo.Result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ModelB implements Model {

    @Override
    public void operate(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line = "";

            System.out.println("Output:");

            while ((line = reader.readLine()) != null) {
                parse(line).print();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public Result parse(String line) {
        Result result = new Result();

        int index = line.indexOf(":");
        int start = Integer.parseInt(line.substring(0, index));
        String[] requests = line.substring(index + 1).split(",");

        int sum = 0;

        Direction previousDirection = null;
        Direction currentDirection = null;
        Set<Integer> set = new HashSet<>();
        int prevDest = -1;
        List<Integer> resultList = new LinkedList<>();
        for (int i = 0; i < requests.length; i++) {
            String[] req = requests[i].split("-");
            int source = Integer.parseInt(req[0]);
            int dest = Integer.parseInt(req[1]);

            if (i == 0) {
                previousDirection = getDirection(start, source);
                currentDirection = getDirection(source, dest);
            } else {
                currentDirection = getDirection(source, dest);
            }


            if (previousDirection == currentDirection) {
                if (i == 0) {
                    set.add(start);

                }
                set.add(source);
                set.add(dest);

                previousDirection = currentDirection;
                prevDest = dest;
                continue;
            } else {

                if (i == 0) {
                    set.add(start);
                    set.add(source);
                }

                //resultList.addAll(print(previousDirection, set));
                add(resultList, print(previousDirection, set));
                set = new HashSet<>();
                if (i != 0 && prevDest != source) {
                    set.add(source);
                }

                set.add(dest);
                previousDirection = currentDirection;
                prevDest = dest;
            }

        }

        if (!set.isEmpty()) {
            //resultList.addAll();
            add(resultList, print(currentDirection, set));
        }

        result.setOrder(resultList);

        //resultList.stream().map(t -> t + " ").forEach(System.out::print);

        for (int i = 0; i < resultList.size() - 1; ) {
            sum += Math.abs(resultList.get(i) - resultList.get(++i));
        }
        result.setSum(sum);

        //System.out.println("(" + sum + ")");


        return result;
    }

    private void add(List<Integer> resultList, Set<Integer> set) {
        if (!resultList.isEmpty() && resultList.get(resultList.size() - 1) == set.stream().findFirst().get()) {
            resultList.remove(resultList.size() - 1);
        }
        resultList.addAll(set);
    }

    private Set<Integer> print(Direction currentDirection, Set<Integer> set) {
/*        int last = 0;
        if (!resultSet.isEmpty()) {
            last = resultSet.get(resultSet.size() - 1);
        }*/

        Set<Integer> resultSet = new LinkedHashSet<>();

/*        int max = set.stream().max(Comparator.comparing(Integer::valueOf)).get();
        int min = set.stream().min(Comparator.comparing(Integer::valueOf)).get();*/
        if (currentDirection == Direction.DOWN) {
            set.stream().sorted(Collections.reverseOrder()).forEach(e -> resultSet.add(e));
        } else {
            set.stream().forEach(e -> resultSet.add(e));
        }
        return resultSet;
        // return  (max - min);
    }

    private Direction getDirection(int start, int end) {
        if (start > end) {
            return Direction.DOWN;
        } else if (start < end) {
            return Direction.UP;
        } else {
            return Direction.STOP;
        }
    }

    public enum Direction {
        UP, DOWN, STOP
    }
}
