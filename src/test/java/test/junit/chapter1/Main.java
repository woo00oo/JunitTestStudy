package test.junit.chapter1;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = (Integer.parseInt(br.readLine()));
        }

        fastTime(arr);
    }

    private static int fastTime(int[] inputs) {
        LinkedList<Integer> dest = new LinkedList<>();
        LinkedList<Integer> source = new LinkedList<>();

        Arrays.sort(inputs);
        for (int i = 0; i < inputs.length; i++) {
            source.add(inputs[i]);
        }

        int takeTime = move(source, dest, 0);

        System.out.println(takeTime);

        return takeTime;
    }


    private static int move(LinkedList<Integer> source, LinkedList<Integer> dest, int time) {
        return fromSourceToDest(source, dest, time, true);
    }

    /**
     * 출발지에서 도착지로 사람을 보냄
     */
    private static int fromSourceToDest(LinkedList<Integer> source, LinkedList<Integer> dest, int takeTime, boolean fast) {

        //출발할 사람이 없으면 종료
        if (source.size() == 0) {
            return takeTime;
        }

        //출발선에 1명만 있으면 가장 빠른,느린 사람을 구할 필요가 없다.
        if (source.size() == 1) {
            int time = source.poll();
            dest.add(time);
            takeTime += time;
        }

        //가장 빠른 사람을 보낸다.
        if (fast) {
            //source를 sort했기 때문에 맨 앞이 가장 빠른 사람들이다.
            int first = source.poll();
            int second = source.poll();

            //2사람을 도착지에 보낸다. 앞에서 부터 꺼낼때 가장 빠른 사람이 나올 수 있도록 두번째 부터 넣는다.
            dest.addFirst(second);
            dest.addFirst(first);

            takeTime += Math.max(first, second);
        } else {
            //가장 느린 사람을 보낸다.
            int first = source.pollLast();
            int second = source.pollLast();

            dest.addLast(first);
            dest.addLast(second);

            takeTime += Math.max(first, second);

        }

        //도착지에서 출발지로 한사람이 손전등을 들고 간다.;
        return fromDestToSourceFastestPerson(source, dest, takeTime, fast);
    }

    /**
     * 도착지에서 출발지로 가장 빠른 사람을 도착지로 보낸다. (후레시를 들고 돌아가줄 사람)
     */
    private static int fromDestToSourceFastestPerson(LinkedList<Integer> source, LinkedList<Integer> dest, int takeTime, boolean fast) {

        //출발선에 사람이 없거나 1명이면 도착지에서 후레쉬 들고갈 사람을 보낼 필요가 없다.
        if (source.size() == 0) {
            return takeTime;
        }

        //도착지에서 가장 빠른 사람으로 손전등을 주고 출발선으로 보낸다.
        int time = dest.pollFirst();
        source.addFirst(time);
        takeTime += time;

        //이전에 출발지에서 가장 빠른 사람을 보냈었다면 다음에는 가장 느린 사람을 보낸다.
        return fromSourceToDest(source, dest, takeTime, !fast);
    }
}
