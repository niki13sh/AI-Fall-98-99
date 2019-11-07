package algorithms.search;

import algorithms.Algorithm;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

public class DFS implements Algorithm {

    Stack<State> stack;
    Stack<String> finalRoute; // UP, DOWN, LEFT, RIGHT
    static int round;
    State goalState;
    Set<State> visited;

    public DFS() {
        stack = new Stack<>();
        finalRoute = new Stack<>();
        visited = new LinkedHashSet<State>();
        round = 1;

    }

    @Override
    public String makeMove(String[][] grid) {
        if (round == 1) {
            State cur = new State(grid, null, null, 1);
            goalState = Helper.goalMaker(grid.length);
            stack.push(cur);
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
        while (!stack.isEmpty()) {
            thisState = stack.pop();
            visited.add(thisState);
            // thisState.printState(); //debugging purposes
            //System.out.println(thisState.lvl);
            if (thisState.equals(goalState)) {
                return thisState;
            }
            if (thisState.lvl < 300) {
                thisState.fillAdj();
                for (int i = 0; i < 4; i++) {
                    State temp = thisState.adj[i];
                    if (!visited.contains(temp)) {
                        visited.add(temp);
                        stack.push(temp);
                    }
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
