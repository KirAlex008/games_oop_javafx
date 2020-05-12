package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        try {
            isDiagonal(source, dest);
        } catch (IllegalStateException moe) {
            System.out.println(String.format("Could not way by diagonal from %s to %s.", source, dest));
        }
            int size = Math.abs(source.x - dest.x);
            Cell[] steps = new Cell[size];
            int deltaX = (dest.x - source.x) / size;
            int deltaY = (dest.y - source.y) / size;
            for (int index = 0; index < size; index++) {
                steps[index] = Cell.findBy(source.x + deltaX + index, source.y + deltaY + index);
        }
        return steps;

    }

    public boolean isDiagonal(Cell source, Cell dest) {
        boolean rsl = true;
        if (Math.abs(source.x - dest.x) != Math.abs(source.y - dest.y )) {
            throw new IllegalStateException();
        }
        return rsl;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
