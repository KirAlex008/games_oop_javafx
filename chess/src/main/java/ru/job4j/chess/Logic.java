package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        try {
            int index1 = this.findBy(source);
            if (index1 != -1) {
                Cell[] steps = this.figures[index1].way(source, dest);
                freeWay(steps);
                if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                    rst = true;
                    this.figures[index1] = this.figures[index1].copy(dest);
                }
            }
        } catch (IllegalStateException e) {
            System.out.println(String.format("Way is not free, try again."));;
        }
        return rst;
    }

    public boolean freeWay(Cell[] steps) {
        boolean rst = true;
            for (int i = 0; i < steps.length; i++) {
                for (int j = 0; j < index; j++) {
                    if (steps[i].equals(figures[j].position())) {
                        throw new IllegalStateException();
                    }
                }
            }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    @Override
    public String toString() {
        return "Logic{" +
                "figures=" + Arrays.toString(this.figures) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logic logic = (Logic) o;
        return index == logic.index &&
                Arrays.equals(figures, logic.figures);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(index);
        result = 31 * result + Arrays.hashCode(figures);
        return result;
    }
}
