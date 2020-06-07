package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class BishopBlackTest {

    @Test
    public void position() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell rsl = bishopBlack.position();
        Cell expected = Cell.findBy(2,7);
        assertThat(rsl, is(expected));
    }
    @Test
    public void copy() {
        BishopBlack bishopBlack = new BishopBlack(Cell.F1);
        Figure newBishopBlack = bishopBlack.copy(Cell.D3);
        Cell rsl = newBishopBlack.position();
        Cell expected = Cell.findBy(3,2);
        assertThat(rsl, is(expected));
    }
    @Test
    public void way() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell[] rsl = bishopBlack.way(Cell.C1, Cell.H6);
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5, Cell.H6};
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenIsNotDiagonal() {
        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(mem));
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        bishopBlack.way(Cell.C1, Cell.G6);
        assertThat(
                mem.toString(),
                is(String.format("Way is not free, try again.%n"))
        );
        System.setOut(out);
    }
}