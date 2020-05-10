package ru.job4j.chess;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class LogicTest {

    //@Ignore
    @Test
    public void wayIsFree() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        boolean rsl = logic.move(Cell.C1, Cell.H6);
        assertThat(rsl, is(true));
    }
    @Test
    public void wayIsNotFree() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.add(new BishopBlack(Cell.D2));
        logic.add(new BishopBlack(Cell.H6));
        boolean rsl = logic.move(Cell.C1, Cell.H6);
        assertThat(rsl, is(false));
    }
    @Test
    public void wayIsNotFreeException() {
        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(mem));
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        logic.add(bishopBlack);
        Cell[] w = bishopBlack.way(Cell.C1, Cell.H6);
        logic.add(new BishopBlack(Cell.D2));
        logic.add(new BishopBlack(Cell.H6));
        boolean rsl = logic.move(Cell.C1, Cell.H6);
        assertThat(
                mem.toString(),
                Matchers.is(String.format("Way is not free, try again.%n"))
        );
        System.setOut(out);
    }
}