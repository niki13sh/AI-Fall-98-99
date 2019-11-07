package algorithms.search;

public class State {
    String[][] state;
    //boolean visited;
    State[] adj; // 0 = UP, 1 = Down, 2 = Left, 3 = Right
    int[] emptyIndx;
    State parent;
    String action;
    int lvl, h, f;

    State(String[][] grid, State parent, String action, int lvl) {
        state = new String[3][3];
        Helper.arraycopy(grid, state);
        //visited = false;
        emptyIndx = Helper.getCell(state, " ");
        adj = new State[4];
        this.parent = parent;
        this.action = action;
        this.lvl = lvl;
        h = scoreThis(grid);
        f = lvl + h;
    }

    public void fillAdj() {
        String[][] temp = new String[3][3];
        Helper.arraycopy(state, temp);
        adj[0] = new State(Helper.moveUp(temp, emptyIndx), this, "Up", lvl + 1);
        Helper.arraycopy(state, temp);
        adj[1] = new State(Helper.moveDown(temp, emptyIndx), this, "Down", lvl + 1);
        Helper.arraycopy(state, temp);
        adj[2] = new State(Helper.moveLeft(temp, emptyIndx), this, "Left", lvl + 1);
        Helper.arraycopy(state, temp);
        adj[3] = new State(Helper.moveRight(temp, emptyIndx), this, "Right", lvl + 1);

        for (int i = 0; i < 4; i++)
            adj[i] = Helper.addToGeneratedStates(adj[i]);


    }

    public void printState() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(state[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("----------");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof State) {
            State s = (State) obj;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!this.state[i][j].equals(s.state[i][j]))
                        return false;
                }
            }
            return true;
        }
        return false;
    }

    private int scoreThis(String[][] state) {
        //int k = 1;
        int score = 0;
        for (int i = 0; i < 8; i++) {
            int[] indx = Helper.getCell(state, Integer.toString(i + 1));
            int[] cindx = Helper.getCorrectPlace(state, Integer.toString(i + 1));
            score += Math.abs(cindx[0] - indx[0]) + Math.abs(cindx[1] - indx[1]);
        }
        int[] indx = Helper.getCell(state, " ");
        int[] cindx = Helper.getCorrectPlace(state, " ");
        score += Math.abs(cindx[0] - indx[0]) + Math.abs(cindx[1] - indx[1]);

        return score;
    }
}
