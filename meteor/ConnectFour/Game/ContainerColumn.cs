using System;
using System.Collections.Generic;
using System.Linq;

namespace ConnectFour.Game
{
    /// <summary>
    /// A Column of Pieces in the Container.
    /// </summary>
    public class ContainerColumn : List<PieceColor>
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
            // this adds to the end of the list, keeping our
            // structure where smaller numbers == the bottom of the 
            // column.
            Add(color);
            return Count - 1;
        }

        public PieceColor GetPiece(int row)
        {
            VerifyRow(row);
            if (row >= Count)
            {
                return PieceColor.None;
            }
            // for things of IList this is O(1)
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
