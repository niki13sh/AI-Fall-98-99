package algorithms.search;

import algorithms.Algorithm;

import java.util.LinkedHashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class AStar implements Algorithm {

    private static final StateComparator sc = new StateComparator();
    PriorityQueue<State> pq;
    Stack<String> finalRoute; // UP, DOWN, LEFT, RIGHT
    static int round;
    State goalState;
    Set<State> visited;

    public AStar() {
        pq = new PriorityQueue<>(sc);
        finalRoute = new Stack<>();
        visited = new LinkedHashSet<State>();
        round = 1;
    }

    @Override
    public String makeMove(String[][] grid) {

        if (round == 1) {
            State cur = new State(grid, null, null, 1);
            goalState = Helper.goalMaker(grid.length);
            pq.add(cur);
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
        while (!pq.isEmpty()) {
            thisState = pq.poll();
            visited.add(thisState);
//            System.out.println("h = " + thisState.h + ", lvl = " + thisState.lvl + ", f = " + thisState.f);
//            thisState.printState(); //debugging purposes
            if (thisState.equals(goalState)) {
                return thisState;
            }
            thisState.fillAdj();
            for (int i = 0; i < 4; i++) {
                State temp = thisState.adj[i];
                if (!visited.contains(temp)) {
                    visited.add(temp);
                    pq.add(temp);
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
