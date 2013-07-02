﻿using System;
using System.Collections.Generic;
using System.Linq;

namespace ConnectFour.Game
{
    /// <summary>
    /// A Column of Pieces in the Container.
    /// 
    /// This behaves much like a Stack, except that we can examine
    /// all parts of the Column.  The downside of the implementation
    /// is that many of these operations will be O(n) instead of
    /// O(1).
    /// </summary>
    public class ContainerColumn : LinkedList<PieceColor>
    {
        /// <summary>
        /// The number of rows in the column.
        /// How many pieces can we cram into one column.
        /// </summary>
        public const int MaxHeight = 6;

        public bool IsFull
        {
            get { return Count == MaxHeight; }
        }

        public int AddPiece(PieceColor color)
        {
            if (IsFull)
            {
                throw new IllegalPlacementException("There is no more room in this row for another piece.");
            }
            AddLast(color);
            return Count - 1;
        }

        public PieceColor GetPiece(int row)
        {
            VerifyRow(row);
            if (row >= Count)
            {
                return PieceColor.None;
            }
            return this.ElementAt(row);
        }

        private void VerifyRow(int row)
        {
            if (row < 0 || row >= MaxHeight)
            {
                throw new ArgumentOutOfRangeException("row", row, "Must be between 0 and " + MaxHeight);
            }
        }
    }
}
