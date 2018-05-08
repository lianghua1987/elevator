package com.nytimes.subscription.platform.interview;

import com.nytimes.subscription.platform.interview.pojo.Result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ModelA implements Model {

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

        //System.out.print(start + " ");
        List<Integer> list = new LinkedList<>();
        list.add(start);
        int sum = 0;
        for (String request : requests) {
            String[] req = request.split("-");
            int source = Integer.parseInt(req[0]);
            sum += Math.abs(start - source);
            int dest = Integer.parseInt(req[1]);
            //System.out.print((source == start ? "" : (source + " ")) + dest + " ");
            if (source != start) {
                list.add(source);
                list.add(dest);
            }
            sum += Math.abs(dest - source);
            start = dest;

        }
        result.setSum(sum);
        result.setOrder(list);

        return result;
        //System.out.println("(" + sum + ")");

    }

}
