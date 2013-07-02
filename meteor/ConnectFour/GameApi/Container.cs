using System;
using System.Collections.Generic;

namespace ConnectFour.GameApi
{
    /// <summary>
    /// The main game board of a ConnectFour game.
    /// 
    /// The Container has 7 columns, each are 6 spaces deep.
    /// 
    /// </summary>
    public class Container
    {
        private const int MaxColumnDepth = 6;

        private readonly Stack<PieceColor>[] columns = new Stack<PieceColor>[]
            {
                new Stack<PieceColor>(6), 
                new Stack<PieceColor>(6), 
                new Stack<PieceColor>(6), 
                new Stack<PieceColor>(6), 
                new Stack<PieceColor>(6), 
                new Stack<PieceColor>(6)
            };
 
        public void AddPiece(PieceColor color, int column)
        {
            if (column < 0 || column > columns.Length)
            {
                throw new ArgumentOutOfRangeException("column", column, "Must be between 0 and " + columns.Length);
            }
            if (IsColumnFull(column))
            {
                throw new IllegalPlacementException("Can't put a piece here, this column is full.");
            }

            Stack<PieceColor> col = columns[column];
            col.Push(color);
        }

        public bool IsColumnFull(int column)
        {
            if (column < 0 || column > columns.Length)
            {
                throw new ArgumentOutOfRangeException("column", column, "Must be between 0 and " + columns.Length);
            }
            return columns[column].Count == MaxColumnDepth;
        }
    }
}
