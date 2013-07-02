using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ConnectFour.GameApi
{
    /// <summary>
    /// A Column of Pieces in the Container.
    /// 
    /// This behaves much like a Stack, except that we can examine
    /// all parts of the Column.
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

        public void Push(PieceColor color)
        {
            if (IsFull)
            {
                throw new IllegalPlacementException("There is no more room in this row for another piece.");
            }
            AddLast(color);
        }

        public PieceColor GetPiece(uint row)
        {
            VerifyRow(row);
            if (row >= Count)
            {
                return PieceColor.None;
            }
            return this.ElementAt((int) row);
        }

        private void VerifyRow(uint row)
        {
            if (row >= MaxHeight)
            {
                throw new ArgumentOutOfRangeException("row", row, "Must be between 0 and " + MaxHeight);
            }
        }

    }
}
