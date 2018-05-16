package ee.ttu.iti0202.gomoku.strategies;

import ee.ttu.iti0202.gomoku.game.Location;
import ee.ttu.iti0202.gomoku.game.SimpleBoard;
import ee.ttu.iti0202.gomoku.opponent.ComputerStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class StudentStrategy implements ComputerStrategy {

    //Modify these values:
    private final static int searchDepth = 2;
    private final static int depthDangerModifier = 3;
    private final static int noMovesWorth = 0;
    final static int interestingPlayerShapeLength = 2;
    final static int interestingOpponentShapeLength = 3;



    private HashMap<Integer, ArrayList<Shape>> shapes = new HashMap<>();
    private List<Shape> relevantShapes = new ArrayList<>();

    private List<Location> occupiedSquares = new ArrayList<>();

    public SimpleBoard board;

    private int player;

    private static List<List<Shape>> shapeBuffer = new ArrayList<>();
    private boolean firstMove = true;

    public Location getMove(SimpleBoard board, int player) {
        this.board = board;
        this.player = player;

        if (firstMove) {
            this.shapes = Shape.generateShapes(board);
            firstMove = false;
        }

        addRealMoves();

        // Initialize minimax
        Location bestMove = null;
        int bestScore = Integer.MIN_VALUE;


        // Minimax first move
        for (Location move : relevantShapes.parallelStream()
                .flatMap(shape -> shape.getLocations().parallelStream())
                .distinct()
                .filter(location -> !occupiedSquares.contains(location))
                .collect(Collectors.toList())) {

            List<Shape> relevantShapes = new ArrayList<>(this.relevantShapes);
            shapes = updateMap(shapes, move, player);

            if (winningMove()) return move;


            int branchScore = minValue(shapes, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

            shapes = revertMap(shapes, move, player);
            this.relevantShapes = relevantShapes;

            if (branchScore > bestScore) {
                bestScore = branchScore;
                bestMove = move;
            }
        }

        // Plagiate from Ago
        if (bestMove == null) {
            int[][] b = board.getBoard();
            for (int row = 0; row < b.length; row++) {
                for (int col = 0; col < b[0].length; col++) {
                    if (b[row][col] == SimpleBoard.EMPTY) {
                        return new Location(row, col);
                    }
                }
            }
        }

        return bestMove;
    }

    private void addRealMoves() {
        board.getLastMove(player).ifPresent(move ->
        {
            shapes.put(-player, removeShapes(shapes.get(-player), move));
            shapes.put(player, addShapeScores(shapes.get(player), move));

            occupiedSquares.add(move);
        });

        board.getLastMove(-player).ifPresent(move ->
        {
            shapes.put(player, removeShapes(shapes.get(player), move));
            shapes.put(-player, addShapeScores(shapes.get(-player), move));

            occupiedSquares.add(move);
        });

        shapeBuffer.clear();
    }

    private HashMap<Integer, ArrayList<Shape>> updateMap(HashMap<Integer, ArrayList<Shape>> shapes, Location move, int turn) {
        if (turn == player) {
            shapes.put(player, addShapeScores(shapes.get(player), move));
            shapes.put(-player, removeShapes(shapes.get(-player), move));
        } else {
            shapes.put(-player, addShapeScores(shapes.get(-player), move));
            shapes.put(player, removeShapes(shapes.get(player), move));
        }

        occupiedSquares.add(move);

        return shapes;
    }


    // After returning from minimax, revert the map to previous state
    private HashMap<Integer, ArrayList<Shape>> revertMap(HashMap<Integer, ArrayList<Shape>> shapes, Location move, int turn) {
        if (turn == player) {
            shapes.get(-player).addAll(shapeBuffer.remove(shapeBuffer.size() - 1));
        } else {
            shapes.get(player).addAll(shapeBuffer.remove(shapeBuffer.size() - 1));
        }

        List<Shape> removeButtons = shapeBuffer.remove(shapeBuffer.size() - 1);
        removeButtons.forEach(shape -> shape.removeButton(this));

        occupiedSquares.remove(move);

        return shapes;
    }

    // Remove shapes that contain a given location
    private ArrayList<Shape> removeShapes(ArrayList<Shape> shapes, Location location) {
        List<Shape> shapesToRemove = shapes
                .parallelStream()
                .filter(shape -> shape.getLocations().contains(location))
                .collect(Collectors.toList());

        shapes.removeAll(shapesToRemove);

        relevantShapes.removeAll(shapesToRemove);

        shapeBuffer.add(shapesToRemove);

        return shapes;
    }

    // Increase button count for all shapes that contain given location
    private ArrayList<Shape> addShapeScores(ArrayList<Shape> shapes, Location location) {
        List<Shape> shapesToSave = shapes
                .parallelStream()
                .filter(shape -> shape.getLocations().contains(location))
                .collect(Collectors.toList());

        shapeBuffer.add(shapesToSave);

        shapesToSave.forEach(shape -> shape.addButton(this));

        return shapes;
    }

    // Search for winning move
    private boolean winningMove() {
        return shapes.values()
                .parallelStream()
                .flatMap(Collection::parallelStream)
                .anyMatch(shape -> shape.getButtons() == 5);
    }

    // Minimax min part
    private int minValue(HashMap<Integer, ArrayList<Shape>> shapes, int depth, int alpha, int beta) {
        if (depth >= searchDepth || winningMove()) {
            return scanShapes(depth);
        }

        int bestScore = Integer.MAX_VALUE;

        for (Location move : relevantShapes.parallelStream()
                .flatMap(shape -> shape.getLocations().parallelStream())
                .distinct()
                .filter(location -> !occupiedSquares.contains(location))
                .collect(Collectors.toList())) {


            List<Shape> relevantShapes = new ArrayList<>(this.relevantShapes);
            shapes = updateMap(shapes, move, -player);
            int branchScore = maxValue(shapes, depth + 1, alpha, beta);
            shapes = revertMap(shapes, move, -player);
            this.relevantShapes = relevantShapes;

            if (branchScore < bestScore) {
                bestScore = branchScore;
            }

            if (branchScore <= alpha) {
                return bestScore;
            }

            if (branchScore < beta) {
                beta = branchScore;
            }
        }

        if (relevantShapes.size() == 0) return -noMovesWorth;

        return bestScore;
    }


    // Minmax max part
    private int maxValue(HashMap<Integer, ArrayList<Shape>> shapes, int depth, int alpha, int beta) {
        if (depth >= searchDepth || winningMove()) {
            return scanShapes(depth);
        }


        int bestScore = Integer.MIN_VALUE;

        for (Location move : relevantShapes.parallelStream()
                .flatMap(shape -> shape.getLocations().parallelStream())
                .distinct()
                .filter(location -> !occupiedSquares.contains(location))
                .collect(Collectors.toList())) {


            List<Shape> relevantShapes = new ArrayList<>(this.relevantShapes);
            shapes = updateMap(shapes, move, player);
            int branchScore = minValue(shapes, depth + 1, alpha, beta);
            shapes = revertMap(shapes, move, player);
            this.relevantShapes = relevantShapes;

            if (branchScore > bestScore) {
                bestScore = branchScore;
            }

            if (branchScore >= beta) {
                return bestScore;
            }

            if (branchScore > alpha) {
                alpha = branchScore;
            }
        }

        if (relevantShapes.size() == 0) return noMovesWorth;

        return bestScore;
    }

    // Calculate score of table
    private int scanShapes(int depth) {
        int stateScore = 0;
        int dangerModifier = ((searchDepth - depth) * depthDangerModifier) + 1;

        for (Shape shape : relevantShapes.parallelStream().filter(shape -> shape.getButtons() >= 3).collect(Collectors.toList())) {
            stateScore += (shape.getScore(this) * dangerModifier);
        }

        return stateScore;
    }

    public int getPlayer() {
        return player;
    }

    List<Shape> getRelevantShapes() {
        return relevantShapes;
    }

    public String getName() {
        return "Bossmaster";
    }

}

class Shape implements Cloneable {

    enum Orientation {
        Vertical,
        Horizontal,
        DiagonalLeftUp,
        DiagonalLeftDown
    }

    private Integer player;
    private List<Location> locations;
    private int buttons;

    private Shape(Location startLocation, int player, Orientation orientation) {
        this.player = player;
        this.locations = generateLocations(startLocation, orientation);
        this.buttons = 0;
    }

    void addButton(StudentStrategy strategy) {
        buttons++;

        if (player == strategy.getPlayer() && buttons == StudentStrategy.interestingPlayerShapeLength) {
            strategy.getRelevantShapes().add(this);
        } else if (player != strategy.getPlayer() && buttons == StudentStrategy.interestingOpponentShapeLength) {
            strategy.getRelevantShapes().add(this);
        }
    }

    void removeButton(StudentStrategy strategy) {
        buttons--;

        if (player == strategy.getPlayer() && buttons == StudentStrategy.interestingPlayerShapeLength - 1) {
            strategy.getRelevantShapes().remove(this);
        } else if (player != strategy.getPlayer() && buttons == StudentStrategy.interestingOpponentShapeLength - 1) {
            strategy.getRelevantShapes().remove(this);
        }
    }

    int getButtons() {
        return buttons;
    }

    List<Location> getLocations() {
        return this.locations;
    }

    public Integer getPlayer() {
        return player;
    }

    int getScore(StudentStrategy strategy) {
        switch (buttons) {
            case 3:
                if (this.player == strategy.getPlayer()) {
                    return 70;
                } else {
                    return -140;
                }

            case 4:
                if (this.player == strategy.getPlayer()) {
                    return 300;
                } else {
                    return -600;
                }

            case 5:
                if (this.player == strategy.getPlayer()) {
                    return 10000;
                } else {
                    return -20000;
                }

            default:
                return 0;
        }
    }

    private List<Location> generateLocations(Location startLocation, Orientation orientation) {
        List<Location> locations = new ArrayList<>();

        if (orientation == Orientation.Vertical) {
            for (int i = 0; i < 5; i++) {
                locations.add(Location.of(startLocation.getRow() + i, startLocation.getColumn()));
            }
        }

        if (orientation == Orientation.Horizontal) {
            for (int i = 0; i < 5; i++) {
                locations.add(Location.of(startLocation.getRow(), startLocation.getColumn() + i));
            }
        }

        if (orientation == Orientation.DiagonalLeftUp) {
            for (int i = 0; i < 5; i++) {
                locations.add(Location.of(startLocation.getRow() + i, startLocation.getColumn() + i));
            }
        }

        if (orientation == Orientation.DiagonalLeftDown) {
            for (int i = 0; i < 5; i++) {
                locations.add(Location.of(startLocation.getRow() - i, startLocation.getColumn() + i));
            }
        }

        return locations;
    }


    static HashMap<Integer, ArrayList<Shape>> generateShapes(SimpleBoard board) {
        HashMap<Integer, ArrayList<Shape>> shapes = new HashMap<>();

        shapes.put(1, new ArrayList<>());
        shapes.put(-1, new ArrayList<>());

        List<Integer> players = new ArrayList<>(Arrays.asList(1, -1));

        for (int player : players) {
            for (int row = 0; row < board.getHeight(); row++) {
                for (int col = 0; col <= board.getWidth() - 5; col++) {
                    shapes.get(player).add(new Shape(new Location(row, col), player, Orientation.Horizontal));
                }
            }

            for (int col = 0; col < board.getWidth(); col++) {
                for (int row = 0; row <= board.getHeight() - 5; row++) {
                    shapes.get(player).add(new Shape(new Location(row, col), player, Orientation.Vertical));
                }
            }

            for (int row = 0; row <= board.getHeight() - 5; row++) {
                for (int col = 0; col <= board.getWidth() - 5; col++) {
                    shapes.get(player).add(new Shape(new Location(row, col), player, Orientation.DiagonalLeftUp));
                }
            }


            for (int row = 4; row < board.getHeight(); row++) {
                for (int col = 0; col <= board.getWidth() - 5; col++) {
                    shapes.get(player).add(new Shape(new Location(row, col), player, Orientation.DiagonalLeftDown));
                }
            }
        }

        return shapes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return Objects.equals(locations, shape.locations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locations);
    }

    @Override
    public String toString() {
        return "Player: " + player + ", buttons: " + buttons + ", locations: " + locations;
    }
}
