package com.meancat.study.questions;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShortestPathTest {

    @Test
    public void getPath() {
        boolean[][] map = {
                //y=0      1      2
/* x=0 */       { true,  false, true},
/* x=1 */       { true,  false, true},
/* x=2 */       { true,   true, true},
        };
        ShortestPath sp = new ShortestPath(map);
        List<ShortestPath.Coord> path = sp.findShortestPath(0,0, 0,2);
        System.out.println(path);
        assertEquals("(0, 0)->(1, 0)->(2, 0)->(2, 1)->(2, 2)->(1, 2)->(0, 2)", pathToString(path));
    }


    @Test
    public void startIsEnd() {
        boolean[][] map = {
                //y=0      1      2
/* x=0 */       { true,  false, true},
/* x=1 */       { true,  false, true},
/* x=2 */       { true,   true, true},
        };
        ShortestPath sp = new ShortestPath(map);
        List<ShortestPath.Coord> path = sp.findShortestPath(0,0, 0,0);
        System.out.println(path);
        assertEquals("(0, 0)", pathToString(path));
    }


    @Test
    public void endIsNotReachable() {
        boolean[][] map = {
                //y=0      1      2
/* x=0 */       { true,  false, true},
/* x=1 */       { true,  false, true},
/* x=2 */       { true,  false, true},
        };
        ShortestPath sp = new ShortestPath(map);
        List<ShortestPath.Coord> path = sp.findShortestPath(0,0, 0,2);
        assertEquals(0, path.size());

    }

    private String pathToString(List<ShortestPath.Coord> path)
    {
        StringBuilder sb = new StringBuilder();
        Iterator<ShortestPath.Coord> iterator = path.iterator();
        while (iterator.hasNext()) {
            ShortestPath.Coord c = iterator.next();
            sb.append(c.toString());
            if (iterator.hasNext()) {
                sb.append("->");
            }
        }
        return sb.toString();
    }
}
