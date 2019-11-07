package algorithms.search;

import algorithms.Algorithm;

import java.util.*;
//import Client;

public class BFS implements Algorithm {
    Queue<State> queue;
    Stack<String> finalRoute; // UP, DOWN, LEFT, RIGHT
    static int round;
    State goalState;
    Set<State> visited;

    public BFS() {
        queue = new LinkedList<>();
        finalRoute = new Stack<>();
        visited = new LinkedHashSet<State>();
        round = 1;
    }

    @Override
    public String makeMove(String[][] grid) {
        if (round == 1) {
            State cur = new State(grid, null, null, 1);
            goalState = Helper.goalMaker(grid.length);
            queue.add(cur);
            Helper.addToGeneratedStates(cur);
            State ans = solve();
            findRoute(ans);
            round++;
        }
        //System.out.println(finalRoute.peek());
        return finalRoute.pop();
    }

    private State solve() {
        State thisState;
        while (!queue.isEmpty()) {
            thisState = queue.poll();
            visited.add(thisState);
            //thisState.printState(); //debugging purposes
            if (thisState.equals(goalState)) {
                return thisState;
            }
            thisState.fillAdj();
            for (int i = 0; i < 4; i++) {
                State temp = thisState.adj[i];
                if (!visited.contains(temp)) {
                    visited.add(temp);
                    queue.add(temp);
                }
            }
        }
        return null;
    }

    private void findRoute(State ans) {
        while (ans.parent != null) {
            finalRoute.add(ans.action);
            ans = ans.parent;
        }
    }
}
