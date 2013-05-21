package com.meancat.study.questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ShortestPath {
    private boolean[][] map;

    public ShortestPath(boolean[][] map) {
        this.map = map;
    }

    public List<Coord> findShortestPath(int fromX, int fromY, int toX, int toY) {
        Coord fromCoord = new Coord(fromX, fromY);
        Coord toCoord = new Coord(toX, toY);

        Queue<Coord> queue = new LinkedList<Coord>();
        boolean[][] visited = new boolean[map.length][map.length];
        Map<Coord, Coord> path = new HashMap<Coord, Coord>();

        queue.add(fromCoord);
        visited[fromCoord.x][fromCoord.y] = true;
        path.put(fromCoord, null);

        while(!queue.isEmpty()) {
            Coord c = queue.remove();
            for(Coord neighbor : c.getNeighbors()) {
                if (!map[neighbor.x][neighbor.y]) {
                    // this neighbor isn't passable, so we skip it.
                    continue;
                }
                if (!visited[neighbor.x][neighbor.y]) {
                    visited[neighbor.x][neighbor.y] = true;
                    queue.add(neighbor);
                    path.put(neighbor, c);
                }
            }
        }
        // given our map, walk backwards to the solution
        List<Coord> walkedPath = new ArrayList<Coord>();
        if (!visited[toCoord.x][toCoord.y]) {
            // failed to find a path!
            return walkedPath;
        }
        walkedPath.add(toCoord);
        Coord prev = path.get(toCoord);
        while (prev != null) {
            walkedPath.add(prev);
            prev = path.get(prev);
        }
        // reverse, since we walked backwards:
        java.util.Collections.reverse(walkedPath);
        return walkedPath;
    }

    public class Coord {
        final int x;
        final int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public List<Coord> getNeighbors() {
            List<Coord> result = new ArrayList<Coord>();
            // top
            if (y - 1 >= 0) {
                result.add(new Coord(x, y-1));
            }
            // left
            if (x -1 >= 0) {
                result.add(new Coord(x - 1, y));
            }
            // bottom
            if (y + 1 < map.length) {
                result.add(new Coord(x, y + 1));
            }
            // right
            if (x + 1 < map.length) {
                result.add(new Coord(x + 1, y));
            }
            return result;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        /*
            avoid any issues with the Map<Coord> by implementing equality & hashing ourselves.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coord coord = (Coord) o;

            if (x != coord.x) return false;
            if (y != coord.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
